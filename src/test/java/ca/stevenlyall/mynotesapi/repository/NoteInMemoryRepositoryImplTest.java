package ca.stevenlyall.mynotesapi.repository;

import ca.stevenlyall.mynotesapi.TestStatics;
import ca.stevenlyall.mynotesapi.domain.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NoteInMemoryRepositoryImplTest {

    private NoteInMemoryRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = new NoteInMemoryRepositoryImpl();
    }

    @Test
    public void testGetAllRetrievesNotesCorrectly() {
        Note[] notes = repository.getAll();
        assertNotNull(notes);
        assertEquals(0, notes.length);
    }

    @Test
    public void testAddAddsNewNoteCorrectly() {
        Note newNote = TestStatics.noteWithId1;
        repository.add(newNote);
        Note[] notes = repository.getAll();
        assertEquals(1, notes.length);
    }

}