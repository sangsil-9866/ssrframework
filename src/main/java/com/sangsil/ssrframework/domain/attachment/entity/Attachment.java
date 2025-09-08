package com.sangsil.ssrframework.domain.attachment.entity;

import com.sangsil.ssrframework.domain.board.entity.Board;
import com.sangsil.ssrframework.global.entity.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 게시판
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ATTACHMENT")
public class Attachment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID", nullable = false, length = 36)
    private String id;

    @Comment("업로드경로")
    @Column(name="UPLOAD_PATH", nullable = false, length = 20)
    private String uploadPath;

    @Comment("원본파일명")
    @Column(name="ORG_FILE_NAME", nullable = false, length = 100)
    private String orgFileName;

    @Comment("시스템파일명")
    @Column(name="SYS_FILE_NAME", nullable = false, length = 50)
    private String sysFileName;

    @Comment("파일대상 게시판")
    @Column(name="PARENT_TYPE", nullable = false, length = 20)
    private String parentType;

    @Comment("활성화여부")
    @Builder.Default
    @Column(name="ENABLED")
    private boolean enabled = true; // 활성화여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

}
