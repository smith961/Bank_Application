package com.example.BankApplicationDevelopment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorTest {

    @Test
    public void addition(){
        Calculator calculator = new Calculator();
        assertEquals(50, calculator.add(30, 20));

    }

    @Test
    public void subtraction(){
        Calculator calculator = new Calculator();
        assertEquals(10, calculator.sub(30, 20));
    }

    @Test
    public void multiplication(){
        Calculator calculator = new Calculator();
        assertEquals(600, calculator.multi(30,20));


    }

    @Test
    public void division(){
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.div(30,20));
    }

}
