/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inviertematriz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author el_fr
 */
public class InvierteMatriz {

    public static double[][] ecuacion;
    public static double[][] resultados;

    public static final String WHITE = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String BYELLOW = "\u001B[43m";
    public static final String BPURPLE = "\u001B[45m";

    public static double aux[][];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        leeMatriz();
        invierteMatriz();
        despliegaMatriz();

        // TODO code application logic here
    }

    public static void leeMatriz() {
        try {

            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingresa el numero de Ecuaciones: ");
            int numEc = Integer.parseInt(entrada.readLine());
            // creamos el arreglo de manera dinamica por ejemplo si se ingresaran 3 escuaciones tendremos 3 filas y 4 columnas donde 
            // la cuarta columna sera el resultado de la ecuacion
            ecuacion = new double[numEc][numEc + numEc];

            aux = new double[numEc][numEc + numEc];
            //identidad = new double[numEc][numEc];

            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 0; j < ecuacion.length; j++) {

                    System.out.print("Escribe el valor de la posicion [" + i + "][" + j + "] {X " + (j + 1) + "}= ");

                    ecuacion[i][j] = Double.parseDouble(entrada.readLine());

                }
            }

            aux = ecuacion.clone();

            // creamos la matriz identidad dentro del arreglo
            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 0; j < ecuacion[0].length; j++) {

                    if (j == (ecuacion.length + i)) {
                        ecuacion[i][j] = 1;
                    } else if (j > ecuacion.length) {
                        ecuacion[i][j] = 0;
                    }

                }
            }

            // duplicamos el arreglo ecuacion y añadimos los elementos a arreglo aux
            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 0; j < ecuacion[i].length; j++) {

                    System.out.print("{" + ecuacion[i][j] + "}");

                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Solo se Admiten Numeros ");
        }

    }

    public static void invierteMatriz() {

        // contador auxiliar para realizar la repeticion de las operaciones donde se convierten los elementos del arreglo 1 y 0
        int contador = 0;
        // mientras el contador sea menor al tamaño numero de filas de nuestro arreglo se realizan las operaciones
        while (contador < ecuacion.length) {
            // si el contador es menor a el numero de columnas entonces podemos continuear
            if (contador < ecuacion[0].length) {
                // se obtiene el elemento [contador][contador] es decir si nuestro contador es 0 los elementos de la columna se dividiran entre 
                // el elemento [0][0]
                double divisor = ecuacion[contador][contador];

                System.out.println("**************  Hacer 1 **************" + divisor);

                for (int i = 0; i < ecuacion[0].length; i++) {
                    // si el contador es 0 nuestro dividendo sera [0][i]/ [0][0] es decir [contador][i] / [contador][contador]
                    // para esto tomamos los valores de el arreglo auxiliar
                    double dividendo = aux[contador][i];
                    // se hace la division
                    double operacion = dividendo / divisor;
                    System.out.printf(" Haciendo 1: [" + contador + "," + i + "]  %8.6f /  [" + contador + "," + contador + "] %8.6f \n", dividendo, divisor);
                    // se establece el valor al elemento que sera cambiador por el resultado de la operacion
                    ecuacion[contador][i] = operacion;
                    // se muestra el resultado de la operacion y se como se intercambio el valor.
                    muestraArreglo(contador, i);

                }
                // cambiamos el valor de nuestro arreglo auxiliar igualandolo a nuestro arreglo modificado .
                aux = ecuacion.clone();

                System.out.println("**************  Hacer 0 **************");

                for (int fil = 0; fil < ecuacion.length; fil++) {
                    // si la fila es diferente de nuestro contador podemos realizar la operacion para convertir a 0 y normalizar la ecuacion.
                    if (fil != contador) {

                        // obtenemos el valor que será 0 el cual sera [fila][contador]  si nuestro contador es 0 entonces es el valor [0][0]
                        double zero = ecuacion[fil][contador];
                        // recorremos las columnas de la posicion [fil][col]
                        for (int col = 0; col < ecuacion[0].length; col++) {

                            System.out.printf(" Haciendo 0:  %8.6f = %8.6f - ( %8.6f * %8.6f )\n",
                                    ecuacion[fil][col], ecuacion[fil][col], aux[contador][col], zero);
                            // se realiza el calculp obteniendo los valores de la fila y columnas
                            ecuacion[fil][col] = ecuacion[fil][col] - (aux[contador][col] * zero);
                            muestraArreglo(fil, col);
                        }
                    }

                }

                // se aumenta el contador para repetir el proceso hasta llegar al resultado.
                contador++;
            }

        }
    }

    public static void muestraArreglo(int fil, int col) {

        // imprimimos el arreglo en su estado actual.
        for (int i = 0; i < ecuacion.length; i++) {
            for (int j = 0; j < ecuacion[i].length; j++) {
                if (i == fil && j == col) {
                    System.out.printf(BYELLOW + RED + "[ % -8.4f ]" + WHITE, ecuacion[i][j]);
                } else {
                    System.out.printf("[ % -8.4f ]", ecuacion[i][j]);
                }

            }
            System.out.println("");
        }
        System.out.println("====================");

    }

    public static void despliegaMatriz() {
        System.out.println("************ RESULTADO ***********");

        // imprimimos el arreglo en su estado actual.
        for (int i = 0; i < ecuacion.length; i++) {
            for (int j = 0; j < ecuacion[i].length; j++) {
                System.out.printf(BPURPLE + "\u001B[37m" + " [ % -8.4f ]", ecuacion[i][j]);

            }
            System.out.println("");
        }
        System.out.println("* * * * * * * * * * * * * * * * ");

    }

}
