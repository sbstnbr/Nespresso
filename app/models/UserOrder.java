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

  public int getQuantity(){
    int quantity = 0;
    Iterator<OrderProduct> iterator = orderProductList.iterator();
    while (iterator.hasNext()){
      quantity += iterator.next().quantity;
    }
    return quantity;
  }

  public int getStatus(){
    int status = 2;
    int globalOrderStatus = this.globalOrder.status;
    if(globalOrderStatus==0){
      status=0;
    } else if ((globalOrderStatus==1||globalOrderStatus==2)&&this.paid==false){
      status=1;
    }
    return status;
  }
    
}