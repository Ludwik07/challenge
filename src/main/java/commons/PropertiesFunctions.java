package commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFunctions {
    Properties properties;

    public PropertiesFunctions() {
        this.properties = new Properties();
    }

    public String getProperty(FileInputStream file, String property) {
        try {
            properties.load(file);
        } catch (IOException ex) {
            System.out.println("Unable to load property file");
            ex.printStackTrace();
        }
        try {
            return properties.getProperty(property);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve property");
            ex.printStackTrace();
        }
        return null;
    }
}
