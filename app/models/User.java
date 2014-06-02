package models; 
import java.util.*;

import play.Logger;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class User extends Model {

  @Id
  public Long id;
  
  @Required
  public String name;

  public boolean isAdmin;

  public String pic;

  @OneToMany
  public List<UserOrder> userOrderList;
  
  public static Finder<Long,User> find = new Finder(
    Long.class, User.class
  );
  
  public static List<User> all() {
    return find.all();
  }
  
  public static void create(User user) {
    user.save();
  }
  
  public static void delete(Long id) {
    find.ref(id).delete();
  }

  public int getNbUserOrderByStatus(int status){
    int nb = 0;
    Iterator<UserOrder> iterator = userOrderList.iterator();
    while (iterator.hasNext()){
      if(iterator.next().getStatus()==status){
        nb++;
      }
    }
    return nb;
  }

  public static User findByName(String name) {
    return find.where()
            .eq("name", name)
            .findUnique();
  }
    
}