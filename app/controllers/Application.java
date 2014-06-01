package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import static play.data.Form.*;


import views.html.*;

public class Application extends Controller {
  
  public static class Login {      
    public String name;
    public String validate() {
      Logger.info("ApplicationLogin"+name);
      if(User.authenticate(name) == null) {
          return "Invalid user";
      }
      return null;
    }     
  }

  public static Result index() {
  	// To be removed
  	return ok(	
  		views.html.index.render()
  	);
  }

  /**
   * Login page.
   */
  public static Result login() {
      return ok(
          login.render(form(Login.class))
      );
  }
  
  /**
   * Handle login form submission.
   */
  public static Result authenticate() {
      Form<Login> loginForm = form(Login.class).bindFromRequest("name");
      Logger.info("ApplicationAuthenticate"+loginForm.toString());
      Logger.info("ApplicationAuthenticate"+loginForm.data().toString());
      if(loginForm.hasErrors()) {
          return badRequest(login.render(loginForm));
      } else {
          Long id = (Long)User.find.where().eq("name",loginForm.get().name).findIds().get(1);
          session("id", id.toString());
          return redirect(
              routes.UserController.user(id)
          );
      }
  }

  /**
   * Logout and clean the session.
   */
  public static Result logout() {
      session().clear();
      flash("success", "You've been logged out");
      return redirect(
          routes.Application.login()
      );
  }

}
