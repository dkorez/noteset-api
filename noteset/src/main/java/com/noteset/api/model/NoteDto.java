package com.noteset.api.model;

import com.noteset.api.domain.NoteType;

public class NoteDto {
	private String guid;
	private String subject;
	private String content;
	private NoteType noteType;
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

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
	
}
