package com.randstad.portfolio.domain.models;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String img;
    @Column(length = 1000)
    private String description;

    private String linkGitHub;

    public Project() {
    }

    public Project(Long id, String title, String img, String description, String linkGitHub) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.description = description;
        this.linkGitHub = linkGitHub;
    }

    public Project(String title, String img, String description, String linkGitHub) {
        this.title = title;
        this.img = img;
        this.description = description;
        this.linkGitHub = linkGitHub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkGitHub() {
        return linkGitHub;
    }

    public void setLinkGitHub(String linkGitHub) {
        this.linkGitHub = linkGitHub;
    }

}
