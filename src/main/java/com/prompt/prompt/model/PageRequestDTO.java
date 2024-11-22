package com.prompt.prompt.model;

import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page=1;
    private String type;
    private String keyword;
    private String link;

    public String getLink(){
        if(link==null){
            StringBuilder builder=new StringBuilder();
            builder.append("page="+page);

            if(type !=null){
                if(type.equals("t")) {
                    builder.append("&type=t");
                }else if(type.equals("tc")){
                    builder.append("&type=c");
                }else{
                    builder.append("&type=tc");
                }
            }

            if(keyword != null && !keyword.isEmpty()){
                String encoded= URLEncoder.encode(keyword,StandardCharsets.UTF_8);
                builder.append("&keyword="+encoded);
            }
            link= builder.toString();
        }
        return link;
    }

    @Override
    public String toString() {
        return getLink();
    }
}
