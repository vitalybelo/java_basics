import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NumberGenerator implements Runnable{

    private static int fileIndex = 1;

    @Override
    public void run() {

        FileOutputStream writer = null;
        try {
            writer = new FileOutputStream("res/numbers_" + fileIndex + ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        String endl = "\n";
        StringBuilder sb = new StringBuilder();
        fileIndex++;

        try {
            for (int regionCode = 199; regionCode < 200; regionCode++) {
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
                writer.write(sb.toString().getBytes());
                sb.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
