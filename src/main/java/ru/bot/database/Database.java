package ru.bot.database;

import ru.bot.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final Connection connection;
    private static Database db;

    private Database() {
        DatabaseConfig databaseConfig = DatabaseConfig.getMysqlConfig();
        try {
            connection = DriverManager.getConnection(
                    databaseConfig.getUrl(),
                    databaseConfig.getUser(),
                    databaseConfig.getPassword()
            );
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getDatabase() {
        if (db == null) db = new Database();
        return Database.db;
    }

}
