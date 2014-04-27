package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Product extends Model {

  @Id
  public Long id;
  
  @Required
  public String name;
  public float price;
  public int intensity;
  
  public static Finder<Long,Product> find = new Finder(
    Long.class, Product.class
  );
  
  public static List<Product> all() {
    return find.all();
  }
  
  public static void create(Product product) {
    product.save();
  }
  
  public static void delete(Long id) {
    find.ref(id).delete();
  }
    
}