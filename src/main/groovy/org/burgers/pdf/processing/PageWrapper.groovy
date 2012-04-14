package org.burgers.pdf.processing

import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.font.PDFont
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream
import org.apache.pdfbox.pdmodel.PDDocument

class PageWrapper {
    PDPage page

    PageWrapper(PDPage page){
        this.page = page
    }

    List<String> getFontNames(){
        page.getResources().getFonts().values().collect {
            it.baseFont
        }
    }

    void writeText(PDDocument document, PDFont font, Float fontSize, Float x, Float y, String value){
        PDPageContentStream contentStream = new PDPageContentStream(document, page, true, false)

        contentStream.beginText()
        contentStream.setFont(font, fontSize)
        contentStream.moveTextPositionByAmount(x, y)
        contentStream.drawString(value)
        contentStream.endText()
        contentStream.close()
    }

}
