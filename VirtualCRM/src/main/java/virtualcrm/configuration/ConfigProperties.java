package virtualcrm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
