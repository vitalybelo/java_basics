public class Connection {

    private String stationName;
    private String lineNumber;

    public Connection(String stationName, String lineNumber) {
        this.stationName = stationName;
        this.lineNumber = lineNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

}
