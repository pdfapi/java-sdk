package io.pdfapi.sdk;

import io.pdfapi.sdk.exception.InvalidArgumentException;
import io.pdfapi.sdk.parameter.Orientation;
import io.pdfapi.sdk.parameter.Size;
import io.pdfapi.sdk.parameter.Type;
import io.pdfapi.sdk.parameter.page.Cover;
import io.pdfapi.sdk.parameter.page.Page;
import io.pdfapi.sdk.parameter.page.TableOfContents;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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

    @Test(expected = InvalidArgumentException.class)
    public void cannotCreateNewInstanceApiKeyNull() throws Exception {
        new PdfApi(null);
    }

    @Test(expected = InvalidArgumentException.class)
    public void cannotCreateNewInstanceApiKeyEmpty() throws Exception {
        new PdfApi("");
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
    public void canSetPageWithUrl() throws MalformedURLException {
        URL url = new URL("http://google.com");
        Page page = new Page(url);
        assertEquals(Type.page, page.getType());
        assertEquals(url, page.getUrl());
        assertEquals(null, page.getHtml());
    }

    @Test
    public void canSetPageWithHtml() throws MalformedURLException {
        String html = "HTML";
        Page page = new Page(html);
        assertEquals(Type.page, page.getType());
        assertEquals(null, page.getUrl());
        assertEquals(html, page.getHtml());
    }

    @Test
    public void canSetCover() {
        Cover cover = new Cover("HTML");
        assertEquals(Type.cover, cover.getType());
    }

    @Test
    public void canSetTableOfContents() {
        TableOfContents page = new TableOfContents("HTML");
        String headerText = "Custom title";
        page.setHeaderText(headerText);

        String stylesheet = "Stylesheet";
        page.setStyleSheet(stylesheet);

        assertEquals(Type.toc, page.getType());
        assertEquals(headerText, page.getHeaderText());
        assertEquals(stylesheet, page.getStyleSheet());
    }

    @Test
    public void canSetPages() throws MalformedURLException {
        List<Page> pages = new ArrayList<Page>();
        pages.add(new Page(new URL("http://google.com")));
        pages.add(new Cover("HTML"));
        pages.add(new TableOfContents("HTML"));
        pdfApi.setPages(pages);

        assertEquals(3, pages.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void canAddPages() throws MalformedURLException {
        Cover cover = new Cover("HTML");
        pdfApi.addPage(cover);

        assertEquals(cover, ((List<Page>) pdfApi.getParameters().get("pages")).get(0));
    }

    @Test
    public void canSetContentParameters() {
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

    @Test
    @SuppressWarnings("unchecked")
    public void canSetPageParameters() throws MalformedURLException {
        Map<String, Object> parameters = new HashMap<String, Object>();

        List<Page> pages = new ArrayList<Page>();
        pages.add(new Page(new URL("http://google.com")));
        pages.add(new Cover("HTML"));
        pages.add(new TableOfContents("HTML"));
        parameters.put("pages", pages);

        parameters.put("html", "HTML");

        pdfApi.setParameters(parameters);

        assertEquals("HTML", pdfApi.getParameters().get("html"));
        assertEquals(3, ((List<Page>) pdfApi.getParameters().get("pages")).size());
    }
}
