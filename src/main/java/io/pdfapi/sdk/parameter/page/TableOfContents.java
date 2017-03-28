package io.pdfapi.sdk.parameter.page;

import io.pdfapi.sdk.parameter.Type;

import java.net.URL;

/**
 * Created by tobre on 19/01/2017.
 */
public class TableOfContents extends Page {

    protected String headerText;
    protected String styleSheet;

    public TableOfContents() {
    }

    public TableOfContents(URL url) {
        super(url);
        type = Type.toc;
    }

    public TableOfContents(String html) {
        super(html);
        type = Type.toc;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getStyleSheet() {
        return styleSheet;
    }

    public void setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
    }
}
