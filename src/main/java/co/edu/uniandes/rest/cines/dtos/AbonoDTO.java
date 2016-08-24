/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.rest.cines.exceptions.AbonoException;
 import java.util.ArrayList;
/**
 *
 * @author pa.alvarado10
 */
//Clase que representa un abono de boletas
public class AbonoDTO {
   


	// nombre del cliente que compra el abono
private String nombreCliente;
// identificador unico del abono
private int idAbono;
//Lista de boletas(minimo 10) que hacen parte del abono
private ArrayList<BoletaDTO> listaBoletas;
// precio total del abono
private double precio;
//Constructor por defecto
public AbonoDTO(){
	
}
/**
 * Metodo que inicia el abono, con el nombre del cliente y el identificador del abono dados como parametro
 * @param nombreC, nombre del cliente que desea comprar el abono
 * @param id, identificador del abono que se desea comprar
 */
public AbonoDTO(String nombreC,int id){
	nombreCliente = nombreC;
	idAbono = id;
	listaBoletas = new ArrayList<BoletaDTO>();
}
/**
 * Metodo que se encarga de agregar la lista de boletas al abono
 * 
 * @param ArrayList boletas, array que contiene las boletas que se desean abonar
 * @throws Exception, en caso de que el numero de boletas que se desea abonar, sea menor a 10
 */
public void setBoletasAbono(ArrayList<BoletaDTO> boletas) throws AbonoException{
	if(boletas.size()>=10){
		for(int i=0;i<boletas.size();i++){
			listaBoletas.add(boletas.get(i));
		}
	}
	else{
		throw new AbonoException("Minimo deben comprarse 10 boletas para que sea un abono");
	}
}
/**
 * Metodo que calcula el precio total del abono 
 * 
 * @throws Exception, en caso de que el numero de boletas a abonar sea menor a 10
 */
public void calcularPrecio()throws AbonoException{
	if(listaBoletas.size()>=10){
		double menor = 1000000;
		for(int i=0; i<listaBoletas.size();i++){
			if(listaBoletas.get(i).getPrecio()<menor){
				menor=listaBoletas.get(i).getPrecio();
			}
		}
		int cantidad=listaBoletas.size();
		precio = menor*cantidad;
	}
	else{
		throw new AbonoException("Minimo deben comprarse 10 boletas para que sea un abono");
	}
}
/**
 * Metodo que retorna el precio total del abono
 * @return precio total del abono
 */
public double getPrecioAbono(){
	return precio;
}
/**
 * Metodo que retorna el nombre del Cliente dueño del abono
 * @return nombreCliente, nombre del cliente dueño del abono
 */
public String getCliente(){
	return nombreCliente;
}
/**
 * metodo que retorna el identificador del abono
 * @return idAbono, identificador del abono
 */
public int getIdAbono(){
	return idAbono;
}
public void setIdAbono(int id){
idAbono=id;
}
    public String toString() {
        return "{" + "NombreCliente:\"" + this.getCliente() + "Id del abono:"+ this.getIdAbono()+ "\"}";
    }
}

