


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import static play.mvc.Results.redirect;
import views.html.*;
/**
 *
 * @author usuario
 */
public class ControllerGrupo extends Controller {
 @Inject
    FormFactory formFactory;
 
 
 public Result indexG() {
        
        return ok(index.render("Gestion Instituto") );
    }
 
 public Result listaGrupos() {
     List<Grupo>grup=Grupo.find.all();
     Form<Grupo> grupForm  = formFactory.form(Grupo.class);
      return ok(crearEliminarEditarGrupo.render("Listado de Grupo",grup, grupForm));
   }
    
    public Result crearGrupoGet() {
            Form<Grupo> grupoForm  = formFactory.form(Grupo.class);
            return ok(crearGrupo.render("Crear grupo",
                   grupoForm,
                    routes.ControllerGrupo.crearGrupoPost()));
        }//Fin

    public Result crearGrupoPost() {
            Form<Grupo> grupoForm = formFactory.form(Grupo.class).bindFromRequest();
            
            if (grupoForm.hasErrors()) {
                return badRequest(crearGrupo.render( "Se han encontrado errores",
                        grupoForm, routes.ControllerGrupo.indexG()));
            } else {
                Grupo grup= grupoForm.get();
                grup.save();
                grupoForm = formFactory.form(Grupo.class);
               
            }
            return ok(crearGrupo.render("El grupo  ha sido creado correctamente", grupoForm ,
                    routes.ControllerGrupo.crearGrupoPost()));
        }//Fian del método.
    
    public Result editarGrupoGet(Long id) {
        Grupo instancia = Grupo.find.byId(id);
        Form<Grupo> grupoForm = formFactory.form(Grupo.class).fill(instancia);
        return ok(crearGrupo.render("Formulario de grupo",
                grupoForm, routes.ControllerGrupo.editarGrupoPost(id)));
    }
    
    public Result editarGrupoPost(Long id) {
       Grupo instancia = Grupo.find.byId(id);
        Form<Grupo> grupoForm = formFactory.form(Grupo.class
        ).fill(instancia).bindFromRequest();
        if (grupoForm .hasErrors()) {
            return badRequest(crearGrupo.render(
                    "Encontramos errores", grupoForm ,
                    routes.ControllerGrupo.editarGrupoPost(id)
            ));
        }
      Grupo grup = grupoForm.get();
  
        instancia.nombre= grup.nombre;
        instancia.identificador = grup.identificador;
        instancia.save();
        return redirect(routes.ControllerGrupo.listaGrupos());
    }
    
    public Result EliminarGrupo(Long id) {
        Grupo instancia = Grupo.find.byId(id);
        instancia.delete();
        return redirect(routes.ControllerGrupo.listaGrupos());
    } 
 //InfoGrupo
    public Result InformacionGrupoGet(Long id) {
        Grupo instancia = Grupo.find.byId(id);
        Form<Grupo> grupForm = formFactory.form(Grupo.class).fill(instancia);
        return ok(crearGrupo.render("Formulario de grupo",
                grupForm, routes.ControllerGrupo.InformacionGrupoPost(id)));
    }
    //lol

    public Result InformacionGrupoPost(Long id) {
        Grupo instancia = Grupo.find.byId(id);
        Form<Grupo> grupForm = formFactory.form(Grupo.class
        ).fill(instancia).bindFromRequest();
        return redirect(routes.ControllerGrupo.listaInfoGrupo());
    }
      public Result listaInfoGrupo() {
        List<Grupo> grup = Grupo.find.all();
        Form<Grupo> grupForm = formFactory.form(Grupo.class);
        return ok(informacionGrupo.render("Listado de grupos", grup, grupForm));
    }
   
      
      public Result mostrarInfoGetGrupo(Long id) {
       Grupo instancia = Grupo.find.byId(id);
       Form<Grupo> grupoForm = formFactory.form(Grupo.class).fill(instancia);
       return ok(crearGrupo.render("Informacion Estudiante.",
              grupoForm,
              routes.ControllerGrupo.mostrarInfoPostGrupo(id)));
        }//Fin
      
      public Result mostrarInfoPostGrupo(Long id) {
        Grupo instancia = Grupo.find.byId(id);
        Form<Grupo> grupoForm = formFactory.form(Grupo.class
        ).fill(instancia).bindFromRequest();
        return redirect(routes.ControllerGrupo.listaGrupos());
      }
       public Result listaMostrarGrupo() {
        List<Grupo> grupo = Grupo.find.all();
        Form<Grupo> grupoForm = formFactory.form(Grupo.class);
        return ok(mostrarInfoGrupo.render("Listado de cursos", grupo, grupoForm));
    }
}
