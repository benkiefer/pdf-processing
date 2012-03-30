package org.burgers.pdf.processing

import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.Paragraph
import com.lowagie.text.Anchor
import com.lowagie.text.FontFactory
import com.lowagie.text.Font
import java.awt.Color
import com.lowagie.text.Document

class SimplePdfCreator {
    void createFrom(String path) {
        Document d = new Document()
        try {
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(path))
            d.open()
            Paragraph p = new Paragraph("This is a test.")
            d.add(p)
            d.close()
        } catch (Exception e) {
            e.printStackTrace()
        }

    }
}
