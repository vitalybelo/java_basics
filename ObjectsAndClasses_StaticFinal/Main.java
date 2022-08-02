public class Main
{

    public static void main(String[] args)
    {
        Processor processor = new Processor(16, 2000, 5000,350);
        Memory memory = new Memory(MemoryType.DDR3, 16384, 60);
        Storage storage = new Storage(StorageType.SSD, 1240000, 330);
        Screen screen = new Screen(21,ScreenType.LED, 950);
        Keyboard keyboard = new Keyboard(KeyboardType.MEMBRANE, false, 200);

        Processor processor2 = processor.setCoreCPU(8);
        processor2 = processor2.setFrequencyCPU(1200);
        processor2 = processor2.setFlopsCPU(3000);
        processor2 = processor2.setWeightCPU(135);
        Memory memory2 = memory.setMemoryCapacity(8192);
        Storage storage2 = storage.setStorageCapacity(512000);
        Screen screen2 = screen.setScreenDiagonal(15);
        screen2 = screen2.setScreenType(ScreenType.IPS);
        screen2 = screen2.setScreenWeight(500);
        Keyboard keyboard2 = keyboard.setKeyboardBackLight(true);
        keyboard2 = keyboard2.setKeyboardType(KeyboardType.MECHANICAL);

        Computer computer1 = new Computer("ACER","Mighty desktop", processor, memory, storage, screen, keyboard);
        Computer computer2 = new Computer("ASUS","Laptop fly", processor2, memory2, storage2, screen2, keyboard2);

        System.out.println("");
        System.out.println(computer1.toString()+"\n");
        System.out.println(computer2.toString());

        System.out.println("");
        System.out.println("Вес компьютера #1 = " + computer1.getWeightComputer());
        System.out.println("Вес компьютера #2 = " + computer2.getWeightComputer());

    }

}
