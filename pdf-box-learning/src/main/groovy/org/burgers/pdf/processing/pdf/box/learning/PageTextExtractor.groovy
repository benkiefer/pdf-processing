package org.burgers.pdf.processing.pdf.box.learning

import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.util.PDFTextStripperByArea
import java.awt.geom.Rectangle2D

class PageTextExtractor {
    String extractFrom(PDPage page, int x, int y, int width, int height){
        def stripper = new PDFTextStripperByArea()
        stripper.addRegion("myRegion", new Rectangle2D.Double(x, y, width, height))
        stripper.extractRegions(page)
        stripper.getTextForRegion("myRegion")
    }

    String extractFrom(PDPage page){
        def stripper = new PDFTextStripperByArea()
        stripper.addRegion("myRegion", new Rectangle2D.Double(0, 0, 9999, 9999))
        stripper.extractRegions(page)
        stripper.getTextForRegion("myRegion")
    }
}
