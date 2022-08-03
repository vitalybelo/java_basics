import java.sql.SQLOutput;

public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount()+"\n");

        char iChar;

        // в первых кодировках букв Ёё не было, и коды ASCII были
        // сформированы без этих букв подряд, позже их решили добавить,
        // найдя пустые места в последовательности символов.
        //
        for ( iChar = 'А'; iChar <= 'Е'; iChar ++ ) {
            System.out.println( iChar + " ::: " + (int) iChar );
        }
        System.out.println( 'Ё' + " ::: " + (int)'Ё' + " исторически сложилось");
        for ( iChar = 'Ж'; iChar <= 'е'; iChar ++ ) {
            System.out.println( iChar + " ::: " + (int) iChar );
        }
        System.out.println( 'ё' + " ::: " + (int)'ё' + " исторически сложилось");
        for ( iChar = 'ж'; iChar <= 'я'; iChar ++ ) {
            System.out.println( iChar + " ::: " + (int) iChar );
        }


    }
}
