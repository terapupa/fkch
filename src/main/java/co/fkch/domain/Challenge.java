package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Challenge {

    @Id
    private String id;
    private String companyName;
    private String description;
    private List<ChallengeTag> challengeTags;
    private List<Solution> solutions;
    private List<Comment> comments;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
                "Challenge[id=%s, companyName='%s', description='%s']",
                id, companyName, description);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
