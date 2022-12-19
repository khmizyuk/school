package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.initDB.DBInfo;
import org.example.initDB.InitializationDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().create();

    public static void main( String[] args )
    {
        DBInfo CREDS = new DBInfo();

        InitializationDB initializer = new InitializationDB(CREDS);
        initializer.initDB();

        SpringApplication.run(App.class, args);
    }
}

