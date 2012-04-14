package org.burgers.pdf.processing;


import java.awt.geom.Rectangle2D
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFTextStripper
import org.apache.pdfbox.util.PDFTextStripperByArea
import org.junit.After
import org.junit.Before
import org.junit.Test

class SimplePdfCreatorTest {
    File file

    @Before
    void setUp() {
        file = File.createTempFile("test", ".pdf")
    }

    @Test
    void createFrom_using_pdf_box_to_extract_text() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        def doc = PDDocument.load(file.absolutePath)
        assert doc.documentCatalog.allPages.size() == 1
        assert new PDFTextStripper().getText(doc).contains("This is a test.")
    }

    @Test
    void createFrom_precise_region_extraction() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        def doc = PDDocument.load(file.absolutePath)
        Rectangle2D.Double d = new Rectangle2D.Double(35, 52, 120, 3)
        def stripper = new PDFTextStripperByArea()
        def pages = doc.getDocumentCatalog().allPages
        stripper.addRegion("myRegion", d)
        stripper.extractRegions(pages[0])
        assert stripper.getTextForRegion("myRegion").contains("This is a test.")
    }

    @Test
    void createFrom_big_region_extraction() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        def doc = PDDocument.load(file.absolutePath)
        Rectangle2D.Double d = new Rectangle2D.Double(0, 0, 120, 100)
        def stripper = new PDFTextStripperByArea()
        def pages = doc.getDocumentCatalog().allPages
        stripper.addRegion("myRegion", d)
        stripper.extractRegions(pages[0])
        assert stripper.getTextForRegion("myRegion").contains("This is a test.")
    }

    @After
    void tearDown() {
        file.delete()
    }
}
