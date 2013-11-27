package android.data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 26/11/2013
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private String user;
    private String message;
    private String direction; //can be incomming or outgoing
    private Date date;
    private boolean read;

    public Message(String user, String message, String direction,
                   Date date, String toUser, boolean read){
        setUser(user);
        setMessage(message);
        setDirection(direction);
        setDate(date);
        setRead(read);
    }
    public Message(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String fromUser) {
        this.user = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

}
