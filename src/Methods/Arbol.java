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
   public DefaultListModel<String> LISTMODEL = new DefaultListModel<>();
    boolean bandera = false;
    int alt =0;
    int hojas = 0;
    int nodos = 0;
    
    public static Arbol instance = null;
    public static Arbol getInstance(){
        if(instance == null){
            instance = new Arbol();
        }
        return instance;
    }
    
    public Pedidos raiz;
    public boolean insertar (int id, Pedidos aux,int peso,String origen,String destino){
        Pedidos nuevo = new Pedidos(id,peso,destino,origen);
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
            insertar (id, aux.izq,peso,origen,destino);
        }else{
            if (aux.der==null){
                aux.der = nuevo;
                bandera = true;
                return true;
            }
            insertar(id, aux.der,peso,origen,destino);
        }
      return bandera;
    }
    
    public void imprimir(Pedidos aux){
        if (aux == null){return;}
            imprimir(aux.izq);
            LISTMODEL.addElement("id " + aux.id);
            LISTMODEL.addElement("Peso " + aux.peso);
            LISTMODEL.addElement("------------------");
            imprimir (aux.der);   
    }
    
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
    
    
}
