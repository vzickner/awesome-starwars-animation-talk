package com.example.awesomestarwarsanimations;

import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AwesomeAnimationController {

    private RuntimeService runtimeService;

    public AwesomeAnimationController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @GetMapping("/")
    public String getAwesomeAnimations(Model model) {

        String processInstanceId = this.runtimeService.startProcessInstanceByKey("starWars")
                .getProcessInstanceId();

        Map<String, Object> processVariables = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .includeProcessVariables()
                .singleResult()
                .getProcessVariables();

        model.addAllAttributes(processVariables);

        return "animations";
    }

}
