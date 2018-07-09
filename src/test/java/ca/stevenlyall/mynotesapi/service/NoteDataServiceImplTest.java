package ca.stevenlyall.mynotesapi.service;

import ca.stevenlyall.mynotesapi.TestStatics;
import ca.stevenlyall.mynotesapi.domain.Note;
import ca.stevenlyall.mynotesapi.repository.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class NoteDataServiceImplTest {

    @InjectMocks
    private NoteDataServiceImpl service;

    @Mock
    private NoteRepository repository;

    private Note[] testNotes;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testNotes = buildTestNoteArray();
    }

    @Test
    public void getAllNotes() {

        Mockito.when(repository.getAll()).thenReturn(testNotes);

        Note[] notes = service.getAllNotes();
        assertArrayEquals(testNotes, notes);
    }

    @Test
    public void addNote() {
        Mockito.when(repository.add(TestStatics.note1)).thenReturn(TestStatics.noteWithId1);

        Note noteAdded = service.addNote(TestStatics.note1);
        assertEquals(TestStatics.noteWithId1, noteAdded);

        Note[] notesAfterAdd = new Note[]{testNotes[0], testNotes[1], noteAdded};
        Mockito.when(repository.getAll()).thenReturn(notesAfterAdd);

        Note[] notes = service.getAllNotes();
        assertEquals(notes.length, 3);

        assertEquals(noteAdded.getId(), notes[2].getId());
        assertEquals(noteAdded.getTitle(), notes[2].getTitle());
        assertEquals(noteAdded.getContent(), notes[2].getContent());
    }

    private Note[] buildTestNoteArray() {
        return new Note[]{TestStatics.noteWithId1, TestStatics.noteWithId2};
    }
}