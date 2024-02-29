package com.lld.and.hld.lldandhld.designpattern.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * Heavy object initialized once and accessed thru proxy object 
 */

/**
 * Database
 */
interface Database {
    void process();
}

/**
 * DBConnection
 */
class DBConnection implements Database {

    public DBConnection() {
        heavyConfigInitialize();
    }

    private void heavyConfigInitialize() {
        System.out.println("heavy Config Initialized\n");
    }

    @Override
    public void process() {
        System.out.println("Process completed\n");
    }
}

class DBConnectionProtectionProxy implements Database {
    private int permittedCalls;
    private String name;
    private long firstCallTimestamp;
    private static DBConnection dbConnection;
    static final int MAX_DURATION_IN_MS = 500;
    static Map<String, DBConnectionProtectionProxy> data = new HashMap<>();

    public DBConnectionProtectionProxy(String name) {
        if (dbConnection == null) {
            System.out.println("DBConnectionProxy constructor");
            dbConnection = new DBConnection();
        }
        this.permittedCalls = 0;
        this.name = name;
        this.firstCallTimestamp = 0;
    }

    @Override
    public void process() {

        System.out.println(this.name + " = ");
        long currentTimestamp = System.currentTimeMillis();

        if (firstCallTimestamp == 0) {
            dbConnection.process();
            this.permittedCalls = 1;
            firstCallTimestamp = currentTimestamp;
            return;
        }

        long diff = currentTimestamp - firstCallTimestamp;

        if (diff > MAX_DURATION_IN_MS) {
            dbConnection.process();
            this.permittedCalls = 1;
            firstCallTimestamp = currentTimestamp;
            return;

        }

        if (this.permittedCalls < 5) {
            dbConnection.process();
            this.permittedCalls++;
            return;
        }

        System.out.println("Rejected request as exceeded allowed calls within timewindow");
        System.out.println();
    }
}

/**
 * InnerProxyPattern
 */
class DBConnectionProxy implements Database {

    private static DBConnection dbConnection;

    public DBConnectionProxy() {
        if (dbConnection == null) {
            System.out.println("DBConnectionProxy constructor");
            dbConnection = new DBConnection();
        }
    }

    @Override
    public void process() {
        dbConnection.process();
    }
}

public class ProxyPattern {
    public static void main(String[] args) throws InterruptedException {

        // Heavy Object Normal Proxy
        DBConnectionProxy dbConnectionProxy = new DBConnectionProxy();
        dbConnectionProxy.process();

        // Protective Proxy
        DBConnectionProtectionProxy dbConnectionProtectionProxy = new DBConnectionProtectionProxy("obj1");
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        Thread.sleep(500);
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();
        dbConnectionProtectionProxy.process();

        // Remote Proxy -> Used as skeleton for remote object 
    }
}
