package com.noteset.api.util;

import com.noteset.api.domain.model.FolderEntity;
import com.noteset.api.domain.model.NoteEntity;
import com.noteset.api.domain.model.TagEntity;
import com.noteset.api.model.FolderDto;
import com.noteset.api.model.NoteDto;
import com.noteset.api.model.NoteTagDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DataMapperTest {

    @InjectMocks
    private  DataMapper dataMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return note dto with same params as entity when content is not set")
    void shouldMapNoteEntityToNoteDtoWithoutContent() {
        NoteEntity entity = new NoteEntity();
        entity.setSubject("subject");
        entity.setGuid("GUID");

        NoteDto note = dataMapper.mapNoteEntityToNoteDto(entity);

        assertThat(note.getSubject()).isEqualTo(entity.getSubject());
        assertThat(note.getGuid()).isEqualTo(entity.getGuid());
    }

    @Test
    @DisplayName("should return note dto with same params as entity when content is set")
    void shouldMapNoteEntityToNoteDtoContent() {
        NoteEntity entity = new NoteEntity();
        entity.setSubject("subject");
        entity.setGuid("GUID");
        entity.setContent("test content");

        NoteDto note = dataMapper.mapNoteEntityToNoteDto(entity);

        assertThat(note.getSubject()).isEqualTo(entity.getSubject());
        assertThat(note.getGuid()).isEqualTo(entity.getGuid());
        assertThat(note.getContent()).isNotBlank();
    }

    @Test
    @DisplayName("should return note entity with same params as dto when content is not set")
    void shouldMapNoteDtoToNoteEntityWithoutContent() {
        NoteDto note = new NoteDto();
        note.setSubject("subject");
        note.setGuid("GUID");

        NoteEntity entity = dataMapper.mapNoteDtoToNoteEntity(note);

        assertThat(note.getSubject()).isEqualTo(entity.getSubject());
        assertThat(note.getGuid()).isEqualTo(entity.getGuid());
    }

    @Test
    @DisplayName("should return note entity with same params as dto when content is set")
    void shouldMapNoteDtoToNoteEntityWithContent() {
        NoteDto note = new NoteDto();
        note.setSubject("subject");
        note.setGuid("GUID");
        note.setContent("some content");

        NoteEntity entity = dataMapper.mapNoteDtoToNoteEntity(note);

        assertThat(note.getSubject()).isEqualTo(entity.getSubject());
        assertThat(note.getGuid()).isEqualTo(entity.getGuid());
        assertThat(note.getContent()).isNotBlank();
    }

    @Test
    @DisplayName("should return folder entity with same params as dto")
    void shouldMapFolderEntityToFolderDto() {
        FolderEntity entity = new FolderEntity();
        entity.setName("folder");

        FolderDto folder = dataMapper.mapFolderEntityToFolderDto(entity);

        assertThat(folder.getName()).isEqualTo(entity.getName());
    }

    @Test
    @DisplayName("should return folder dto with same params as entity")
    void shouldMapFolderDtoToFolderEntity() {
        FolderDto folder = new FolderDto();
        folder.setName("folder");

        FolderEntity entity = dataMapper.mapFolderDtoToFolderEntity(folder);

        assertThat(folder.getName()).isEqualTo(entity.getName());
    }

    @Test
    @DisplayName("should return tag dto with same params as entity")
    void shouldMapTagEntityToTagDto() {
        TagEntity entity = new TagEntity();
        entity.setTitle("tag");

        NoteTagDto tag = dataMapper.mapTagEntityToTagDto(entity);

        assertThat(tag.getTitle()).isEqualTo(entity.getTitle());
    }

    @Test
    @DisplayName("should return tag entity with same params as dto")
    void shouldMapTagDtoToTagEntity() {
        NoteTagDto tag = new NoteTagDto();
        tag.setTitle("tag");

        TagEntity entity = dataMapper.mapTagDtoToTagEntity(tag);

        assertThat(tag.getTitle()).isEqualTo(entity.getTitle());
    }
}