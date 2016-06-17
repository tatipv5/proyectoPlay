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
public class ControllersCurso extends Controller {
 @Inject
    FormFactory formFactory;
 
 
 public Result indexC() {
        
        return ok(index.render("Gestion Instituto") );
    }
    
    
    
     public Result listaCursos() {
     List<Curso>Cur=Curso.find.all();
     Form<Curso> cursoForm  = formFactory.form(Curso.class);
    
      return ok(crearCurso.render("Listado de curso",Cur));
   }
}

