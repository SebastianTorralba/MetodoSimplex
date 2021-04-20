/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.metodosimplex.controladores;

import ar.com.coder.metodosimplex.objetos.FilaCabecera;
import ar.com.coder.metodosimplex.objetos.Valor;
import ar.com.coder.metodosimplex.ui.grillas.SimplexGrilla;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TwoBooT
 */
public class SimplexControlador {

    public static final int COLUMNA_X1 = 1;
    public static final int COLUMNA_X2 = 2;

    public static final int COLUMNA_S1 = 3;

    public static final int COLUMNA_S2 = 4;

    public static final int COLUMNA_S3 = 5;
    public static final int COLUMNA_RC = 6;
    public static final int FILA_Z = 3;
    private StringBuilder calculos;
    private SimplexGrilla grillaAsociada;
    private Map<Integer, Valor[][]> tablas;

    public String getMensajes() {
        return calculos.toString();
    }

    public void setGrillaAsociada(SimplexGrilla grillaAsociada) {
        this.grillaAsociada = grillaAsociada;
    }

    public Valor[][] getGrilla(Integer iteracion) {
        return tablas.get(iteracion);
    }

    private Valor[][] valores = new Valor[4][8];

    public Valor[][] getGrilla() {
        return valores;
    }

    public Integer getIteracion() {
        return iteracion;
    }
    private Valor[][] valoresAnteriores = new Valor[4][8];

    private Integer iteracion = 0;
    Valor pivot;

    public SimplexControlador() {
      
    }

    private Valor[][] generarGrilla() {
        valores = new Valor[4][8];
        for (int i = 0; i < 4; i++) {
            valores[i][0] = new FilaCabecera("R" + String.valueOf(i + 1), new Float(i));
            if (i == FILA_Z) {
                valores[i][0] = new FilaCabecera("Z", new Float(i));
            }
            for (int j = 1; j < 8; j++) {

            }

        }
        return valores;
    }

    public Valor[][] inicializarGrilla() {
        this.valores= new Valor[4][8];;
        this.tablas = new HashMap();
        this.tablas.put(iteracion, valores);
        this.calculos = new StringBuilder("CALCULOS:\n");
        iteracion = 0;
        for (int i = 0; i < 4; i++) {
            valores[i][0] = new FilaCabecera("R" + String.valueOf(i + 1), new Float(i));
            if (i == FILA_Z) {
                valores[i][0] = new FilaCabecera("Z", new Float(i));
            }
            for (int j = 1; j < 8; j++) {
                valores[i][j] = new Valor(0f);
                if (i == 0 && j == 3) {
                    valores[i][j] = new Valor(1f);
                }
                if (i == 1 && j == 4) {
                    valores[i][j] = new Valor(1f);
                }
                if (i == 2 && j == 5) {
                    valores[i][j] = new Valor(1f);
                }
                if (i == 3 && j == 7) {
                    valores[i][j] = new Valor(1f);
                }

            }

        }
        return valores;
    }

    public boolean cargarFuncionEnMatriz(Valor x1, Valor x2, Valor rc) {
        x1.negativizar();
        x2.negativizar();
        valores[FILA_Z][COLUMNA_X1] = x1;
        valores[FILA_Z][COLUMNA_X2] = x2;
        valores[FILA_Z][COLUMNA_RC] = rc;
        return false;
    }

    public boolean cargarRestriccionEnMatriz(int fila, Valor x1, Valor x2, Valor rc) {
        valores[fila][COLUMNA_X1] = x1;
        valores[fila][COLUMNA_X2] = x2;
        valores[fila][COLUMNA_RC] = rc;
        return false;
    }

    private boolean verificarZ() {
        for (int i = 0; i < 8; i++) {
            Valor v = valores[FILA_Z][i];
            if (v.getValorNumerico() < 0) {
                return true;
            }
        }
        return false;
    }

    private Valor encontrarPivotColumna() {
        Valor negativo = new Valor(0f);
        for (int i = 0; i < 8; i++) {
            Valor v = valores[FILA_Z][i];
            if (v.getValorNumerico() < 0) {
                if (v.getValorNumerico() < negativo.getValorNumerico()) {
                    negativo = v;
                }
            }
        }
        if (negativo.getValorNumerico() < 0F) {
            negativo.setSeleccionado(true);
        }
        return negativo;
    }

    private Valor encontrarPivotFila(Valor v) {
        Valor pivot = null;
        Float menor = null;
        for (int i = 0; i < 3; i++) {
            Valor valorFila = valores[i][v.getColumna()];
            Valor valorRC = valores[i][COLUMNA_RC];
            Float calculo = valorRC.getValorNumerico() / valorFila.getValorNumerico();
            if (i == 0) {
                menor = calculo;
                pivot = valorFila;
            }
            if (calculo < menor) {
                menor = calculo;
                pivot = valorFila;
            }
        }
        v.setSeleccionado(false);
        pivot.setSeleccionado(true);
        return pivot;
    }

    public boolean calcularNuevaTabla() {
        if (verificarZ()) {
            calculos.append("\n------------------ITERACION:------------------");
            Valor v = encontrarPivotColumna();
            pivot = encontrarPivotFila(v);

            mensajePivot();

            valoresAnteriores = valores;
            tablas.put(iteracion + 1, generarGrilla());

            calcularFilaNuevaPivot();
            calcularFilaNueva();
             calculos.append("\n------------------FIN:------------------");
            return true;
        } else {
            return false;
        }
    }

    private void mensajePivot() {
        calculos.append("Pivot Selecionado:\n");
        calculos.append(pivot.toString());
        calculos.append("\nPivot Fila:\n");
        calculos.append(pivot.getFila());
        calculos.append("\nPivot Columna:\n");
        calculos.append(pivot.getColumna());
    }

    private boolean calcularFilaNueva() {
        Valor valorAnterior;
        Valor valorFilaEntrante;
        Valor valorColumnaPivotAnterior;
        Valor valorNuevo;
        String mensajeCalculo = null;

        for (int i = 0; i < valores.length; i++) {
            if (i != pivot.getFila()) {
                valorColumnaPivotAnterior = valoresAnteriores[i][pivot.getColumna()];
                for (int j = 0; j < valoresAnteriores[i].length; j++) {
                    if (j > 0) {
                        valorAnterior = valoresAnteriores[i][j];
                        valorFilaEntrante = valores[pivot.getFila()][j];
                        System.out.println("");
                        valorNuevo = new Valor(valorAnterior.getValorNumerico() - (valorColumnaPivotAnterior.getValorNumerico() * valorFilaEntrante.getValorNumerico()));
                        mensajeCalculo = valorAnterior.getValorNumerico().toString() + "- (" + valorColumnaPivotAnterior.getValorNumerico().toString()
                                + "/" + valorFilaEntrante.getValorNumerico().toString()
                                + ")=" + valorNuevo.toString();

                        mensajeFilasCalculos(mensajeCalculo);
                        valores[i][j] = valorNuevo;
                    } else{
                        valores[i][j]=valoresAnteriores[i][j];
                    }

                }
            }

        }
        return true;
    }

    private boolean calcularFilaNuevaPivot() {
        Valor valorAnterior;
        Valor valorNuevo;
        String mensajeCalculo = null;
        for (int i = 0; i < valoresAnteriores[pivot.getFila()].length; i++) {
            if (i > 0) {
                valorAnterior = valoresAnteriores[pivot.getFila()][i];
                valorNuevo = new Valor(valorAnterior.getValorNumerico() / pivot.getValorNumerico());
                mensajeCalculo = valorAnterior.getValorNumerico().toString() + "/" + pivot.getValorNumerico().toString()
                        + "=" + valorNuevo.toString();

                mensajePivotCalculos(mensajeCalculo);
                valores[pivot.getFila()][i] = valorNuevo;
            } else {
                nombrarFilaPivot();
            }
        }
        return true;
    }

    private void nombrarFilaPivot() {
        switch (pivot.getColumna()) {
            case COLUMNA_X1:
                valores[pivot.getFila()][0] = new FilaCabecera("X1", new Float(pivot.getFila()));
                break;
            case COLUMNA_X2:
                valores[pivot.getFila()][0] = new FilaCabecera("X2", new Float(pivot.getFila()));
                
                break;
            case COLUMNA_S1:
                valores[pivot.getFila()][0] = new FilaCabecera("S1", new Float(pivot.getFila()));
                break;
            case COLUMNA_S2:
                valores[pivot.getFila()][0] = new FilaCabecera("S2", new Float(pivot.getFila()));
                break;
            case COLUMNA_S3:
                valores[pivot.getFila()][0] = new FilaCabecera("S3", new Float(pivot.getFila()));
                break;
        }
    }

    private void mensajePivotCalculos(String calculo) {
        calculos.append("\nCalculando Nueva Fila Pivot: ");
        calculos.append(calculo);
    }

    private void mensajeFilasCalculos(String calculo) {
        calculos.append("\nCalculando Nueva Filas: ");
        calculos.append(calculo);
    }
}
