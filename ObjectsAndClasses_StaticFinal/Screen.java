public class Screen
{
    private final int screenDiagonal;
    private final ScreenType screenType;
    private final int screenWeight;

    public Screen(int screenDiagonal, ScreenType screenType, int screenWeight)
    {
        this.screenDiagonal = screenDiagonal;
        this.screenType = screenType;
        this.screenWeight = screenWeight;
    }

    // SETTERS
    public Screen setScreenDiagonal (int screenDiagonal) {
        return new Screen(screenDiagonal, screenType, screenWeight);
    }
    public Screen setScreenType (ScreenType screenType) {
        return new Screen(screenDiagonal, screenType, screenWeight);
    }
    public Screen setScreenWeight (int screenWeight) {
        return new Screen(screenDiagonal, screenType, screenWeight);
    }
    // GETTERS
    public int getScreenDiagonal() {
        return screenDiagonal;
    }
    public ScreenType getScreenType() {
        return screenType;
    }
    public int getScreenWeight() {
        return screenWeight;
    }
    // TO STRING
    public String toString () {
        String output = "Параметры экрана\n";
        output += "\tтип экрана: " + this.screenType.toString() +"\n";
        output += "\tдиагональ: " + this.screenDiagonal + "\n";
        return output;
    }

}
