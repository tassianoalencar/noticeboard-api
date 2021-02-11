package com.tassiano.noticeboard.noticeboard.notices.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notices")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime visualizedAt;

    public Notice() {
    }

    public Notice(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getVisualizedAt() {
        return visualizedAt;
    }

    public void setVisualizedAt(LocalDateTime visualizedAt) {
        this.visualizedAt = visualizedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return Objects.equals(id, notice.id) && Objects.equals(title, notice.title) && Objects.equals(description, notice.description) && Objects.equals(createdAt, notice.createdAt) && Objects.equals(visualizedAt, notice.visualizedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, createdAt, visualizedAt);
    }
}
