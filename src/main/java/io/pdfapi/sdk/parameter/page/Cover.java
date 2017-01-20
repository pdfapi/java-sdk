package io.pdfapi.sdk.parameter.page;

import io.pdfapi.sdk.parameter.Type;

import java.net.URL;

/**
 * Created by tobre on 19/01/2017.
 */
public class Cover extends Page {

    public Cover(URL url) {
        super(url);
        type = Type.cover;
    }

    public Cover(String html) {
        super(html);
        type = Type.cover;
    }

}
