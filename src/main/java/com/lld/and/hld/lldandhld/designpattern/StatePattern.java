package com.lld.and.hld.lldandhld.designpattern;

import java.util.ArrayList;
import java.util.List;

public class StatePattern {

    static class SachinCenturyNotify {
        List<SachinFan> sachinFans = new ArrayList<>();

        public void addFan(SachinFan sachinFan) {
            sachinFans.add(sachinFan);
        }

        public void notifySachinFans() {
            for (SachinFan sachinFan : sachinFans) {
                sachinFan.notifyFan();
            }
        }
    }

    static class SachinFan {

        private String name;

        SachinFan(String name) {
            this.name = name;
        }

        public void notifyFan() {
            System.out.println("Name=" + this.name + " notified");
        }
    }

    public static void main(String[] args) {
        SachinFan f1 = new SachinFan("Manju");
        SachinFan f2 = new SachinFan("bhagya");
        SachinFan f3 = new SachinFan("shivu");
        SachinCenturyNotify sachinCenturyNotify = new SachinCenturyNotify();
        sachinCenturyNotify.addFan(f1);
        sachinCenturyNotify.addFan(f2);
        sachinCenturyNotify.addFan(f3);
        sachinCenturyNotify.notifySachinFans();
    }
}
