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
 
    public class ControllerEstudiant extends Controller {
    @Inject
    FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
 
    public Result indexE() {
        
        return ok(index.render("Gestion de Estudiantes") );
    }
 
    public Result crearEstudiantesGet() {
            Form<Estudiant> EstuForm  = formFactory.form(Estudiant.class);
            return ok(crearEstudiant.render("Matricular Estudiante.",
                   EstuForm,
                    routes.ControllerEstudiant.crearEstudiantesPost()));
        }//Fin

    public Result crearEstudiantesPost() {
            Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).bindFromRequest();

            if (EstuForm.hasErrors()) {

                return badRequest(crearEstudiant.render( "Se han encontrado errores",
                        EstuForm, routes.ControllerEstudiant.indexE()));
            } else {
                Estudiant estu = EstuForm.get();
                estu.save();
                EstuForm = formFactory.form(Estudiant.class);
            }
            return ok(crearEstudiant.render("La matricula ha sido creada correctamente", EstuForm ,
                    routes.ControllerEstudiant.crearEstudiantesPost()));
        }//Fian del método.
    
        public Result listaEstudiantes() {
       List<Estudiant>estud=Estudiant.find.all();
       Form<Estudiant> EstuForm  = formFactory.form(Estudiant.class);
       return ok(crearEliminarEditar.render("Listado de estudiantes", estud,EstuForm));
    }
        
    public Result editarEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("Formulario de estudiante",
                EstuForm, routes.ControllerEstudiant.editarEstudiantePost(id)));
    }
    
    public Result editarEstudiantePost(Long id) {
       Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();
        if (EstuForm .hasErrors()) {
            return badRequest(crearEstudiant.render(
                    "Encontramos errores", EstuForm ,
                    routes.ControllerEstuduiant.editarEstudiantePost(id)
            ));
        }
      Estudiant estu = EstuForm.get();
        //instancia.id = estu.id;
        instancia.nombre= estu.nombre;
        instancia.direccion = estu.direccion;
        instancia.cedula=estu.cedula;
        instancia.telefono = estu.telefono;
        instancia.requerida=estu.requerida;
        instancia.save();
        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    }
    
    public Result BuscarEstudiantePost(Long id) {
       Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();

        if (EstuForm .hasErrors()) {
            return badRequest(crearEstudiant.render(
                    "Encontramos errores", EstuForm ,
                    routes.ControllerEstudiant.BuscarEstudiantePost(id)
            ));
        }
        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    }
    
    public Result BuscarEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("estudiante",
                EstuForm, routes.HomeController.BuscarEstudiantePost(id)));
    }
    
     public Result EliminarEstudiante(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        instancia.delete();
        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    } 
    
    //InformaciónEstudiant
    public Result InformacionEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(informacionEstudiante.render("Informacion Estudiantes",
                EstuForm, routes.ControllerEstudiant.InformacionEstudiantePost(id)));
    }
    
     public Result InformacionEstudiantePost(Long id) {
       Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();
        if (EstuForm .hasErrors()) {
            return badRequest(informacionEstudiante.render(
                    "Encontramos errores", EstuForm ,
                    routes.ControllerEstudiant.InformacionEstudianteGet(id)
            ));
        }

        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    }     

}//Fin de la clase