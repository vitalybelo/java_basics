import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {

        // задание 2 продолжение классы
        //
        Dimensions box1 = new Dimensions(20, 30, 40);
        Dimensions box2 = box1.setHeight(25);
        Dimensions box3 = box1.setWidth(35);
        Cargo postBox1 = new Cargo (box1, 450, true, false,
                "133-01-11","Москва, 125521, Площадь имени Карновалова");

        Cargo postBox2 = postBox1.setRegNumber("133-02-99");
        postBox2 = postBox2.setAddress("Москва, тупик имени отца Звездония");
        postBox2 = postBox2.setWeight(498);
        Cargo postBox3 = postBox2.setAddress("Проспект творений Гениалисимуса");


        System.out.println("");
        System.out.println("1-ая посылка имеет объем = " + box1.volume() + " куб.м");
        System.out.println("2-ая посылка имеет объем = " + box2.volume() + " куб.м");
        System.out.println("3-ая посылка имеет объем = " + box3.volume() + " куб.м");

        System.out.println("\n1-ая посылка\n" + postBox1.toString());
        System.out.println("\n2-ая посылка\n" + postBox2.toString());
        System.out.println("\n3-ая посылка\n" + postBox3.toString());

        System.out.println("\n\nВНИМАНИЕ !!! вы в лифте !\n");
        // задание 1 про лифт
        //
        Elevator elevator = new Elevator (-3,26);
        Scanner scanner = new Scanner(System.in);
        int floor;

        do {
            System.out.println("Лифт находится на этаже : " + elevator.getCurrentFloor());
            System.out.print("Введите номер этажа на который поедете: ");
            floor = scanner.nextInt();

            elevator.move (floor);
        }
        while (true);


    }
}
