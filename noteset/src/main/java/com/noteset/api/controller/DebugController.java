package com.noteset.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noteset.api.bl.service.FolderBlService;
import com.noteset.api.model.FolderDto;
import com.noteset.api.model.NoteDto;

@RestController
@RequestMapping("/debug")
public class DebugController {
	@Autowired
	private FolderBlService folderService;

	@PutMapping("dummy-data")
	public ResponseEntity<?> fillDummyData() {
		
		FolderDto folder1 = new FolderDto();
		folder1.setName("Default");
		folder1 = folderService.createFolder(folder1);
		
		NoteDto note1 = new NoteDto();
		note1.setSubject("Dummy note");
		note1.setContent("Some dummy content");
		note1 = folderService.createNoteInFolder(folder1.getId(), note1);
		
		
		NoteDto note2 = new NoteDto();
		note2.setSubject("Testing");
		note2.setContent("Just testing some notes....");
		note2 = folderService.createNoteInFolder(folder1.getId(), note2);
		
		FolderDto folder2 = new FolderDto();
		folder2.setName("Deyan");
		folder2 = folderService.createFolder(folder2);
		
		NoteDto note3 = new NoteDto();
		note3.setSubject("Private note");
		note3.setContent("This note belongs to another folder");
		note3 = folderService.createNoteInFolder(folder2.getId(), note3);
		
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
