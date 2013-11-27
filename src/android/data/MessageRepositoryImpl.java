package android.data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 26/11/2013
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageRepositoryImpl implements MessageRepository{
    private static ArrayList<Message> messages;

    public MessageRepositoryImpl(){
        this.messages=new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages(String user) {
        Iterator<Message> itr=messages.iterator();
        ArrayList <Message> userMessages=new ArrayList<Message>();
        while(itr.hasNext()){
            Message msg=itr.next();
            if(msg.getUser().equals(user)){
                userMessages.add(itr.next());
            }

        }
        return userMessages;
    }

    public void storeMessages(Message message) {
        this.messages.add(message);
    }




}
