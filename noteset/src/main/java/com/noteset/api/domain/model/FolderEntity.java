package com.noteset.api.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@Table(name = "FOLDER", schema = "NOTESET")
@Table(name = "FOLDER")
public class FolderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "PARENT")
	private Long parentFolder;
	
	@OneToMany(mappedBy = "folder")
	private Set<NoteEntity> notes = new HashSet<>();
	
	public FolderEntity() {
		super();
	}

	public FolderEntity(Long id, String name, Long parentFolder, Set<NoteEntity> notes) {
		super();
		this.id = id;
		this.name = name;
		this.parentFolder = parentFolder;
		this.notes = notes;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Long parentFolder) {
		this.parentFolder = parentFolder;
	}

	public Set<NoteEntity> getNotes() {
		return notes;
	}

	public void setNotes(Set<NoteEntity> notes) {
		this.notes = notes;
	}
	
	public void addNote(NoteEntity note) {
		this.notes.add(note);
	}
}
