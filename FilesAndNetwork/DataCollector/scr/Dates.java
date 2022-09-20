import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Dates {

    private String name;
    private String date;

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public static void parseDateFile (File file, List<Station> stations) {

        List<Dates> dates = new ArrayList<>();
        // получаем названия станций и даты их сооружения в коллекцию Dates
        Gson gson = new Gson();
        try (Reader reader = new FileReader(file.getAbsolutePath())) {
            dates = gson.fromJson(reader, new TypeToken<List<Dates>>() {
            }.getType());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + file.getName());
        }
        // заносим данные о датах в коллекцию
        for (Dates d : dates)
            Station.setOpenDateInStationList(d.getName(), d.getDate(), stations);

    }

}
