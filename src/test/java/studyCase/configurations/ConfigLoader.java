package studyCase.configurations;


import studyCase.constants.EnvType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;
    private  static String envKey;

    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(EnvType.STG));
        switch (EnvType.valueOf(env)) {
            case PROD:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                envKey = "prod";
                break;
            case STG:
                properties = PropertyUtils.propertyLoader("src/test/resources/stage_config.properties");
                envKey = "stg";
                break;
            default:
                throw new IllegalStateException("INVALID ENV: " + env);
        }
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not defined in the properties file");
    }

    public static String getEnv() {
        return envKey;
    }

    public URL getGridUrl(){
        try {
            return new URL(properties.getProperty("gridUrl"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
