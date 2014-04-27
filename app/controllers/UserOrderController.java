package controllers;

import models.*;
import java.util.*;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UserOrderController extends Controller {

  static Form<UserOrder> userOrderForm = Form.form(UserOrder.class);

  public static Result userOrder(Long id) {
    id = 1L;
    return ok(
      views.html.userorder.render(UserOrder.find.ref(id), userOrderForm, Product.all())
    );
  }

  public static Result newUserOrder(){

    return ok();
  } 

    //   UserOrder userOrder = new UserOrder();
    //   userOrder.quantity = 10;
    //   userOrder.save();

    //   OrderProduct orderProduct = new OrderProduct();
    //   orderProduct.quantity = 10;
    //   orderProduct.userOrder = userOrder;
    //   orderProduct.save();

    //   orderProduct = new OrderProduct();
    //   orderProduct.quantity = 20;
    //   orderProduct.userOrder = userOrder;
    //   orderProduct.save();

    //   List<OrderProduct> test = OrderProduct.findByUserOrder(Long.valueOf(1));
      
    //   for(int i = 0; i < test.size(); i++){
    //     System.out.println((test.get(i).id));
    //   }

    //   Logger.debug("test");


    // return ok(  
    //   views.html.index.render()
    // );
 // }

} 