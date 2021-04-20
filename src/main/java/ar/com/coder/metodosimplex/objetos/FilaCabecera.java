/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.metodosimplex.objetos;


public class FilaCabecera extends Valor {
    private String nombre;
    public FilaCabecera(Float valorNumerico) {
        super(valorNumerico);
    }

    public FilaCabecera(String nombre, Float fila) {
        super(fila);
        this.nombre = nombre;
        this.setValor(this.nombre);
        this.setFila(fila.intValue());
    }
    
    
}
