package org.techfrog.foo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class HelloController {

    @GetMapping
    public String sayHelloWithDelay(@RequestParam(value = "delay", required = false) Integer delay) throws InterruptedException {
        delay = delay != null ? delay : 0;
        Thread.sleep(delay*1000);
        return String.format("Hello from Foo with delay %d s !", delay);
    }
}
