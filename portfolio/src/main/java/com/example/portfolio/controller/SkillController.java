package com.example.portfolio.controller;

import java.util.Arrays;
import java.util.List;

import com.example.portfolio.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkillController {

    @GetMapping("/skills")
    public String getSkillsPage(Model model) {
        List<Skill> skills = Arrays.asList(
                new Skill("JAVA"),
                new Skill("HTML"),
                new Skill("CSS"),
                new Skill("BOOTSTRAP"),
                new Skill("JS"),
                new Skill("TS"),
                new Skill("ANGULAR")
        );

        model.addAttribute("skills", skills);
        return "skills"; 
    }
}
