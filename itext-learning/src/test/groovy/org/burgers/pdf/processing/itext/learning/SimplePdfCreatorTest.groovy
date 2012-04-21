package org.burgers.pdf.processing.itext.learning

import org.junit.Before
import org.junit.Test
import com.lowagie.text.pdf.parser.PdfTextExtractor
import com.lowagie.text.pdf.PdfReader
import org.junit.After

class SimplePdfCreatorTest {
    SimplePdfCreator creator
    File file

    @Before
    void setUp(){
        file = File.createTempFile("test", ".pdf")
        creator = new SimplePdfCreator()
    }

    @Test
    void create(){
        String text = "This is a test."
        creator.create(file, text)
        PdfReader reader = new PdfReader(file.absolutePath)
        assert reader.numberOfPages == 1
        new PdfTextExtractor(reader).getTextFromPage(1) == text
    }

    @After
    void tearDown(){
        file.delete()
    }
}
