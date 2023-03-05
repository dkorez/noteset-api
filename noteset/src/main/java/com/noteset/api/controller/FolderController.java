package com.noteset.api.controller;

import java.util.List;

import com.noteset.api.bl.service.FolderBlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noteset.api.model.FolderDto;
import com.noteset.api.model.NoteDto;

@RestController
@RequestMapping("/api/folders")
public class FolderController {
	
	@Autowired
	private FolderBlService folderService;

	@PostMapping("/")
	public ResponseEntity<?> createFolder(@RequestBody FolderDto folder) {
		FolderDto newFolder = folderService.createFolder(folder);
		
		return new ResponseEntity<FolderDto>(newFolder, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllFolders() {
		List<FolderDto> folderList = folderService.getFolders();
		
		return new ResponseEntity<List<FolderDto>>(folderList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFolderById(@PathVariable Long id) {
		FolderDto folder = folderService.getFolderById(id);
		
		return new ResponseEntity<FolderDto>(folder, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateFolder(@PathVariable Long id, @RequestBody FolderDto folder) {
		FolderDto updatedFolder = folderService.updateFolder(id, folder);
		
		return new ResponseEntity<FolderDto>(updatedFolder, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFolder(@PathVariable Long id) {
		folderService.deleteFolder(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}/notes")
	public ResponseEntity<?> getFolderNotest(@PathVariable Long id) {
		List<NoteDto> noteList = folderService.getNotes(id);
		
		return new ResponseEntity<List<NoteDto>>(noteList, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/notes")
	public ResponseEntity<?> createNotesInFolder(@PathVariable Long id, @RequestBody NoteDto note) {
		NoteDto newNote = folderService.createNoteInFolder(id, note);
		
		return new ResponseEntity<NoteDto>(newNote, HttpStatus.CREATED);
	}
}
