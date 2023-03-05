package com.noteset.api.bl.service;

import com.noteset.api.domain.model.FolderEntity;
import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.service.FolderDomainService;
import com.noteset.api.domain.service.NoteDomainService;
import com.noteset.api.exception.NotFoundException;
import com.noteset.api.model.CreateNoteRequest;
import com.noteset.api.model.NoteDetailsDto;
import com.noteset.api.model.NoteDto;
import com.noteset.api.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteBlServiceImpl implements NoteBlService {

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private NoteDomainService noteDomainService;
    
    @Autowired
    private FolderDomainService folderDomainService;

    public NoteDto createNote(CreateNoteRequest noteReq) {
    	NoteEntity entity = new NoteEntity();
    	entity.setSubject(noteReq.getSubject());
    	entity.setContent(noteReq.getContent());
    	entity.setNoteType(noteReq.getNoteType());
    	
    	FolderEntity folderEntity = folderDomainService.getFolderById(noteReq.getFolderId());
		entity.setFolder(folderEntity);
    	
        NoteEntity newNote = noteDomainService.createNote(entity);

        return dataMapper.mapNoteEntityToNoteDto(newNote);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<NoteEntity> noteEntityList = noteDomainService.getNoteList();
        List<NoteDto> noteList = noteEntityList.stream()
                .map(note -> dataMapper.mapNoteEntityToNoteDto(note))
                .collect(Collectors.toList());

        return noteList;
    }

    @Override
    public NoteDetailsDto getNoteByGuid(String guid) {
        NoteEntity note = noteDomainService.findByGuid(guid);

        return dataMapper.mapNoteEntityToNoteDetailsDto(note);
    }

    @Override
    public NoteDto updateNote(NoteDto note) {
    	if (note.getGuid() == null || note.getGuid().isEmpty()) {
    		throw new NotFoundException("Cannot update note without GUID");
    	}
    	
        NoteEntity entity = noteDomainService.findByGuid(note.getGuid());

        entity.setSubject(note.getSubject());
       	entity.setContent(note.getContent());

        if (note.getNoteType() != null) {
            entity.setNoteType(note.getNoteType());
        }

        NoteEntity updatedNote = noteDomainService.updateNote(entity);

        return dataMapper.mapNoteEntityToNoteDto(updatedNote);
    }
    
    @Override
    public NoteDto patchNote(String guid, NoteDto note) {
        NoteEntity entity = noteDomainService.findByGuid(guid);

        if (note.getSubject() != null && !note.getSubject().isEmpty()) {
        	entity.setSubject(note.getSubject());
        }
        if (note.getContent() != null && !note.getContent().isEmpty()) {
        	entity.setContent(note.getContent());
        }
        if (note.getNoteType() != null) {
            entity.setNoteType(note.getNoteType());
        }

        NoteEntity updatedNote = noteDomainService.updateNote(entity);

        return dataMapper.mapNoteEntityToNoteDto(updatedNote);
    }

    @Override
    public void deleteNote(String guid) {
        noteDomainService.deleteNote(guid);
    }

    @Override
    public NoteDto addNoteTag(String guid, Long tagId) {
        NoteEntity note = noteDomainService.addNoteTag(guid, tagId);

        return dataMapper.mapNoteEntityToNoteDto(note);
    }

    @Override
    public NoteDto removeNoteTag(String guid, Long tagId) {
        NoteEntity note = noteDomainService.removeNoteTag(guid, tagId);

        return dataMapper.mapNoteEntityToNoteDto(note);
    }


}
