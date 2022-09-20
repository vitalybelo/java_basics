import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public  class Depth1 {

    private String name;
    private String depth;

    public String getName() {
        return name;
    }

    public String getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "name: " + name + " depth: " + depth;
    }

    public static void parseDepths1File (File file, List<Station> stations) {

        List<Depth1> depth1 = new ArrayList<>();
        Gson gson = new Gson();
        // получаем названия станций и глубину положения в коллекцию Depth1
        try (Reader reader = new FileReader(file.getAbsolutePath())) {
            depth1 = gson.fromJson(reader, new TypeToken<List<Depth1>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + file.getName());
        }
        // заносим данные о глубине в коллекцию
        for (Depth1 d : depth1)
            Station.setDepthInStationList(d.getName(), d.getDepth(), stations);
    }

}
