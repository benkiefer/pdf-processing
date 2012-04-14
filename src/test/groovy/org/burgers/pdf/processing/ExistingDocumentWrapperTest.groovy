package org.burgers.pdf.processing

import org.junit.Test
import org.junit.Before
import org.junit.After

import static org.burgers.pdf.processing.PdfContentAssertion.*
import org.apache.pdfbox.util.PDFStreamEngine
import org.apache.pdfbox.pdmodel.PDDocument

public class ExistingDocumentWrapperTest {
    ExistingDocumentWrapper wrapper
    File file

    @Before
    void setUp(){
        file = File.createTempFile("test", ".pdf")
        new SimplePdfCreator().createFrom(file.path)
        wrapper = new ExistingDocumentWrapper(file)
    }

    @Test
    void getPage(){
        assertPageContains(wrapper.getPage(0).page, "This is a test")
    }

    @Test
    void getNumberOfPages(){
        assert wrapper.numberOfPages() == 1
    }

    @Test
    void loadFont(){
        File fileName = new File(this.class.getResource("/FRE3OF9X.TTF").toURI())
        assert wrapper.loadFont(fileName).baseFont == "Free3of9Extended"
    }

    @After
    void tearDown(){
        wrapper.document.close()
        file.delete()
    }



}
