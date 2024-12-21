package com.example.LoanCalculator;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoanController {

	@GetMapping("/")
	public String index() {
		return "index"; // This will render the index.html template
	}

	@PostMapping("/calculate")
	public String calculate(
			@RequestParam double loanAmount,
			@RequestParam double interestRate,
			@RequestParam int loanTerm,
			Model model
			) {
		// 1. Calculate monthly interest rate
		double monthlyInterestRate = (interestRate / 100) / 12;

		// 2. Calculate monthly payment
		double monthlyPayment = (loanAmount * monthlyInterestRate) /
				(1 - Math.pow(1 + monthlyInterestRate, -loanTerm * 12));

		// Format the result to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.##"); 
		String formattedMonthlyPayment = df.format(monthlyPayment);

		// Add the formatted result to the model
		model.addAttribute("monthlyPayment", formattedMonthlyPayment); 

		return "result"; 
	}
}