package de.fhws.fiw.fds.suttondemoMessage.model;


import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.io.Serializable;

public class Message extends AbstractModel implements Serializable {
    private String text;
    private String textAuthor;
    public Message(){}

    public Message(String text, String textAuthor) {
        this.text = text;
        this.textAuthor = textAuthor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextAuthor() {
        return textAuthor;
    }

    public void setTextAuthor(String textAuthor) {
        this.textAuthor = textAuthor;
    }



}
