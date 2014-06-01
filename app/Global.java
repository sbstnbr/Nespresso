import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {
    
    public void onStart(Application app) {
        InitialData.insert(app);
    }
    
    static class InitialData {
        
        public static void insert(Application app) {
            if(Ebean.find(User.class).findRowCount() == 0) {
                Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
                Ebean.save(all.get("users"));
            }
            if(Ebean.find(Product.class).findRowCount() == 0) {
                //Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
                //Ebean.save(all.get("products"));
            }
                // // Insert projects
                // Ebean.save(all.get("projects"));
                // for(Object project: all.get("projects")) {
                //     // Insert the project/user relation
                //     Ebean.saveManyToManyAssociations(project, "members");
                // }

                // // Insert tasks
                // Ebean.save(all.get("tasks"));
                
        }
        
    }
    
}