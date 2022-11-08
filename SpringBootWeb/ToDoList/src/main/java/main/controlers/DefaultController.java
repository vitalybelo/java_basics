package main.controlers;

import main.repository.Event;
import main.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private EventRepository eventRepository;

    @Value("${someParameter.value}")
    private String someParameter;

    @Value("${oneMoreParameter.value}")
    private Integer oneMoreParameter;

    @RequestMapping("/")
    public String index(Model model) {

        Iterable<Event> eventIter = eventRepository.findAll();
        List<Event> events = new ArrayList<>();
        for (Event event : eventIter) {
            events.add(event);
        }

        model.addAttribute("events", events);
        model.addAttribute("eventsCount", events.size());
        model.addAttribute("someParameter", someParameter);
        model.addAttribute("oneMoreParameter", oneMoreParameter);

        return "index";
    }
}
