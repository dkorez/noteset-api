package com.noteset.api.domain.service;

import com.noteset.api.domain.model.FolderEntity;
import com.noteset.api.domain.repository.FolderRepository;
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

class FolderDomainServiceImplTest {

    @Mock
    private FolderRepository folderRepository;

    @InjectMocks
    private FolderDomainServiceImpl folderService;

    @Captor
    private ArgumentCaptor<FolderEntity> folderCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save folder")
    void shouldSavFolder() {
        // check if folder was saved

        FolderEntity folderMock = new FolderEntity();
        folderMock.setName("folder");

        folderService.createFolder(folderMock);

        then(folderRepository).should().save(folderCaptor.capture());
        FolderEntity capturedFolder = folderCaptor.getValue();
        Assertions.assertThat(capturedFolder).isEqualTo(folderMock);

        assertThat(capturedFolder.getName()).isEqualTo(folderMock.getName());
    }


    @Test
    @DisplayName("getFolderById should throw exception when passing invalid ID")
    void shouldFailWithInvalidId() {
        Long fakeId = 0l;

        assertThatThrownBy(() -> folderService.getFolderById(fakeId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Folder with id " + String.valueOf(fakeId) + " not found");
    }

    @Test
    @DisplayName("getFolderById should return entity when passing correct ID")
    void shouldPassWithValidId() {
        FolderEntity folderMock = new FolderEntity();
        folderMock.setName("folder");
        folderService.createFolder(folderMock);

        then(folderRepository).should().save(folderCaptor.capture());
        FolderEntity capturedFolder = folderCaptor.getValue();
        Long testId = capturedFolder.getId();

        given(folderRepository.findById(testId)).willReturn(Optional.of(capturedFolder));

        FolderEntity foundEntity = folderService.getFolderById(testId);
        assertThat(foundEntity).isNotNull();
        assertThat(foundEntity.getName()).isEqualTo(folderMock.getName());
    }

    @Test
    void getNotes() {

    }

    @Test
    void addNoteToFolder() {

    }

    @Test
    void removeNoteFromFolder() {

    }
}