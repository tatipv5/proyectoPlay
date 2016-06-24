


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
    
    
    
//     public Result listaGrupos() {
//     List<Grupo>Grup=Grupo.find.all();
//     Form<Grupo> grupForm  = formFactory.form(Grupo.class);
//      return ok(crearGrupo.render("Listado de Grupo",Grup));
//   }
}
