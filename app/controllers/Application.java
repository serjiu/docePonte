package controllers;

import models.Login;
import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
	public static Result index() {

		return ok(index.render(form(Login.class)));
	}

    public static Result authenticate() {

    	System.out.println("PRINT 1");
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
        
        	System.out.println("PRINT 2");
        	return badRequest("FAIL!!!");
        } else {
        	System.out.println("PRINT 3");
            session("email", loginForm.get().email);
            return ok("YAYYYY");
            //return redirect(routes.Dashboard.index());
        }
    }
    
    
	public static Result registUser() {

		return ok(registUser.render("Your new application is ready.",
				form(User.class)));
	}
	
	public static Result registUserSubmit() {

		Form<User> userForm = form(User.class).bindFromRequest();

		if (userForm.hasErrors()) {
			System.out.println("Chegou cá sem sucesso!!!");
			return badRequest(registUser.render("Has Errors",
					userForm));
		}

		System.out.println("Chegou cá com sucesso!!!");

		return redirect(routes.Application.index());
	}
}