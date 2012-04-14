package org.burgers.pdf.processing

import org.junit.Test
import org.junit.Before
import org.junit.After
import org.apache.pdfbox.util.PDFTextStripper
import static org.burgers.pdf.processing.PdfContentAssertion.*

public class DocumentWrapperTest {
    DocumentWrapper wrapper
    File file

    @Before
    void setUp(){
        file = File.createTempFile("test", ".pdf")
        new SimplePdfCreator().createFrom(file.path)
        wrapper = new DocumentWrapper(file)
    }

    @Test
    void getPage(){
        assertPageContains(wrapper.getPage(0), "This is a test")
    }

    @After
    void tearDown(){
        file.delete()
    }



}
