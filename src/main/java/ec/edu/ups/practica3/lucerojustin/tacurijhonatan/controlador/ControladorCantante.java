/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.practica3.lucerojustin.tacurijhonatan.controlador;

import ec.edu.ups.practica3.lucerojustin.tacurijhonatan.modelo.Cantante;
import ec.edu.ups.practica3.lucerojustin.tacurijhonatan.modelo.Disco;
import ec.edu.ups.practica3.lucerojustin.tacurijhonatan.idao.ICantanteDao;
import ec.edu.ups.practica3.lucerojustin.tacurijhonatan.idao.IDiscoDao;
import ec.edu.ups.practica3.lucerojustin.tacurijhonatan.vista.VistaCantante;
import ec.edu.ups.practica3.lucerojustin.tacurijhonatan.vista.VistaDisco;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ControladorCantante {
    //objetos vist
    private VistaCantante vistaCantante;
    private VistaDisco vistaDisco;
    //objetos modelo
    private Cantante cantante;
    private Disco disco;
    //objetos DAO
    private ICantanteDao cantanteDao;
    private IDiscoDao discoDao;
    

    // constructor

    public ControladorCantante(VistaCantante vistaCantante, VistaDisco vistaDisco, ICantanteDao cantanteDao, IDiscoDao discoDao) {
        this.vistaCantante = vistaCantante;
        this.vistaDisco = vistaDisco;
        this.cantanteDao = cantanteDao;
        this.discoDao = discoDao;
    }
    

    
    //llama al DAO para guardar un cliente
    public void registrar() {
        cantante = vistaCantante.ingresarCantante();
        List<Disco> list = vistaDisco.ingresarDisco();
        cantante.agregarDisco(list);
        cantanteDao.create(cantante);
        ///eingresar disco
    }
    
    //llama al DAO para actualizar un cliente
    public void actualizar() {
        int nombre = vistaCantante.buscarCantante();
        cantante = cantanteDao.read(nombre);
        if (cantante != null) {
            List <Disco> discoss = cantante.getDiscos();
            int id =cantante.getCodigo();
            cantante = vistaCantante.actualizarCantante(id);
            cantante.agregarDisco(discoss);
            cantanteDao.update(cantante);
        } else {
            System.out.println("No se encontró el cantante en la base de datos.");
        }
    }

    //llama al DAO para eliminar un cliente
    public void eliminar() {
        
        int nombre = vistaCantante.eliminarCantante();
        cantanteDao.delete(cantanteDao.read(nombre));
    }
    
    
    //llama al DAO para obtener un cliente por el id y luego los muestra en la vista
    public void buscarCantante() {
        int nombre = vistaCantante.buscarCantante();
        cantante = cantanteDao.read(nombre);
        vistaCantante.verCantante(cantante);
    }


    //llama al DAO para obtener todos los clientes y luego los muestra en la vista
    public void verCantantes() {
        List<Cantante> cantantes;
        cantantes = cantanteDao.findAll();
        vistaCantante.verCantantes(cantantes);
    }
    
    public void buscarporDisco(){
        String nombre = vistaCantante.buscarPorDisco();
        cantanteDao.buscarPorNombreDeDisco(nombre);
    }
    
    public void eliminarDisco(){
        int nombreCantante = vistaCantante.buscarCantante();
        Cantante cantante = cantanteDao.read(nombreCantante);

        if (cantante != null) {
            int codigoDisco = vistaCantante.eliminarDisco();
            cantante.eliminarDisco(codigoDisco);
            cantanteDao.update(cantante);
            System.out.println("Disco eliminado correctamente.");
        } else {
            System.out.println("No se encontró el cantante en la base de datos.");
        }
    }
    
    public void actualizarDisco(){
        int id = vistaCantante.buscarCantante();
        Cantante cantante = cantanteDao.read(id);

        if (cantante != null) {
            Disco discoa = vistaCantante.actualizarDisco();
            cantante.actualizarDisco(discoa);
            cantanteDao.update(cantante);
            System.out.println("Disco actualizado correctamente.");
        } else {
            System.out.println("No se encontró el cantante en la base de datos.");
        }
    }
    
}
