package org.burgers.pdf.processing.bar.coding

import groovy.mock.interceptor.MockFor
import org.junit.After
import org.junit.Before
import org.junit.Test

public class BarCodeValueGeneratorTest {
    BarCodeValueGenerator generator

    private mockCheckSumCalculator

    @Before
    void setUp() {
        mockCheckSumCalculator = new MockFor(Mod43CheckSumCalculator)
    }

    @Test
    void generate() {
        mockCheckSumCalculator.use {
            generator = new BarCodeValueGenerator()
            generator.generate("BOB") == "*BOB*"
        }
    }

    @Test
    void generate_with_check_sum_true() {
        mockCheckSumCalculator.demand.calculate {arg1 ->
            assert arg1 == "BOB"
            "A"
        }

        mockCheckSumCalculator.use {
            generator = new BarCodeValueGenerator()
            generator.generate("BOB", true) == "*BOBA*"
        }
    }

    @Test
    void generate_with_check_sum_false() {
        mockCheckSumCalculator.use {
            generator = new BarCodeValueGenerator()
            generator.generate("BOB", false) == "*BOBA*"
        }
    }

}
