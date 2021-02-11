package com.tassiano.noticeboard.noticeboard.notices.repository;

import com.tassiano.noticeboard.noticeboard.notices.model.Notice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long> {}
