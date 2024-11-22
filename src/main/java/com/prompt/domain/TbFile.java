package com.prompt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "tb_file", schema = "", catalog = "")
public class TbFile {

  @EmbeddedId
  private GroupTbFile groupTbFile;
  @Column(name = "file_nm")
  private String fileNm;
  @Column(name = "file_extsn_nm")
  private String fileExtsnNm;
  @Column(name = "file_size")
  private String fileSize;
  @Column(name = "file_partn_cours")
  private String filePartnCours;
  @Column(name = "file_abslt_cours")
  private String fileAbsltCours;
  @Column(name = "cntnts_ty")
  private String cntntsTy;
  @Column(name = "sort_ordr")
  private Integer sortOrdr;
  @Column(name = "register_no")
  private String registerNo;
  @Column(name = "regist_dt")
  private LocalDateTime registDt;
  @Column(name = "file_dc")
  private String fileDc;
  @Column(name = "attrb_1")
  private String attrb1;
  @Column(name = "attrb_2")
  private String attrb2;
  @Column(name = "attrb_3")
  private String attrb3;
}
