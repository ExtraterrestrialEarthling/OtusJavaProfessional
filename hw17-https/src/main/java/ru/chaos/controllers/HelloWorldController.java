package ru.chaos.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaos.services.HelloService;


@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    private final HelloService helloService;

    @Autowired
    public HelloWorldController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/admin/hello")
    public String sayHelloAdmin(){
        return helloService.sayHelloAdmin();
    }

    @GetMapping("/user/hello")
    public String sayHelloUser(){
        return helloService.sayHelloUser();
    }

    @GetMapping("/goodbye")
    public String sayGoodbye(){
        return helloService.sayGoodbye();
    }

}
