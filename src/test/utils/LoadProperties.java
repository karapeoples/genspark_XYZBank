package test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
static Properties properties = new Properties();
    private LoadProperties(){

    }

    public static void init() {
        try {
            String dir = System.getProperty("user.dir");
            String path = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "properties.properties";
            properties.load(new FileInputStream(dir + path ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String nabProperty(String key){
        return properties.getProperty(key);
    }
}
