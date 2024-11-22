package com.prompt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GroupTbFile implements Serializable {
  @Column(name = "file_id", unique = true, updatable = false)
  private String fileId;
  @Column(name = "doc_id", unique = true, updatable = false)
  private String docId;
}
