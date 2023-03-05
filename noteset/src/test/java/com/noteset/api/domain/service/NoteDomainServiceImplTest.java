package com.noteset.api.domain.service;

import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.repository.NoteRepository;
import com.noteset.api.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class NoteDomainServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteDomainServiceImpl noteService;

    @Captor
    private ArgumentCaptor<NoteEntity> noteCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should set GUID, Dates and save note")
    void shouldSaveNote() {
        // check if note was saved
        // also check if it sets GUID and date

        NoteEntity noteMock = new NoteEntity();
        noteMock.setSubject("subject");

        noteService.createNote(noteMock);

        then(noteRepository).should().save(noteCaptor.capture());
        NoteEntity capturedNote = noteCaptor.getValue();
        Assertions.assertThat(capturedNote).isEqualTo(noteMock);

        assertThat(capturedNote.getGuid()).isNotBlank();
        assertThat(capturedNote.getCreated()).isNotNull();
        assertThat(capturedNote.getUpdated()).isNotNull();

        assertThat(capturedNote.getSubject()).isEqualTo(noteMock.getSubject());
    }

    @Test
    @DisplayName("findById should throw exception when passing invalid ID")
    void shouldFailWithInvalidId() {
        Long fakeId = 0l;

        assertThatThrownBy(() -> noteService.findById(fakeId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Note with ID " + String.valueOf(fakeId) + " was not found");
    }

    @Test
    @DisplayName("findById should return entity when passing correct ID")
    void shouldPassWithValidId() {
        NoteEntity noteMock = new NoteEntity();
        noteMock.setSubject("subject");
        noteService.createNote(noteMock);

        then(noteRepository).should().save(noteCaptor.capture());
        NoteEntity capturedNote = noteCaptor.getValue();
        Long testId = capturedNote.getId();

        given(noteRepository.findById(testId)).willReturn(Optional.of(capturedNote));

        NoteEntity foundEntity = noteService.findById(testId);
        assertThat(foundEntity).isNotNull();
        assertThat(foundEntity.getSubject()).isEqualTo(noteMock.getSubject());
    }

    @Test
    @DisplayName("findByGuid should throw exception when passing invalid GUID")
    void shouldFailWithFakeGuid() {
        //test if it throws exception when not found
        String fakeGuid = "FAKE";

        assertThatThrownBy(() -> noteService.findByGuid(fakeGuid))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Note with GUID " + fakeGuid + " not found");
    }

    @Test
    @DisplayName("findByGuid should return entity when passing correct GUID")
    void shouldReturnEntityWithCorrectGuid() {
        //test if it throws exception when not found
        NoteEntity noteMock = new NoteEntity();
        noteMock.setSubject("subject");
        noteService.createNote(noteMock);

        then(noteRepository).should().save(noteCaptor.capture());
        NoteEntity capturedNote = noteCaptor.getValue();
        String testGuid = capturedNote.getGuid();

        given(noteRepository.findByGuid(testGuid)).willReturn(Optional.of(capturedNote));

        NoteEntity foundEntity = noteService.findByGuid(testGuid);
        assertThat(foundEntity).isNotNull();
        assertThat(foundEntity.getSubject()).isEqualTo(noteMock.getSubject());
    }

    @Test
    void addNoteTag() {
    }

    @Test
    void removeNoteTag() {
    }


}