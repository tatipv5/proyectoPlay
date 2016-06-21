/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class ArrayProfesor {
      private ArrayList<Curso> listaCurso;
 
      public ArrayProfesor(){
         listaCurso=new ArrayList<Curso>();
  
        }
  
    public void insertarCurso(Curso curso){  
    listaCurso.add(curso);
    JOptionPane.showMessageDialog(null,"Persona insertada a la lista");
    }
    
    
 





}

