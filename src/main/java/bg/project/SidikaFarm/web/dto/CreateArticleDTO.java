package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class CreateArticleDTO {
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime lastEdited;
    private MultipartFile image;
    private String imageUrl;
    private User author;

    public CreateArticleDTO() {
        setCreated();
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

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
