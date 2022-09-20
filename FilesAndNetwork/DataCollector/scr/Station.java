import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Station {

    private String stationName;
    private String lineName;
    private String stationOpenDate;
    private String stationDepth;
    private final List<Connection> connections = new ArrayList<>();

    public Station(String name, String line) {
        this.stationName = name;
        this.lineName = line;
        this.stationOpenDate = "";
        this.stationDepth = "";
    }

    @Override
    public String toString() {
        StringBuilder outLine = new StringBuilder("Название: " + stationName +"\n");
        outLine.append("Линия: ").append(lineName).append("\n");
        outLine.append("Дата открытия: ").append(stationOpenDate).append("\n");
        outLine.append("Глубина залегания: ").append(stationDepth).append("\n");
        for (Connection c : connections) {
            outLine.append("-> ").append(c.getStationName());
            outLine.append(" (").append(c.getLineNumber()).append(")\n");
        }
        return outLine.toString();
    }

    public void addConnection (Connection connection) {
        connections.add(connection);
    }

    public int getConnectionCount () {
        return connections.size();
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationOpenDate() {
        return stationOpenDate;
    }

    public String getStationDepth() {
        return stationDepth;
    }

    public String getLineName() {
        return lineName;
    }

    public static void setDepthInStationList (String name, String depth, List<Station> stations) {
        List<Station> onceNamed = new ArrayList<>();
        // получаем коллекцию одноименных станций
        for (Station s : stations)
            if (s.getStationName().contains(name))
                onceNamed.add(s);
        // прописываем глубину для каждой станции в коллекции !!! но ....
        // в реальности одноименные станции - разные станции с разной глубиной
        for (Station s : onceNamed)
                s.setStationDepth(depth);
    }

    public static void setOpenDateInStationList (String name, String date, List<Station> stations) {
        List<Station> onceNamed = new ArrayList<>();
        // получаем коллекцию одноименных станций
        for (Station s : stations)
            if (s.getStationName().contains(name))
                onceNamed.add(s);
        // прописываем глубину для каждой станции в коллекции !!! но ....
        // в реальности одноименные станции - разные станции с разной глубиной
        for (Station s : onceNamed)
                s.setStationOpenDate(date);
    }

    public static void writeStationCollectionJSON(List<Station> stations, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(stations, writer);
        } catch (IOException e) {
            System.err.println("Ошибка записи json файла объекта Line.");
        }
        System.out.println("JSON файл " + fileName + " создан");
    }

    public static List<Station> readStationsCollectionJSON (String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Station> jasonStations = new ArrayList<>();

        try (Reader reader = new FileReader(filename)) {
            jasonStations = gson.fromJson(reader, new TypeToken<List<Station>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Ошибка чтения json файла объекта Line.");
        }
        return jasonStations;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationOpenDate(String stationOpenDate) {
        this.stationOpenDate = stationOpenDate;
    }

    public void setStationDepth(String stationDepth) {
        this.stationDepth = stationDepth;
    }

    public void setLineName(String lineNumber) {
        this.lineName = lineNumber;
    }
}

