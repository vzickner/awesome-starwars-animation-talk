package com.example.awesomestarwarsanimations;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddAnimationService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        ObjectNode person = (ObjectNode) execution.getVariable("people");
        System.out.println("I think that " + person.get("name") + " is awesome!");

        ObjectNode animation = (ObjectNode) execution.getVariable("animation");
        String link = animation.get("data").get(0).get("images").get("fixed_height").get("url").asText();

        ArrayList<Object> links = (ArrayList<Object>) execution.getVariable("links");
        if (links == null) {
            links = new ArrayList<>();
        }
        links.add(link);
        execution.setVariable("links", links);
    }

}
