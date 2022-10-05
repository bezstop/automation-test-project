package selenium.helpDesk.readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    String URL = readConfig().getString("url");
    Integer age = readConfig().getInt("age");
    String ADMIN_LOGIN = readConfig().getString("usersParams.admin.login");
    String DEMO_PASSWORD = readConfig().getString("usersParams.demo.password");
    String DEMO_LOGIN = readConfig().getString("usersParams.demo.login");
    Boolean IS_DEMO_ADMIN = readConfig().getBoolean("usersParams.demo.isAdmin");

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfiles"))
                : ConfigFactory.load("application.conf");
    }
    //    Boolean IS_USER_ADMIN = readConfig().getBoolean("usersParams.admin.isAdmin");
}
