package org.burgers.pdf.processing.pdf.box.learning.bar.coding

class Mod43CheckSumCalculator {
    public static final CHARACTERS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/-%'

    String calculate(String barcode){
        int i = 0
        barcode.chars.each {
            def value = CHARACTERS.indexOf(it as int)
            if (value != -1) i = i + value
        }
        CHARACTERS.charAt(i % 43).toString()
    }
}
