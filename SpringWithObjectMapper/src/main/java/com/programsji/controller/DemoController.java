package com.programsji.controller;

import java.io.IOException;
import com.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.programsji.bo.Person;

@Controller
public class DemoController {

    private static final Logger logger = LoggerFactory
            .getLogger(DemoController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String demo(Locale locale, Model model)
            throws JsonGenerationException, JsonMappingException, IOException {
        List<Person> personlist = new ArrayList<Person>();
        List<String> hobby1 = new ArrayList<String>();
        hobby1.add("Hobby1");
        hobby1.add("Hobby2");
        Person p1 = new Person(1, "Steve", "Gates", hobby1);

        List<String> hobby2 = new ArrayList<String>();
        hobby2.add("Hobby3");
        hobby2.add("Hobby4");
        Person p2 = new Person(2, "Bill", "Jobs", hobby2);

        personlist.add(p1);
        personlist.add(p2);
        String json = convertPersonListToJson(personlist);
        List<Person> newList = convertJsonToPersonList(json);

        logger.info("Person List : " + newList.toString());
        model.addAttribute("personlistinjsonformat", newList);
        return "demo";
    }

    private String convertPersonListToJson(List<Person> personlist)
            throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(personlist);
        return s;
    }

    private List<Person> convertJsonToPersonList(String json)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> personlist = objectMapper.readValue(json,
                new TypeReference<List<Person>>() {
                });

        return personlist;
    }
}
