package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class UserOrder extends Model {

  @Id
  public Long id;
  
  @Required
  public Date date;

  public boolean paid;

  @OneToMany
  public List<OrderProduct> orderProductList;

  @OneToOne
  public User user;

  @OneToOne
  public GlobalOrder globalOrder;

  public static Finder<Long,UserOrder> find = new Finder(
    Long.class, UserOrder.class
  );
  
  public static List<UserOrder> all() {
    return find.all();
  }
  
  public static void create(UserOrder userOrder) {
    userOrder.save();
  }
  
  public static void delete(Long id) {
    find.ref(id).delete();
  }

  public float getPrice(){
    int price = 0;
    for (int i = 0; i<this.orderProductList.size();i++){
      price += this.orderProductList.get(i).quantity * this.orderProductList.get(i).product.price;
    }
    return price;
  }
    
}