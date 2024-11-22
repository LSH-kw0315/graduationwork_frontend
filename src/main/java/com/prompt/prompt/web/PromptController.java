package com.prompt.prompt.web;

import com.prompt.common.page.util.PageNavigationUtil;
import com.prompt.common.page.vo.PageNavigationVo;
import com.prompt.common.util.ExcelSxssfUtil;
import com.prompt.common.util.MessageSourceUtil;
import com.prompt.common.util.TransReturnUtil;
import com.prompt.config.security.AuthUtil;
import com.prompt.constant.Const;
import com.prompt.domain.MemberAdmn;
import com.prompt.prompt.model.*;
import com.prompt.prompt.service.PromptService;
import com.prompt.properties.GlobalsProperties;
import com.prompt.repository.MemberAdmnRepository;
import com.prompt.repository.promptpaging.MemberPromptCondition;
import com.prompt.repository.promptpaging.MemberPromptDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PromptController {

    //private final PromptService promptService;
    private final MessageSourceUtil messageSourceUtil;
    private final TransReturnUtil transReturnUtil;
    private final GlobalsProperties globalsProperties;
    private final ExcelSxssfUtil excelSxssfUtil;
    private final PromptService promptService;
    private final MemberAdmnRepository memberAdmnRepository;
    private static final Integer LIMIT = Integer.parseInt(Const.DEF_ROW_PER_PAGE_05);

    @GetMapping(value = "/prompt/promptList.do")
    public String promptList(@ModelAttribute("paramVO") PromptVO promptVO,
                             HttpServletRequest request,
                             PageRequestDTO pageRequestDTO,
                             ModelMap model) throws Exception {
        //System.out.println(promptService.selectListPrompt().toString());

        int real_pageNumber;
        int pageNumber = pageRequestDTO.getPage();
        if (pageNumber == 0) {
            real_pageNumber = 1;
        } else {
            real_pageNumber = pageNumber;
        }

        String type = pageRequestDTO.getType();
        String title = null;
        String content = null;
        if (type != null) {
            if (type.contains("t")) {
                title = pageRequestDTO.getKeyword();
            }
            if (type.contains("c")) {
                content = pageRequestDTO.getKeyword();
            }
        }

        MemberPromptCondition condition = new MemberPromptCondition(
                AuthUtil.getUserDetails().getMemberId(),
                title,
                content);
        Pageable pageable = PageRequest.of(real_pageNumber - 1, LIMIT, Sort.by("promptSeq").descending());
        Page<MemberPromptDto> page = promptService.getPage(condition, pageable);
        System.out.println(page.toString());
        List<PromptListResponseDto> dtos =
                page.stream()
                        .map(
                                memberPromptDto ->
                                    new PromptListResponseDto(
                                            memberPromptDto.getPromptSeq(),
                                            memberPromptDto.getPrompt_name(),
                                            memberPromptDto.getPrompt_content(),
                                            memberPromptDto.getUser_id()

                                    )


                        ).collect(Collectors.toList());
        PageNavigationVo init = new PageNavigationVo();
        init.setTotalCount((int) page.getTotalElements());
        init.setCurrentPage(real_pageNumber);
        init.setPageCount(page.getTotalPages());
        init.setRowPerPage(LIMIT);

        PageNavigationVo result = new PageNavigationUtil().createNavigationInfo(model, init);
        log.info("paging result:" + result);
        //model.addAttribute("promptList", promptService.selectListPrompt());
        model.addAttribute("promptList", dtos);
        model.addAttribute("paging", result);
        model.addAttribute("pageRequestDTO",pageRequestDTO);
        return "prompt/promptList";
    }

    @GetMapping(value = "/prompt/promptReg.do")
    public String promptReg(@ModelAttribute("paramVO") PromptVO promptVO,
        HttpServletRequest request,
        ModelMap model) throws Exception {
        model.addAttribute("promptCreateRequestDto", new PromptCreateRequestDto());
        return "prompt/promptReg";
    }

    @GetMapping(value = "/prompt/promptAutoReg.do")
    public String promptAutoReg(@ModelAttribute("paramVO") PromptVO promptVO,
        HttpServletRequest request,
        ModelMap model) throws Exception {
        model.addAttribute("promptCreateRequestDto", new PromptCreateRequestDto());
        return "prompt/promptAutoReg";
    }


    @GetMapping(value = "/prompt/promptHalfAutoReg.do")
    public String promptHalfAutoReg(@ModelAttribute("paramVO") PromptVO promptVO,
                                HttpServletRequest request,
                                ModelMap model) throws Exception {
        model.addAttribute("promptCreateRequestDto", new PromptCreateRequestDto());
        return "prompt/promptHalfAutoReg";
    }



    @GetMapping(value = "/prompt/hautogenerator.do")
    @ResponseBody
    public PromptVO hautogenerator(String persona) throws Exception {
        WebClient webClient = WebClient.create(Const.PROMPT_ENGINE_URL_MAIN_LOCAL); //환경에따라수정

        PromptVO answer = webClient.get()
            .uri("/restApi/hautogenerator/" + persona)
            .retrieve()
            .bodyToMono(PromptVO.class)
            .block();

        System.out.println(answer.getAnswer());
        return answer;
    }



    @GetMapping(value = "/prompt/autogenerator.do")
    @ResponseBody
    public PromptVO autogenerator(String persona) throws Exception {
        WebClient webClient = WebClient.create(Const.PROMPT_ENGINE_URL_MAIN_LOCAL); //환경에따라수정

        PromptVO answer = webClient.get()
                .uri("/restApi/autogenerator/" + persona)
                .retrieve()
                .bodyToMono(PromptVO.class)
                .block();

        System.out.println(answer.getAnswer());
        return answer;
    }

    @PostMapping(value = "/prompt/eval.do")
    @ResponseBody
    public EvalVO eval(@RequestBody HashMap<String, Object> map) throws Exception {
        System.out.println("map print");
        System.out.println(map);
        WebClient webClient = WebClient.create(Const.PROMPT_ENGINE_URL_MAIN_LOCAL); //환경에따라수정

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("origin",String.valueOf(map.get("origin")));
        formData.add("result",String.valueOf(map.get("result")));
        EvalVO answer = webClient.post()
                .uri("/restApi/geval/")
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(EvalVO.class).block();

        return answer;
    }

    @PostMapping(value = "/prompt/analysis.do")
    @ResponseBody
    public EvalVO analysis(@RequestBody HashMap<String, Object> map) throws Exception {
        System.out.println("map print");
        System.out.println(map);
        WebClient webClient = WebClient.create(Const.PROMPT_ENGINE_URL_MAIN_LOCAL); //환경에따라수정

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("origin",String.valueOf(map.get("origin")));
        formData.add("result",String.valueOf(map.get("result")));
        EvalVO answer = webClient.post()
            .uri("/restApi/analysis/")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(EvalVO.class).block();

        return answer;
    }

    @PostMapping(value = "/prompt/improve.do")
    @ResponseBody
    public EvalVO improve(@RequestBody HashMap<String, Object> map) throws Exception {
        System.out.println("map print");
        System.out.println(map);
        WebClient webClient = WebClient.create(Const.PROMPT_ENGINE_URL_MAIN_LOCAL); //환경에따라수정

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("origin",String.valueOf(map.get("origin")));
        formData.add("request",String.valueOf(map.get("request")));
        formData.add("subject",String.valueOf(map.get("subject")));
        EvalVO answer = webClient.post()
            .uri("/restApiTest/improve/")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(EvalVO.class).block();

        return answer;
    }

    @PostMapping(value = "/prompt/promptCreate.do")
    public String promptCreate(@ModelAttribute PromptCreateRequestDto promptCreateRequestDto, ModelMap model) throws Exception {
        //promptCreateRequestDto.setReg_id();
        //System.out.println(promptCreateRequestDto.toString());
        promptCreateRequestDto.setReg_id(memberAdmnRepository.getReferenceById(AuthUtil.getUserDetails()
            .getMemberId()));
        log.info("promptCreate.do 실행 중");
        System.out.println(promptCreateRequestDto.toString());
        promptService.create(promptCreateRequestDto);
        return "redirect:/prompt/promptList.do";
    }

    @GetMapping(value = "/prompt/promptDelete.do")
    @ResponseBody
    public Integer promptDelete(Integer promptSeq) throws Exception {
        promptService.delete(new PromptDeleteRequestDto(promptSeq));
        return promptSeq;
    }

    @GetMapping(value = "/prompt/{promptID}")
    public String promptDetail(@PathVariable Integer promptID, PageRequestDTO pageRequestDTO,Model model) throws Exception {
        PromptListResponseDto detail = promptService.promptDetailProcess(promptID, model);

        String nlString = System.getProperty("line.separator").toString();
        model.addAttribute("detail", detail);
        model.addAttribute("nlString", nlString);

        return "prompt/promptDetail";
    }
    @GetMapping(value = "/prompt/edit/{promptID}")
    public String promptEditPage(@PathVariable Integer promptID,
                                 Model model) throws Exception {
        PromptListResponseDto detail = promptService.promptDetailProcess(promptID, model);

        model.addAttribute("promptUpdateRequestDto", new PromptUpdateRequestDto());
        model.addAttribute("detail", detail);
        return "prompt/promptModify";
    }

    @PostMapping(value = "/prompt/editProcess.do/{promptID}")
    public String promptEdit(@ModelAttribute PromptUpdateRequestDto promptUpdateRequestDto,
                             @PathVariable Integer promptID) throws Exception {
        //promptUpdateRequestDto.setpromptSeq(promptID);
        //promptUpdateRequestDto.setReg_id(2); // 각 이용자별로 다르게 수정하기 (임시)
        //promptService.update(promptUpdateRequestDto);
        promptService.update(promptID,promptUpdateRequestDto);
        return "redirect:/prompt/promptList.do";
    }
}
