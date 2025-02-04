package bg.project.SidikaFarm.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class Article extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String title;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(name = "last_edited")
    private LocalDateTime lastEdited;

    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private User author;

    public Article() {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
