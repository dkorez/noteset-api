package com.noteset.api.bl.service;

import java.util.List;

import com.noteset.api.model.FolderDto;
import com.noteset.api.model.NoteDto;

public interface FolderBlService {
	public FolderDto createFolder(FolderDto folder);
	
	public List<FolderDto> getFolders();
	
	public FolderDto getFolderById(Long id);
	
	public FolderDto getFolderByName(String name);
	
	public void deleteFolder(Long id);

	public FolderDto updateFolder(Long id, FolderDto folder);

	public List<NoteDto> getNotes(Long id);
	
	public NoteDto createNoteInFolder(Long id, NoteDto note);
}
