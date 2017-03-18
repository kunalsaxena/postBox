package com.mailclient.dto;

/**
 * Created by kunal on 15/2/17.
 */
public class EmailDTO implements Comparable<EmailDTO> {

    public EmailDTO() {

    }

    public EmailDTO(String subject, String from) {
        this.subject = subject;
        this.from = from;
    }

    public EmailDTO(String subject, String from, String contentType, String content) {
        this.subject = subject;
        this.from = from;
        this.contentType = contentType;
        this.content = content;
    }

    private String messageId;

    private String messageNo;

    private String subject;

    private String from;

    private String content;

    private Long receivedDate;

    private String contentType;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Long receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "messageId='" + messageId + '\'' +
                ", messageNo='" + messageNo + '\'' +
                ", subject='" + subject + '\'' +
                ", from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", receivedDate=" + receivedDate +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    @Override
    public int compareTo(EmailDTO o) {
        return this.subject.compareTo(o.getSubject());
    }
}
