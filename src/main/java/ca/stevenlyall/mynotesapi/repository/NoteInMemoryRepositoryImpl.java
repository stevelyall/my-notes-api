package ca.stevenlyall.mynotesapi.repository;

import ca.stevenlyall.mynotesapi.domain.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class NoteInMemoryRepositoryImpl implements NoteRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Note> notes;

    public NoteInMemoryRepositoryImpl() {
        this.notes = new ArrayList<>();
    }

    @Override
    public Note[] getAll() {
        return notes.toArray(new Note[0]);
    }

    @Override
    public Note add(Note noteToAdd) {
        Note note = new Note(UUID.randomUUID(), noteToAdd.getTitle(), noteToAdd.getContent());
        notes.add(note);
        logger.info("Added note", note);

        return note;
    }


}
