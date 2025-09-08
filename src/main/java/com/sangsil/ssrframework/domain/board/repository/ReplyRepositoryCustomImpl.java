package com.sangsil.ssrframework.domain.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sangsil.ssrframework.domain.board.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.sangsil.ssrframework.domain.board.entity.QReply.reply;

@RequiredArgsConstructor
public class ReplyRepositoryCustomImpl implements ReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReplyDto.Response> findReplyAll(String boardId, Pageable pageable) {
        List<ReplyDto.Response> query = queryFactory.select(
                        Projections.bean(ReplyDto.Response.class
                                , reply.id
                                , reply.board.id
                                , reply.commentary
                                , reply.createdBy
                                , reply.createdAt
                                , reply.modifiedBy
                                , reply.modifiedAt
                        ))
                .from(reply)
                .where(reply.board.id.eq(boardId))
                .orderBy(
                        reply.board.id.asc().nullsFirst()
                        , reply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reply.count())
                .from(reply)
                .where(reply.board.id.eq(boardId))
                ;

        return PageableExecutionUtils.getPage(query, pageable, countQuery::fetchOne);
    }
}
