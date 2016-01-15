package com.programsji.controller;

import com.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.programsji.bo.Person;

@Controller
public class DemoController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String demo() {
        return "demo";
    }

    @RequestMapping(value = "/exportpersonlist", method = RequestMethod.GET, produces = {"application/xml"})
    @ResponseBody
    public Person exportpersonlist(Locale locale, Model model) {
        List<String> hobbies1 = new ArrayList<String>();
        hobbies1.add("Hobby1");
        hobbies1.add("Hobby2");
        Person person1 = new Person(1, 1, "Steve", "Gates", 54, hobbies1);
        return person1;

    }
}
