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
public class Curso extends Model {
    
    
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String nombre;
    
    @Constraints.Required
    public String identificador;
    
    
    static public ArrayList<Grupo>listaGrupos =new ArrayList<>();
     static public ArrayList<Docente>listaDocentes =new ArrayList<>();
     
    public static Finder<Long, Curso> find = new Finder<Long, Curso>(Curso.class); 
}