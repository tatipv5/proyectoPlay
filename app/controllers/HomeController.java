     
package controllers;

import java.util.List;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import models.Grupo;
import models.Curso;
import models.Estudiant;
import play.data.FormFactory;
import static play.mvc.Results.ok;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
 @Inject
    FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
 
 //picha/picha
    public Result index() {
        
        return ok(index.render("Gestion Instituto") );
    }
    
    


}
