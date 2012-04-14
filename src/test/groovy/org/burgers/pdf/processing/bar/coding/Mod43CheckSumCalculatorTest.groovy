package org.burgers.pdf.processing.bar.coding

import org.junit.Before
import org.junit.Test;

public class Mod43CheckSumCalculatorTest {
    Mod43CheckSumCalculator calculator

    @Before
    void setUp(){
        calculator = new Mod43CheckSumCalculator()
    }

    @Test
    void calculate(){
        assert calculator.calculate("HI") == "Z"
        assert calculator.calculate("CODE-39") == "P"
        assert calculator.calculate("CODE39") == "W"
    }
}
