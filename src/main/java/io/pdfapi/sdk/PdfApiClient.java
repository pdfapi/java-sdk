package io.pdfapi.sdk;

import com.google.gson.Gson;
import io.pdfapi.sdk.exception.PdfApiClientException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by tobre on 09/11/2016.
 */
public class PdfApiClient {

    private static String BASE_URL = "https://pdfapi.io/api";
    private static String API_VERSION = "1";
    private static int DEFAULT_REQUEST_TIMOUT = 60;

    private CloseableHttpClient httpClientHandler = HttpClients.createDefault();

    private String apiKey;

    PdfApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    PdfApiResponse send(PdfApiRequest request) {
        HttpPost httpPost = new HttpPost(BASE_URL + "/v" + API_VERSION + request.getEndpoint());
        httpPost.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64((":" + apiKey).getBytes())));
        httpPost.addHeader("Content-Type", "application/json");

        httpPost.setEntity(new StringEntity(new Gson().toJson(request.getBody()), Charset.defaultCharset()));

        HttpResponse response;
        try {
            response = httpClientHandler.execute(httpPost);
        } catch (IOException e) {
            throw new PdfApiClientException(e);
        }

        return new PdfApiResponse(response);
    }
}
