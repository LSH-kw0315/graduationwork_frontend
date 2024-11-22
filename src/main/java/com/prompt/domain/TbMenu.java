package com.prompt.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_menu", schema = "", catalog = "")
public class TbMenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_seq", unique = true, updatable = false)
  private Integer menuSeq;
  @Column(name = "up_menu_seq")
  private Integer upMenuSeq;
  @Column(name = "url")
  private String url;
  @Column(name = "sort_ordr")
  private Double sortOrdr;
  @Column(name = "menu_yn")
  private String menuYn;
  @Column(name = "menu_nm")
  private String menuNm;
}
