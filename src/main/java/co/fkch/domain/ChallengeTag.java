package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ChallengeTag {

    @Indexed(sparse = true)
    private String tag;

    public ChallengeTag() {
    }

    public ChallengeTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format(
                "ChallengeTag[tag='%s']",
                tag);
    }

}
