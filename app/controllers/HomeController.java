     
package controllers;

import java.util.List;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import models.Estudiant;
import models.Docente;
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
    //Maria
    //hola
    //get donde se crea la vEstudiante
    public Result crearEstudiantesGet() {
        Form<Estudiant> EstuForm  = formFactory.form(Estudiant.class);
        return ok(crearEstudiant.render("Matricular Estudiante.",
               EstuForm,
                routes.HomeController.crearEstudiantesPost()));
    }//Fin

    //set, donde creo el post para el localhost nos salga en el servidor
     public Result crearEstudiantesPost() {
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).bindFromRequest();
       
        if (EstuForm.hasErrors()) {
            
            return badRequest(crearEstudiant.render( "Se han encontrado errores",
                    EstuForm, routes.HomeController.index()));
        } else {
            Estudiant estu = EstuForm.get();
            estu.save();
            EstuForm = formFactory.form(Estudiant.class);
        }
        return ok(crearEstudiant.render("La matricula ha sido creada correctamente", EstuForm ,
                routes.HomeController.crearEstudiantesPost()));
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
        //instancia.id = estu.id;
        instancia.nombre= estu.nombre;
        instancia.direccion = estu.direccion;
        instancia.cedula=estu.cedula;
        instancia.telefono = estu.telefono;
        instancia.requerida=estu.requerida;
        instancia.save();
        return redirect(routes.HomeController.listaEstudiantes());
    }
       public Result BuscarEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("estudiante",
                EstuForm, routes.HomeController.BuscarEstudiantePost(id)));
    }

     public Result BuscarEstudiantePost(Long id) {
       Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();

        if (EstuForm .hasErrors()) {
            return badRequest(crearEstudiant.render(
                    "Encontramos errores", EstuForm ,
                    routes.HomeController.BuscarEstudiantePost(id)
            ));
        }
        
    
        return redirect(routes.HomeController.listaEstudiantes());
    }
     
     
     //DOCENTE
     public Result crearDocenteGet() {
        Form<Docente> docenForm = formFactory.form(Docente.class);
        return ok(crearDocente.render("Ingresar docente al sistema",
                docenForm,
                routes.HomeController.crearDocentesPost()));  
    } //Fin del Get crearDocentecc
     
     //set, donde creo el post para el localhost nos salga en el servidor
     public Result crearDocentesPost() {
        Form<Docente> docenForm = formFactory.form(Docente.class).bindFromRequest();
        if (docenForm.hasErrors()) {
            return badRequest(crearDocente.render("Se han encontrado errores",
                    docenForm, routes.HomeController.index()));
        } else {
            Docente docen = docenForm.get();
            docen.save();
            docenForm = formFactory.form(Docente.class);
        }
        return ok(crearDocente.render("EL profesor se ha agregado de forma correcta", docenForm,
                routes.HomeController.crearDocentesPost()));
    }//Fin del Post crearDocente
     
     public Result listaDocentes() {
       List<Docente>docen=Docente.find.all();
       Form<Docente> DoceForm  = formFactory.form(Docente.class);
       return ok(crearEliminarEditarDocente.render("Listado de docentes", docen,DoceForm));
    }

      public Result editarDocenteGet(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class).fill(instancia);
        return ok(crearDocente.render("Formulario de estudiante",
                DocenForm, routes.HomeController.EditarDocentePost(id)));
    }
    
    public Result EditarDocentePost(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class
        ).fill(instancia).bindFromRequest();
        if (DocenForm .hasErrors()) {
            return badRequest(crearDocente.render(
                    "Encontramos errores", DocenForm ,
                    routes.HomeController.EditarDocentePost(id)
            ));
        }
        
      Docente doce = DocenForm.get();
        //instancia.id = estu.id;
        instancia.nombre= doce.nombre;
        instancia.materia = doce.materia;
        instancia.cedula=doce.cedula;
        instancia.telefono = doce.telefono;
        //instancia.requerida=estu.requerida;
        instancia.save();
        return redirect(routes.HomeController.listaDocentes());
    }
    
    
    
     public Result EliminarEstudiante(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        instancia.delete();
        return redirect(routes.HomeController.listaEstudiantes());
    } 
     
     
     
      public Result EliminarDocente(Long id) {
        Docente instancia = Docente.find.byId(id);
        instancia.delete();
        return redirect(routes.HomeController.listaDocentes());
    } 

 
     public Result BuscarDocenteGet(Long id) {
        Docente instancia = Docente.find.byId(id);
        Form<Docente> EstuForm = formFactory.form(Docente.class).fill(instancia);
        return ok(crearDocente.render("Docente",
                EstuForm, routes.HomeController.BuscarDocentePost(id)));
    }

     public Result BuscarDocentePost(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> EstuForm = formFactory.form(Docente.class
        ).fill(instancia).bindFromRequest();

        if (EstuForm .hasErrors()) {
            return badRequest(crearDocente.render(
                    "Encontramos errores", EstuForm ,
                    routes.HomeController.BuscarDocentePost(id)
            ));
        }
        
    
        return redirect(routes.HomeController.listaEstudiantes());
    }

     
    
     
     //Informacion estudiante
    public Result InformacionEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(informacionEstudiante.render("Informacion Estudiantes",
                EstuForm, routes.HomeController.InformacionEstudiantePost(id)));
    }
     public Result InformacionEstudiantePost(Long id) {
       Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();
        if (EstuForm .hasErrors()) {
            return badRequest(informacionEstudiante.render(
                    "Encontramos errores", EstuForm ,
                    routes.HomeController.InformacionEstudianteGet(id)
            ));
        }

        return redirect(routes.HomeController.listaEstudiantes());
    }
     
      //Informacion docente
    public Result InformacionDocentesGet(Long id) {
        Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class).fill(instancia);
        return ok(informacionDocente.render("Informacion Docente",
                DocenForm, routes.HomeController.InformacionDocentePost(id)));
    }
     public Result InformacionDocentePost(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class
        ).fill(instancia).bindFromRequest();
        if (DocenForm .hasErrors()) {
            return badRequest(informacionDocente.render(
                    "Encontramos errores", DocenForm ,
                    routes.HomeController.InformacionDocentePost(id)
            ));
        }

        return redirect(routes.HomeController.listaEstudiantes());
    }
}
