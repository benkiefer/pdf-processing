package org.burgers.pdf.processing.pdf.box.learning

import org.apache.pdfbox.pdmodel.PDDocument

import org.apache.pdfbox.pdmodel.font.PDFont
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont

class ExistingDocumentWrapper {
    File file
    PDDocument document

    ExistingDocumentWrapper(File file){
        this.file = file
        loadAnExistingDocument(file)
    }

    PDFont loadFont(ttfFontFilePath){
        PDTrueTypeFont.loadTTF(document, ttfFontFilePath)
    }

    PageWrapper getPage(Integer zeroBasedPageNumber){
        new PageWrapper(document.documentCatalog.allPages[zeroBasedPageNumber])
    }

    Integer numberOfPages(){
        document.documentCatalog.allPages.size()
    }

    private loadAnExistingDocument(File file){
        document = PDDocument.load(file)
    }

}
