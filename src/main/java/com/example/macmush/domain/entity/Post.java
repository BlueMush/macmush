package com.example.macmush.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  // Post(1) : Comment(N)
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private final List<Comment> comments = new ArrayList<>();

  // Post(1) : PostLike(N)
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private final List<PostLike> postLikes = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long postId;

  // Post(N) : Board(1)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  // Post(N) : User(1)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private Users users; // 이전에 만든 User 엔티티

  @Column(name = "title", length = 255, nullable = false)
  private String title;

  @Column(name = "content", columnDefinition = "MEDIUMTEXT", nullable = false)
  private String content;

  @ColumnDefault("0") // DEFAULT '0'
  @Column(name = "view_count", nullable = false)
  private int viewCount;

  @ColumnDefault("0")
  @Column(name = "like_count", nullable = false)
  private int likeCount;

  @ColumnDefault("0")
  @Column(name = "comment_count", nullable = false)
  private int commentCount;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // --- 빌더용 생성자 ---
  // (id, count, date 등 자동 생성 필드 제외)
  @Builder
  public Post(Board board, Users users, String title, String content) {
    this.board = board;
    this.users = users;
    this.title = title;
    this.content = content;
  }
}