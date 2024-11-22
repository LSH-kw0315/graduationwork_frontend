package com.prompt.prompt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prompt.admin.common.vo.CommonVO;
import lombok.*;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalVO extends CommonVO {

    @JsonProperty
    private Map<String, Object> answer;

}
