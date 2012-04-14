package org.burgers.pdf.processing.bar.coding

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream
import org.burgers.pdf.processing.ExistingDocumentWrapper
import org.apache.pdfbox.pdmodel.font.PDFont

class BarCodeStamper {
    BarCodeValueGenerator generator = new BarCodeValueGenerator()

    void stamp(File file, Integer zeroBasedPageNumber, String barCode) {
        barCode = generator.generate(barCode)

        def wrapper = new ExistingDocumentWrapper(file)

        PDFont font = wrapper.loadFont(getBarCodeFont())

        PDDocument document = wrapper.document

        PDPage page = wrapper.getPage(zeroBasedPageNumber).page

        PDPageContentStream contentStream = new PDPageContentStream(document, page, true, false)

        contentStream.beginText()
        contentStream.setFont(font, 30)
        contentStream.moveTextPositionByAmount(200, 700)
        contentStream.drawString(barCode)
        contentStream.endText()
        contentStream.close()

        document.save(file.absolutePath)
        document.close()
    }

    private File getBarCodeFont() {
        new File(this.class.getResource("/FRE3OF9X.TTF").toURI())
    }
}
