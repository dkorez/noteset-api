package com.noteset.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteset.api.domain.model.TagEntity;

@Repository
public interface NoteTagRepository extends JpaRepository<TagEntity, Long> {

}
