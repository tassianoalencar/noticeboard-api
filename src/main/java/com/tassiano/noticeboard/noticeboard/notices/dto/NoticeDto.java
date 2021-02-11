package com.tassiano.noticeboard.noticeboard.notices.dto;

import com.tassiano.noticeboard.noticeboard.notices.model.Notice;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NoticeDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime visualizedAt;

    public NoticeDto(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.description = notice.getDescription();
        this.createdAt = notice.getCreatedAt();
        this.visualizedAt = notice.getVisualizedAt();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getVisualizedAt() {
        return visualizedAt;
    }

    public static List<NoticeDto> convert(Page<Notice> notices) {
        return notices.stream().map(NoticeDto::new).collect(Collectors.toList());
    }
}
