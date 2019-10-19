package edu.mum.cs.cs425.athlete.controller;


import edu.mum.cs.cs425.athlete.model.Athlete;
import edu.mum.cs.cs425.athlete.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EliteAthleteController {
    @Autowired
    private AthleteService athleteService;

    @GetMapping(value = "/eAthlete/athlete/elite")
    public String getEliteAthletes(Model model) {
        Comparator<Athlete> comp = (Athlete a1, Athlete a2) -> a1.getDateOfRegistration().compareTo(a2.getDateOfRegistration());
        List<Athlete> athleteList = athleteService.getAllAthletes().stream().sorted(comp).collect(Collectors.toList());

        List<Athlete> athlete = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Athlete a : athleteList) {
            if (a.getTotalNumberOfModelsWon() >= 3) {
                LocalDate dob = a.getDateOfBirth();
                double age = now.getYear() - dob.getYear();
                if (age >= 5) {
                    athlete.add(a);
                }
                model.addAttribute("athletes", athlete);
            }

    }
        return "athlete/elite";
    }
}
//    @GetMapping(value = "/eAthlete/athlete/elite")
//    public ModelAndView displayEliteList() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("athletes", athleteService.getElites());
//        modelAndView.setViewName("athlete/elite");
//        boolean isListEmpty = false;
//        if((athleteService.getElites()).isEmpty()) {
//            isListEmpty = true;
//        }
//        modelAndView.addObject("isListEmpty", isListEmpty);
//        return modelAndView;
//    }

