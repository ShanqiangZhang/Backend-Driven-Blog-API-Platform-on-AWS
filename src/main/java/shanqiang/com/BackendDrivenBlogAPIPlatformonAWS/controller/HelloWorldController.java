package shanqiang.com.BackendDrivenBlogAPIPlatformonAWS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    String HelloWorld() {
        return "hello world";
    }
}
