package com.noteset.api.controller;

import java.util.List;

import com.noteset.api.bl.service.NoteBlService;
import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noteset.api.model.CreateNoteRequest;
import com.noteset.api.model.NoteDetailsDto;
import com.noteset.api.model.NoteDto;
import com.noteset.api.domain.service.FolderDomainService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
	private NoteBlService noteService;
	
	@Autowired
	private FolderDomainService folderService;

	@Autowired
	private DataMapper dataMapper;
	
	@PostMapping("/")
	public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest noteReq) {
		NoteDto newNote = noteService.createNote(noteReq);
		
		return new ResponseEntity<NoteDto>(newNote, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllNotes() {
		List<NoteDto> notes = noteService.getAllNotes();

		return new ResponseEntity<List<NoteDto>>(notes, HttpStatus.OK);
	}
	
	@GetMapping("/{guid}")
	public ResponseEntity<?> findByGuid(@PathVariable String guid) {
		NoteDetailsDto note = noteService.getNoteByGuid(guid);
		
		return new ResponseEntity<NoteDetailsDto>(note, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateNote(@RequestBody NoteDto note) {
		NoteDto updatedNote = noteService.updateNote(note);
		
		return new ResponseEntity<NoteDto>(updatedNote, HttpStatus.OK);
	}
	
	@PatchMapping("/{guid}")
	public ResponseEntity<?> pathcNote(@PathVariable String guid, @RequestBody NoteDto note) {
		NoteDto updatedNote = noteService.patchNote(guid, note);
		
		return new ResponseEntity<NoteDto>(updatedNote, HttpStatus.OK);
	}
	
	@DeleteMapping("/{guid}")
	public ResponseEntity<?> deleteNote(@PathVariable String guid) {
		noteService.deleteNote(guid);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/{guid}/folder/{folderId}")
	public ResponseEntity<?> addNoteToFolder(@PathVariable String guid, @PathVariable Long folderId) {
		NoteEntity updatedNote = folderService.addNoteToFolder(folderId, guid);

		return new ResponseEntity<NoteDto>(dataMapper.mapNoteEntityToNoteDto(updatedNote), HttpStatus.OK);
	}

	@DeleteMapping("/{guid}/folder")
	public ResponseEntity<?> removeNoteFromFolder(String guid) {
		folderService.removeNoteFromFolder(guid);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	/* end folder move */
	
	@PutMapping("/{guid}/tags/{tagId}")
	public ResponseEntity<?> addNoteTag(@PathVariable String guid, @PathVariable Long tagId) {
		NoteDto note = noteService.addNoteTag(guid, tagId);
		
		return new ResponseEntity<NoteDto>(note, HttpStatus.OK);
	}
	
	@DeleteMapping("/{guid}/tags/{tagId}")
	public ResponseEntity<?> removeNoteTag(@PathVariable String guid, @PathVariable Long tagId) {
		NoteDto note = noteService.removeNoteTag(guid, tagId);
		
		return new ResponseEntity<NoteDto>(note, HttpStatus.OK);
	}
}
