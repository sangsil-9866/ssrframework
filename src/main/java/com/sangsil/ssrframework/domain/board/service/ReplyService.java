package com.sangsil.ssrframework.domain.board.service;

import com.sangsil.ssrframework.domain.board.dto.ReplyDto;
import com.sangsil.ssrframework.domain.board.entity.Reply;
import com.sangsil.ssrframework.domain.board.repository.ReplyRepository;
import com.sangsil.ssrframework.global.exception.CustomException;
import com.sangsil.ssrframework.global.response.ResponseCode;
import com.sangsil.ssrframework.global.util.UtilMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UtilMessage utilMessage;

    /**
     * 목록
     * @param search
     * @return
     */
    public Page<ReplyDto.Response> replyList(ReplyDto.Search search) {
        // Search 정보로 Pageable 객체 생성
        Pageable pageable = PageRequest.of(
                search.getPage(),
                search.getSize(),
                search.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC,
                search.getSortBy()
        );

        Page<Reply> replyPage = replyRepository.findByBoardIdAndEnabledTrue(search.getBoardId(), pageable);
        return replyPage.map(ReplyDto.Response::toDto);

    }

    /**
     * 등록
     * @param request
     * @return
     */
    @Transactional
    public ReplyDto.Response replyCreate(ReplyDto.CreateRequest request) {

        // S: 유효성검증
        Reply reply = replyRepository.findById(request.getBoardId())
                .orElseThrow(() -> new CustomException(ResponseCode.EXCEPTION_NODATA, utilMessage.getMessage("notfound.data", null)));
        // E: 유효성검증

        replyRepository.save(request.toEntity());
        return ReplyDto.Response.toDto(reply);
    }

    /**
     * 수정
     * @param id
     * @param request
     */
    @Transactional
    public ReplyDto.Response modifyReply(String id, ReplyDto.ModifyRequest request) {

        // S: 유효성검증
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCode.EXCEPTION_NODATA, utilMessage.getMessage("notfound.data", null)));

        // 권한확인(자기것만)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getName().equals(reply.getCreatedBy())){
            throw new CustomException(ResponseCode.EXCEPTION_NODATA, utilMessage.getMessage("reply.auth.forbidden", null));
        }
        // E: 유효성검증

        request.modifyReply(reply);
        replyRepository.save(reply);
        return ReplyDto.Response.toDto(reply);
    }

    /**
     * 삭제
     * @param id
     */
    @Transactional
    public void deleteReply(String id) {

        // S: 유효성검증
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCode.EXCEPTION_NODATA, utilMessage.getMessage("notfound.data", null)));

        // 권한확인(자기것만)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getName().equals(reply.getCreatedBy())){
            throw new CustomException(ResponseCode.EXCEPTION_NODATA, utilMessage.getMessage("reply.auth.forbidden", null));
        }
        // E: 유효성검증

        replyRepository.deleteById(reply.getId());
    }
}