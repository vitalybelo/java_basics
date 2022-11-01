package main;

import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {

        System.out.println(Math.random() + "<br/>");
        return (new Date()).toInstant().toString();

    }

}
