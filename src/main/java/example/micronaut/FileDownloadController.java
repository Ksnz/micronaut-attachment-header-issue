package example.micronaut;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.http.server.types.files.StreamedFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller("/download")
public class FileDownloadController {

    public static final String FILENAME = "汉字<&>(?).ЙЦУКЕН.obj";

    @Get(value = "/default", produces = {MediaType.MULTIPART_FORM_DATA})
    public StreamedFile downloadDefault() {
        return new StreamedFile(FileDownloadController.class.getResourceAsStream(FILENAME), MediaType.forFilename(FILENAME))
            .attach(FILENAME);
    }

    @Get(value = "/default/encoded", produces = {MediaType.MULTIPART_FORM_DATA})
    public StreamedFile downloadDefaultButUrlEncoded() {
        return new StreamedFile(FileDownloadController.class.getResourceAsStream(FILENAME), MediaType.forFilename(FILENAME))
            .attach(URLEncoder.encode(FILENAME, StandardCharsets.UTF_8));
    }

    @Get(value = "/fixed", produces = {MediaType.MULTIPART_FORM_DATA})
    public FixedStreamedFile downloadFixed() {
        return new FixedStreamedFile(FileDownloadController.class.getResourceAsStream(FILENAME), MediaType.forFilename(FILENAME))
            .attach(FILENAME);
    }
}
