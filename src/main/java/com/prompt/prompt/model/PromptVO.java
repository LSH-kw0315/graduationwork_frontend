package com.prompt.prompt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prompt.admin.common.vo.CommonVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptVO extends CommonVO {

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @JsonProperty
    private String query;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @JsonProperty
    private String answer;

    public String getIntermedia() {
        return intermedia;
    }

    public void setIntermedia(String intermedia) {
        this.intermedia = intermedia;
    }

    @JsonProperty
    private String intermedia;


}
