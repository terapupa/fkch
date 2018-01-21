package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ChallengeTag {

    @Id
    private String id;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "ChallengeTag[id=%s, tag='%s']",
                id, tag);
    }

}
