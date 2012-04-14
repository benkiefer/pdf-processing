package org.burgers.pdf.processing

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.font.PDFont
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream

class SimplePdfCreator {
    void createFrom(String path) {
        PDDocument document = new PDDocument()

        PDFont font = PDType1Font.HELVETICA_BOLD

        PDPage page = new PDPage()
        PDPageContentStream contentStream = new PDPageContentStream(document, page)

        contentStream.beginText()
		contentStream.setFont( font, 12 )
		contentStream.moveTextPositionByAmount( 100, 700 )
		contentStream.drawString( "This is a test." )
		contentStream.endText()
        contentStream.close()



        document.addPage(page)

        document.save( path )
		document.close()
    }
}
