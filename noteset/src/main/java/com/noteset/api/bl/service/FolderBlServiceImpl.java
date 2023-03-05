package com.noteset.api.bl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noteset.api.domain.model.FolderEntity;
import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.service.FolderDomainService;
import com.noteset.api.domain.service.NoteDomainService;
import com.noteset.api.model.FolderDto;
import com.noteset.api.model.NoteDto;
import com.noteset.api.util.DataMapper;

@Service
public class FolderBlServiceImpl implements FolderBlService {
	@Autowired
    private DataMapper dataMapper;
	
	@Autowired
	private FolderDomainService folderDomainService;
	
	@Autowired
    private NoteDomainService noteDomainService;
	
	@Override
	public FolderDto createFolder(FolderDto folder) {
		FolderEntity entity = dataMapper.mapFolderDtoToFolderEntity(folder);
		FolderEntity newFolder = folderDomainService.createFolder(entity);

        return dataMapper.mapFolderEntityToFolderDto(newFolder);
	}

	@Override
	public List<FolderDto> getFolders() {
		List<FolderEntity> folderEntityList = folderDomainService.getFolders();
        List<FolderDto> folderList = folderEntityList.stream()
                .map(folder -> dataMapper.mapFolderEntityToFolderDto(folder))
                .collect(Collectors.toList());

        return folderList;
	}

	@Override
	public FolderDto getFolderById(Long id) {
		FolderEntity folder = folderDomainService.getFolderById(id);
		
		return dataMapper.mapFolderEntityToFolderDto(folder);
	}
	
	@Override
	public FolderDto getFolderByName(String name) {
		FolderEntity folder = folderDomainService.getFolderByName(name);
		
		return dataMapper.mapFolderEntityToFolderDto(folder);
	}
	
	@Override
	public void deleteFolder(Long id) {
		folderDomainService.deleteFolder(id);
	}
	
	@Override
	public FolderDto updateFolder(Long id, FolderDto folder) {
		FolderEntity entity = folderDomainService.getFolderById(id);

        entity.setName(folder.getName());

        FolderEntity updatedFolder = folderDomainService.updateFolder(entity);

        return dataMapper.mapFolderEntityToFolderDto(updatedFolder);
	}
	
	@Override
	public List<NoteDto> getNotes(Long id) {
		List<NoteEntity> noteEntityList = folderDomainService.getNotes(id);
		List<NoteDto> noteList = noteEntityList.stream()
				.map(note -> dataMapper.mapNoteEntityToNoteDto(note))
				.collect(Collectors.toList());
		
		return noteList;
	}
	
	@Override
	public NoteDto createNoteInFolder(Long id, NoteDto note) {
		NoteEntity noteEntity = dataMapper.mapNoteDtoToNoteEntity(note);
		FolderEntity folder = folderDomainService.getFolderById(id);
		noteEntity.setFolder(folder);
		
		NoteEntity newNote = noteDomainService.createNote(noteEntity);
		return dataMapper.mapNoteEntityToNoteDto(newNote);
	}
}
