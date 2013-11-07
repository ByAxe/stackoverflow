/**
 * @author Apache Software Foundation (ASF)
 * @version 1.0.0
 * 
 * Simple class that represents a report. 
 */
package model;

import java.io.Serializable;

public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String content;
    private String reply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n>> ***********************************************\n");
        result.append(">> Report id: " + this.id + "\n");
        result.append(">> Report title: " + this.title + "\n");
        result.append(">> Report content: " + this.content + "\n");
        result.append(">> Report reply: " + this.reply + "\n");
        result.append(">> ***********************************************\n");
        return result.toString();
    }
}