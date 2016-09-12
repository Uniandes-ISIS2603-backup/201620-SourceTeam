/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 *
 * @author pa.alvarado10
 */
public class CalificacionDTO {
private CriticoDTO critico;    
private FuncionDTO funcion;
private String comentarioCritico;
private String comentarioFuncion;
private int calificacionCritico;
private int calificacionFuncion;
private int id;
  /**
 * Metodo constructor de la clase Calificacion
 * @param c, criticio al cual se le va a otorgar la calificacion
 * @param f, funcion a la  cual se le va a otorgar la calificacion
 */
public CalificacionDTO(CriticoDTO c,FuncionDTO f){
    super();

	critico = c;
	funcion = f;
        id = this.critico.getCredencial()+this.funcion.getId();

}
/**
 * Metodo que le asigna un comentario al critico
 * @param a, comentario que se le desea otorgar al critico
 */
private void setComentarioCritico(String a){
	comentarioCritico=a;
}
/**
 * Metodo que le asigna un comentario a la funcion
 * @param a, comentario que se le desea asignar a la funcion
 */
private void setComentarioFuncion(String a){
	comentarioFuncion=a;
}
/**
 * Metodo que le asigna una calificacion al critico
 * @param a, calificacion que se le dio al critico
 */
private void setCalificacionCritico(int a){
	calificacionCritico=a;
}
/**
 * metodo que le asigna una calificacion a una funcion
 * @param a, calificacion que se le dio a la funcion
 */
private void setCalificacionFuncion(int a){
	calificacionFuncion=a;
}
/**
 * Metodo que le asigna un critico a la calificacion
 * @param c, Critico al que se le dara la calificacion
 */
private void setCritico(CriticoDTO c){
	critico=c;
}
/**
 * Metodo que asigna la funcion que se desea calificar
 * @param f, funcion a calificar
 */
private void setFuncion(FuncionDTO f){
	funcion=f;
}
/**
 * Metodo que retorna el comentario que se le dio al critico
 * @return comentarioCritico, comentario que se dio respecto al critico
 */
public String getComentarioCritico(){
	return comentarioCritico;
}
public String getComentarioFuncion(){
	return comentarioFuncion;
}
public int getCalificacionCritico(){
	return calificacionCritico;
}
public int getCalificacionFuncion(){
	return calificacionFuncion;
}
public CriticoDTO getCritico(){
	return critico;
}
public FuncionDTO getFuncion(){
	return funcion;
}
public int getId(){
return id;
}
public void setId(int a){
id=a;
}
@Override
    public String toString() {
        return "{id:"+ this.getId()
                + ",nombre critico:\"" + this.getCritico().getNombre()
                + ",nombre funcion:\""+this.getFuncion().toString()
                +"\"}";
    }
}
  



