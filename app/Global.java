import com.avaje.ebean.Ebean;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

public class Global extends GlobalSettings {

    public void onStart(Application app) {
        //Enable log sql queries
        //Ebean.getServer(null).getAdminLogging().setDebugGeneratedSql(true);
        //Delete users from database
        //Ebean.delete(Ebean.find(User.class).findList());
        InitialData.insert(app);

        // start email dispatcher
    }

    static class InitialData {


        public static void insert(Application app) {
            @SuppressWarnings("unchecked")
            Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yaml");

            if (Ebean.find(User.class).findRowCount() == 0) {
                List<Object> usersList = all.get("users");
                for (Object o : usersList) {
                    User u = (User) o;
                        u.password = "Password";
                }

                // Insert users first
                Ebean.save(all.get("users"));
            }
        }

    }

}