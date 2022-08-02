public class Computer
{

    private final String vendor;
    private final String name;
    private final Processor processor;
    private final Memory memory;
    private final Storage storage;
    private final Screen screen;
    private final Keyboard keyboard;

    public Computer(String vendor, String name, Processor processor, Memory memory,
                    Storage storage, Screen screen, Keyboard keyboard)
    {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.screen = screen;
        this.keyboard = keyboard;
    }

    public Computer setVendor (String vendor) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public Computer setName (String name) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public Computer setProcessor (Processor processor) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public Computer setMemory (Memory memory) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public Computer setStorage (Storage storage) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public Computer setScreen (Screen screen) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public Computer setKeyboard (Keyboard keyboard) {
        return new Computer(vendor, name, processor, memory, storage, screen, keyboard);
    }
    public int getWeightComputer () {
        return (processor.getWeightCPU() + memory.getMemoryWeight() +
                storage.getStorageWeight() + screen.getScreenWeight() + keyboard.getKeyboardWeight());
    }

    public String getVendor() {
        return vendor;
    }
    public String getName() {
        return name;
    }
    public Processor getProcessor() {
        return processor;
    }
    public Memory getMemory() {
        return memory;
    }
    public Storage getStorage() {
        return storage;
    }
    public Screen getScreen() {
        return screen;
    }
    public Keyboard getKeyboard() {
        return keyboard;
    }
    public String toString () {
        String output =
                "Производитель: " + this.vendor + "\n" +
                "Модель компьютера : " + this.name + "\n" +
                processor.toString() +
                memory.toString() +
                storage.toString() +
                screen.toString() +
                keyboard.toString();
        return output;
    }

}
