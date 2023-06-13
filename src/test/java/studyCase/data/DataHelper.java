package studyCase.data;

import com.google.gson.JsonObject;
import studyCase.configurations.ConfigLoader;
import studyCase.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataHelper {
    private static final String USER_CREDENTIAL = "src/test/java/studyCase/data/userCredential.json";
    private  static final String env = ConfigLoader.getEnv();
    public static Map<String, String> getRandomUser() {
        Random random = new Random();
        List<JsonObject> userList = JsonUtil.getJsonList(USER_CREDENTIAL, env + "_users");

        if (!userList.isEmpty()) {
            int randomIndex = random.nextInt(userList.size());
            JsonObject randomUser = userList.get(randomIndex);
            Map<String, String> userMap = new HashMap<>();
            userMap.put("username", randomUser.get("username").getAsString());
            userMap.put("password", randomUser.get("password").getAsString());
            userMap.put("fullName", randomUser.get("fullName").getAsString());

            return userMap;
        }
        return null;
    }

    public static Map<String, String> getUserDataByIndex(int index){
        List<JsonObject> userList = JsonUtil.getJsonList(USER_CREDENTIAL, env + "_users");
        JsonObject randomUser = userList.get(index);
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", randomUser.get("username").getAsString());
        userMap.put("password", randomUser.get("password").getAsString());
        userMap.put("fullName", randomUser.get("fullName").getAsString());
        return userMap;
    }


}
