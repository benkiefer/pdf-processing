package org.burgers.pdf.processing;

import org.junit.Test
import de.oio.jpdfunit.DocumentTestCase
import de.oio.jpdfunit.document.util.DocumentDataSource
import de.oio.jpdfunit.document.util.PdfDataSource
import de.oio.jpdfunit.DocumentTester
import de.oio.jpdfunit.document.util.TextSearchType
import org.junit.Before
import org.junit.After;

class SimplePdfCreatorTest {
    DocumentTester tester
    File file

    @Before
    void setUp(){
        file = File.createTempFile("test", ".pdf")
    }

    @Test
    void test_createFrom() {
        new SimplePdfCreator().createFrom(file.absolutePath)
        tester = new DocumentTester(file.absolutePath)
        tester.assertContentContainsText("This is a test.", TextSearchType.CONTAINS)
        tester.assertPageCountEquals(1)
    }

    @After
    void tearDown(){
        file.delete()
    }
}
