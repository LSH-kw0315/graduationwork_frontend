package com.prompt.web.main.web;

import com.prompt.admin.common.vo.CommonVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    @RequestMapping(value = {"/", "/web/main/main.do"})
    public String auth(@ModelAttribute("commonVO") CommonVO commonVO, ModelMap model) throws Exception {
        //return "web/main/main";
        return "redirect:/prompt/promptList.do";
    }
}
