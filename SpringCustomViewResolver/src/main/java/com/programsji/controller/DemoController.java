package com.programsji.controller;

import com.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui1.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.programsji.model.Person;
import org.springframework.ui.Model;

@Controller
public class DemoController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String personlistonjspview(Model model) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person(1, "Steve", "Gates"));
        personList.add(new Person(2, "Bill", "Jobs"));
        model.addAttribute("personlist", personList);
        return "personlist";
    }

    @RequestMapping(value = "/personlistintxtfile", method = RequestMethod.GET)
    public String personlistintxtfile(Model model) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person(1, "Steve", "Gates"));
        personList.add(new Person(2, "Bill", "Jobs"));
        model.addAttribute("personlist", personList);
        return "txt:personlist";
    }
}
