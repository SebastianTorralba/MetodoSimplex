/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.metodosimplex.ui.grillas.renders;

import ar.com.coder.metodosimplex.controladores.SimplexControlador;
import ar.com.coder.metodosimplex.objetos.Valor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TwoBooT
 */
public class SimplexGrillaRender extends DefaultTableCellRenderer {

    public static final Color COLOR_VERDE = new Color(146, 208, 80);
    public static final Color COLOR_AZUL = new Color(83, 141, 213);
    public static final Color COLOR_AMARILLO = new Color(255, 255, 0);
    public static final Color COLOR_NARANJA = new Color(250, 191, 143);
    public static final Color COLOR_ROJO = new Color(192, 0, 0);
    public static final Color COLOR_GRIS = new Color(191, 191, 191);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        Valor estado;
        estado = (Valor) table.getValueAt(row, column);
        setBackground(Color.WHITE);

//        if (estado.getValorNumerico() == 0) {
//            setBackground(COLOR_GRIS);
        if (row == SimplexControlador.FILA_Z) {
            if (estado.getValorNumerico() < 0) {
                setBackground(COLOR_GRIS);
            }
        }
        if (estado.isSeleccionado()) {
            setBackground(COLOR_NARANJA);
        }
        return this;

    }
}
