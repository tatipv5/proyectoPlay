package controllers;

import java.util.List;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import models.Estudiant;
import models.Grupo;
import play.data.FormFactory;
import static play.mvc.Results.ok;
import views.html.*;

//ME CAGO EN TODO
/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class ControllerEstudiant extends Controller {
//tomennnn

    @Inject
    FormFactory formFactory;

    @Inject
    Grupo grupo;

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a <code>GET</code> request with a
     * path of <code>/</code>.
     */
    public Result indexE() {
//me escupo
        return ok(index.render("Gestion de Estudiantes"));
    }

    public Result crearEstudiantesGet() {
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class);
        return ok(crearEstudiant.render("Matricular Estudiante.",
                EstuForm,
                routes.ControllerEstudiant.crearEstudiantesPost()));
    }//Fin

    public Result crearEstudiantesPost() {
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).bindFromRequest();
        if (EstuForm.hasErrors()) {

            return badRequest(crearEstudiant.render("Se han encontrado errores",
                    EstuForm, routes.ControllerEstudiant.indexE()));
        } else {
            Estudiant estu = EstuForm.get();
            System.out.print("paso");
            estu.save();
            System.out.println("id de estudante: " + estu.id);
            EstuForm = formFactory.form(Estudiant.class);
//            llamamos aqui el metodo hecho en el grupo, para poder guardar estudiantes en el array en grupo  
            grupo.agregarCEstud(estu.id);
            // models.Grupo.setEstudiante(estu.nombre);
            System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmm " + estu);

        }
        return ok(crearEstudiant.render("La matricula ha sido creada correctamente", EstuForm,
                routes.ControllerEstudiant.crearEstudiantesPost()));
    }//Fian del método.

    public Result listaEstudiantes() {
        List<Estudiant> estud = Estudiant.find.all();
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class);
        return ok(crearEliminarEditar.render("Listado de estudiantes", estud, EstuForm));
    }

    public Result listaInfoEstudiantes() {
        List<Estudiant> estud = Estudiant.find.all();
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class);
        return ok(informacionEstudiante.render("Listado de estudiantes", estud, EstuForm));
    }

    public Result editarEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("Formulario de estudiante",
                EstuForm, routes.ControllerEstudiant.editarEstudiantePost(id)));
    }

    public Result editarEstudiantePost(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();
        if (EstuForm.hasErrors()) {
            return badRequest(crearEstudiant.render(
                    "Encontramos errores", EstuForm,
                    routes.ControllerEstudiant.editarEstudiantePost(id)
            ));
        }
        Estudiant estu = EstuForm.get();
        //instancia.id = estu.id;
        instancia.nombre = estu.nombre;
        instancia.direccion = estu.direccion;
        instancia.cedula = estu.cedula;
        instancia.telefono = estu.telefono;
        instancia.requerida = estu.requerida;
        instancia.save();
        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    }

    public Result BuscarEstudianteGet(Long ced) {
        Estudiant instancia = (Estudiant) Estudiant.find.byId(ced);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("estudiante",
                EstuForm, routes.ControllerEstudiant.BuscarEstudiantePost(ced)));
    }

    public Result BuscarEstudiantePost(Long ced) {
        Estudiant instancia = (Estudiant) Estudiant.find.byId(ced);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();

        if (EstuForm.hasErrors()) {
            return badRequest(crearEstudiant.render(
                    "Encontramos errores", EstuForm,
                    routes.ControllerEstudiant.BuscarEstudiantePost(ced)
            ));
        }
        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    }

    public Result EliminarEstudiante(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        instancia.delete();
        return redirect(routes.ControllerEstudiant.listaEstudiantes());
    }

    //InformaciónEstudiant
    public Result InformacionEstudianteGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("Formulario de estudiante",
                EstuForm, routes.ControllerEstudiant.InformacionEstudiantePost(id)));
    }
    //lol

    public Result InformacionEstudiantePost(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();
        return redirect(routes.ControllerEstudiant.listaInfoEstudiantes());
    }

    //INFORMACION\
    public Result mostrarInfoGet(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class).fill(instancia);
        return ok(crearEstudiant.render("Informacion Estudiante.",
                EstuForm,
                routes.ControllerEstudiant.mostrarInfoPost(id)));
    }//Fin

    public Result mostrarInfoPost(Long id) {
        Estudiant instancia = Estudiant.find.byId(id);
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class
        ).fill(instancia).bindFromRequest();
        return redirect(routes.ControllerEstudiant.listaEstudiantes());

    }
//jaaja

    public Result listaMostrarEstudiantes() {
        List<Estudiant> estud = Estudiant.find.all();
        Form<Estudiant> EstuForm = formFactory.form(Estudiant.class);
        return ok(mostrarInfoEstudiante.render("Listado de estudiantes", estud, EstuForm));
    }
}//Fin de la clase
