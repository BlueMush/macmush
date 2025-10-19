package com.example.macmush.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

  // Board(1)가 Post(N)를 여러 개 가짐
  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private final List<Post> posts = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Integer boardId; // int -> Integer

  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @Column(name = "slug", length = 50, nullable = false, unique = true)
  private String slug;

  @Column(name = "description", length = 255)
  private String description;

  // --- 빌더용 생성자 ---
  @Builder
  public Board(String name, String slug, String description) {
    this.name = name;
    this.slug = slug;
    this.description = description;
  }
}
