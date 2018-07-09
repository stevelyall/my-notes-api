package ca.stevenlyall.mynotesapi.service;

import ca.stevenlyall.mynotesapi.domain.Note;
import ca.stevenlyall.mynotesapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteDataServiceImpl implements NoteDataService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteDataServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note[] getAllNotes() {
        return noteRepository.getAll();
    }

    @Override
    public Note addNote(Note noteToAdd) {
        return noteRepository.add(noteToAdd);
    }
}
