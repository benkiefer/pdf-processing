package org.burgers.pdf.processing

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage

class DocumentWrapper {
    File file
    PDDocument document

    DocumentWrapper(File file){
        this.file = file
        try {
            loadAnExistingDocument(file)
        } catch (e){
            e.printStackTrace()
        }
    }

    PDPage getPage(Integer zeroBasedPageNumber){
        document.documentCatalog.allPages[zeroBasedPageNumber]
    }

    private loadAnExistingDocument(File file){
        document = PDDocument.load(file)
    }

}
