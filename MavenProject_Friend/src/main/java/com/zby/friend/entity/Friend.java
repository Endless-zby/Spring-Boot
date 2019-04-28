package com.zby.friend.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private String userId;
    @Id
    private String friendId;

    private String isStar;      //  单向星标。 0：不是星标   1：是单向星标

    public Friend(String userId, String friendId, String isStar) {
        this.userId = userId;
        this.friendId = friendId;
        this.isStar = isStar;
    }
    public Friend(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Friend() {

    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getIsStar() {
        return isStar;
    }

    public void setIsStar(String isStar) {
        this.isStar = isStar;
    }
}
