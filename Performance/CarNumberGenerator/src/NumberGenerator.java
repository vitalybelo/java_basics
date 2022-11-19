import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumberGenerator implements Runnable{

    private final String regionLine;
    private final PrintWriter writer;
    private final StringBuilder sb;
    private final char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public NumberGenerator(int regionCode) throws FileNotFoundException {

        this.sb = new StringBuilder();
        this.regionLine = padNumber(regionCode, 2);
        String fileName = "res/numbers_" + regionLine + ".txt";
        writer = new PrintWriter(fileName);
    }

    @Override
    public void run() {

        for (int number = 1; number < 1000; number++)
            for (char firstLetter : letters)
                for (char secondLetter : letters)
                    for (char thirdLetter : letters) {
                        sb.append(firstLetter);
                        sb.append(padNumber(number, 3));
                        sb.append(secondLetter);
                        sb.append(thirdLetter);
                        sb.append(regionLine);
                        sb.append("\n");
                    }

        writer.write(sb.toString());
        writer.flush();
        writer.close();
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
