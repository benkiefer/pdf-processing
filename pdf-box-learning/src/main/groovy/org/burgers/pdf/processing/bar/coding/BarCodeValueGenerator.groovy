package org.burgers.pdf.processing.bar.coding

class BarCodeValueGenerator {
    Mod43CheckSumCalculator calculator = new Mod43CheckSumCalculator()

    String generate(String barCode){
        generate(barCode, false)
    }

    String generate(String barCode, boolean appendCheckSum){
        if(appendCheckSum) barCode = barCode + calculator.calculate(barCode)
        surroundWithAsterisks(barCode)
    }

    private String surroundWithAsterisks(String barcode){
        "*$barcode*"
    }
}
