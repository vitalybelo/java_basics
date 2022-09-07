import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        findPlanesLeavingInTheNextTwoHours(Airport.getInstance())
                .forEach(System.out::println);

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.
        List<Flight> flights = new ArrayList<>();
        for (Terminal value : airport.getTerminals())
                flights.addAll(value.getFlights());

        Date dateNow = Date.from(Instant.now());
        Date dateP2h = Date.from(Instant.now().plus(2, ChronoUnit.HOURS));
        return
                flights.stream()
                        .filter( f -> f.getType() == Flight.Type.DEPARTURE)
                        .filter( f -> f.getDate().after(dateNow))
                        .filter( f -> f.getDate().before(dateP2h))
                .collect(Collectors.toList());
    }


//    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
//        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.
//
//        return airport.getTerminals().stream().flatMap(t -> t.getFlights().stream())
//                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
//                .filter(f -> {
//                        LocalDateTime nowTime = LocalDateTime.now();
//                        LocalDateTime flyTime =
//                            LocalDateTime.ofInstant(f.getDate().toInstant(), ZoneId.systemDefault());
//                        return flyTime.isAfter(nowTime) && flyTime.isBefore(nowTime.plusHours(2));
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
//                flights.stream()
//                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
//                .filter(f -> {
//                    LocalDateTime nowTime = LocalDateTime.now();
//                    LocalDateTime flyTime =
//                            LocalDateTime.ofInstant(f.getDate().toInstant(), ZoneId.systemDefault());
//                    return flyTime.isAfter(nowTime) && flyTime.isBefore(nowTime.plusHours(2));
//                }).collect(Collectors.toList());
//    }


}
