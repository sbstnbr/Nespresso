package controllers;

import models.OrderProduct;
import models.Product;
import models.UserOrder;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class OrderProductController extends Controller {

  static Form<OrderProduct> orderProductForm = Form.form(OrderProduct.class);

  public static Result newOrderProduct(Long uoid){
    Form<OrderProduct> filledForm = orderProductForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return redirect(routes.UserOrderController.userOrder(uoid)); 
    } else {
      OrderProduct orderProduct = filledForm.get();
      orderProduct.quantity = 10;
      orderProduct.userOrder = new UserOrder();
      orderProduct.userOrder.id = uoid;
      OrderProduct.create(orderProduct);
      return redirect(routes.UserOrderController.userOrder(uoid)); 
    }
  }
  
	public static Result deleteOrderProduct(Long id) {
    Long userOrderId = OrderProduct.find.ref(id).userOrder.id;
    OrderProduct.delete(id);
    return redirect(routes.UserOrderController.userOrder(userOrderId));
	}

  public static Result increaseQuantity(Long id) { 
    OrderProduct orderProduct = OrderProduct.find.ref(id);
    Long userOrderId = orderProduct.userOrder.id;
    orderProduct.increaseQuantity();
    return redirect(routes.UserOrderController.userOrder(userOrderId));
  }

  public static Result decreaseQuantity(Long id) {
    OrderProduct orderProduct = OrderProduct.find.ref(id);
    Long userOrderId = orderProduct.userOrder.id;
    orderProduct.decreaseQuantity();    
    return redirect(routes.UserOrderController.userOrder(userOrderId));
  }
} 