package com.noteset.api.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.noteset.api.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.noteset.api.domain.model.TagEntity;
import com.noteset.api.domain.repository.NoteTagRepository;

import java.util.Optional;

public class TagDomainServiceImplTest {
	
	@Mock
	private NoteTagRepository noteTagRepository;
	
	@InjectMocks
	private TagDomainServiceImpl tagDomainService;
	
	@Captor
    private ArgumentCaptor<TagEntity> tagCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
    @DisplayName("Should save tag")
    void shouldSaveNote() {
        // check if tag was saved
        // also check if tag's name equals the parameter

		String tagTitle = "testTag";
		tagDomainService.createNoteTag(tagTitle);

        then(noteTagRepository).should().save(tagCaptor.capture());
        TagEntity capturedTag = tagCaptor.getValue();

        assertThat(capturedTag.getTitle()).isEqualTo(tagTitle);
    }

    @Test
    @DisplayName("getById should throw exception when passing invalid ID")
    void shouldFailWithInvalidId() {
        Long fakeId = 0l;

        assertThatThrownBy(() -> tagDomainService.getById(fakeId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Tag with id " + String.valueOf(fakeId) + " not found");
    }

    @Test
    @DisplayName("getById should return TAG entity when passing correct ID")
    void shouldPassWithValidId() {
        String tagTitle = "testTag";
        tagDomainService.createNoteTag(tagTitle);

        then(noteTagRepository).should().save(tagCaptor.capture());
        TagEntity capturedTag = tagCaptor.getValue();
        Long testId = capturedTag.getId();

        given(noteTagRepository.findById(testId)).willReturn(Optional.of(capturedTag));

        TagEntity foundEntity = tagDomainService.getById(testId);
        assertThat(foundEntity).isNotNull();
    }
}
