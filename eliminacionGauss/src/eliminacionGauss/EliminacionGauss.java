/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eliminacionGauss;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Jose Alfredo Nuñez Aguirre 00000181930
 */
public class EliminacionGauss {

    public static Double ecuaciones[][];
    public static Double resultados[];
    public static String format = "00.0000";
    // Constante para dar formata la salida de los resultados
    public static final DecimalFormat FORMATEADOR = new DecimalFormat(format);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ecuaciones = leeEcuaciones();
        eliminacionGauss();
        despliegaSolucion();
    }

    public static void eliminacionGauss() {
        pivotea();
        eliminacionAdelante();
        resultados = sustitucionAtras();
    }

    public static Double[][] leeEcuaciones() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el numero de ecuaciones (Máximo 10): ");
        Integer tamanio = entrada.nextInt();

        if (tamanio > 10) {
            System.out.println("");
            entrada.close();
            return null;
        } else {
            Double ecuaciones[][] = new Double[tamanio][tamanio + 1];

            for (int i = 0; i < tamanio; i++) {
                for (int j = 0; j < tamanio + 1; j++) {
                    if (j < tamanio) {
                        System.out.printf("ingresa el valor coeficiente   [%s] de la ecuacion [%s]: ", j, i);
                        ecuaciones[i][j] = entrada.nextDouble();
                    } else {
                        System.out.printf("ingresa el valor Independiente [%s] de la ecuacion [%s]: ", j, i);

                        ecuaciones[i][j] = entrada.nextDouble();
                    }

                }
            }
            entrada.close();
            return ecuaciones;
        }
    }

    public static void pivotea() {
        for (int i = 0; i < ecuaciones.length; i++) {
            for (int j = 0; j < ecuaciones[i].length; j++) {
                if (j + 1 < ecuaciones.length) {

                    if (ecuaciones[j][0] < ecuaciones[j + 1][0]) {
                        Double aux[] = ecuaciones[j];
                        ecuaciones[j] = ecuaciones[j + 1];
                        ecuaciones[j + 1] = aux;
                    }

                }
            }

        }

    }

    public static void eliminacionAdelante() {

        int indice = ecuaciones.length;

        for (int k = 0; k < indice; k++) {

            for (int i = k + 1; i < indice; i++) {

                double factor = ecuaciones[i][k] / ecuaciones[k][k];

                for (int j = k; j <= indice; j++) {

                    ecuaciones[i][j] = ecuaciones[i][j] - ecuaciones[k][j] * factor;
                }
            }
        }

    }

    public static Double[] sustitucionAtras() {
        int indice = ecuaciones.length;
        Double[] resultado = new Double[indice];

        resultado[indice - 1] = ecuaciones[indice - 1][indice]
                / ecuaciones[indice - 1][indice - 1];

        for (int f = indice - 2; f >= 0; f--) {

            double sum = 0;

            for (int i = indice - 1; i > f; i--) {

                sum += ecuaciones[f][i] * resultado[i];

            }

            resultado[f] = (ecuaciones[f][indice] - sum) / ecuaciones[f][f];
        }

        return resultado;
    }


    public static void despliegaSolucion() {
        for (int i = 0; i < resultados.length; i++) {
            System.out.printf("X_%d=  %s\t", i + 1, FORMATEADOR.format(resultados[i]));
        }
    }

}
