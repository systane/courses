package hello.world.cli.maven.controller;

import io.micronaut.http.annotation.*;

@Controller("/helloCli")
public class HelloCliController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "aaaaaaaaaaaaaaaaaaaaa";
    }
}