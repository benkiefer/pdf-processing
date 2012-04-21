package org.burgers.pdf.processing.itext.learning

import com.lowagie.text.Document
import com.lowagie.text.pdf.PdfCopy
import com.lowagie.text.pdf.PdfReader

class PdfCopier {
    void copyPages(File source, File destination, int startPage, int numberOfPagesToRead) {
        def sourceReader = new PdfReader(source.absolutePath)
        Document document = new Document()
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(destination))
        document.open()

        def lastPageToRead = calculateLastPageToRead(startPage, numberOfPagesToRead, sourceReader.numberOfPages)
        println lastPageToRead

        (startPage..lastPageToRead).each{
            def page = copy.getImportedPage(sourceReader, 1)
            copy.addPage page
        }

        copy.freeReader(sourceReader)
        document.close()
    }

    private int calculateLastPageToRead(int startPage, int numberOfPagesToRead, int pagesInDocument){
        if (startPage == 0 || startPage > pagesInDocument)  throw new RuntimeException("Starting page $startPage not found.")
        (startPage + numberOfPagesToRead - 1) > pagesInDocument ? (pagesInDocument) : (startPage + numberOfPagesToRead)
    }

    void copy(File source, File destination, int pageNumber){
        copyPages(source, destination, pageNumber, 0)
    }

}
