package com.lld.and.hld.lldandhld.designpattern.behavioral;

import java.util.Observable;
import java.util.Observer;

/**
 * ObservableNewsAgency
 */
class ObservableNewsAgency extends Observable {
    private String news;

    public void setNews(String news) {
        this.news = news;
        setChanged();
        notifyObservers(news);
    }
}

/**
 * InnerObserverPatternWithInbuiltClasses
 */
class ObserverNewsChannel implements Observer {
    private String news;

    @Override
    public void update(Observable o, Object arg) {
        this.setNews((String) arg);
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}

public class ObserverPatternWithBuiltInClasses {
    public static void main(String[] args) {
        ObservableNewsAgency obserable = new ObservableNewsAgency();
        ObserverNewsChannel observer = new ObserverNewsChannel();
        obserable.addObserver(observer);
        obserable.setNews("new news");
        System.out.println(observer.getNews());
    }
}
