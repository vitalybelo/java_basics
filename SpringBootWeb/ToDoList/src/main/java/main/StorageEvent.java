package main;

import main.repository.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorageEvent {

    private static int eventId = 1;
    private static final HashMap<Integer, Event> events = new HashMap<>();

    public static List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    public static Event getEvent(int id) {
        if (events.containsKey(id)) {
            return events.get(id);
        }
        return null;
    }

    public static int addEvent(Event event) {
        event.setId(eventId);
        events.put(eventId, event);
        return eventId++;
    }

    public static void deleteEvent(int id) {
        events.remove(id);
    }

    public static void deleteAllEvents() {
        eventId = 1;
        events.clear();
    }

    public static int editEvent (Event event) {
        int id = event.getId();
        if (events.containsKey(id)) {
            if (event.getText() == null) {
                event.setText(events.get(id).getText());
            }
            events.put(id, event);
            return id;
        }
        return -1;
    }
}
