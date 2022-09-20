import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Line {

    private String lineName;
    private String lineNumber;
    private String lineColor;
    private final List<Station> stations = new ArrayList<>();

    public Line(String name, String number, String color) {
        this.lineName = name;
        this.lineNumber = number;
        this.lineColor = color;
    }

    public Line(String name, String number) {
        this(name, number,"");
    }

    public void addStation (Station station) {
        stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    public Station getStationByName (String name) {
        for (Station s : stations)
            if (s.getStationName().contains(name)) return s;
        return null;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder(lineName + " (номер: " + lineNumber + ")\n");
        line.append("количество станций: ").append(stations.size()).append("\n");
        int i = 1;
        for (Station station : stations) {
            line.append(i++).append(". ").append(station.getStationName());
            station.getConnections().forEach
                    (c -> line.append("\n\t -> ").append(c.getStationName())
                            .append(" (").append(c.getLineNumber()).append(")"));
            line.append("\n");
        }
        return line.toString();
    }

    public static List<Line> createLinesCollection(String fileName) {
        List<Line> metroLines = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(fileName).get();
            System.out.println("Парсинг с сайта: " + doc.title() + " (" + fileName + ")");

            Elements lines = doc.getElementsByClass("js-metro-line");
            Elements stationsOnLine = doc.select("div.js-metro-stations");
            System.out.println("Обнаружено линий метро: " + lines.size() + "\n");

            for (int i = 0; i < lines.size(); i++) {

                // Парсинг линии метро - название и номер
                String lineName = lines.get(i).text();
                String lineNumber = lines.get(i).attr("data-line");
                metroLines.add(new Line(lineName, lineNumber));

                // Парсим станции входящие в состав данной линии метро
                Elements stationNames = stationsOnLine.get(i).getElementsByClass("single-station");
                for (Element s : stationNames) {
                    // из каждого tag <span class="name" ...> получаем название
                    // станции и добавляем в список для текущей линии метро = get(i)
                    String stationName = s.select("span.name").text();
                    Station station = new Station(stationName, lineName);

                    // определяем есть ли для этой станции переходы
                    Elements connections = s.getElementsByClass("t-icon-metroln");
                    for (Element c : connections) {
                        // добавляем переходы к переменной класса Station
                        // String line = c.attr("class").replaceAll("[^D,A,0-9+]","");
                        String line = c.attr("class").replace("t-icon-metroln ln-","");
                        String name = c.attr("title");
                        Connection connection = new Connection(name,line);
                        station.addConnection(connection);
                    }
                    metroLines.get(i).addStation(station);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metroLines;
    }

    public static void writeLinesCollectionJSON(List<Line> lines, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(lines, writer);
        } catch (IOException e) {
            System.err.println("Ошибка записи json файла объекта Line.");
        }
        System.out.println("JSON файл " + fileName + " создан");
    }

    public static List<Line> readLinesCollectionJSON (String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Line> jasonLines = new ArrayList<>();

        try (Reader reader = new FileReader(filename)) {
            jasonLines = gson.fromJson(reader, new TypeToken<List<Line>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Ошибка чтения json файла объекта Line.");
        }
        return jasonLines;
    }


    public String getLineName() {
        return lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

}
