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
      if(User.findByName(name) == null) {
          return "Invalid user";
      }
      return null;
    }     
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
      Form<Login> loginForm = form(Login.class).bindFromRequest();
      Logger.info("ApplicationAuthenticate"+loginForm.toString());
      Logger.info("ApplicationAuthenticate"+loginForm.data().toString());
      if(loginForm.hasErrors()) {
          return badRequest(login.render(loginForm));
      } else {
          String name = loginForm.get().name;
          session("name", name);
          Logger.info(session("name"));
          return redirect(
              routes.GlobalOrderController.globalOrderList()
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
