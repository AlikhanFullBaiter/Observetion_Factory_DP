import java.util.ArrayList;
import java.util.List;


interface NewsSubscriber {
    void update(String news);
}


class User implements NewsSubscriber {
    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void update(String news) {
        System.out.println(username + " received the latest news: " + news);
    }
}


class NewsChannel {
    private List<NewsSubscriber> subscribers = new ArrayList<>();
    private String latestNews;

    public void subscribe(NewsSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(NewsSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publishNews(String news) {
        latestNews = news;
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (NewsSubscriber subscriber : subscribers) {
            subscriber.update(latestNews);
        }
    }
}


interface NewsChannelFactory {
    NewsChannel createNewsChannel();
}

class NewsChannelFactoryImpl implements NewsChannelFactory {
    @Override
    public NewsChannel createNewsChannel() {
        return new NewsChannel();
    }
}

public class Main {
    public static void main(String[] args) {
        NewsChannelFactory channelFactory = new NewsChannelFactoryImpl();

        NewsChannel sportsNews = channelFactory.createNewsChannel();
        NewsChannel techNews = channelFactory.createNewsChannel();

        NewsSubscriber user1 = new User("User1");
        NewsSubscriber user2 = new User("User2");

        sportsNews.subscribe(user1);
        sportsNews.subscribe(user2);
        techNews.subscribe(user1);

        sportsNews.publishNews("New world record in F1!");
        techNews.publishNews("Apple announce a new  16.");

        sportsNews.unsubscribe(user2);

        sportsNews.publishNews("FC Barcelona wins the championship!");

    }
}
