package ru.bot.config;

import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private final String url;
    private final String user;
    private final String password;

    private static final String FILE_PATH = "config/Mysql.properties";
    private static final String PREFIX = "jdbc:mysql://";

    private static DatabaseConfig databaseConfig;

    private DatabaseConfig() {
        try {
            // reading file
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            Properties properties = new Properties();
            properties.load(classloader.getResourceAsStream(FILE_PATH));

            // get authentication info
            this.url = DatabaseConfig.PREFIX + properties.getProperty("url");
            System.out.println(url);
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");
        } catch (IOException e) {
            throw new IllegalStateException("""
                    \n---------------
                    Solution
                    You should create a file path/name: resources/%s and paste there two lines:
                    1) url={Database url} (example: localhost::3306/database_name)
                    2) user={User's login}
                    3) password={User's password}
                    """.formatted(FILE_PATH), e);
        }
    }

    public static DatabaseConfig getMysqlConfig() {
        if (DatabaseConfig.databaseConfig != null) DatabaseConfig.databaseConfig = new DatabaseConfig();
        return DatabaseConfig.databaseConfig;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
