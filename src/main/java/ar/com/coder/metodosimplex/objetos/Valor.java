/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.metodosimplex.objetos;

/**
 *
 * @author TwoBooT
 */
public class Valor {

    private int fila;
    private int columna;
    private String valor;
    private Float valorNumerico;
    private boolean seleccionado=false;

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }
    
    
    public Valor(Float valorNumerico) {
        this.valorNumerico = valorNumerico;
        this.valor=valorNumerico.toString();
    }
    
    public Float negativizar(){
         this.valorNumerico=this.valorNumerico * -1;
         this.valor=this.valorNumerico.toString();
         return this.valorNumerico;
    }
    
    
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Float getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(Float valorNumerico) {
        this.valorNumerico = valorNumerico;
    }
    
    @Override
    public String toString() {
        return valor ;
    }
    
    
}
