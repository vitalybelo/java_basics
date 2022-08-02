public class Memory
{
    private final MemoryType memoryType;
    private final int memoryCapacity;
    private final int memoryWeight;

    public Memory(MemoryType memoryType, int memoryCapacity, int memoryWeight)
    {
        this.memoryType = memoryType;
        this.memoryCapacity = memoryCapacity;
        this.memoryWeight = memoryWeight;
    }

    // SETTERS
    public Memory setMemoryType (MemoryType memoryType) {
        return new Memory(memoryType, memoryCapacity, memoryWeight);
    }
    public Memory setMemoryCapacity (int memoryCapacity ) {
        return new Memory(memoryType, memoryCapacity, memoryWeight);
    }
    public Memory setMemoryWeight (int memoryWeight ) {
        return new Memory(memoryType, memoryCapacity, memoryWeight);
    }
    // GETTERS
    public MemoryType getMemoryType() {
        return memoryType;
    }
    public int getMemoryCapacity() {
        return memoryCapacity;
    }
    public int getMemoryWeight() {
        return memoryWeight;
    }
    // TO STRING
    public String toString () {
        String output = "Параметры памяти\n";
        output += "\tтип памяти: " + this.memoryType.toString() +"\n";
        output += "\tобъем памяти: " + this.memoryCapacity + "\n";
        return output;
    }

}

