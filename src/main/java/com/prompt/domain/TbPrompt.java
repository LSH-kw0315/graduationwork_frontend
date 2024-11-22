package com.prompt.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Table(name = "tb_prompt")
public class TbPrompt {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "prompt_seq", nullable = false)
  private Integer promptSeq;

  @Column(name = "prompt_name")
  private String prompt_name;

  @Column(columnDefinition = "TEXT")
  private String prompt_content;

  //@Column(name = "reg_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="reg_id")
  private MemberAdmn reg_id;

  public void edit(String prompt_name,String prompt_content){
    this.prompt_name=prompt_name;
    this.prompt_content=prompt_content;
  }
}
