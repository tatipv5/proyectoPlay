package controllers;

import java.util.List;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import models.Docente;

import play.data.FormFactory;
import static play.mvc.Results.ok;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
    public class ControllerDocente extends Controller {
     @Inject
        FormFactory formFactory;
        /**
         * An action that renders an HTML page with a welcome message.
         * The configuration in the <code>routes</code> file means that
         * this method will be called when the application receives a
         * <code>GET</code> request with a path of <code>/</code>.
         */
     
     public Result indexD() {
        
        return ok(index.render("Gestion de Docentes") );
    }
         
    public Result crearDocenteGet() {
       Form<Docente> docenForm = formFactory.form(Docente.class);
       return ok(crearDocente.render("Ingresar docente al sistema",
           docenForm,
           routes.ControllerDocente.crearDocentesPost()));  
      }//Fin del Get crearDocente

    //set, donde creo el post para el localhost nos salga en el servidor
    public Result crearDocentesPost() {
    Form<Docente> docenForm = formFactory.form(Docente.class).bindFromRequest();
    if (docenForm.hasErrors()) {
        return badRequest(crearDocente.render("Se han encontrado errores",
                docenForm, routes.ControllerDocente.indexD()));
    } else {
        Docente docen = docenForm.get();
        docen.save();
        docenForm = formFactory.form(Docente.class);
    }
    return ok(crearDocente.render("EL profesor se ha agregado de forma correcta", docenForm,
            routes.ControllerDocente.crearDocentesPost()));
    }//Fin del Post crearDocente
         
     public Result listaDocentes() {
       List<Docente>docen=Docente.find.all();
       Form<Docente> DoceForm  = formFactory.form(Docente.class);
       return ok(crearEliminarEditarDocente.render("Listado de docentes", docen,DoceForm));
    }
     
     public Result listaInfoDocentes(){
         List<Docente>docen=Docente.find.all();
         Form<Docente>DoceForm = formFactory.form(Docente.class);
         return ok(informacionDocente.render("Lista de Docentes", docen, DoceForm));
     }
     
     
     public Result editarDocenteGet(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class).fill(instancia);
        return ok(crearDocente.render("Formulario de estudiante",
                DocenForm, routes.ControllerDocente.EditarDocentePost(id)));
    }
     
     public Result EditarDocentePost(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class
        ).fill(instancia).bindFromRequest();
        if (DocenForm .hasErrors()) {
            return badRequest(crearDocente.render(
                    "Encontramos errores", DocenForm ,
                    routes.ControllerDocente.EditarDocentePost(id)
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
        return redirect(routes.ControllerDocente.listaDocentes());
    }
     
     public Result EliminarDocente(Long id) {
        Docente instancia = Docente.find.byId(id);
        instancia.delete();
        return redirect(routes.ControllerDocente.listaDocentes());
    } 

        public Result BuscarDocenteGet(Long id) {
        Docente instancia = Docente.find.byId(id);
        Form<Docente> EstuForm = formFactory.form(Docente.class).fill(instancia);
        return ok(crearDocente.render("Docente",
                EstuForm, routes.ControllerDocente.BuscarDocentePost(id)));
    }

     public Result BuscarDocentePost(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> EstuForm = formFactory.form(Docente.class
        ).fill(instancia).bindFromRequest();

        if (EstuForm .hasErrors()) {
            return badRequest(crearDocente.render(
                    "Encontramos errores", EstuForm ,
                    routes.ControllerDocente.BuscarDocentePost(id)
            ));
        }
        
    
        return redirect(routes.ControllerDocente.listaDocentes());
    }
     
     
     
     //Informacion docente
    public Result InformacionDocentesGet(Long id) {
        Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class).fill(instancia);
        return ok(crearDocente.render("Informacion Docente",
               DocenForm, routes.ControllerDocente.InformacionDocentePost(id)));
    }
    
     public Result InformacionDocentePost(Long id) {
       Docente instancia = Docente.find.byId(id);
        Form<Docente> DocenForm = formFactory.form(Docente.class
        ).fill(instancia).bindFromRequest();
        return redirect(routes.ControllerDocente.listaInfoDocentes());
    }
     
 
     public Result mostrarInfoGetDocente(Long id) {
        Docente instancia = Docente.find.byId(id);
        Form<Docente> EstuForm = formFactory.form(Docente.class).fill(instancia);
        return ok(crearDocente.render("Informacion Estudiante.",
                EstuForm,
                routes.ControllerDocente.mostrarInfoPostDocente(id)));   //Ingresar Routes
    }//FIn 
     
     public Result mostrarInfoPostDocente(Long id) {
         Docente instancia = Docente.find.byId(id);
        Form<Docente> EstuForm = formFactory.form(Docente.class).fill(instancia).bindFromRequest();
        return redirect(routes.ControllerDocente.listaMostrarDocentes()); //Ingresar Routes
    }//Fian del método.
     
     public Result listaMostrarDocentes() {
        List<Docente> docen = Docente.find.all();
        Form<Docente> docenForm = formFactory.form(Docente.class);
        return ok(mostrarInfoDocente.render("Listado de docentes", docen, docenForm));
    }//Fin 
     

     
    
    }//Fin de la clase.