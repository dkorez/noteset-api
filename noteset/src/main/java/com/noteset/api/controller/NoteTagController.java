package com.noteset.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noteset.api.domain.model.TagEntity;
import com.noteset.api.domain.service.TagDomainService;
import com.noteset.api.model.NoteTagDto;
import com.noteset.api.util.DataMapper;

@RestController
@RequestMapping("/api/tags")
public class NoteTagController {
	
	@Autowired
	private TagDomainService noteTagService;
	
	@Autowired
	private DataMapper dataMapper;

	@GetMapping("/")
	public ResponseEntity<?> getAllNoteTags() {
		List<TagEntity> tags = noteTagService.getAllNoteTags();
		
		List<NoteTagDto> tagList = tags.stream().map(t -> dataMapper.mapTagEntityToTagDto(t)).collect(Collectors.toList());
		
		return new ResponseEntity<List<NoteTagDto>>(tagList, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createNoteTag(@RequestBody NoteTagDto note) {
		TagEntity newTag = noteTagService.createNoteTag(note.getTitle());
		
		return new ResponseEntity<NoteTagDto>(dataMapper.mapTagEntityToTagDto(newTag), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNoteTag(@PathVariable Long id) {
		noteTagService.removeNoteTag(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
