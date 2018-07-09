package ca.stevenlyall.mynotesapi.controller;

import ca.stevenlyall.mynotesapi.domain.Note;
import ca.stevenlyall.mynotesapi.service.NoteDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("notes")
public class NoteRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private NoteDataService noteDataService;

    @Autowired
    public NoteRestController(NoteDataService noteDataService) {
        this.noteDataService = noteDataService;

        // create mock data
        this.noteDataService.addNote(
                new Note(
                        UUID.randomUUID(),
                        "Note 1 Title",
                        "This is a note's content"));
        this.noteDataService.addNote(
                new Note(UUID.randomUUID(),
                        "Note 2 Title",
                        "This is a note's content"));

    }

    /**
     * Retrieve all notes
     */
    @RequestMapping(method = GET)
    public ResponseEntity<Note[]> getNotes() {
        logger.info("GET /notes");

        Note[] notes = noteDataService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    /**
     * Create a new noteDao
     *
     * @param note request body object for new noteDao
     */
    @RequestMapping(method = POST)
    public ResponseEntity<Note> addNote(@Valid @RequestBody Note note) {
        logger.info("POST /notes", note);

        Note added = noteDataService.addNote(note);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

}
