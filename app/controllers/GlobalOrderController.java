package controllers;

import models.*;
import java.util.*;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class GlobalOrderController extends Controller {

  static Form<GlobalOrder> globalOrderForm = Form.form(GlobalOrder.class);

  public static Result globalOrderList() {
    return ok(
      views.html.globalorderlist.render(GlobalOrder.all())
    );
  }

  public static Result newGlobalOrder(){
    Form<GlobalOrder> filledForm = globalOrderForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return redirect(routes.GlobalOrderController.globalOrderList()); 
    } else {
      GlobalOrder globalOrder = filledForm.get();
      GlobalOrder.create(globalOrder);
      return redirect(routes.GlobalOrderController.globalOrderList()); 
  	}
  }

} 