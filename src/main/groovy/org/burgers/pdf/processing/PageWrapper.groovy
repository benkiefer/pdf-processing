package org.burgers.pdf.processing

import org.apache.pdfbox.pdmodel.PDPage

class PageWrapper {
    PDPage page

    PageWrapper(PDPage page){
        this.page = page
    }

    List<String> getFontNames(){
        page.getResources().getFonts().values().collect {
            it.baseFont
        }
    }

}
