package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class UserController extends Controller {
  
  static Form<User> userForm = Form.form(User.class);

  public static Result user(String name) {
    return ok(
      views.html.userorderlist.render(User.findByName(name))
    );
  }
}
