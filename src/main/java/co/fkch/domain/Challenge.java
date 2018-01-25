package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Challenge {

    @Id
    private String id;
    @NotNull
    private String company;
    private String description;
    private List<ChallengeTag> challengeTags;
    private List<Solution> solutions;
    private List<Comment> comments;
    private String userId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChallengeTag> getChallengeTags() {
        return challengeTags;
    }

    public void setChallengeTags(List<ChallengeTag> challengeTags) {
        this.challengeTags = challengeTags;
    }

    @Override
    public String toString() {
        return String.format(
                "Challenge[id=%s, company='%s', description='%s']",
                id, company, description);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
