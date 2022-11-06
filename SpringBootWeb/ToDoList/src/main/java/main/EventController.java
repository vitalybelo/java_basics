package main;

import main.model.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    //@Autowired
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events/")
    public List<Event> list() {
        Iterable<Event> eventIter = eventRepository.findAll();
        List<Event> events = new ArrayList<>();

        for (Event event : eventIter)
            events.add(event);
        return events;
    }

    @GetMapping("/events/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent())
            return new ResponseEntity(optionalEvent.get(), HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("задания с таким id нет");
    }

    @PostMapping("/events/")
    public int add(Event event) {

        if (event.getText() == null) {
            event.setText(" ");
        }
        Event e = eventRepository.save(event);
        return e.getId();
    }

    @DeleteMapping("/events/{id}")
    public void delete(@PathVariable int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent())
            eventRepository.delete(optionalEvent.get());
    }

    @DeleteMapping("/events/")
    public void deleteAll() {
        eventRepository.deleteAll();
    }

    @DeleteMapping
    public void deleteAllFinished() {
        Iterable<Event> eventIter = eventRepository.findAll();
        for (Event event : eventIter) {
            if (event.isDone())
                eventRepository.delete(event);
        }
    }

    @PutMapping("/events/")
    public int edit(Event event) {
        Optional<Event> optionalEvent = eventRepository.findById(event.getId());

        if (optionalEvent.isPresent()) {
            if (event.getText() == null)
                event.setText(optionalEvent.get().getText());

            eventRepository.save(event);
            return event.getId();
        }
        return -1;
    }

}
