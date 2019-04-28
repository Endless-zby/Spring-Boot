package com.zby.friend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_blacklist")
@IdClass(BlackList.class)
public class BlackList implements Serializable {
    @Id
    private String userId;
    @Id
    private String friendId;

    public BlackList(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public BlackList() {

    }
    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
