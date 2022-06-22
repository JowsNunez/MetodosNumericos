/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussSeidel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author el_fr
 */
public class GaussSeidel {

    public static double[][] ecuacion;
//    public static double[][] ecuacion = {
//        {-10, 2, -1, 27},
//        {-3, -6, 2, -61.5},
//        {1, 1, 5, -21.5}
//    };

//    public static double resultados[] = {0, 0, 0};
//    public static double rAnt[] = {0, 0, 0};
//    public static double verifX[] = {0, 0, 0};
    public static double resultados[];
    public static double rAnt[];
    public static double verifX[];

    public static double Error = 0.05;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        gaussSeidel();
    }

    public static void leeEcuaciones() {
        try {

            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingresa el Error: ");
            Error = Double.parseDouble(entrada.readLine());
            System.out.println("Ingresa el numero de Ecuaciones: ");
            int numEc = Integer.parseInt(entrada.readLine());
            // creamos el arreglo de manera dinamica por ejemplo si se ingresaran 3 escuaciones tendremos 3 filas y 4 columnas donde 
            // la cuarta columna sera el resultado de la ecuacion
            ecuacion = new double[numEc][numEc + 1];
            resultados = new double[numEc];
            rAnt = new double[numEc];
            verifX = new double[numEc];

            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 0; j < ecuacion[i].length; j++) {

                    if (j != ecuacion[i].length - 1) {
                        System.out.print("Escribe el valor de la posicion [" + i + "][" + j + "] {X " + (j + 1) + "}= ");

                    } else {
                        System.out.print("Escribe el valor de la posicion [" + i + "][" + j + "] {Resultado}= ");
                    }
                    ecuacion[i][j] = Double.parseDouble(entrada.readLine());

                }
            }

            System.out.println("\n************* Matriz ********************\n");

            for (int i = 0; i < ecuacion.length; i++) {
                for (int j = 0; j < ecuacion[i].length; j++) {
                    if (j != ecuacion[i].length - 1) {
                        System.out.printf("{ %8.4f X%d }", ecuacion[i][j], i);

                    } else {
                        System.out.printf(" = [ %8.4f ]\n", ecuacion[i][j]);

                    }

                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Solo se Admiten Numeros ");
        }

    }

    public static void gaussSeidel() {

        leeEcuaciones();
        double suma = 0;
        double verif;
        int contador = 0;

        while (true) {

            System.out.println("******************* vuelta: " + contador);
            for (int i = 0; i < ecuacion.length; i++) {
                suma = 0;
                for (int j = 0; j < ecuacion.length; j++) {
                    if (i != j) {
                        // multiplicamos por -1 para cambiar los valores al sustituir
                        suma += ((-1 * ecuacion[i][j]) * resultados[j]);

                    }

                }

                // sumamos nuestro resultado con el valor del resultado de las ecuaciones y lo dividimos entre el valor de el elemento [n,n]
                suma = (ecuacion[i][ecuacion.length] + suma) / ecuacion[i][i];
                rAnt[i] = resultados[i];
                resultados[i] = suma;
            }

            if (contador > 0) {
                for (int i = 0; i < rAnt.length; i++) {
                    System.out.printf("Valor de X%d %8.6f\n", (i + 1), resultados[i]);

                    verif = Math.abs(Math.abs(resultados[i]) - Math.abs(rAnt[i]));
                    verifX[i] = verif;

                }

                int auxContador = 0;
                for (int i = 0; i < verifX.length; i++) {
                    System.out.printf("Error X%d: %8.6f \n", (i + 1), verifX[i]);

                    if (verifX[i] < Error) {
                        auxContador++;

                    }

                }

                // si los errores encontrados son igual al tamaño de errores terminan las iteraciones
                if (verifX.length == auxContador) {
                    break;
                }

            }else{
                for (int i = 0; i < rAnt.length; i++) {
                    System.out.printf("Valor de X%d %8.6f\n", (i + 1), resultados[i]);

                   

                }
                
            }

            contador++;
        }

        System.out.println("****************************************"
                + " \niteraciones: " + contador);

        despliegaSolucion();

    }

    public static void despliegaSolucion() {

        // imprimimos los valores de X
        System.out.println("***************** Resultados *****************");
        for (int i = 0; i < resultados.length; i++) {

            System.out.printf("X%d: { %10.6f }\n", i + 1, resultados[i]);

        }
        System.out.println("===============================================");

    }

}
