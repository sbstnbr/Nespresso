package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class UserController extends Controller {
  
  static Form<User> userForm = Form.form(User.class);

  public static Result user(Long id) {
   id = 1L;
   return ok(
      views.html.userorderlist.render(User.find.ref(id))
    );
  }

  public static Result users() {
    return ok(
  		views.html.user.render(User.all(), userForm)
  	);
	}  

  public static Result newUser() {
  	Form<User> filledForm = userForm.bindFromRequest();
	  if(filledForm.hasErrors()) {
	    return badRequest(
	      views.html.user.render(User.all(), filledForm)
	    );
	  } else {
				User.create(filledForm.get());
    		return redirect(routes.UserController.users()); 
    } 
	} 

	public static Result deleteUser(Long id) {
		User.delete(id);
    return redirect(routes.UserController.users());
	} 

}
