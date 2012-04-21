package org.burgers.pdf.processing.itext.learning

import org.junit.Test
import com.lowagie.text.pdf.PdfCopy
import com.lowagie.text.Document
import org.junit.Before
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.parser.PdfTextExtractor
import org.junit.After

class PdfCopyTest {
    File destination
    File toCopy

    @Before
    void setUp(){
        destination = File.createTempFile("test", ".pdf")
        toCopy = File.createTempFile("toCopy", ".pdf")
    }

    @Test
    void copy(){
        String text = "Copy Me."
        new SimplePdfCreator().create(toCopy, text)
        Document document = new Document()
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(destination))
        document.open()

        def reader = new PdfReader(toCopy.absolutePath)
        10.times{
            def page = copy.getImportedPage(reader, 1)
            copy.addPage page
        }

        copy.freeReader(reader)

        document.close()

        def destinationReader = new PdfReader(destination.absolutePath)
        assert destinationReader.numberOfPages == 10

        (1..destinationReader.numberOfPages).each {
            assert new PdfTextExtractor(destinationReader).getTextFromPage(it)
        }
    }

    @After
    void tearDown(){
        toCopy.delete()
        destination.delete()
    }

}
