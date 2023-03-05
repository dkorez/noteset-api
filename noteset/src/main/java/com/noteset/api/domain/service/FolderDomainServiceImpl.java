package com.noteset.api.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noteset.api.domain.model.FolderEntity;
import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.repository.FolderRepository;
import com.noteset.api.exception.NotFoundException;

@Service
public class FolderDomainServiceImpl implements FolderDomainService {
	
	@Autowired
	private FolderRepository folderRepository;
	
	@Autowired
	private NoteDomainService noteRepositoryService;

	
	@Override
	public FolderEntity createFolder(FolderEntity entity) {
		return folderRepository.save(entity);
	}
	
	@Override
	public List<FolderEntity> getFolders() {
		return folderRepository.findAll();
	}

	@Override
	public FolderEntity getFolderById(Long id) {
		Optional<FolderEntity> entity = folderRepository.findById(id);

		if (entity.isEmpty()) {
			throw new NotFoundException("Folder with id " + String.valueOf(id) + " not found");
		}

		return entity.get();
	}

	@Override
	public FolderEntity getFolderByName(String name) {
		Optional<FolderEntity> entity = folderRepository.findByName(name);

		if (entity.isEmpty()) {
			throw new NotFoundException("Folder with name " + name + " not found");
		}

		return entity.get();
	}

	@Override
	public void deleteFolder(Long id) {
		folderRepository.deleteById(id);
	}

	@Override
	public FolderEntity updateFolder(FolderEntity entity) {
		return folderRepository.save(entity);
	}
	
	@Override
	public List<NoteEntity> getNotes(Long folderId) {
		FolderEntity entity = getFolderById(folderId);

		return entity.getNotes().stream().collect(Collectors.toList());
	}
	
	@Override
	public NoteEntity addNoteToFolder(Long folderId, String guid) {
		FolderEntity folderEntity = getFolderById(folderId);

		NoteEntity noteEntity = noteRepositoryService.findByGuid(guid);
		noteEntity.setFolder(folderEntity);

		return noteRepositoryService.updateNote(noteEntity);
	}

	@Override
	public NoteEntity removeNoteFromFolder(String guid) {
		NoteEntity noteEntity = noteRepositoryService.findByGuid(guid);
		noteEntity.setFolder(null);

		return noteRepositoryService.updateNote(noteEntity);
	}
}
