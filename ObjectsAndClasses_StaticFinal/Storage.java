public class Storage
{
    public final StorageType storageType;
    public final int storageCapacity;
    public final int storageWeight;

    public Storage(StorageType storageType, int storageCapacity, int storageWeight)
    {
        this.storageType = storageType;
        this.storageCapacity = storageCapacity;
        this.storageWeight = storageWeight;
    }

    // SETTERS
    public Storage setStorageType (StorageType storageType) {
        return new Storage (storageType, storageCapacity, storageWeight);
    }
    public Storage setStorageCapacity (int storageCapacity) {
        return new Storage (storageType, storageCapacity, storageWeight);
    }
    public Storage setStorageWeight (int storageWeight) {
        return new Storage (storageType, storageCapacity, storageWeight);
    }
    // GETTERS
    public StorageType getStorageType() {
        return storageType;
    }
    public int getStorageCapacity() {
        return storageCapacity;
    }
    public int getStorageWeight() {
        return storageWeight;
    }
    // TO STRING
    public String toString () {
        String output = "Параметры носителя\n";
        output += "\tтип носителя: " + this.storageType.toString() +"\n";
        output += "\tобъем носителя: " + this.storageCapacity + "\n";
        return output;
    }

}
