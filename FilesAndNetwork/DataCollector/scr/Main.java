import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;

/**
 *  Заранее приношу извинения, мной была изменена структура
 *  объектов станций, линий и переходов - карта метрополитена.
 *  структура реализованная в metroSPB - не использовалась !
 *
 *  В проекте реализовано:
 *  1. Парсинг вэб страницы https://skillbox-java.github.io/
 *     и создание карты метро по модифицированной иерархии:
 *     Линия метро :: название, цвет, список станций
 *     Станция метро :: название, линия, инфо, список переходов
 *  2. Добавление справочной информации по каждой станции из
 *     файлов json и csv с предварительным поиском файлов с дереве
 *     каталогов, и последовательным добавлением информации из файлов
 *     с учетом того, что файлы имеют разный формат. Так как ограничений
 *     на использование ресурсов не было, а курс вообще скудный, мной
 *     были предварительно изучены и использованы библиотеки:
 *     - для парсинга json файлов использовалась библиотека Gson
 *     - для парсинга csv файлов использовалась библиотека OpenCsv
 *  3. Запись на диск файла json содержащего карту метрополитена
 *     с линиями -> станциями входящими в состав линии -> переходами для
 *     станций (если такие есть) и всей дополнительной информацией.
 *     Запись на диск файла json с полной коллекцией станций метро.
 *  4. Реализованы методы чтения этих файлов с созданием готовых
 *     к использованию коллекций объектов.
 *
 */
public class Main {

    public static void main(String[] args) {

        String dataFiles = "DataCollector/data/data/";                  // путь для поиска файлов
        String jsonMetro = "DataCollector/json/msk_metro_map.json";     // путь файла карты метро
        String jsonStations = "DataCollector/json/msk_stations.json";   // путь файла станций
        String parsingURL = "https://skillbox-java.github.io/";         // html страница парсинга

        // Парсим вэб страницу и получаем коллекцию: линии, станции, переходы
        List<Line> lines = Line.createLinesCollection(parsingURL).stream().toList();
        // собираем отдельно коллекцию станции из общей коллекции линий
        List<Station> stations = new ArrayList<>();
        for (Line line : lines)
            stations.addAll(line.getStations());

        // Добавляем информацию из файлов JSON
        parseJsonDataFiles(dataFiles, stations);
        // Добавляем информацию из файлов CSV
        parseSheetDataFiles(dataFiles, stations);

        // Запись карты метрополитена Москвы и карты станций в json (Lib GSON)
        Line.writeLinesCollectionJSON(lines, jsonMetro);
        Station.writeStationCollectionJSON(stations, jsonStations);
        // Чтение карты метрополитена Москвы и карты станций из json (Lib GSON)
        List<Line> jMetro = Line.readLinesCollectionJSON(jsonMetro).stream().toList();
        List<Station> jStations = Station.readStationsCollectionJSON (jsonStations).stream().toList();

        System.out.println();
        jMetro.forEach(System.out::println);

//        System.out.println();
//        jStations.forEach(System.out::println);

    }

    static void scanFoldersTree(File folder, Collection<File> all, String pattern) {

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile())
                    if (file.getName().contains(pattern)) all.add(file);
                scanFoldersTree(file, all, pattern);
            }
        }
    }

    static void parseJsonDataFiles(String folder, List<Station> stations) {

        Collection<File> jsnFiles = new ArrayList<>();
        scanFoldersTree(new File(folder), jsnFiles, ".json");

        for (File file : jsnFiles) {
            if (file.getName().contains("dates")) {
                Dates.parseDateFile(file, stations);
            } else if (file.getName().contains("depths-1")) {
                Depth1.parseDepths1File(file, stations);
            } else if (file.getName().contains("depths-3")) {
                Depth3.parseDepths3File(file, stations);
            }
        }
    }

    static void parseSheetDataFiles (String folder, List<Station> stations) {

        Collection<File> jsnFiles = new ArrayList<>();
        scanFoldersTree(new File(folder), jsnFiles, ".csv");

        for (File file : jsnFiles) {
            try (CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()))) {
                String[] line;
                if (file.getName().contains("depths")) {
                    while ((line = reader.readNext()) != null)
                        Station.setDepthInStationList(line[0], line[1], stations);
                }
                if (file.getName().contains("dates")) {
                    while ((line = reader.readNext()) != null)
                        Station.setOpenDateInStationList(line[0], line[1], stations);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

