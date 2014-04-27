package controllers;

import models.*;
import java.util.*;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class GlobalOrderController extends Controller {

  public static Result globalOrderList() {
    return ok(
      views.html.globalorderlist.render(GlobalOrder.all())
    );
  }

} 