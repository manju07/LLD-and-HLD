package com.lld.and.hld.lldandhld.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * NewAgency (Observable)
 * https://www.baeldung.com/java-observer-pattern
 */
class NewsAgency {

    private String news;
    private List<NewsChannel> observers = new ArrayList<>();

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
        for (NewsChannel newsChannel : observers) {
            newsChannel.update(news);
        }
    }

    public void addObserver(NewsChannel observer) {
        observers.add(observer);
    }

    public void removeObserver(NewsChannel observer) {
        observers.remove(observer);
    }

}

/**
 * Channel
 */
interface Channel {
    void update(String news);
}

/**
 * NewsChannel (Observer)
 */
class NewsChannel implements Channel {
    private String news;

    @Override
    public void update(String news) {
        this.setNews(news);
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

}

public class ObserverPattern {
    public static void main(String[] args) {
        NewsAgency observable = new NewsAgency();
        NewsChannel observer1 = new NewsChannel();
        NewsChannel observer2 = new NewsChannel();
        observable.addObserver(observer1);
        observable.addObserver(observer2);

        System.out.println("observer1=" + observer1.getNews());
        System.out.println("observer2=" + observer2.getNews());

        observable.setNews("new news");

        System.out.println("observer1=" + observer1.getNews());
        System.out.println("observer2=" + observer2.getNews());
    }
}
