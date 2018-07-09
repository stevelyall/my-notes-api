package ca.stevenlyall.mynotesapi.service;

import ca.stevenlyall.mynotesapi.domain.Note;

public interface NoteDataService {

    Note[] getAllNotes();

    Note addNote(Note noteToAdd);

}
