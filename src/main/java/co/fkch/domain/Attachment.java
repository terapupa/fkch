package co.fkch.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Attachment {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
