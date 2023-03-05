package com.noteset.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteset.api.domain.model.FolderEntity;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
	public Optional<FolderEntity> findByName(String name);
}
