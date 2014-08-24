package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class OrderProduct extends Model {

  @Id
  public Long id;
  
  @Required
  public int quantity;

  @OneToOne
  public Product product;

  @OneToOne
  public  UserOrder userOrder;
  
  public static Finder<Long,OrderProduct> find = new Finder(
    Long.class, OrderProduct.class
  );
  
  public static List<OrderProduct> all() {
    return find.all();
  }
  
  public static void create(OrderProduct orderProduct) {
    orderProduct.save();
  }
  
  public static void delete(Long id) {
    find.ref(id).delete();
  }

  public void increaseQuantity(){
    this.quantity = this.quantity + 10;
    this.save();
  }

  public void decreaseQuantity(){
    if (this.quantity >= 20){
      this.quantity = this.quantity - 10;
      this.save();
    }
  }
  
  public float getPrice(){
    return this.quantity * this.product.price;
  }
}