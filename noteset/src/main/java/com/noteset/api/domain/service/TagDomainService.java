package com.noteset.api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noteset.api.domain.model.TagEntity;

@Service
public interface TagDomainService {
	
	public TagEntity createNoteTag(String title);
	
	public void removeNoteTag(Long id);
	
	public List<TagEntity> getAllNoteTags();
	
	public TagEntity getById(Long id);
}
