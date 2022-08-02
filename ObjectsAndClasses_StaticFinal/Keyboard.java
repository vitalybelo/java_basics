public class Keyboard
{
    private final KeyboardType keyboardType;
    private final boolean keyboardBackLight;
    private final int keyboardWeight;

    public Keyboard(KeyboardType keyboardType, boolean keyboardBackLight, int keyboardWeight)
    {
        this.keyboardType = keyboardType;
        this.keyboardBackLight = keyboardBackLight;
        this.keyboardWeight = keyboardWeight;
    }

    // SETTERS
    public Keyboard setKeyboardType (KeyboardType keyboardType) {
        return new Keyboard(keyboardType, keyboardBackLight, keyboardWeight);
    }
    public Keyboard setKeyboardBackLight (boolean keyboardBackLight) {
        return new Keyboard(keyboardType, keyboardBackLight, keyboardWeight);
    }
    public Keyboard setKeyboardWeight (int keyboardWeight) {
        return new Keyboard(keyboardType, keyboardBackLight, keyboardWeight);
    }
    // GETTERS
    public KeyboardType getKeyboardType() {
        return keyboardType;
    }
    public boolean isKeyboardBackLight() {
        return keyboardBackLight;
    }
    public int getKeyboardWeight() {
        return keyboardWeight;
    }
    // TO STRING
    public String toString () {
        String output = "Параметры клавиатуры\n";
        output += "\tтип клавиатуры: " + this.keyboardType.toString() +"\n";
        output += "\tподсветка BackLight: " + this.keyboardBackLight + "\n";
        return output;
    }

}
