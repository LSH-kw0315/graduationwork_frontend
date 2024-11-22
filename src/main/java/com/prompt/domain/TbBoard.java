package com.prompt.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_board", schema = "", catalog = "")
public class TbBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, updatable = false)
  private Integer idx;
  @Column(name = "title")
  private String title;
  @Column(name = "contents")
  private String contents;
  @Column(name = "count")
  private Integer count;
  @Column(name = "doc_id")
  private String docId;
  @Column(name = "reg_id")
  private String regId;
  @Column(name = "reg_dt")
  private LocalDateTime regDt;
  @Column(name = "mod_id")
  private String modId;
  @Column(name = "mod_dt")
  private LocalDateTime modDt;
}
