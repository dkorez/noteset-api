package com.noteset.api.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.model.TagEntity;
import com.noteset.api.domain.repository.NoteRepository;
import com.noteset.api.exception.NotFoundException;

@Service
public class NoteDomainServiceImpl implements NoteDomainService {
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private TagDomainService noteTagService;

	@Override
	public NoteEntity createNote(NoteEntity noteEntity) {
		UUID guid = UUID.randomUUID();

		noteEntity.setGuid(guid.toString());
		noteEntity.setCreated(new Date());
		noteEntity.setUpdated(new Date());
		
		return noteRepository.save(noteEntity);
	}

	@Override
	public NoteEntity updateNote(NoteEntity note) {
		note.setUpdated(new Date());
		return noteRepository.save(note);
	}

	@Override
	public void deleteNote(String guid) {
		NoteEntity entity = findByGuid(guid);
		noteRepository.deleteById(entity.getId());
	}

	@Override
	public List<NoteEntity> getNoteList() {
		return noteRepository.findAll();
	}

	@Override
	public NoteEntity findById(Long id) {
		Optional<NoteEntity> note = noteRepository.findById(id);
		if (!note.isPresent()) {
			throw new NotFoundException("Note with ID " + String.valueOf(id) + " was not found");
		}
		
		return note.get();
	}

	@Override
	public NoteEntity findByGuid(String guid) {
		Optional<NoteEntity> note = noteRepository.findByGuid(guid);
		if (note.isEmpty()) {
			throw new NotFoundException("Note with GUID " + guid + " not found");
		}

		return note.get();
	}

	@Override
	public NoteEntity addNoteTag(String guid, Long tagId) {
		TagEntity tag = noteTagService.getById(tagId);
		NoteEntity note = findByGuid(guid);
		
		note.addNoteTag(tag);
		return noteRepository.save(note);
	}

	@Override
	public NoteEntity removeNoteTag(String guid, Long tagId) {
		TagEntity tag = noteTagService.getById(tagId);
		NoteEntity note = findByGuid(guid);
		
		note.removeNoteTag(tag);
		return noteRepository.save(note);
	}
}
