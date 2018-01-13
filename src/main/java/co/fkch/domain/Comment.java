package co.fkch.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

    private String commentBody;

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}
