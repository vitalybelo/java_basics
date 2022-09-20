import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Depth3 {

    private String station_name;
    private String depth_meters;

    public String getName() {
        return station_name;
    }

    public String getDepth() {
        return depth_meters;
    }

    @Override
    public String toString() {
        return "name: " + station_name + " depth: " + depth_meters;

    }

    public static void parseDepths3File (File file, List<Station> stations) {

        List<Depth3> depth3 = new ArrayList<>();
        Gson gson = new Gson();
        // получаем названия станций и глубину положения в коллекцию Depth1
        try (Reader reader = new FileReader(file.getAbsolutePath())) {
            depth3 = gson.fromJson(reader, new TypeToken<List<Depth3>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + file.getName());
        }
        // заносим данные о глубине в коллекцию
        for (Depth3 d : depth3)
            Station.setDepthInStationList(d.getName(), d.getDepth(), stations);
    }

}