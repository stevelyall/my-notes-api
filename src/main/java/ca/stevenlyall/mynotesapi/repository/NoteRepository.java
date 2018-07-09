package ca.stevenlyall.mynotesapi.repository;

import ca.stevenlyall.mynotesapi.domain.Note;

public interface NoteRepository {

    Note[] getAll();

    Note add(Note itemToAdd);
}
