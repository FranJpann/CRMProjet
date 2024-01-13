package virtualcrm.configuration;

import java.io.*;
import java.util.Properties;

public class ConfigProperties {

    Properties props = new Properties();

    public ConfigProperties() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        props.load(inputStream);
    }

    public String getConfigValue(String configKey){
        return props.getProperty(configKey);
    }
}
