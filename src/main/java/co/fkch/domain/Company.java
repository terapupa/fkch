package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Company {

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    @Id
    private String id;

    @Indexed(unique = true)
    private String companyName;

    @DBRef
    private List<Challenge> challenges;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
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
                "Company[id=%s, companyName='%s']",
                id, companyName);
    }

}
