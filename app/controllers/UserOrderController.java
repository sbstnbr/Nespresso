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
      views.html.userorder.render(UserOrder.find.ref(id), Product.all())
    );
  }

  public static Result newUserOrder(){
    UserOrder userOrder = new UserOrder();
    userOrder.user = User.find.ref(1L);
    userOrder.globalOrder = GlobalOrder.find.ref(1L);
    userOrder.save();
    return ok(
      views.html.userorder.render(userOrder, Product.all())
    );
  } 
} 