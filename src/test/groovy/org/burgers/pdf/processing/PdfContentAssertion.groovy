package org.burgers.pdf.processing

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFTextStripper
import org.apache.pdfbox.pdmodel.PDPage

class PdfContentAssertion {
    static void assertDocumentContains(PDDocument document, String text){
        assert new PDFTextStripper().getText(document).contains(text)
        document.close()
    }

    static void assertNumberOfPages(PDDocument document, Integer size){
        assert document.documentCatalog.allPages.size() == size
        document.close()
    }

    static void assertPageContains(PDPage page, String text){
        assert new PageTextExtractor().extractFrom(page).contains(text)
    }

    static void assertPageContainsAt(PDPage page, String text, int x, int y, int width, int height){
        assert new PageTextExtractor().extractFrom(page, x, y, width, height).contains(text)
    }

}
