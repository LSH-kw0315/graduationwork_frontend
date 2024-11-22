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
@Table(name = "tb_user", schema = "", catalog = "")
public class TbUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true, updatable = false)
  private String userId;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "password")
  private String password;
  @Column(name = "use_yn")
  private String useYn;

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }
}
