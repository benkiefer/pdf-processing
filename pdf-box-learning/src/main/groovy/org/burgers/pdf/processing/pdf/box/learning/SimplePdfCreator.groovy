package org.burgers.pdf.processing.pdf.box.learning

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage

import org.apache.pdfbox.pdmodel.font.PDFont
import org.apache.pdfbox.pdmodel.font.PDType1Font

class SimplePdfCreator {
    void createFrom(String path) {
        PDDocument document = new PDDocument()
        PDFont font = PDType1Font.HELVETICA_BOLD
        PageWrapper pageWrapper = new PageWrapper(new PDPage())
        document.addPage(pageWrapper.page)
        pageWrapper.writeText(document, font, 12, 100, 700, "This is a test.")
        document.save(path)
        document.close()
    }
}
