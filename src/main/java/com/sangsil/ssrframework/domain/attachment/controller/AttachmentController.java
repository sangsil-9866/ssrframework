package com.sangsil.ssrframework.domain.attachment.controller;

import com.sangsil.ssrframework.domain.attachment.dto.AttachmentDto;
import com.sangsil.ssrframework.domain.attachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 파일다운로드
     * @param id
     * @return
     * @throws MalformedURLException
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) throws MalformedURLException {
        // 첨부파일 정보 조회
        AttachmentDto.Response attachment = attachmentService.attachmentDetail(id);

        // 파일 경로 생성
        String fileDir = attachmentService.getFileDir(attachment.getParentType());
        String filePath = fileDir + attachment.getUploadPath() + java.io.File.separator + attachment.getSysFileName();

        // 파일 리소스 생성
        UrlResource resource = new UrlResource("file:" + filePath);

        // 다운로드 파일명 인코딩 (한글 깨짐 방지)
        String encodedFileName = UriUtils.encode(attachment.getOrgFileName(), StandardCharsets.UTF_8);

        // Content-Disposition 헤더 설정
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}