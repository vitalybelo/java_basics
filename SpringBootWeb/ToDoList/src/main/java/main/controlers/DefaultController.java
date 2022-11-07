package main.controlers;

import main.repository.Event;
import main.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping("/")
    public String index(Model model) {

        Iterable<Event> eventIter = eventRepository.findAll();
        List<Event> events = new ArrayList<>();
        for (Event event : eventIter) {
            events.add(event);
        }

        model.addAttribute("events", events);
        model.addAttribute("eventsCount", events.size());

        return "index";
    }
}
