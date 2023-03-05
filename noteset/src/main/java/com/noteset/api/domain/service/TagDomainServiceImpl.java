package com.noteset.api.domain.service;

import com.noteset.api.domain.model.TagEntity;
import com.noteset.api.domain.repository.NoteTagRepository;
import com.noteset.api.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagDomainServiceImpl implements TagDomainService {

	@Autowired
	private NoteTagRepository noteTagRepository;
	
	@Override
	public TagEntity createNoteTag(String title) {
		TagEntity entity = new TagEntity();
		entity.setTitle(title);
		return noteTagRepository.save(entity);
	}

	@Override
	public void removeNoteTag(Long id) {
		noteTagRepository.deleteById(id);
	}

	@Override
	public List<TagEntity> getAllNoteTags() {
		return noteTagRepository.findAll();
	}

	@Override
	public TagEntity getById(Long id) {
		Optional<TagEntity> tag = noteTagRepository.findById(id);
		if (tag.isEmpty()) {
			throw new NotFoundException("Tag with id " + String.valueOf(id) + " not found");
		}
		
		return tag.get();
	}	
}
