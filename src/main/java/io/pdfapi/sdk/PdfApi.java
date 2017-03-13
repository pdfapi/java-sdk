package io.pdfapi.sdk;

import io.pdfapi.sdk.exception.PdfApiException;
import io.pdfapi.sdk.parameter.Margin;
import io.pdfapi.sdk.parameter.Orientation;
import io.pdfapi.sdk.parameter.Size;
import io.pdfapi.sdk.parameter.page.Cover;
import io.pdfapi.sdk.parameter.page.Page;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

/**
 * Created by tobre on 09/11/2016.
 */
public class PdfApi {

    public static String VERSION = "1.1.1-SNAPSHOT";

    private String apiKey;
    private Parameters parameters = new Parameters();

    public PdfApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setContents(String contents) {
        parameters.setContents(contents);
    }

    public void setHtml(String html) {
        parameters.setContents(html);
    }

    public void setHeader(String header) {
        parameters.setHeader(header);
    }

    public void setFooter(String footer) {
        parameters.setFooter(footer);
    }

    public void setSize(Size size) {
        parameters.setSize(size);
    }

    public void setOrientation(Orientation orientation) {
        parameters.setOrientation(orientation);
    }

    public void setMargins(Margin margins) {
        parameters.setMargins(margins);
    }

    public void setPages(List<Page> pages) {
        parameters.setPages(pages);
    }

    public void addPage(Page page) {
        parameters.addPage(page);
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters.setParameters(parameters);
    }

    public Map<String, Object> getParameters() {
        return parameters.getParameters();
    }

    public InputStream generate() {

        return convert().getContents();
    }

    public void save(String location) {
        try {
            Files.copy(generate(), new File(location).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new PdfApiException(e);
        }
    }

    private PdfApiResponse convert() {
        PdfApiRequest request = new PdfApiRequest();
        request.setMethod("POST");
        request.setEndpoint("/pdf");
        request.setBody(parameters.getParameters());

        PdfApiClient client = new PdfApiClient(apiKey);

        return client.send(request);
    }
}
