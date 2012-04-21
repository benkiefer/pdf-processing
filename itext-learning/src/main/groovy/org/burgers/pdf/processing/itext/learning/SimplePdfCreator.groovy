package org.burgers.pdf.processing.itext.learning

import com.lowagie.text.Document
import com.lowagie.text.Paragraph
import com.lowagie.text.pdf.PdfWriter

class SimplePdfCreator {
    void create(File file, String text){
        Document document = new Document()
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        PdfWriter.getInstance(document, baos)
        document.open()
        document.add(new Paragraph(text))
        document.close()
        FileOutputStream fos = new FileOutputStream(file)
        fos.write(baos.toByteArray())
        fos.close()
    }
}
