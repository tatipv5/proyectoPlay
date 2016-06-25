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
     List<Curso>curs=Curso.find.all();
     Form<Curso> cursoForm  = formFactory.form(Curso.class);
      return ok(crearEliminarEditarCurso.render("Listado de Curso",curs,cursoForm));
   }
     
       public Result crearCursoGet() {
            Form<Curso> cursoForm  = formFactory.form(Curso.class);
            return ok(crearCurso.render("Crear curso",
                   cursoForm,
                    routes.ControllersCurso.crearCursoPost()));
        }//Fin

    public Result crearCursoPost() {
            Form<Curso> cursoForm = formFactory.form(Curso.class).bindFromRequest();
            
            if (cursoForm.hasErrors()) {
                return badRequest(crearCurso.render( "Se han encontrado errores",
                        cursoForm, routes.ControllersCurso.indexC()));
            } else {
                Curso curso= cursoForm.get();
                curso.save();
                cursoForm = formFactory.form(Curso.class);
               
            }
            return ok(crearCurso.render("El curso ha sido creado correctamente", cursoForm ,
                    routes.ControllersCurso.crearCursoPost()));
        }//Fian del método.
    
     public Result editarCursoGet(Long id) {
        Curso instancia = Curso.find.byId(id);
        Form<Curso> cursoForm = formFactory.form(Curso.class).fill(instancia);
        return ok(crearCurso.render("Formulario de curso",
                cursoForm, routes.ControllersCurso.editarCursoPost(id)));
    }
    
    public Result editarCursoPost(Long id) {
       Curso instancia = Curso.find.byId(id);
        Form<Curso> cursoForm = formFactory.form(Curso.class
        ).fill(instancia).bindFromRequest();
        if (cursoForm .hasErrors()) {
            return badRequest(crearCurso.render(
                    "Encontramos errores", cursoForm ,
                    routes.ControllersCurso.editarCursoPost(id)
            ));
        }
      Curso curs = cursoForm.get();
  
        instancia.nombre= curs.nombre;
        instancia.identificador = curs.identificador;
        instancia.save();
        return redirect(routes.ControllersCurso.listaCursos());
    }
    
    public Result EliminarGrupo(Long id) {
        Curso instancia = Curso.find.byId(id);
        instancia.delete();
        return redirect(routes.ControllersCurso.listaGrupos());
    } 
    
}

