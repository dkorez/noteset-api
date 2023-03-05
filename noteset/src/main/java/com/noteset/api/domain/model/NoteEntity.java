package com.noteset.api.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.noteset.api.domain.NoteType;

@Entity
//@Table(name = "NOTE", schema = "NOTESET")
@Table(name = "NOTE")
public class NoteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "GUID", nullable = false, unique = true, updatable = false)
	private String guid;

	@Column(name = "SUBJECT")
	private String subject;
	
	@Lob
	@Column(name = "CONTENT")
	private String content;
	
	@Enumerated(EnumType.STRING)
	private NoteType noteType;
	
	//dates
	//one-to-one -> task/reminder
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED")
	private Date created;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED")
	private Date updated;
	
	@ManyToMany()
	@JoinTable(name = "NOTE_TAG", joinColumns = @JoinColumn(name = "NOTE_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
	private Set<TagEntity> noteTags = new HashSet<>();

	//TODO:
	//tags
	//attachments / resources
	
	@ManyToOne()
	@JoinColumn(name = "FOLDER_ID", referencedColumnName = "ID")
	private FolderEntity folder;
	
	public NoteEntity() {
		super();
	}

	public NoteEntity(Long id, String guid, String subject, String content, NoteType noteType, Date created, Date updated, Set<TagEntity> noteTags, FolderEntity folder) {
		super();
		this.id = id;
		this.guid = guid;
		this.subject = subject;
		this.content = content;
		this.noteType = noteType;
		this.created = created;
		this.updated = updated;
		this.noteTags = noteTags;
		this.folder = folder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public FolderEntity getFolder() {
		return folder;
	}

	public void setFolder(FolderEntity folder) {
		this.folder = folder;
	}
	
	public void addNoteTag(TagEntity tag) {
		this.noteTags.add(tag);
	}
	
	public void removeNoteTag(TagEntity tag) {
		this.noteTags.remove(tag);
	}
	
	//TODO: override toString + equals
}
