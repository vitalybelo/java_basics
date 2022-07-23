public class Main
{

    public static void main(String[] args)
    {
        Arithmetic classTest = new Arithmetic(5);

        System.out.println();
        System.out.println("Значение первого параметра = "+ classTest.getVar1());
        System.out.println("Значение второго параметра = "+ classTest.getVar2()+"\n");

        System.out.println("Сумма двух параметров = " + classTest.getSum());
        System.out.println("Разница между первым и вторым = " + classTest.getSub());
        System.out.println("Результат умножения параметров = " + classTest.getMul());
        System.out.println("Результат деления 1-го на 2-ое = " + classTest.getDiv());
        System.out.println("Модуль деления этих параметров = " + classTest.getMod());
        System.out.println("Минимальное значение параметров = " + classTest.getMin());
        System.out.println("Максимальное значение параметров = " + classTest.getMax());

        System.out.println("\nМы можем переустановить значения параметров");
        System.out.println("Сейчас мы это сделаем и посмотрим что получилось\n");
        classTest.setVar1(15);
        classTest.setVar2(10);

        System.out.println("Значение первого параметра (нового) = "+ classTest.getVar1());
        System.out.println("Значение второго параметра (нового) = "+ classTest.getVar2()+"\n");

        System.out.println("Сумма двух параметров = " + classTest.getSum());
        System.out.println("Разница между первым и вторым = " + classTest.getSub());
        System.out.println("Результат умножения параметров = " + classTest.getMul());
        System.out.println("Результат деления 1-го на 2-ое = " + classTest.getDiv());
        System.out.println("Модуль деления этих параметров = " + classTest.getMod());
        System.out.println("Минимальное значение параметров = " + classTest.getMin());
        System.out.println("Максимальное значение параметров = " + classTest.getMax());

    }
}
