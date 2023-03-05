package com.noteset.api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noteset.api.domain.model.NoteEntity;

@Service
public interface NoteDomainService {
	public NoteEntity createNote(NoteEntity note);

	public NoteEntity updateNote(NoteEntity note);
	
	public void deleteNote(String guid);
	
	public List<NoteEntity> getNoteList();
	
	public NoteEntity findById(Long id);
	
	public NoteEntity findByGuid(String guid);

	public NoteEntity addNoteTag(String guid, Long tagId);
	
	public NoteEntity removeNoteTag(String guid, Long tagId);
}
