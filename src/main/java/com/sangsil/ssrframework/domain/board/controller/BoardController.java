package com.sangsil.ssrframework.domain.board.controller;

import com.sangsil.ssrframework.domain.board.dto.BoardDto;
import com.sangsil.ssrframework.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 게시판
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 목록
     * @param search
     * @return
     */
    @GetMapping
    public String boardList(Model model, BoardDto.Search search) {
        Page<BoardDto.Response> boards = boardService.boardList(search);
        model.addAttribute("boards", boards);
        return "pages/board/boardList";
    }

    /**
     * 게시판 상세
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String boardDetail(Model model, @PathVariable String id) {
        BoardDto.Response board = boardService.boardDetail(id);
        model.addAttribute("board", board);
        return "pages/board/boardDetail";
    }

    /**
     * 게시판 등록화면
     * @return
     */
    @GetMapping("/create")
    public String createForm() {
        return "pages/board/boardCreate";
    }


    /**
     * 게시판 등록
     * @param request
     * @param mFiles
     * @return
     * @throws IOException
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BoardDto.Response> boardCreate(
            @Valid BoardDto.CreateRequest request,
            @RequestParam (name = "mFiles", required = false) MultipartFile[] mFiles
    ) throws IOException {
        BoardDto.Response response = boardService.boardCreate(request, mFiles);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 게시판 수정화면
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}/modify")
    public String boardModifyForm(Model model, @PathVariable String id) {
        BoardDto.Response board = boardService.boardDetail(id);
        model.addAttribute("board", board);
        return "pages/board/boardModify";
    }

    /**
     * 게시판 수정
     * @param id
     * @param request
     * @param mFiles
     * @return
     * @throws IOException
     */
    @PostMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BoardDto.Response> boardModify(@PathVariable String id
        , @Valid BoardDto.ModifyRequest request
        , @RequestParam(name = "mFiles", required = false) MultipartFile[] mFiles) throws IOException {
        BoardDto.Response response = boardService.boardModify(id, request, mFiles);
        log.info(response.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 게시판 삭제
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardDto.Response> boardDelete(@PathVariable String id) throws IOException {
        boardService.boardDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}