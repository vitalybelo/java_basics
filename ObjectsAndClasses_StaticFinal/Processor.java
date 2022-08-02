public class Processor
{
    private final int coreCPU;      // core numbers
    private final int frequencyCPU; // tact frequency
    private final int flopsCPU;     // f.l.o.p
    private final int weightCPU;    // weight in grams

    public Processor(int coreCPU, int frequencyCPU, int flopsCPU, int weightCPU)
    {
        this.coreCPU = coreCPU;
        this.frequencyCPU = frequencyCPU;
        this.flopsCPU = flopsCPU;
        this.weightCPU = weightCPU;
    }

    // SETTERS
    public Processor setCoreCPU (int coreCPU) {
        return new Processor(coreCPU, frequencyCPU, flopsCPU, weightCPU);
    }
    public Processor setFrequencyCPU (int frequencyCPU) {
        return new Processor(coreCPU, frequencyCPU, flopsCPU, weightCPU);
    }
    public Processor setFlopsCPU (int flopsCPU) {
        return new Processor(coreCPU, frequencyCPU, flopsCPU, weightCPU);
    }
    public Processor setWeightCPU (int weightCPU) {
        return new Processor(coreCPU, frequencyCPU, flopsCPU, weightCPU);
    }
    // GETTERS
    public int getCoreCPU() {
        return coreCPU;
    }
    public int getFrequencyCPU() {
        return frequencyCPU;
    }
    public int getFlopsCPU() {
        return flopsCPU;
    }
    public int getWeightCPU() {
        return weightCPU;
    }
    // TO STRING
    public String toString () {
        String output = "Параметры процессора\n";
        output += "\tколичество ядер: " + this.getCoreCPU() +"\n";
        output += "\tтактовая частота: " + this.getFrequencyCPU() + "\n";
        output += "\tпроизводительность: " + this.getFlopsCPU() + "\n";

        return output;
    }
}
