package com.example.springedu2.controller;

import com.example.springedu2.entity.Visitor;
import com.example.springedu2.repositoty.VisitorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class VisitorController {


    // 1번 방식: @Autowired 대신 생성자 주임
    // @Autowired
    // private VisitorRepository visitorRepository;

    // 2번 방식: 생성자 주임: 요즘 방식
    /*
    private VisitorRepository visitorRepository;
    public VisitorController(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }*/

    // 3. 생성자 주입  다른 방법
    // @RequiredArgsConstructor  최상단에 필수 :  Lombok 필수 사용
    private final VisitorRepository visitorRepository;

    @GetMapping("/vlist")
    public ModelAndView vlist(){
        return null;
    }

    @GetMapping("/vsearch")
    public ModelAndView vsearch(){
        return null;
    }

    // @Valid: form에서 넘어온 자료를 @Entity에 있는 설정과 비교해서
    // 입력 데이터를 검증함
    @PostMapping("/vinsert")
    @Transactional
    public String vinsert(@Valid Visitor visitor,
            BindingResult bindingResult,
            Model model){

        System.out.print("visitor:" + visitor);
        System.out.print("bindingResult:" + bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("msg","이름과 내용을 모두 입력하세요");
            return "visitorView"; // visitorview.html
        }
        visitorRepository.save(visitor); // entity 객체를 사용해야 한다

        return "redirect:/vlist";
    }
}
