package org.burgers.pdf.processing.itext.learning

import org.junit.Test
import com.lowagie.text.pdf.PdfCopy
import com.lowagie.text.Document
import org.junit.Before
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.parser.PdfTextExtractor
import org.junit.After

import static junit.framework.Assert.fail

class PdfCopierTest {
    public static final TEXT = "My Text"
    File destination
    File toCopy
    PdfCopier copier

    @Before
    void setUp() {
        copier = new PdfCopier()
        destination = File.createTempFile("test", ".pdf")
        toCopy = File.createTempFile("toCopy", ".pdf")
    }

    @Test
    void copy_exact_number_of_pages() {
        new SimplePdfCreator().create(toCopy, TEXT)
        copier.copy(toCopy, destination, 1)
        verifyResult(destination, 1, TEXT)
    }

    @Test
    void copyPages_range_of_pages() {
        createDocumentWithPageCount(10, TEXT, toCopy)
        copier.copyPages(toCopy, destination, 5, 5)
        verifyResult(destination, 6, TEXT)
    }

    @Test
    void copyPages_read_too_many_pages() {
        createDocumentWithPageCount(10, TEXT, toCopy)
        copier.copyPages(toCopy, destination, 5, 5000)
        verifyResult(destination, 6, TEXT)
    }

    @Test
    void copyPages_read_not_enough_pages() {
        createDocumentWithPageCount(1, TEXT, toCopy)
        try{
            copier.copyPages(toCopy, destination, 5, 5000)
            fail("Should have failed")
        }catch (e){
            assert e.message == "Starting page 5 not found."
        }
    }

    @Test
    void copy_invalid_startPage_zero() {
        createDocumentWithPageCount(1, TEXT, toCopy)
        try{
            copier.copy(toCopy, destination, 0)
            fail("Should have failed")
        }catch (e){
            assert e.message == "Starting page 0 not found."
        }
    }

    @Test
    void copyPages_invalid_startPage_zero() {
        createDocumentWithPageCount(1, TEXT, toCopy)
        try{
            copier.copyPages(toCopy, destination, 0, 5)
            fail("Should have failed")
        }catch (e){
            assert e.message == "Starting page 0 not found."
        }
    }

    private verifyResult(File file, int numberOfPages, String text){
        def reader = new PdfReader(file.absolutePath)
        assert reader.numberOfPages == numberOfPages

        def extractor = new PdfTextExtractor(reader)
        (1..reader.numberOfPages).each {
            extractor.getTextFromPage(it) == text
        }
    }

    private createDocumentWithPageCount(int pageCount, String text, File destinationFile) {
        File file = File.createTempFile("copy", ".pdf")
        new SimplePdfCreator().create(file, text)

        def reader = new PdfReader(file.absolutePath)
        assert reader.numberOfPages == 1

        Document document = new Document()
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(destinationFile))
        document.open()

        pageCount.times {
            copy.addPage(copy.getImportedPage(reader, 1))
        }

        copy.freeReader(reader)
        document.close()

        verifyResult(destinationFile, pageCount, text)

        file.delete()
    }

    @After
    void tearDown() {
        toCopy.delete()
        destination.delete()
    }

}
