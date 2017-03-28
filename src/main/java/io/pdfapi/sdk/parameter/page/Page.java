package io.pdfapi.sdk.parameter.page;

import io.pdfapi.sdk.exception.InvalidArgumentException;
import io.pdfapi.sdk.parameter.Type;
import io.pdfapi.sdk.util.ObjectUtil;

import java.net.URL;

/**
 * Created by tobre on 19/01/2017.
 */
public class Page {

    protected Type type = Type.page;
    protected String html;
    protected URL url;

    Page() {
    }

    public Page(URL url) {
        if (ObjectUtil.isEmpty(url)) throw new InvalidArgumentException("URL cannot be empty");

        this.url = url;
    }

    public Page(String html) {
        if (ObjectUtil.isEmpty(html)) throw new InvalidArgumentException("Html cannot be empty");

        this.html = html;
    }

    public Type getType() {
        return type;
    }

    public String getHtml() {
        return html;
    }

    public URL getUrl() {
        return url;
    }

}
