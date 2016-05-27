package controllers;

import java.util.List;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
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
        return ok(index.render("Gestion Instituto"));
    }
    //hola
    //get donde se crea la vEstudiante
    public Result crearEstudiantesGet() {
        Form<Estudiant> EstuForm  = formFactory.form(Estudiant.class);
        return ok(crearEstudiant.render("Matricular Estudiante",
               EstuForm,
                routes.HomeController.crearEstudiantesPost()));
    }
    7
    //set, donde creo el post para el localhost nos salga en el servidor
     public Result crearEstudiantesPost() {
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).bindFromRequest();
        if (EstuForm.hasErrors()) {
            return badRequest(crearEstudiant.render("Encontramos errores",
                    EstuForm, routes.HomeController.index()));
        } else {
            Estudiant estu = EstuForm.get();
            estu.save();
            EstuForm = formFactory.form(Estudiant.class);
        }
        return ok(crearEstudiant.render("La matricula ha sido creada correctamente", EstuForm ,
                routes.HomeController.crearEstudiantesPost()));
    }
      public Result listaEstudiantes() {
        List<Estudiant> estu = Estudiant.find.all();

        return ok(crearEliminareditar.render("Listado de estudiantes", estu));
    }
     
     public Result editarEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("Formulario de estudiante",
                EstuForm, routes.HomeController.editarEstudiantePost(id)));
    }

    public Result editarEstudiantePost(Long id) {
       Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();

        if (EstuForm .hasErrors()) {
            return badRequest(crearEstudiant.render(
                    "Encontramos errores", EstuForm ,
                    routes.HomeController.editarEstudiantePost(id)
            ));
        }
        
      Estudiant estu = EstuForm.get();
        instancia.id = estu.id;
        instancia.nombre= estu.nombre;
        instancia.direccion = estu.direccion;
        instancia.telefono = estu.telefono;
        instancia.save();
        return redirect(routes.HomeController.crearEliminareditar());
    }
     public Result EliminarEstudiante(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        instancia.delete();
        return redirect(routes.HomeController.crearEliminareditar());
    } 

}
