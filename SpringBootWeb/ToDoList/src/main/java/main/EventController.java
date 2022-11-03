package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Event;

import java.util.List;

@RestController
public class EventController {

    @GetMapping("/events/")
    public List<Event> list() {
        return StorageEvent.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Event event = StorageEvent.getEvent(id);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
        }
        return new ResponseEntity(event, HttpStatus.OK);
    }

    @PostMapping("/events/")
    public void add(Event event) {
        StorageEvent.addEvent(event);
    }

}
