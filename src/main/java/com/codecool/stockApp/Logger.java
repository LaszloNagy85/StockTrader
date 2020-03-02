package com.codecool.stockApp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public Logger() {
    }

    public void log(String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String msg = dateFormat.format(date) + " " + message;
        System.out.println(msg);
        try {
            FileWriter fileWriter = new FileWriter("log.txt",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(msg);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Log file write failed :( " + e);
        }

    }
}
