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

    private static final String KEY_HTML = "html";
    private static final String KEY_HEADER = "header";
    private static final String KEY_FOOTER = "footer";
    private static final String KEY_SIZE = "size";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_ZOOM = "zoom";
    private static final String KEY_ORIENTATION = "orientation";
    private static final String KEY_MARGINS = "margins";
    private static final String KEY_PAGES = "pages";
    private static final String KEY_JAVASCRIPT_DELAY = "javascriptDelay";
    private static final String KEY_DISABLE_SMART_SHRINK = "disableSmartShrink";

    private String contents;
    private String header;
    private String footer;
    private Orientation orientation;
    private Margin margins;
    private List<Page> pages = new ArrayList<Page>();

    /**
     * Delay after page is ready for javascript execution in milliseconds. If your html has no javascript, you can omit
     * this parameter. If your page has javascript and is not displayed correctly, try increasing this parameter above 500.
     */
    private Integer javascriptDelay;

    private Boolean disableSmartShrink;

    private Size size;
    private Integer width;
    private Integer height;
    private Double zoom;

    Parameters() {

    }

    Parameters(Map<String, Object> parameters) {
        setParameters(parameters);
    }

    void setParameters(Map<String, Object> parameters) {
        if (parameters.containsKey(KEY_HTML)) {
            this.setContents((String) parameters.get(KEY_HTML));
        }

        if (parameters.containsKey(KEY_HEADER)) {
            this.setHeader((String) parameters.get(KEY_HEADER));
        }

        if (parameters.containsKey(KEY_FOOTER)) {
            this.setFooter((String) parameters.get(KEY_FOOTER));
        }

        if (parameters.containsKey(KEY_SIZE)) {
            this.setSize((Size) parameters.get(KEY_SIZE));
        }

        if (parameters.containsKey(KEY_WIDTH)) {
            this.setWidth((Integer) parameters.get(KEY_WIDTH));
        }

        if (parameters.containsKey(KEY_HEIGHT)) {
            this.setHeight((Integer) parameters.get(KEY_HEIGHT));
        }

        if (parameters.containsKey(KEY_ZOOM)) {
            this.setZoom((Double) parameters.get(KEY_ZOOM));
        }

        if (parameters.containsKey(KEY_ORIENTATION)) {
            this.setOrientation((Orientation) parameters.get(KEY_ORIENTATION));
        }

        if (parameters.containsKey(KEY_MARGINS)) {
            Margin margins = new Margin();

            @SuppressWarnings("unchecked")
            Map<String, Integer> map = (Map<String, Integer>) parameters.get(KEY_MARGINS);

            margins.setParameters(map);
            this.setMargins(margins);
        }

        if (parameters.containsKey(KEY_PAGES)) {
            this.setPages((List<Page>) parameters.get(KEY_PAGES));
        }

        if (parameters.containsKey(KEY_JAVASCRIPT_DELAY)) {
            this.setJavascriptDelay(((Integer) parameters.get(KEY_JAVASCRIPT_DELAY)));
        }

        if (parameters.containsKey(KEY_DISABLE_SMART_SHRINK)) {
            this.setDisableSmartShrink(((Boolean) parameters.get(KEY_DISABLE_SMART_SHRINK)));
        }
    }

    public Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(KEY_HTML, getContents());
        parameters.put(KEY_HEADER, getHeader());
        parameters.put(KEY_FOOTER, getFooter());
        parameters.put(KEY_SIZE, getSize());
        parameters.put(KEY_ORIENTATION, getOrientation());
        parameters.put(KEY_MARGINS, margins != null ? margins.getParameters() : null);
        parameters.put(KEY_PAGES, pages.size() > 0 ? pages : null);
        parameters.put(KEY_JAVASCRIPT_DELAY, javascriptDelay);
        parameters.put(KEY_DISABLE_SMART_SHRINK, disableSmartShrink);
        parameters.put(KEY_WIDTH, getWidth());
        parameters.put(KEY_HEIGHT, getHeight());
        parameters.put(KEY_ZOOM, getZoom());

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

    public Boolean getDisableSmartShrink() {
        return disableSmartShrink;
    }

    public void setDisableSmartShrink(Boolean disableSmartShrink) {
        this.disableSmartShrink = disableSmartShrink;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getZoom() {
        return zoom;
    }

    public void setZoom(Double zoom) {
        this.zoom = zoom;
    }
}
