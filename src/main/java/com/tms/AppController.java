package com.tms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private CityService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<City> listCities = service.listAll();
        model.addAttribute("listCities", listCities);

        return "index";
    }
}
