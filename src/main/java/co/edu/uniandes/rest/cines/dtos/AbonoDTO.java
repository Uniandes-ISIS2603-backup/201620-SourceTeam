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
private ClienteDTO dueño;
// identificador unico del abono
private int id;
//Lista de boletas(minimo 10) que hacen parte del abono
private ArrayList<BoletaDTO> listaBoletas;
// precio total del abono
private double precio;
//Constructor por defecto
public AbonoDTO(){
	
}
/**
 * Metodo que inicia el abono, con el nombre del cliente y el identificador del abono dados como parametro
 * @param client, nombre del cliente que desea comprar el abono
 * @param i, identificador del abono que se desea comprar
 */
public AbonoDTO(ClienteDTO client,int i){
    super();
	dueño = client;
	id = i;
	listaBoletas = new ArrayList<>();
     
}
/** Metodo que calcula el precio total del abono 
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
public ClienteDTO getCliente(){
	return dueño;
}

public void setCliente(ClienteDTO nuevo){
    dueño= nuevo;
}
/**
 * metodo que retorna el identificador del abono
 * @return idAbono, identificador del abono
 */
public int getId(){
	return id;
}
public void setId(int i){
id=i;
}
@Override
    public String toString() {
        return "{"+" NombreCliente:"+this.getCliente().getNombre()
                + "Id del abono: \""+ this.getId()
                + "\"}";
    }
public ArrayList getBoletas(){
return listaBoletas;
}
}

