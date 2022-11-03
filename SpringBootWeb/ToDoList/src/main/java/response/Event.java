package response;

public class Event {

    private int id;
    private String evenContent;
    private boolean eventFinished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvenContent() {
        return evenContent;
    }

    public void setEvenContent(String evenContent) {
        this.evenContent = evenContent;
    }

    public boolean isEventFinished() {
        return eventFinished;
    }

    public void setEventFinished(boolean eventFinished) {
        this.eventFinished = eventFinished;
    }
}
