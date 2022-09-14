import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class calculateDurationTest extends TestCase {

    private List<Station> route;
    Line line1 = new Line(1,"Red line");
    Line line2 = new Line(2,"Blue line");

    @Override
    protected void setUp() throws Exception {

        route = new ArrayList<>();
        route.add(new Station("Red Station 1",line1));

    }
    @Test
    public void testcalculateDuration_NO_stations () {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 0.0;
        assertEquals(expected,actual);
    }
    @Test
    public void testcalculateDuration_2_stations () {
        route.add(new Station("Red Station 2",line1));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 2.5;
        assertEquals(expected,actual);
    }
    @Test
    public void testcalculateDuration_3stations_1connect () {
        route.add(new Station("Red Station 2",line1));
        route.add(new Station("Red Station 3",line1));
        route.add(new Station("Red Station 4",line1));
        route.add(new Station("Blue Station 1",line2));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 11.0;
        assertEquals(expected,actual);
    }
    @Test
    public void testcalculateDuration_3stations_1connect_1stations () {
        route.add(new Station("Red Station 2",line1));
        route.add(new Station("Red Station 3",line1));
        route.add(new Station("Red Station 4",line1));
        route.add(new Station("Blue Station 1",line2));
        route.add(new Station("Blue Station 2",line2));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 13.5;
        assertEquals(expected,actual);
    }
    @Test
    public void testcalculateDuration_3stations_1connect_2stations () {
        route.add(new Station("Red Station 2",line1));
        route.add(new Station("Red Station 3",line1));
        route.add(new Station("Red Station 4",line1));
        route.add(new Station("Blue Station 1",line2));
        route.add(new Station("Blue Station 2",line2));
        route.add(new Station("Blue Station 3",line2));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 16.0;
        assertEquals(expected,actual);
    }
    @Test
    public void testcalculateDuration_3stations_1connect_3stations () {
        route.add(new Station("Red Station 2",line1));
        route.add(new Station("Red Station 3",line1));
        route.add(new Station("Red Station 4",line1));
        route.add(new Station("Blue Station 1",line2));
        route.add(new Station("Blue Station 2",line2));
        route.add(new Station("Blue Station 3",line2));
        route.add(new Station("Blue Station 4",line2));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 18.5;
        assertEquals(expected,actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
