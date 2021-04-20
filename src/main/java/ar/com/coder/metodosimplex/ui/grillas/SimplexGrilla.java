/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.metodosimplex.ui.grillas;

import ar.com.coder.metodosimplex.objetos.Valor;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TwoBooT
 */
public class SimplexGrilla extends AbstractTableModel {

    private Valor valores[][]= new Valor[4][8];
    private String[] columnas = {"Fila", "X1", "X2", "S1", "S2", "S3", "CR", "Z"};

    public Valor[][] getValores() {
        return valores;
    }

    public void setValores(Valor[][] valores) {
        this.valores = valores;
    }
    
    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;

    }

    @Override
    public int getRowCount() {
        return valores.length;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
    
            return super.getColumnClass(columnIndex);
    
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Valor valor;
            
        valor = valores[rowIndex][columnIndex];
        valor.setFila(rowIndex);
        valor.setColumna(columnIndex);
        return valor;
        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
