package com.noteset.api.domain.service;

import java.util.List;

import com.noteset.api.domain.model.NoteEntity;
import org.springframework.stereotype.Service;

import com.noteset.api.domain.model.FolderEntity;

@Service
public interface FolderDomainService {
	
	public FolderEntity createFolder(FolderEntity folder);
	
	public List<FolderEntity> getFolders();
	
	public FolderEntity getFolderById(Long id);
	
	public FolderEntity getFolderByName(String name);
	
	public void deleteFolder(Long id);

	//move to abstract service
	public FolderEntity updateFolder(FolderEntity folder);

	public List<NoteEntity> getNotes(Long folderId);

	public NoteEntity addNoteToFolder(Long folderId, String guid);

	public NoteEntity removeNoteFromFolder(String guid);
}
