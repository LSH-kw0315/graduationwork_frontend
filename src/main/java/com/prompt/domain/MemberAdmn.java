package com.prompt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "member_admn", schema = "", catalog = "")
public class MemberAdmn {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  @Column(name = "member_id", nullable = false, unique = true, updatable = false)
  private Integer memberId;
  @NotNull
  @Column(name = "logintype")
  private String logintype;
  @NotNull
  @Column(name = "usr_id")
  private String usrId;
  @NotNull
  @Column(name = "paswrd")
  private String paswrd;
  @Column(name = "usr_nam")
  private String usrNam;
  @Column(name = "usr_mal")
  private String usrMal;
  @Column(name = "regi_dt")
  private LocalDateTime regiDt;
  @Column(name = "del_fg")
  private String delFg;
  @Column(name = "deldat")
  private LocalDateTime deldat;

  @Column(name = "bigo")
  private String bigo;
}
