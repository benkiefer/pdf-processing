package org.burgers.pdf.processing

import com.lowagie.text.Document
import com.lowagie.text.Paragraph
import com.lowagie.text.pdf.PdfWriter

class SimplePdfCreator {
    void createFrom(String path) {
        Document d = new Document()
        try {
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(path))
            d.open()
            d.add(new Paragraph("This is a test."))
            d.close()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
