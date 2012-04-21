package org.burgers.pdf.processing.bar.coding

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream
import org.burgers.pdf.processing.ExistingDocumentWrapper
import org.apache.pdfbox.pdmodel.font.PDFont
import org.burgers.pdf.processing.PageWrapper

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
