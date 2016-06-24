


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
               // models.Grupo.agregarCEstud(grup.id);
            }
            return ok(crearGrupo.render("El grupo  ha sido creado correctamente", grupoForm ,
                    routes.ControllerGrupo.crearGrupoPost()));
        }//Fian del método.
    
    

}
