package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Solution {

    @Id
    private String id;
    private String solutionBody;
    private String language;
    private List<Comment> comments;

    public String getSolutionBody() {
        return solutionBody;
    }

    public void setSolutionBody(String solutionBody) {
        this.solutionBody = solutionBody;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
