package com.sangsil.ssrframework.domain.sample.controller;

import com.sangsil.ssrframework.domain.sample.service.SampleService;
import com.sangsil.ssrframework.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    private final SampleService sampleService;

    @GetMapping
    public String sampleList(Model model, UserDto.Search search) {
        List<UserDto.Response> users = sampleService.findUserAll();
        model.addAttribute("users", users);
        return "sample/sampleList";
    }

}
