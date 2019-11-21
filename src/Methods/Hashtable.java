/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;
import Main.Main;
import Classes.Usuario;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pache
 */
public class Hashtable {
    /**
     * Este es el sigleton de el hash para que no se pierdan los datos
     */
    public DefaultListModel<String> LISTMODEL2 = new DefaultListModel<>();
    public static Hashtable instance = null;
    public static Hashtable getInstance(){
        if(instance == null){
            instance = new Hashtable();
        }
        return instance;
    }
    /**
     * Esta es la formula de el hash para meter los usuarios en el hash
     * @param number
     * @return el numero
     */
    private int hash(int number){
        return (number & 0xfffffff)% 13;
    }
    
    /**
     * Insert user in table
     * @param user user to insert
     * @return  TRUE if inserted or False if not inserted.
     */
    public boolean insertarUsuario(Usuario user){
        int key = this.hash(user.ID);
        
        Usuario aux = Main.listaUsuarios[key];
        
        if(aux == null){
            Main.listaUsuarios[key] = user;
        }else{
            for (;aux != null; aux = aux.sig){
                if(aux.ID == user.ID){
                    return false;
                }
                if(aux.sig == null){
                    aux.sig = user;
                    break;
                }
            }
        }
        return true;
    } 
    
    /**
     * Search user by Id
     * @param number id to search in list.
     * @return Object User if is found or NULL if not found.
     */
    public Usuario BuscarUsuario(int number){
        int key = this.hash(number);
        
        if(Main.listaUsuarios[key] == null){
            return null;
        }
        
        Usuario aux = Main.listaUsuarios[key];
        
        for (;aux != null; aux = aux.sig){
            if(aux.ID == number)
                break;
        }
        
        return aux;
       
    }
   /**
    * Impirme los usuarios registrados en el sistema
    */
    public void print(){
        LISTMODEL2.addElement("                          USUARIOS REGISTRADOS EN EL SISTEMA");
        for (int i = 0; i < Main.listaUsuarios.length; i++) {
            for (Usuario aux = Main.listaUsuarios[i]; aux != null; aux = aux.sig) {
                LISTMODEL2.addElement("Nombre: " + aux.nombre);
                LISTMODEL2.addElement("ID: " + aux.ID);
                LISTMODEL2.addElement("Licencia: " + aux.tipoLicencia);
                LISTMODEL2.addElement("---------------------------");
            }
        }
         
    }
    /**
     * Modifica los usuarios registrados en el sistema
     * @param id
     * @param nombre
     * @param licencia
     * @return true o false dependiendo del resultado de la modificacion
     */
    public boolean modificar(int id,String nombre,String licencia){
        for (int i = 0; i < Main.listaUsuarios.length; i++) {
            for (Usuario aux = Main.listaUsuarios[i]; aux != null; aux = aux.sig) {
                if(aux.ID==id){
                    aux.nombre = nombre;
                    aux.tipoLicencia = licencia;
                    return true;
                }
            }
    }
        return false;
    }
    /**
     * Elimina usuarios de la tabla hash
     * @param number
     * @return un string dependiendo de lo que paso
     */
    public String delete(int number) {
        int key = this.hash(number);

        if (Main.listaUsuarios[key] == null) {
            return null;
        }

        Usuario aux = Main.listaUsuarios[key];

        if (aux.sig == null) {
            Main.listaUsuarios[key] = null;
            return "Eliminado directo";
        }

        int index = 0;
        while (aux != null) {

            aux = aux.sig;
            index++;
        }
        Usuario arrayUsers[] = new Usuario[index];

        aux = Main.listaUsuarios[key];

        int n = 0;
        while (aux != null) {

            if (aux.ID == number) {
                System.out.println("Usuario Eliminado");

            } else {
                arrayUsers[n] = aux;
                n++;
            }
            aux = aux.sig;

        }

        Main.listaUsuarios[key] = null;

        for (int i = 0; i < index - 1; i++) {
            if (arrayUsers[i].ID == number) {

            } else {
                Usuario nUser = new Usuario(arrayUsers[i].ID, arrayUsers[i].nombre, arrayUsers[i].tipoLicencia);
                insertarUsuario(nUser);
            }

        }

        return "";

    }
}
