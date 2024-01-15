package virtualcrm.configuration;

import java.io.*;
import java.util.Properties;

public class ConfigProperties {

    /*  ConfigProperties    */
    /*  récupère les informations de application.properties */

    Properties props = new Properties();

    public ConfigProperties() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        props.load(inputStream);
    }

    public String getConfigValue(String configKey){
        return props.getProperty(configKey);
    }
}
