package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class GlobalOrder extends Model {

  @Id
  public Long id;
  
  public Date start;
  public Date end;
  public int status;

  @OneToMany
  public List<UserOrder> userOrderList;

  public static Finder<Long,GlobalOrder> find = new Finder(
    Long.class, GlobalOrder.class
  );
  
  public static List<GlobalOrder> all() {
    return find.all();
  }
  
  public static void create(GlobalOrder globalOrder) {
    globalOrder.save();
  }

  public float getPrice(){
    int price = 0;
    Iterator<UserOrder> iterator = userOrderList.iterator();
    while (iterator.hasNext()){
      price += iterator.next().getPrice();
    }
    return price;
  }

  public static int getNbGlobalOrderByStatus(int status){
    int nb = 0;
    Iterator<GlobalOrder> iterator = GlobalOrder.all().iterator();
    while (iterator.hasNext()){
      if(iterator.next().status==status){
        nb++;
      }
    }
    return nb;
  }

  public int getQuantity(){
    int quantity = 0;
    Iterator<UserOrder> iterator = userOrderList.iterator();
    while (iterator.hasNext()){
      quantity += iterator.next().getQuantity();
    }
    return quantity;
  }
    
}