package com.ddmai.CoronaTracker.controllers;

import com.ddmai.CoronaTracker.models.LocationStats;
import com.ddmai.CoronaTracker.services.CoronaDataService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaDataService coronaDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaDataService.getLocationStats();
        //JFreeChart chart = ChartFactory.createLineChart();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPreviousDay).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
