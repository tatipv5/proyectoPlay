/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints;

/**
 *
 * @author usuario
 */
@Entity
public class Docente extends Model{
    @Id
    @Constraints.Min(10)
    public Long id;
    
    
    //MARIA
    @Constraints.Required
    public String nombre;
     
     @Constraints.Required
    public String materia;
     
      @Constraints.Required
    public String cedula;
 
        @Constraints.Required
    public String telefono;
     

     public ArrayList<Curso>listaCursos =new ArrayList<>();
     public ArrayList<Grupo>listaGrupos =new ArrayList<>();
    //VERIFICAR ESTA VARIABLE
     static Curso miCurso;
     //AGREGAR CONDICIONES POR SI NECESTAM0S UN CONTADOR
     //se agrego el metodo agregarProfesor OJO
    public  boolean agregarProfesor(Curso curso,Grupo grupo){
        
      if(miCurso.equals(curso)){
          System.out.println("ya esta ese curso");
      }
      else{
           listaCursos.add(curso);
           listaGrupos.add(grupo);
           return true;
           }
    return false;
   }
    
    public static Finder<Long, Docente> find = new Finder<Long, Docente>(Docente.class); 
    
}
