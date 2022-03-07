package com.tms.service;

import com.tms.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/new")
    public String showNewCityForm(Model model) {
        City city = new City();
        model.addAttribute("city", city);
        return "new_city";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCity(@ModelAttribute("city") City city) {
        service.save(city);
        return "redirect:/";
    }

}
