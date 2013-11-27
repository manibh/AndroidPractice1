package android.data;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 27/11/2013
 * Time: 8:49 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageRepository {
    public ArrayList<Message> getMessages(String user);
    public void storeMessages(Message message);
}
