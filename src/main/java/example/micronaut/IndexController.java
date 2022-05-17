package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/index")
public class IndexController {
    @View("home")
    @Get
    public HttpResponse<?> index() {
        return HttpResponse.ok();
    }
}
