package co.fkch.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

    @Id
    private String id = new ObjectId().toString();
    private String commentBody;

    public Comment() {
    }

    public Comment(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Comment[id=%s, commentBody='%s']",
                id, commentBody);
    }

}
