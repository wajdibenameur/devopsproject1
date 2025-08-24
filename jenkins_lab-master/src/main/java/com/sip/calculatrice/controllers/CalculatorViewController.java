package com.sip.calculatrice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sip.calculatrice.services.CalculatorService;

@Controller
@RequestMapping("/calculator")
public class CalculatorViewController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping
    public String showCalculator() {
        return "calculator";
    }

    @PostMapping("/compute")
    public String compute(
            @RequestParam double a,
            @RequestParam double b,
            @RequestParam String operation,
            Model model) {

        double result;
        try {
            switch (operation) {
                case "add":
                    result = calculatorService.add(a, b);
                    break;
                case "subtract":
                    result = calculatorService.subtract(a, b);
                    break;
                case "multiply":
                    result = calculatorService.multiply(a, b);
                    break;
                case "divide":
                    result = calculatorService.divide(a, b);
                    break;
                default:
                    throw new IllegalArgumentException("Opération inconnue");
            }
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("operation", operation);
        return "calculator";
    }
}
