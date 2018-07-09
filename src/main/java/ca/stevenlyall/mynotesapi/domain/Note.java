package ca.stevenlyall.mynotesapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Entity class for Note,
 */
public class Note {

    private UUID id;

    @NotNull
    @Size(min = 1, max = 255)
    private String title;


    @NotNull
    @Size(min = 1, max = 1024)
    private String content;

    public Note(@JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
    }

    public Note(@JsonProperty("id") UUID id, @JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Note {id=" + id + ", title=" + title + ", content=" + content + "}";
    }
}
