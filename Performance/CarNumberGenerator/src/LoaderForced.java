import java.io.PrintWriter;

/**
 * Замена операций со строками на StringBuilder ускоряет примерно в 30 раз
 * Замена PrintWriter сложно сказать, скорее нет, большую часть буферизации берет на себя OS
 * Оптимизация метода padNumber() - установить % ускорения сложно, слишком маленькая задача
 * Генерация номеров с 1-го до 199-го региона включительно занимает = 55-65 секунд (~ минуту)
 *
 */
public class LoaderForced {

    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();

        //FileOutputStream writer = new FileOutputStream("res/numbers.txt");
        PrintWriter writer = new PrintWriter("res/numbers.txt");

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        String endl = "\n";
        StringBuilder sb = new StringBuilder();

        for (int regionCode = 1; regionCode < 200; regionCode++)
        {
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            sb.append(firstLetter);
                            sb.append(padNumber(number, 3));
                            sb.append(secondLetter);
                            sb.append(thirdLetter);
                            sb.append(padNumber(regionCode, 2));
                            sb.append(endl);
                        }
                    }
                }
            }
            writer.write(sb.toString());
            sb.setLength(0);
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - begin) + " ms");
    }

    private static String padNumber(int value, int minLength)
    {
        StringBuilder sb = new StringBuilder(Integer.toString(value));
        int doCount = minLength - sb.length();

        for (int i = 0; i < doCount; i++) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

}
