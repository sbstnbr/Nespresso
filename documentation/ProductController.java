package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class ProductController extends Controller {

  static Form<Product> productForm = Form.form(Product.class);

  public static Result products() {
    return ok(
  		views.html.product.render(Product.all(), productForm)
  	);
	}  

  public static Result newProduct() {
  	Form<Product> filledForm = productForm.bindFromRequest();
	  if(filledForm.hasErrors()) {
	    return badRequest(
	      views.html.product.render(Product.all(), filledForm)
	    );
	  } else {
			Product.create(filledForm.get());
    	return redirect(routes.ProductController.products()); 
    } 
	} 

	public static Result deleteProduct(Long id) {
		Product.delete(id);
    return redirect(routes.ProductController.products());

	}
} 