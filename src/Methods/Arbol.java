/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.Pedidos;
import javax.swing.DefaultListModel;

/**
 *
 * @author pache
 */
public class Arbol {
    /**
     * Esto son algunas variables necesarias en los metodos de los arboles
     */
    public Pedidos variable;
   public DefaultListModel<String> LISTMODEL = new DefaultListModel<>();
    public boolean bandera = false;
    int alt =0;
    int hojas = 0;
    int nodos = 0;
    //sigleton del arbol para que no se pierdan los datos
    public static Arbol instance = null;
    public static Arbol getInstance(){
        if(instance == null){
            instance = new Arbol();
        }
        return instance;
    }
    
    public Pedidos raiz;
    /**
     * Este es el metodo encargado de insertar los pedidos en un arbol
     * @param id
     * @param aux
     * @param peso
     * @param origen
     * @param destino
     * @param conductor
     * @return boolean para indicar si se realizo la accion o no
     */
    public boolean insertar (int id, Pedidos aux,int peso,String origen,String destino,String conductor){
        Pedidos nuevo = new Pedidos(id,peso,destino,origen,conductor);
        if (raiz==null){
            raiz = nuevo;
            bandera = true;
            return true;
        }
        if (id==aux.id){
            bandera = false;
            return false;
        }
        if (id < aux.id){
            if (aux.izq==null){
                aux.izq = nuevo;
                bandera = true;
                return true;
            }
            insertar (id, aux.izq,peso,origen,destino,conductor);
        }else{
            if (aux.der==null){
                aux.der = nuevo;
                bandera = true;
                return true;
            }
            insertar(id, aux.der,peso,origen,destino,conductor);
        }
      return bandera;
    }
    /**
     * Este metodo es el encargado de imprimir los pedidos ordenados
     * @param aux 
     */
    public void imprimir(Pedidos aux){
        if (aux == null){return;}
            imprimir(aux.izq);
            LISTMODEL.addElement("ID: " + aux.id);
            LISTMODEL.addElement("Usuario: " + aux.conductor);
            LISTMODEL.addElement("Origen: "+aux.origen);
            LISTMODEL.addElement("Destino: "+aux.destino);
            LISTMODEL.addElement("Peso: " + aux.peso);
            LISTMODEL.addElement("------------------");
            imprimir (aux.der);   
    }
    /**
     * Este metodo es el encargado de eliminar pedidos del el arbol :D
     * @param aux
     * @param num
     * @return 
     */
    public boolean Eliminar(Pedidos aux,int num){
            if(raiz.id == num){
                if(raiz.izq==null &&raiz.der==null){
                    raiz=null;
                    return true;
                }
                if(raiz.izq==null){
                    raiz=raiz.der;
                    return true;
                }
                if(raiz.der==null){
                    raiz=raiz.izq;
                    return true;
                }
                aux=aux.izq;
                if(aux.der==null){
                    aux.der=raiz.der;
                    raiz=aux;
                    return true;
                }
                while(aux.der!=null){
                    aux=aux.der;
                }
                aux.der=raiz.der;
                raiz=raiz.izq;
                return true;
            }
            Pedidos anterior = aux;
            Pedidos aux2;
            while(aux!=null){
                if(aux.id==num){
                    break;
                }
                if (aux.id<num){
                    anterior = aux;
                    aux = aux.der;
                }
                if (aux.id>num){
                    anterior = aux;
                    aux = aux.izq;
                }
            }
            if(aux==null){
                return false;
            }
            if(aux.der ==null &&aux.izq==null){
                if(anterior.id>aux.id){
                    anterior.izq=null;
                    return true;
                }
                if(anterior.id<aux.id){
                    anterior.der=null;
                    return true;
                }
            }
            if(aux.izq==null){
                if(anterior.id>aux.id){
                    anterior.izq=aux.der;
                    return true;
                }
                if(anterior.id<aux.id){
                    anterior.der=aux.der;
                    return true;
                }
                
            }
            if(aux.der == null){
                if(anterior.id>aux.id){
                    anterior.izq=aux.izq;
                }
                if(anterior.id<aux.id){
                    anterior.der=aux.izq;
                }
            }
            aux2=aux;
            aux=aux.izq;
            if(aux.der==null){
                aux.der=aux2.izq;
                if (aux2.id>anterior.id){
                   anterior.der= aux; 
                }
                if(aux2.id<anterior.id){
                    anterior.izq=aux;
                }
                return true;
            }
            while(aux!=null){
                aux=aux.der;
            }
            aux.der=aux2.der;
            
            if(aux2.id>anterior.id){
                anterior.der=aux2.izq;
                return true;
            }
            if(aux2.id<anterior.id){
                anterior.izq=aux.izq;
                return true;
            }
            return false;
        }
    /**
     * Este metodo modifica los pedidos que existen en el sistema y dependiendo del estado de la bandera fue correcto la modificacion o no
     * @param id
     * @param aux
     * @param origen
     * @param destino
     * @param peso
     * @return nada
     */
    public Pedidos modificar(int id,Pedidos aux,String origen,String destino,int peso){
        if (raiz == null){
            return null;
        }
        if (id<aux.id){
            if(aux.izq == null){
                return null;
            }
            
             modificar(id,aux.izq,origen,destino,peso);   
        }
        if (id>aux.id){
            if(aux.der == null){
                return null;
            }
            
            modificar(id,aux.der,origen,destino,peso);
        }
        if(aux.id == id){
                aux.destino = destino;
                aux.origen = origen;
                aux.peso = peso;
                bandera = true;
                return aux;
            }
        return null;
    }
    public void buscar(int id,Pedidos aux){
        if (raiz == null){
            variable = null;
            return;
        }
        if (id<aux.id){
            if(aux.izq == null){
                return;
            }
            
             buscar(id,aux.izq);   
        }
        if (id>aux.id){
            if(aux.der == null){
                return;
            }
            
            buscar(id,aux.der);
        }
        if(aux.id == id){
                variable = aux;
                return ;
            }
    }
}
