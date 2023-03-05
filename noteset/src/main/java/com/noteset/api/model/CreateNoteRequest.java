package com.noteset.api.model;

import com.noteset.api.domain.NoteType;

public class CreateNoteRequest {
	private String subject;
	private String content;
	private NoteType noteType;
	private Long folderId;
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public NoteType getNoteType() {
		return noteType;
	}
	
	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}
	
	public Long getFolderId() {
		return folderId;
	}
	
	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}
}
