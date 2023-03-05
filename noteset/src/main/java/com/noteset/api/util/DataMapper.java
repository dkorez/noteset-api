package com.noteset.api.util;

import org.springframework.stereotype.Component;

import com.noteset.api.domain.model.FolderEntity;
import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.model.TagEntity;
import com.noteset.api.model.FolderDto;
import com.noteset.api.model.NoteDetailsDto;
import com.noteset.api.model.NoteDto;
import com.noteset.api.model.NoteTagDto;

@Component
public class DataMapper {
	
	public NoteDetailsDto mapNoteEntityToNoteDetailsDto(NoteEntity entity) {
		NoteDetailsDto note = new NoteDetailsDto();
		note.setGuid(entity.getGuid());
		if (entity.getContent() != null) {
			note.setContent(entity.getContent().toString());
		}
		note.setSubject(entity.getSubject());
		note.setNoteType(entity.getNoteType());
		note.setFolder(mapFolderEntityToFolderDto(entity.getFolder()));
		
		//TODO: add tags
		
		return note;
	}

	public NoteDto mapNoteEntityToNoteDto(NoteEntity entity) {
		NoteDto note = new NoteDto();
		note.setGuid(entity.getGuid());
		if (entity.getContent() != null) {
			note.setContent(entity.getContent().toString());
		}
		note.setSubject(entity.getSubject());
		note.setNoteType(entity.getNoteType());
		
		return note;
	}
	
	public NoteEntity mapNoteDtoToNoteEntity(NoteDto note) {
		NoteEntity entity = new NoteEntity();
		entity.setGuid(note.getGuid());
		entity.setContent(note.getContent());
		entity.setSubject(note.getSubject());
		entity.setNoteType(note.getNoteType());
		
		return entity;
	}
	
	public FolderDto mapFolderEntityToFolderDto(FolderEntity entity) {
		FolderDto folder = new FolderDto();
		folder.setId(entity.getId());
		folder.setName(entity.getName());
		
		return folder;
	}
	
	public FolderEntity mapFolderDtoToFolderEntity(FolderDto folder) {
		FolderEntity entity = new FolderEntity();
		entity.setId(folder.getId());
		entity.setName(folder.getName());
		
		return entity;
	}
	
	public NoteTagDto mapTagEntityToTagDto(TagEntity entity) {
		NoteTagDto tag = new NoteTagDto();
		tag.setId(entity.getId());
		tag.setTitle(entity.getTitle());
		
		return tag;
	}
	
	public TagEntity mapTagDtoToTagEntity(NoteTagDto tag) {
		TagEntity entity = new TagEntity();
		entity.setId(tag.getId());
		entity.setTitle(tag.getTitle());
		
		return entity;
	}
}
