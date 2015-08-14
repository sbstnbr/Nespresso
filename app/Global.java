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
            Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
            if(Ebean.find(User.class).findRowCount() == 0) {
                Ebean.save(all.get("users"));
                Logger.info("Users loaded!");
            }
            if(Ebean.find(Product.class).findRowCount() == 0) {
                Ebean.save(all.get("products"));
                Logger.info("Products loaded!");
            }
            // if(Ebean.find(GlobalOrder.class).findRowCount() == 0) {
            //     Ebean.save(all.get("globalOrders"));
            //     Logger.info("GlobalOrders loaded!");
            // }
            if(Ebean.find(UserOrder.class).findRowCount() == 0) {
                Ebean.save(all.get("userOrders"));
                Logger.info("UserOrders loaded!");
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