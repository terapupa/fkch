package co.fkch.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Solution {
    private String solutionBody;
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
}
