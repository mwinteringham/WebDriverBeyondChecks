package databuilder.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BugCommentModel {

    @JsonProperty
    private String comment;
    @JsonProperty
    private boolean is_private;
    @JsonProperty
    private boolean is_markdown;

    public BugCommentModel(String comment, boolean is_private, boolean is_markdown) {
        this.comment = comment;
        this.is_private = is_private;
        this.is_markdown = is_markdown;
    }

    public String getComment() {
        return comment;
    }

    public boolean is_private() {
        return is_private;
    }

    public boolean is_markdown() {
        return is_markdown;
    }
}
