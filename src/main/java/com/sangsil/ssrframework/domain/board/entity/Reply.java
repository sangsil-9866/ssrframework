package com.sangsil.ssrframework.domain.board.entity;

import com.sangsil.ssrframework.global.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 댓글
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "REPLY")
public class Reply extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID", nullable = false, length = 36)
    private String id;

    @Comment("컬럼 설명")
    @Column(name="COMMENTARY", nullable = false, length = 100)
    private String commentary;  // 댓글

    @Comment("활성화여부")
    @Builder.Default
    @Column(name="ENABLED")
    private boolean enabled = true; // 활성화여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}