package ca.stevenlyall.mynotesapi;

import ca.stevenlyall.mynotesapi.domain.Note;

import java.util.UUID;

public class TestStatics {
    public static Note noteWithId1 = new Note(UUID.randomUUID(), "Note 1", "Note One");
    public static Note noteWithId2 = new Note(UUID.randomUUID(), "Note 2", "Note Two");

    public static Note note1 = new Note("Note 1", "Note One");
}

