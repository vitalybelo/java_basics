import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        findPlanesLeavingInTheNextTwoHours(Airport.getInstance())
                .forEach(System.out::println);

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.

        return airport.getTerminals().stream().flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
                .filter(f -> {
                        LocalDateTime nowTime = LocalDateTime.now();
                        LocalDateTime flyTime =
                            LocalDateTime.ofInstant(f.getDate().toInstant(), ZoneId.systemDefault());
                        return flyTime.isAfter(nowTime) && flyTime.isBefore(nowTime.plusHours(2));
                }).collect(Collectors.toList());
    }

//    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
//        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.
//        List<Terminal> terminals = airport.getTerminals();
//        List<Flight> flights = new ArrayList<>();
//
//        for (Terminal value : terminals)
//            flights.addAll(value.getFlights());
//
//        return
//                flights.stream()
//                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
//                .filter(f -> {
//                    LocalDateTime nowTime = LocalDateTime.now();
//                    LocalDateTime flyTime =
//                            LocalDateTime.ofInstant(f.getDate().toInstant(), ZoneId.systemDefault());
//                    return flyTime.isAfter(nowTime) && flyTime.isBefore(nowTime.plusHours(2));
//                }).collect(Collectors.toList());
//    }

//    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
//        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.
//        List<Terminal> terminals = airport.getTerminals();
//        List<Flight> flights = new ArrayList<>();
//
//        for (Terminal value : terminals)
//            flights.addAll(value.getFlights());
//
//        return
//                flights.stream().filter((f) -> {
//                    long flightsTime = f.getDate().getTime();
//                    long currentTime = new Date().getTime();
//                    long twoHours = 1000 * 60 * 60 * 2;
//                    boolean timeMatched = false;
//                    long time = flightsTime - currentTime;
//                    if (time >= 0 && time <= twoHours)
//                        timeMatched = true;
//                    boolean typeMatched = (f.getType() == Flight.Type.DEPARTURE);
//                    return typeMatched && timeMatched;
//                }).collect(Collectors.toList());
//    }
    
}
