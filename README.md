[![Build Status](https://travis-ci.org/pdfapi/java-sdk.svg?branch=master)](https://travis-ci.org/pdfapi/java-sdk)

# pdfapi.io SDK for Java

## Installation

pdfapi.io Java SDK can be installed with [Gradle](https://gradle.org) or [Maven](https://maven.apache.org). Add dependency for Gradle project:

```gradle
compile group: 'io.pdfapi', name: 'sdk', version: '1.1.1'
```


## Usage

Usage of pdfapi.io PHP SDK is very simple. The easyest way to get started is:

```java

import io.pdfapi.sdk.PdfApi;

import java.io.*;

String template = "<html>\n" +
        "    <body>\n" +
        "        <h1>pdfapi.io makes PDF generation so easy.</h1>\n" +
        "        <p>And it can do complicated stuff.</p>\n" +
        "    </body>\n" +
        "</html>";

PdfApi pdf = new PdfApi("YOUR_API_KEY");
pdf.setHtml(template);

InputStream rawPdf = pdf.generate();
```

Full example of the usage:
Note: Be aware that header and footer are separate HTML documents (with styles and everything) that are copied to each page.
```java

import io.pdfapi.sdk.PdfApi;
import io.pdfapi.sdk.parameter.Margin;
import io.pdfapi.sdk.parameter.Orientation;
import io.pdfapi.sdk.parameter.Size;

import java.io.*;

String template = "<html>\n" +
        "    <body>\n" +
        "        <h1>pdfapi.io makes PDF generation so easy.</h1>\n" +
        "        <p>And it can do complicated stuff.</p>\n" +
        "    </body>\n" +
        "</html>";

String header = "<html>\n" +
        "  <body>\n" +
        "      <p>pdfapi.io</p>\n" +
        "  </body>\n" +
        "</html>";

String footer = "<html>\n" +
        "  <body>\n" +
        "      <p>pdfapi.io</p>\n" +
        "  </body>\n" +
        "</html>";

PdfApi pdf = new PdfApi("YOUR_API_KEY");
pdf.setHtml(template);
pdf.setHeader(header);
pdf.setFooter(footer);
pdf.setSize(Size.A4);
pdf.setOrientation(Orientation.Landscape);
pdf.setMargins(new Margin(60, 60, 60, 60));

InputStream rawPdf = pdf.generate();

//or optionally you can save PDF directly to file
pdf.save("/path/to/file.pdf");

```

For getting API KEY you need to register account at https://pdfapi.io. Generating API KEY will take you 10 seconds. And it is free. Really.
