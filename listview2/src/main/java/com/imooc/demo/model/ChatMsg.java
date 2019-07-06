package com.imooc.demo.model;

public class ChatMsg {
    private int id;
    private int friendId;
    private String name;
    private String date;
    private String content;
    private String time;
    private String avatartUrl;
    private boolean isComeMessage;

    public ChatMsg(int id, int friendId, String name, String date, String content, String time, String avatartUrl, boolean isComeMessage) {
        this.id = id;
        this.friendId = friendId;
        this.name = name;
        this.date = date;
        this.content = content;
        this.time = time;
        this.avatartUrl = avatartUrl;
        this.isComeMessage = isComeMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAvatartUrl() {
        return avatartUrl;
    }

    public void setAvatartUrl(String avatartUrl) {
        this.avatartUrl = avatartUrl;
    }

    public boolean isComeMessage() {
        return isComeMessage;
    }

    public void setComeMessage(boolean comeMessage) {
        isComeMessage = comeMessage;
    }
}
