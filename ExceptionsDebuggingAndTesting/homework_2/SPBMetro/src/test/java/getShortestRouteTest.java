import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getShortestRouteTest extends TestCase {

    StationIndex stationIndex;

    Line line1 = new Line(1,"TOP");
    Line line2 = new Line(2,"RIGHT");
    Line line3 = new Line(3,"DOWN");
    Line line4 = new Line(4,"LEFT");

    Station station_01 = new Station("A1",line1);  //   A1 B C D1
    Station station_02 = new Station("B1",line1);  // 1A . . . . 1D пересадки по углам
    Station station_03 = new Station("C1",line1); // 2A .       . 2D
    Station station_04 = new Station("D1",line1); // 3A .       . 3D
    Station station_05 = new Station("1D",line2);  // 4A . . . . 4D
    Station station_06 = new Station("2D",line2);  //   A4 B C D4
    Station station_07 = new Station("3D",line2);
    Station station_08 = new Station("4D",line2);
    Station station_09 = new Station("D4",line3);
    Station station_10 = new Station("C4",line3);
    Station station_11 = new Station("B4",line3);
    Station station_12 = new Station("A4",line3);
    Station station_13 = new Station("4A",line4);
    Station station_14 = new Station("3A",line4);
    Station station_15 = new Station("2A",line4);
    Station station_16 = new Station("1A",line4);

    @Override
    protected void setUp() throws Exception {
    }

    @Test
    public void testGetRouteOnTheLine(){
        RouteCalculator calculator = getRouteCalculator();
        Station from = stationIndex.getStation("A1", 1);
        Station to = stationIndex.getStation("D1", 1);

        List<Station> actual = calculator.getShortestRoute(from, to);
        List<Station> expected =
                Arrays.asList(station_01, station_02, station_03, station_04);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testGetRouteWithOneConnection(){
        RouteCalculator calculator = getRouteCalculator();
        Station from = stationIndex.getStation("2D", 2);
        Station to = stationIndex.getStation("C4", 3);

        List<Station> actual = calculator.getShortestRoute(from, to);
        List<Station> expected =
                Arrays.asList(station_06, station_07, station_08, station_09, station_10);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testGetRouteWithTwoConnections(){
        RouteCalculator calculator = getRouteCalculator();
        Station from = stationIndex.getStation("3D", 2);
        Station to = stationIndex.getStation("3A", 4);

        List<Station> actual = calculator.getShortestRoute(from, to);
        List<Station> expected =
                Arrays.asList(station_07, station_08, station_09, station_10, station_11, station_12, station_13, station_14);
        Assert.assertEquals(expected,actual);
    }


    public  RouteCalculator getRouteCalculator() {

        createIndex();
        return new RouteCalculator(stationIndex);
    }

    private void createIndex() {
        stationIndex = new StationIndex();

        stationIndex.addStation(station_01);
        stationIndex.addStation(station_02);
        stationIndex.addStation(station_03);
        stationIndex.addStation(station_04);
        stationIndex.addStation(station_05);
        stationIndex.addStation(station_06);
        stationIndex.addStation(station_07);
        stationIndex.addStation(station_08);
        stationIndex.addStation(station_09);
        stationIndex.addStation(station_10);
        stationIndex.addStation(station_11);
        stationIndex.addStation(station_12);
        stationIndex.addStation(station_13);
        stationIndex.addStation(station_14);
        stationIndex.addStation(station_15);
        stationIndex.addStation(station_16);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addLine(line4);

        line1.addStation(station_01);
        line1.addStation(station_02);
        line1.addStation(station_03);
        line1.addStation(station_04);
        line2.addStation(station_05);
        line2.addStation(station_06);
        line2.addStation(station_07);
        line2.addStation(station_08);
        line3.addStation(station_09);
        line3.addStation(station_10);
        line3.addStation(station_11);
        line3.addStation(station_12);
        line4.addStation(station_13);
        line4.addStation(station_14);
        line4.addStation(station_15);
        line4.addStation(station_16);

        stationIndex.addConnection(new ArrayList<>
                (Arrays.asList(stationIndex.getStation("1A"), stationIndex.getStation("A1"))));

        stationIndex.addConnection(new ArrayList<>
                (Arrays.asList(stationIndex.getStation("D1"), stationIndex.getStation("1D"))));

        stationIndex.addConnection(new ArrayList<>
                (Arrays.asList(stationIndex.getStation("4D"), stationIndex.getStation("D4"))));

        stationIndex.addConnection(new ArrayList<>
                (Arrays.asList(stationIndex.getStation("A4"), stationIndex.getStation("4A"))));


    }

}


