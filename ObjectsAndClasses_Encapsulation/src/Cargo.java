public class Cargo
{

    private final Dimensions sizes;      // height * width * depth
    private final int weight;            // weight
    private final boolean turnAble;      // true = can turn up|down
    private final boolean fragile;       // true = take carefully only
    private final String regNumber;      // registration number string
    private final String addressPost;    // delivery address string

    public Cargo (Dimensions sizes, int weight,
                  boolean turnAble, boolean fragile,
                  String regNumber, String addressPost)
    {
        this.sizes = sizes;
        this.weight = weight;
        this.turnAble = turnAble;
        this.fragile = fragile;
        this.regNumber = regNumber;
        this.addressPost = addressPost;
    }

    public Cargo setWeight (int weight) {
        return new Cargo(sizes, weight, turnAble, fragile, regNumber, addressPost);
    }

    public Cargo setTurnAble (boolean turnAble) {
        return new Cargo(sizes, weight, turnAble, fragile, regNumber, addressPost);
    }

    public Cargo setFragile (boolean fragile) {
        return new Cargo(sizes, weight, turnAble, fragile, regNumber, addressPost);
    }

    public Cargo setRegNumber (String regNumber) {
        return new Cargo(sizes, weight, turnAble, fragile, regNumber, addressPost);
    }

    public Cargo setAddress (String addressPost) {
        return new Cargo(sizes, weight, turnAble, fragile, regNumber, addressPost);
    }

    public String toString () {
        return ("ID: "+regNumber+"\nадрес: "+addressPost+"\nвес: "+weight+"\nгабариты: "+sizes.toString());
    }
}
