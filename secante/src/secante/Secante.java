/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

/**
 * @Class Secante Programa que determina la raíz real de la función: f(x) = (1 –
 * 0.6x)/x, empleando el Método de la Secante. El programa pide los valores
 * iniciales y el error aproximadomáximo. El programa calcula y despliega los
 * valores de la raíz, el valor de la función para esa raíz y el número de
 * iteraciones requerida para encontrar la raíz.
 * @author Jose Alfredo Nuñez Aguirre 00000189130
 */
public class Secante {

    public static void main(String args[]) throws ParseException {

        System.err.println(" * Programa que determina la raíz real de la función: f(x) = (1 – 0.6x)/x,"
                + " empleando el Método de la Secante. \n"
                + " * El programa  pide los valores iniciales y el error aproximadomáximo. \n"
                + " * El programa calcula y despliega los valores de la raíz, el valor de la función\n"
                + " * para esa raíz y el número de iteraciones requerida para encontrar la raíz."
                + ".\n*********************************************\n");

        try {

            // objeto para obtener la entrada en consola
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Ingresa el valor Xi-1 inicial: ");
            double xa = Double.parseDouble(entrada.readLine());
            System.out.print("Ingresa el valor X0 inicial: ");
            double xi = Double.parseDouble(entrada.readLine());

            System.out.print("Ingresa el valor del Error: ");
            double error = Double.parseDouble(entrada.readLine());

            // se llama al metodo secante para hacer la busqueda de la raiz
            secante(xi, xa, error);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.err.println("Solo se aceptan números de tipo Double");
        }

    }

    /**
     * Metodo f que calcula la ecuacion f(x) = (1 – 0.6x)/x,
     *
     * @param x representa el valor a sustituir en X
     * @return devuelve el resultado de tipo double obtenido en la ecuacion.
     */
    public static double f(double x) {
        return (1 - (0.6 * x)) / x;
    }

    /**
     * Metodo biseccion, busca las raices de la ecuacion f(x) = (1 – 0.6x)/x, e
     * imprime una tabla.
     *
     * @param xi Representa el valor de X0
     * @param xa Representea el valor Xi-1
     *
     * @param errMax Representa el valor del máximo error en porcentaje
     */
    public static void secante(double xi, double xa, double errMax) {

        // representa a xi+1
        double xn;
        // representa a xi-1
        double fxa;
        // variable fxi donde se guarda el valor de el resultado de f(xi);
        double fxi = 1;

        // contador auxiliar para el conteo de las iteraciones realizadas.
        int contador = 0;

        System.out.printf("%-11s | %-9s | %-9s | %-9s | %-9s | %-9s \n",
                "interacion", "Xi-1", "xi", "f(xi)", "f(Xi-1)", "Xi+1");
        System.out.println("-------------------------------------------------------------------------------------");

        // mientras el valor absoluto de fxi sea mayor que el error continuan las operaciones.
        while (Math.abs(fxi) > errMax) {

            // se calcula f(xi) con la funcion f(x) representando x actual
            fxi = f(xi);

            // se calcula el valor de f(xi-1) representando x anterior
            fxa = f(xa);

            // se calcula xn que representa el valor de xi+1 representando x siguiente
            xn = xi - ((fxi) * (xa - xi) / (fxa - fxi));

            // formato de la tabla usando 4 decimales.
            System.out.format("x %-11d | %9.4f |%9.4f | %9.4f | %9.4f | %9.4f |\n",
                    contador, xa, xi, fxi, fxa, xn);
            System.out.println("-------------------------------------------------------------------------------------");

            // se cambia el valor de xa por el valor de xi que representara x anterior
            xa = xi;
            // se cambia el valor de xi por el valor xn que representara el siguiente valor
            xi = xn;
            // se aumenta la el contador 
            contador++;

        }

    }

}
