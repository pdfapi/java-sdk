package io.pdfapi.sdk;

import io.pdfapi.sdk.parameter.Orientation;
import io.pdfapi.sdk.parameter.Size;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by tobre on 21/11/2016.
 */
public class PdfApiTest {

    private PdfApi pdfApi = new PdfApi("apiKey");

    @Test
    public void canCreateNewInstance() {
        assertEquals(PdfApi.class, pdfApi.getClass());
    }

    @Test
    public void canSetContent() {
        String contents = "contents";
        pdfApi.setContents(contents);

        assertEquals(contents, pdfApi.getParameters().get("html"));
    }

    @Test
    public void canSetHtml() {
        String contents = "contents";
        pdfApi.setHtml(contents);

        assertEquals(contents, pdfApi.getParameters().get("html"));
    }

    @Test
    public void canSetHeader() {
        String contents = "contents";
        pdfApi.setHeader(contents);

        assertEquals(contents, pdfApi.getParameters().get("header"));
    }

    @Test
    public void canSetFooter() {
        String contents = "contents";
        pdfApi.setFooter(contents);

        assertEquals(contents, pdfApi.getParameters().get("footer"));
    }

    @Test
    public void canSetOrientation() {
        Orientation orientation = Orientation.Landscape;
        pdfApi.setOrientation(orientation);

        assertEquals(orientation, pdfApi.getParameters().get("orientation"));
    }

    @Test
    public void canSetSize() {
        Size size = Size.A4;
        pdfApi.setSize(size);

        assertEquals(size, pdfApi.getParameters().get("size"));
    }


    @Test
    public void canSetParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();

        Map<String, Integer> margins = new HashMap<String, Integer>();
        margins.put("top", 10);
        margins.put("right", 11);
        margins.put("bottom", 12);
        margins.put("left", 13);
        parameters.put("margins", margins);

        parameters.put("header", "<html><body>Header</body></html>");
        parameters.put("footer", "<html><body>Footer</body></html>");
        parameters.put("html", "<html><body>Contents</body></html>");

        pdfApi.setParameters(parameters);

        assertEquals("<html><body>Header</body></html>", pdfApi.getParameters().get("header"));
        assertEquals("<html><body>Footer</body></html>", pdfApi.getParameters().get("footer"));
        assertEquals("<html><body>Contents</body></html>", pdfApi.getParameters().get("html"));

        assertEquals(10, ((Map) pdfApi.getParameters().get("margins")).get("top"));
        assertEquals(11, ((Map) pdfApi.getParameters().get("margins")).get("right"));
        assertEquals(12, ((Map) pdfApi.getParameters().get("margins")).get("bottom"));
        assertEquals(13, ((Map) pdfApi.getParameters().get("margins")).get("left"));

    }


}
