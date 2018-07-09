package ca.stevenlyall.mynotesapi.controller;

import ca.stevenlyall.mynotesapi.TestStatics;
import ca.stevenlyall.mynotesapi.domain.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(NoteRestController.class)
public class NoteRestControllerTest {

    @Autowired
    MockMvc mockApp;

    @MockBean
    private NoteRestController controller;

    @Test
    public void testGetNotesReturnsNotesCorrectly() throws Exception {
        Note[] noteResponse = {TestStatics.noteWithId1, TestStatics.noteWithId2};
        given(controller.getNotes()).willReturn(new ResponseEntity<>(noteResponse, HttpStatus.OK));

        mockApp.perform(get("/notes"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(TestStatics.noteWithId1.getId().toString())))
                .andExpect(jsonPath("$[0].title", is(TestStatics.noteWithId1.getTitle())))
                .andExpect(jsonPath("$[0].content", is(TestStatics.noteWithId1.getContent())))
                .andExpect(jsonPath("$[1].id", is(TestStatics.noteWithId2.getId().toString())))
                .andExpect(jsonPath("$[1].title", is(TestStatics.noteWithId2.getTitle())))
                .andExpect(jsonPath("$[1].content", is(TestStatics.noteWithId2.getContent())));
    }

    @Test
    public void testPostNoteRespondsWithCreatedNoteCorrectly() throws Exception {
        Note noteRequest = TestStatics.note1;
        Note noteResponse = TestStatics.noteWithId1;
        when(controller.addNote(noteRequest)).thenReturn(new ResponseEntity<>(noteResponse, HttpStatus.OK));

        mockApp.perform(post("/notes")
                .header("Content-Type", APPLICATION_JSON_UTF8)
                .content(stringifyObject(noteRequest))
        )
                .andExpect(status().is(200));
    }

    @Test
    public void testPostNoteWithInvalidBodyResponds400() throws Exception {

        mockApp.perform(post("/notes")
                .content("foo")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(400));
    }

    @Test
    public void testPostNoteWithMissingTitleResponds400() throws Exception {

        mockApp.perform(post("/notes")
                .content("{\n" +
                        "    \"id\": \"b37a8de4-16e6-45b0-8293-8a31d56cb438\",\n" +
                        "    \"content\": \"This is a note's content\"\n" +
                        "  }")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(400));
    }


    @Test
    public void testPostNoteWithMissingContentResponds400() throws Exception {

        mockApp.perform(post("/notes")
                .content("{\n" +
                        "    \"id\": \"b37a8de4-16e6-45b0-8293-8a31d56cb438\",\n" +
                        "    \"title\": \"Note 1 Title\",\n" +
                        "  }")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(400));
    }


    /**
     * convert an object to a JSON string
     *
     * @param obj to stringify
     * @return JSON String representation of the object
     */
    private String stringifyObject(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}