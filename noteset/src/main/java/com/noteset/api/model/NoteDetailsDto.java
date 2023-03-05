package com.noteset.api.model;

import java.util.ArrayList;
import java.util.List;

public class NoteDetailsDto extends NoteDto {
	private FolderDto folder;
	private List<NoteTagDto> tags = new ArrayList<>();
	
	public FolderDto getFolder() {
		return folder;
	}
	
	public void setFolder(FolderDto folder) {
		this.folder = folder;
	}
	
	public List<NoteTagDto> getTags() {
		return tags;
	}
	
	public void setTags(List<NoteTagDto> tags) {
		this.tags = tags;
	}
}
