/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Sebas
 */
public class Usuario {
    public int ID;
    public String nombre;
    public String tipoLicencia;
    public Usuario sig;

    public Usuario(int ID, String nombre, String tipoLicencia) {
        this.ID = ID;
        this.nombre = nombre;
        this.tipoLicencia = tipoLicencia;
    }    
    
}
