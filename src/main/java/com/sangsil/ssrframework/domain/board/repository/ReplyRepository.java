package com.sangsil.ssrframework.domain.board.repository;

import com.sangsil.ssrframework.domain.board.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, String> {
    Page<Reply> findByBoardIdAndEnabledTrue(String boardId, Pageable pageable);
}