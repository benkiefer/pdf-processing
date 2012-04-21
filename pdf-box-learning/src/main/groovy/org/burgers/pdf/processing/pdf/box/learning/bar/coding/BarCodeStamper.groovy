package org.burgers.pdf.processing.pdf.box.learning.bar.coding

import org.apache.pdfbox.pdmodel.PDDocument

import org.burgers.pdf.processing.pdf.box.learning.ExistingDocumentWrapper
import org.apache.pdfbox.pdmodel.font.PDFont

class BarCodeStamper {
    BarCodeValueGenerator generator = new BarCodeValueGenerator()

    void stamp(File file, Integer zeroBasedPageNumber, String barCode) {
        barCode = generator.generate(barCode, true)

        def wrapper = new ExistingDocumentWrapper(file)

        PDFont font = wrapper.loadFont(getBarCodeFont())

        PDDocument document = wrapper.document

        wrapper.getPage(zeroBasedPageNumber).writeText(document, font, 30, 200, 700, barCode)

        document.save(file.absolutePath)
        document.close()
    }

    private File getBarCodeFont() {
        new File(this.class.getResource("/FRE3OF9X.TTF").toURI())
    }
}
