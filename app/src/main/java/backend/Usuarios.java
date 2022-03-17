package backend;

import java.util.ArrayList;

public class Usuarios {
    private ArrayList correos = new ArrayList();
    private ArrayList claves = new ArrayList();
    private ArrayList nombres = new ArrayList();
    private ArrayList niveles = new ArrayList();


    public  void eliminarUsuario(int indice){
        correos.remove(indice);
        claves.remove(indice);
        nombres.remove(indice);
        niveles.remove(indice);
    }

    public void upClave(int indice,String clave){
        claves.set(indice,clave);
    }
    public void upNombre(int indice,String nombre){
        nombres.set(indice,nombre);
    }
    public void upNivel(int indice, String nivel){
        niveles.set(indice,nivel);
    }

    public void setCorreo(String correo){
        correos.add(correo);
    }
    public void setClave(String clave){
        claves.add(clave);
    }
    public void setNombre(String nombre){
        nombres.add(nombre);
    }
    public void setNivel(String nivel){
        niveles.add(nivel);
    }
    public String getCorreo(int indice){
        return correos.get(indice).toString();
    }
    public String getClave(int indice){
        return claves.get(indice).toString();
    }
    public String getNombre(int indice){
        return nombres.get(indice).toString();
    }
    public String getNivel(int indice){
        return niveles.get(indice).toString();
    }

    public int getCantidadCorreos(){
        return correos.size();
    }

    public int getIndiceCorreo(String correo){
        return correos.indexOf(correo);
    }
}
