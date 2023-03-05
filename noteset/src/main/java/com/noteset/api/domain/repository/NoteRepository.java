package com.noteset.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteset.api.domain.model.NoteEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

	public Optional<NoteEntity> findByGuid(String guid);
}
