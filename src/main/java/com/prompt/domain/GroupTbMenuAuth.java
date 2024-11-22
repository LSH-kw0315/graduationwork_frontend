package com.prompt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor

public class GroupTbMenuAuth implements Serializable {
  @Column(name = "menu_seq", unique = true, updatable = false)
  private Integer menuSeq;
  @Column(name = "auth_cd", unique = true, updatable = false)
  private String authCd;
}
