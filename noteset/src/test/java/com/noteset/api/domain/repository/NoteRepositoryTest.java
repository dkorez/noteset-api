package com.noteset.api.domain.repository;

import com.noteset.api.domain.model.NoteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NoteRepositoryTest {
    
    @Autowired
    private NoteRepository noteRepository;

    @Test
    void shouldFindNoteByGuid() {
        String guid = UUID.randomUUID().toString();
        
        NoteEntity newNote = new NoteEntity();
        newNote.setGuid(guid);
        noteRepository.save(newNote);

        Optional<NoteEntity> savedNote = noteRepository.findByGuid(guid);

        assertThat(savedNote).isPresent();
    }

    @Test
    void shouldFailToFindNoteByGuidWithInvalidGUID() {
        String guid = "TESTAAA";

        Optional<NoteEntity> savedNote = noteRepository.findByGuid(guid);

        assertThat(savedNote).isNotPresent();
    }
}