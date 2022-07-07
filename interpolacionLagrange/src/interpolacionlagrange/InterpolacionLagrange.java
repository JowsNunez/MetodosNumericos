/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolacionlagrange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author el_fr
 */
public class InterpolacionLagrange {

    public static double[][] puntos = new double[2][4];
    public static int grado = 3;
    public static double bAbscisa;
    public static double bOrdenada;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        leePuntos();
        bOrdenada = interpolacionLagrange();

        System.out.printf("El valor de\n X= %.6f | Y=%.6f\n", bAbscisa, bOrdenada);

        for (int i = 0; i < puntos[0].length; i++) {
            for (int j = 0; j < puntos.length; j++) {
                System.out.printf("| %.4f |", puntos[j][i]);

            }
            System.out.println("");

        }

        // TODO code application logic here
    }

    public static void leePuntos() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            for (int i = 0; i < puntos.length; i++) {
                for (int j = 0; j < puntos[0].length; j++) {
                    if (i !=puntos.length-1) {
                        System.out.printf("Escribe el valor de X%d: ", j);

                    } else {
                        System.out.printf("Escribe el valor de Y%d: ", j);

                    }
                    puntos[i][j] = Double.parseDouble(entrada.readLine());

                }

            }

            System.out.println("Valor de la X?");
            bAbscisa = Double.parseDouble(entrada.readLine());

            entrada.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {

        }

    }

    public static double interpolacionLagrange() {
        int contador = 0;
        double result = 0;

        while (contador < puntos[0].length) {
            double dividendo = 1;
            double aux;
            double divisor = 1;
            double aux2;

            for (int i = 0; i < puntos[0].length; i++) {
                if (i != contador) {
                    aux = (bAbscisa - puntos[0][i]);
                    aux = dividendo * aux;
                    dividendo = aux;

                }
            }

            for (int i = 0; i < puntos[0].length; i++) {
                if (i != contador) {
                    aux2 = puntos[0][contador] - puntos[0][i];
                    aux2 = aux2 * divisor;
                    divisor = aux2;

                }

            }

            result = result + ((dividendo / divisor) * puntos[1][contador]);

            contador++;
        }

        return result;
    }
}
