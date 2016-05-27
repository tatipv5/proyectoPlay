package controllers;

import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import models.Estudiant;
import models.Docente;
import play.data.FormFactory;

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
        return ok(index.render("Gestion Instituto"));
    }
    
    //hola
    //get donde se crea la vEstudiante
    public Result crearEstudiantesGet() {
        Form<Estudiant> pregForm = formFactory.form(Estudiant.class);
        return ok(crearEstudiant.render("Matricular Estudiante",
                pregForm,
                routes.HomeController.crearEstudiantesPost()));
    }
    //set, donde creo el post para el localhost nos salga en el servidor
     public Result crearEstudiantesPost() {
        Form<Estudiant> pregForm = formFactory.form(Estudiant.class).bindFromRequest();
        if (pregForm.hasErrors()) {
            return badRequest(crearEstudiant.render("Encontramos errores",
                    pregForm, routes.HomeController.index()));
        } else {
            Estudiant preg = pregForm.get();
            preg.save();
            pregForm = formFactory.form(Estudiant.class);
        }
        return ok(crearEstudiant.render("La matricula ha sido creada correctamente", pregForm,
                routes.HomeController.crearEstudiantesPost()));
    }
     
     //Bryam Blanco
     public Result crearDocenteGet() {
        Form<Docente> pregForm = formFactory.form(Docente.class);
        return ok(crearDocente.render("Ingresar docente al sistema",
                pregForm,
                routes.HomeController.crearDocentesPost()));  
    } //Fin del Get crearDocente
     
     //set, donde creo el post para el localhost nos salga en el servidor
     public Result crearDocentesPost() {
        Form<Docente> pregForm = formFactory.form(Docente.class).bindFromRequest();
        if (pregForm.hasErrors()) {
            return badRequest(crearDocente.render("Encontramos errores",
                    pregForm, routes.HomeController.index()));
        } else {
            Docente preg = pregForm.get();
            preg.save();
            pregForm = formFactory.form(Docente.class);
        }
        return ok(crearDocente.render("EL profesor se ha agregado de forma correcta", pregForm,
                routes.HomeController.crearDocentesPost()));
    }//Fin del Post crearDocente
     

}//Fin de la clase
