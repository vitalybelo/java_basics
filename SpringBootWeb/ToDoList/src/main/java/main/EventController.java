package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Event;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("задания с таким id нет");
        }
        return new ResponseEntity(event, HttpStatus.OK);
    }

    @PostMapping("/events/")
    public int add(Event event) {
        return StorageEvent.addEvent(event);
    }

    @DeleteMapping("/events/{id}")
    public void delete(@PathVariable int id) {
        StorageEvent.deleteEvent(id);
    }

    @DeleteMapping("/events/")
    public void deleteAll() {
        StorageEvent.deleteAllEvents();
    }

    @PutMapping("/events/")
    public int editEvent(Event event) {
        return StorageEvent.editEvent(event);
    }

}
