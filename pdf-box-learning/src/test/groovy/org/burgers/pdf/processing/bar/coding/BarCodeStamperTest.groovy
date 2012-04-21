package org.burgers.pdf.processing.bar.coding

import org.junit.Before
import org.junit.Test
import org.junit.After
import org.burgers.pdf.processing.SimplePdfCreator
import static org.burgers.pdf.processing.PdfContentAssertion.*
import org.apache.pdfbox.pdmodel.PDDocument
import org.burgers.pdf.processing.ExistingDocumentWrapper
import org.burgers.pdf.processing.PageWrapper

public class BarCodeStamperTest {
    BarCodeStamper stamper
    File file

    @Before
    void setUp(){
        file = File.createTempFile("test", ".pdf")
        new SimplePdfCreator().createFrom(file.path)
        stamper = new BarCodeStamper()
    }

    @Test
    void stamp(){
        assertDocumentContains(PDDocument.load(file), "This is a test.")
        stamper.stamp(file, 0, "TEST-BARCODE")
        assertDocumentContains(PDDocument.load(file), ["TEST-BARCODE", "This is a test."])
        assert new ExistingDocumentWrapper(file).getPage(0).getFontNames().contains("Free3of9Extended")
    }

    @After
    void tearDown(){
        println file.absolutePath
        file.delete()
    }
}
