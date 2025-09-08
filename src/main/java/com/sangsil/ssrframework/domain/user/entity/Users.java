package com.sangsil.ssrframework.domain.user.entity;

import com.sangsil.ssrframework.global.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "USERS")
public class Users extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    @Comment("아이디")
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    @Comment("비밀번호")
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Comment("이름")
    @Column(name = "NAME", length = 50)
    private String name;

    @Comment("이메일")
    @Column(name = "EMAIL", length = 50)
    private String email;

    @Comment("비밀번호")
    @Column(name = "ROLE", length = 50)
    private String role;

    @Comment("가입일시")
    @Column(name = "JOINED_AT", updatable = false)
    private LocalDateTime joinedAt;

    @Comment("로그인일시")
    @Column(name = "SIGNED_AT")
    private LocalDateTime signedAt;

    @Comment("활성화여부")
    @Builder.Default
    @Column(name = "ENABLED")
    private boolean enabled = true;
}

