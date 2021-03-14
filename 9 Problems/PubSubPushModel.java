import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


// Topic alwasy will be string and msg could be any object
// why cant we just create with Object class?
public  interface Message<T> {
    String getTopic();
    T getMsg();
}

// Message should Immutable
// that is why no setters
public class MessageImpl<String,T> implements Message<T> {
    String topic;
    T msg;

    public Message(String topic, T msg){
        this.topic = topic;
        this.msg = msg;
    }

    public String getTopic() {
        return topic;
    };

    public T getMsg() {
        return msg;
    };
}

public interface Publisher {
    void publishMessage(Message msg,PubSubService service);
}

public class PublisherImpl implements Publisher {
    public void publishMessage(Message msg,PubSubService service){
        service.addMessage(msg);
    }
}

public interface Subscriber {
    void saveMessage(Message msg);
    void subscribe(String topic, PubSubService service);
    void unsubscribe(String topic, PubSubService service);
}

// we are not passing PubSubService
// because we dont want to be tied to one PubSubService
// I can make this as an abstract class and this is the default implementation
// child classes can have a processMessage()
public class SubscriberImpl implements Subscriber {
    // concurrent bcz pubsubservice may add messages while
    // subscribing processing a message
    // you can start with a queue here and in the end modify that queue .. goal here to showcase thinking process
    // ConcurrentLinkedQueue or ConcurrentLinkedDeque
    ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<Message>();

    public SubscriberImpl(){
    }

    public void saveMessage(Message msg){
        messages.add(msg);
    }

    public void subscribe(String topic, PubSubService service){
        service.subscribe(topic,this);
    }

    public void unsubscribe(String topic, PubSubService service) {
        service.unsubscribe(topic,this);
    }

    /*
    *  public abstract void processMessage(); If we do abstract method
    *
    * */
}

public class PubSubService {

    // Concurrent HashMap bcz during broad cast publisher can add messages
    // topic -> messages
    ConcurrentMap<String, ConcurrentLinkedQueue<Message>> mapTopicMessage
            = new ConcurrentHashMap<String, ConcurrentLinkedQueue<Message> >();
    // topic -> subscribers
    ConcurrentMap<String, ConcurrentSkipListSet<Subscriber>> mapTopicSub
            = new ConcurrentHashMap<String,ConcurrentSkipListSet<Subscriber>>();


    // single object for driver class
    static private PubSubService instance = new PubSubService();
    private PubSubService() {
    }

    static PubSubService getInstance() {
        return instance;
    }

    public void addMessage(Message msg){
        mapTopicMessage.putIfAbsent(msg.getTopic(), new ConcurrentLinkedQueue<Message>());
        mapTopicMessage.get(msg.getTopic()).add(msg);
    }

    public void subscribe(String topic, Subscriber sub){
        mapTopicSub.putIfAbsent(topic, new ConcurrentSkipListSet<Subscriber>());
        mapTopicSub.get(topic).add(sub);
    }

    public void unsubscribe(String topic, Subscriber sub){
        if(mapTopicSub.get(topic)!=null)
            mapTopicSub.get(topic).remove(sub);
    }

    // send a one message from a topic
    public boolean sendMessage(String topic){
        ConcurrentLinkedQueue<Message> messages = mapTopicMessage.get(topic);
        // There is a message
        if(messages==null ||  messages.size()==0){
            return false;
        }
        // there is a subscriber
        ConcurrentSkipListSet<Subscriber> subscribers = mapTopicSub.get(topic);
        if(subscribers==null || subscribers.size()==0){
            return false;
        }

        Message msg  = messages.poll();
        for(Subscriber sub : subscribers){
            sub.saveMessage(msg);
        }

        return true;
    }

    // send all messages from all topics
    public void broadCast(){
        if(mapTopicMessage.size()==0)
            return;

        for(ConcurrentMap.Entry<String, ConcurrentLinkedQueue<Message>> e: mapTopicMessage.entrySet()){
            while(sendMessage(e.getKey()));
        }
    }
    
    // Try Making PUll model like kafka 
}
