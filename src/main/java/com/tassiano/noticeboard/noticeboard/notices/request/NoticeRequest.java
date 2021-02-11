package com.tassiano.noticeboard.noticeboard.notices.request;

import com.tassiano.noticeboard.noticeboard.notices.model.Notice;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NoticeRequest {

    @NotNull @NotEmpty
    private String title;

    @NotNull @NotEmpty
    private String description;

    public static Notice convert(NoticeRequest noticeRequest) {
        return new Notice(noticeRequest.getTitle(), noticeRequest.getDescription());
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
}
