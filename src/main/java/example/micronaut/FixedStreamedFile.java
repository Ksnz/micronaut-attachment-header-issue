package example.micronaut;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.server.types.files.StreamedFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FixedStreamedFile extends StreamedFile {

    private static final String ATTACHMENT_HEADER = "attachment; filename=\"%s\"; filename*=UTF-8''%s";

    private String attachmentName;

    public FixedStreamedFile(InputStream inputStream, MediaType mediaType) {
        super(inputStream, mediaType);
    }


    /**
     * Sets the file to be downloaded as an attachment.
     * The name is set in the Content-Disposition header.
     *
     * @param attachmentName The attachment name.
     * @return The same StreamedFile instance
     */
    @Override
    public FixedStreamedFile attach(String attachmentName) {
        this.attachmentName = attachmentName;
        return this;
    }

    @Override
    public void process(MutableHttpResponse<?> response) {
        if (attachmentName != null) {
            response.header(HttpHeaders.CONTENT_DISPOSITION,
                String.format(ATTACHMENT_HEADER,
                    attachmentName,
                    URLEncoder.encode(attachmentName, StandardCharsets.UTF_8)));
        }
    }
}
