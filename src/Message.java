import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class Message implements Serializable {

    private Date date = null;
    private String name = null;
    private String text = null;
    private String namePCAndIP = null;
    private String status = null;

    // date, name, message, ip, status
    Message(Date date, String name, String text, String namePCAndIP, String status) {
        this.date = date;
        this.name = name;
        this.text = text;
        this.namePCAndIP = namePCAndIP;
        this.status = status;
    }

    Message(String name, String text) {
        this.date = new Date();
        this.name = name;
        this.text = text;
        this.namePCAndIP = null;
        this.status = null;
    }

    Message(String name, String text, String status) {
        this.date = new Date();
        this.name = name;
        this.text = text;
        this.namePCAndIP = null;
        this.status = status;
    }

    Message(Message message) {
        this.date = message.getDate();
        this.name = message.getName();
        this.text = message.getText();
        this.namePCAndIP = message.getNamePCAndIP();
        this.status = message.getStatus();
    }

    Date getDate() { return date; }

    String getName() { return name; }

    String getText() {
        return text;
    }

    String getNamePCAndIP() { return namePCAndIP; }

    String getStatus() { return status; }

    void setStatus(String status) { this.status = status; }

    @Override
    public String toString() { return  name + ": " + text + " - " + new SimpleDateFormat("HH:mm:ss").format(date); }
}
