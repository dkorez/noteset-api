package com.noteset.api.bl.service;

import com.noteset.api.model.CreateNoteRequest;
import com.noteset.api.model.NoteDetailsDto;
import com.noteset.api.model.NoteDto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface NoteBlService {

    public NoteDto createNote(CreateNoteRequest noteReq);

    public List<NoteDto> getAllNotes();

    public NoteDetailsDto getNoteByGuid(String guid);

    public NoteDto updateNote(NoteDto note);
    
    public NoteDto patchNote(String guid, NoteDto note);

    public void deleteNote(String guid);

    public NoteDto addNoteTag(String guid, Long tagId);

    public NoteDto removeNoteTag(String guid, Long tagId);
}
