package io.pdfapi.sdk;

import io.pdfapi.sdk.exception.InvalidArgumentException;
import io.pdfapi.sdk.exception.PdfApiException;
import io.pdfapi.sdk.parameter.Margin;
import io.pdfapi.sdk.parameter.Orientation;
import io.pdfapi.sdk.parameter.Size;
import io.pdfapi.sdk.parameter.page.Page;
import io.pdfapi.sdk.util.ObjectUtil;

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

    public final static String VERSION = "2.2.0";
    private final static String BASE_URL = "https://pdfapi.io/api";

    private String apiKey;
    private String baseUrl;
    private Parameters parameters = new Parameters();

    public PdfApi(String apiKey) {
        this(apiKey, BASE_URL);
    }

    public PdfApi(String apiKey, String baseUrl) {
        if (ObjectUtil.isEmpty(apiKey)) throw new InvalidArgumentException("API key cannot be empty");

        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
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

    public void setJavascriptDelay(Integer delay) {
        parameters.setJavascriptDelay(delay);
    }

    public void setWidth(Integer width) {
        parameters.setWidth(width);
    }

    public void setHeight(Integer height) {
        parameters.setHeight(height);
    }

    public void setZoom(Double zoom) {
        parameters.setZoom(zoom);
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters.setParameters(parameters);
    }

    public Map<String, Object> getParameters() {
        return parameters.getParameters();
    }

    public InputStream generate() throws PdfApiException {
        try {
            return convert().getContents();
        } catch (IOException e) {
            throw new PdfApiException(e);
        }
    }

    public void save(String location) throws PdfApiException {
        try {
            Files.copy(generate(), new File(location).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new PdfApiException(e);
        }
    }

    private PdfApiResponse convert() throws PdfApiException {
        PdfApiRequest request = new PdfApiRequest();
        request.setMethod("POST");
        request.setEndpoint("/pdf");
        request.setBody(parameters.getParameters());

        PdfApiClient client = new PdfApiClient(apiKey, baseUrl);

        return client.send(request);
    }
}
