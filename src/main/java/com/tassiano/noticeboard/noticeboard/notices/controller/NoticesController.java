package com.tassiano.noticeboard.noticeboard.notices.controller;

import com.tassiano.noticeboard.noticeboard.notices.dto.NoticeDto;
import com.tassiano.noticeboard.noticeboard.notices.model.Notice;
import com.tassiano.noticeboard.noticeboard.notices.repository.NoticeRepository;
import com.tassiano.noticeboard.noticeboard.notices.request.NoticeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notices")
public class NoticesController {

    @Autowired()
    private NoticeRepository noticeRepository;

    @GetMapping
    public Page<NoticeDto> notices(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Notice> pageResult = noticeRepository.findAll(pageRequest);
        List<NoticeDto> notices = NoticeDto.convert(pageResult);

        return new PageImpl<>(notices, pageRequest, pageResult.getTotalElements());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<NoticeDto> create(@RequestBody @Valid NoticeRequest noticeRequest, UriComponentsBuilder uriComponentsBuilder) {
        Notice notice = NoticeRequest.convert(noticeRequest);
        noticeRepository.save(notice);

        URI uri = uriComponentsBuilder.path("/notices/{id}").buildAndExpand(notice.getId()).toUri();
        return ResponseEntity.created(uri).body(new NoticeDto(notice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);

        if (notice.isPresent()) {
            return ResponseEntity.ok(new NoticeDto(notice.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid NoticeRequest noticeRequest) {
        Optional<Notice> notice = noticeRepository.findById(id);

        if (notice.isPresent()) {
            notice.get().setTitle(noticeRequest.getTitle());
            notice.get().setDescription(noticeRequest.getDescription());
            return ResponseEntity.ok(new NoticeDto(notice.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);

        if (notice.isPresent()) {
            noticeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
