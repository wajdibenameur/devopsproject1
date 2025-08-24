package com.sip.calculatrice;

import com.sip.calculatrice.controllers.CalculatorViewController;
import com.sip.calculatrice.services.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorViewController.class)
class CalculatorViewControllerFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        // Optionnel si on veut définir des comportements spécifiques
        when(calculatorService.add(10, 5)).thenReturn(15.0);
        when(calculatorService.subtract(10, 5)).thenReturn(5.0);
        when(calculatorService.multiply(4, 5)).thenReturn(20.0);
        when(calculatorService.divide(20, 4)).thenReturn(5.0);
    }

    @Test
    void testAddition() throws Exception {
        mockMvc.perform(post("/calculator/compute")
                        .param("a", "10")
                        .param("b", "5")
                        .param("operation", "add"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("result", 15.0))
                .andExpect(model().attribute("a", 10.0))
                .andExpect(model().attribute("b", 5.0))
                .andExpect(model().attribute("operation", "add"));
    }

    @Test
    void testDivisionParZero() throws Exception {
        when(calculatorService.divide(5, 0)).thenThrow(new IllegalArgumentException("Division by zero is not allowed"));

        mockMvc.perform(post("/calculator/compute")
                        .param("a", "5")
                        .param("b", "0")
                        .param("operation", "divide"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("error", "Division by zero is not allowed"));
    }
}
