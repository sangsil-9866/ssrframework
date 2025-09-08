package com.sangsil.ssrframework.domain.board.repository;

import com.sangsil.ssrframework.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, String>, BoardRepositoryCustom {
}