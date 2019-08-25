package cn.paulpaulzhang.dormitorymanagement.model;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Uid;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.model
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
@Entity
public class Notification {

    @Id
    private long id;

    private String title;

    private String content;

    private Date date;

    private String pusher;

    private String imageUrl;

    public Notification(String title, String content, Date date, String pusher) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.pusher = pusher;
    }

    public Notification(String title, String content, Date date, String pusher, String imageUrl) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.pusher = pusher;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPusher() {
        return pusher;
    }

    public void setPusher(String pusher) {
        this.pusher = pusher;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
