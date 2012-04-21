package org.burgers.pdf.processing.pdf.box.learning;


import java.awt.geom.Rectangle2D
import org.apache.pdfbox.pdmodel.PDDocument

import org.junit.After
import org.junit.Before
import org.junit.Test
import static PdfContentAssertion.*

class SimplePdfCreatorTest {
    File file
    PDDocument doc

    @Before
    void setUp() {
        file = File.createTempFile("test", ".pdf")
    }

    @Test
    void createFrom_using_pdf_box_to_extract_text() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        doc = PDDocument.load(file.absolutePath)
        assertDocumentContains(doc, "This is a test.")
        assertNumberOfPages(doc, 1)
    }

    @Test
    void createFrom_precise_region_extraction() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        doc = PDDocument.load(file.absolutePath)
        def pages = doc.getDocumentCatalog().allPages
        assertPageContainsAt(pages[0], "This is a test.", 100, 90, 80, 5)
    }

    @Test
    void createFrom_big_region_extraction() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        doc = PDDocument.load(file.absolutePath)
        def pages = doc.getDocumentCatalog().allPages
        assertPageContainsAt(pages[0], "This is a test.", 0, 0, 500, 500)
    }

    @After
    void tearDown() {
        if (doc != null) doc.close()
        file.delete()
    }
}
