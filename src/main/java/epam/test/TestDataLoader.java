package epam.test;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class TestDataLoader {

    private static final Logger log = Logger.getLogger(TestDataLoader.class);

    public String getProperty(String property) {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("test.properties"));
            if (props.getProperty(property) == null)
                log.error(String.format("no such property [%s] in test.properties", property));
            return props.getProperty(property);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
