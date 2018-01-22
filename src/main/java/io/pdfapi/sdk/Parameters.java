package io.pdfapi.sdk;

import io.pdfapi.sdk.parameter.Margin;
import io.pdfapi.sdk.parameter.Orientation;
import io.pdfapi.sdk.parameter.Size;
import io.pdfapi.sdk.parameter.page.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tobre on 09/11/2016.
 */
public class Parameters {

    private String contents;
    private String header;
    private String footer;
    private Size size;
    private Orientation orientation;
    private Margin margins;
    private List<Page> pages = new ArrayList<Page>();

    /**
     * Delay after page is ready for javascript execution in milliseconds. If your html has no javascript, you can omit
     * this parameter. If your page has javascript and is not displayed correctly, try increasing this parameter above 500.
     */
    private Integer javascriptDelay;

    Parameters() {

    }

    Parameters(Map<String, Object> parameters) {
        setParameters(parameters);
    }


    void setParameters(Map<String, Object> parameters) {
        if (parameters.containsKey("html")) {
            this.setContents((String) parameters.get("html"));
        }

        if (parameters.containsKey("header")) {
            this.setHeader((String) parameters.get("header"));
        }

        if (parameters.containsKey("footer")) {
            this.setFooter((String) parameters.get("footer"));
        }

        if (parameters.containsKey("size")) {
            this.setSize((Size) parameters.get("size"));
        }

        if (parameters.containsKey("orientation")) {
            this.setOrientation((Orientation) parameters.get("orientation"));
        }

        if (parameters.containsKey("margins")) {
            Margin margins = new Margin();

            @SuppressWarnings("unchecked")
            Map<String, Integer> map = (Map<String, Integer>) parameters.get("margins");

            margins.setParameters(map);
            this.setMargins(margins);
        }

        if (parameters.containsKey("pages")) {
            this.setPages((List<Page>) parameters.get("pages"));
        }

        if (parameters.containsKey("javascriptDelay")) {
            this.setJavascriptDelay(((Integer) parameters.get("javascriptDelay")));
        }
    }

    public Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("html", getContents());
        parameters.put("header", getHeader());
        parameters.put("footer", getFooter());
        parameters.put("size", getSize());
        parameters.put("orientation", getOrientation());
        parameters.put("margins", margins != null ? margins.getParameters() : null);
        parameters.put("pages", pages.size() > 0 ? pages : null);
        parameters.put("javascriptDelay", javascriptDelay);

        return parameters;
    }

    public String getContents() {
        return contents;
    }

    void setContents(String contents) {
        this.contents = contents;
    }

    public String getHeader() {
        return header;
    }

    void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    void setFooter(String footer) {
        this.footer = footer;
    }

    public Size getSize() {
        return size;
    }

    void setSize(Size size) {
        this.size = size;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Margin getMargins() {
        return margins;
    }

    void setMargins(Margin margins) {
        this.margins = margins;
    }

    void setPages(List<Page> pages) {
        this.pages = pages;
    }

    void addPage(Page page) {
        pages.add(page);
    }

    public List<Page> getPages() {
        return pages;
    }

    public Integer getJavascriptDelay() {
        return javascriptDelay;
    }

    public void setJavascriptDelay(Integer javascriptDelay) {
        this.javascriptDelay = javascriptDelay;
    }
}
