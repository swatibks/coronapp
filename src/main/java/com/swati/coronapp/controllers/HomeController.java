package com.swati.coronapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swati.coronapp.models.GlobalStats;
import com.swati.coronapp.models.USADeath;
import com.swati.coronapp.models.USAStats;
import com.swati.coronapp.services.CoronaTrackerService;
import com.swati.coronapp.services.USACoronaTrackerService;
import com.swati.coronapp.services.USADeathTrackerService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaTrackerService coronaTrackerService;
	
	@Autowired
	USACoronaTrackerService usaCoronaTrackerService;
	
	@Autowired
	USADeathTrackerService usaDeathTrackerService;
	
	@RequestMapping("/")
	public String HomePage(Model model) {
		
		List<GlobalStats> globalstats = coronaTrackerService.getfinalstats();
		
		int totalReportedCases = globalstats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
		int totalNewCases = globalstats.stream().mapToInt(stat -> stat.getDiffFromLastDay()).sum();
		model.addAttribute("globalstats", globalstats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);		
		return "Home";
	}
	
	@GetMapping("/USA")
	public String USA(Model model) {
		
		List<USAStats> usastats = usaCoronaTrackerService.getfinalstats();
		
		int totalReportedCases = usastats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
		int totalNewCases = usastats.stream().mapToInt(stat -> stat.getDiffFromLastDay()).sum();
		model.addAttribute("usastats", usastats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);		
		return "USA";
	}
	
	@GetMapping("/USADeath")
	public String USADeath(Model model) {
		
		List<USADeath> usadeaths = usaDeathTrackerService.getfinalstats();
		
		int totalReportedCases = usadeaths.stream().mapToInt(stat -> stat.getTotalDeath()).sum();
		int totalNewCases = usadeaths.stream().mapToInt(stat -> stat.getDiffFromLastDay()).sum();
		model.addAttribute("usadeaths", usadeaths);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);		
		return "USADeath";
	}

}
