import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
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
