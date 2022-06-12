/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonRaphson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.function.Function;

/**
 * @Class NewtonRapshon programa que determina la raíz real mayor de: f(x) = x3-
 * 6x2 + 11x – 6, empleando el Método de Newton - Raphson. El programa pide el
 * valor inicial y el error aproximado máximo. El programa calcula y despliega
 * los valores de la raíz, el valor de la función para esa raíz y el número de
 * iteraciones requerida para encontrar la raíz.
 * @author Jose Alfredo Nuñez Aguirre 00000181930
 */
public class NewtonRaphson {

    public static void main(String args[]) throws ParseException {

        System.err.println(" * Programa que determina la raíz real mayor de: f(x) = x3- 6x2 + 11x – 6,\n"
                + " * empleando el Método de Newton - Raphson. El programa pide el\n"
                + " * valor inicial y el error aproximado máximo. El programa calcula y despliega\n"
                + " * los valores de la raíz, el valor de la función para esa raíz y el número de\n"
                + " * iteraciones requerida para encontrar la raíz.."
                + "\n*********************************************\n");

        try {

            // objeto para obtener la entrada en consola
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Ingresa el valor X inicial: ");
            double xi = Double.parseDouble(entrada.readLine());

            System.out.print("Ingresa el valor del Error: ");
            double error = Double.parseDouble(entrada.readLine());

            // se llama al metodo newtonRapshon para realizar la búsqueda de las raices.
            newtonRapson(xi, error);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Solo se aceptan números de tipo Double");
        }

    }

    /**
     * Metodo f que calcula la ecuacion f(x) = x3 - 6x2 + 11x – 6,
     *
     * @param x representa el valor a sustituir en X
     * @return devuelve el resultado de tipo double obtenido en la ecuacion.
     */
    public static double f(double x) {

        return Math.pow(x, 3) - (6 * Math.pow(x, 2)) + (11 * x) - 6;

    }

    /**
     * Metodo newtonRapson, busca las raices de la ecuacion f(x) = x^3 - 6x2 +
     * 11x – 6, e imprime una tabla.
     *
     * @param xi Representa el valor de X izquierda o X inicial.
     *
     * @param errMax Representa el valor del máximo error en porcentaje
     */
    public static void newtonRapson(double xi, double errMax) {

        // funcion lambda para resolver la derivada de f(x) = x^3 - 6x2 + 11x – 6 = f'(x)= 3x^2 - 12x +11
        Function<Double, Double> derivada = (x) -> {
            return (3 * Math.pow(x, 2)) - (12 * x) + 11;
        };

        // variable fxi donde se guarda el valor de el resultado de f(xi);
        double fxi = 1;
        // variable dfxi donde se guarda el resultado de la derivada de f(x)
        double dfxi;

        // contador auxiliar para el conteo de las iteraciones realizadas.
        int contador = 0;

        System.out.printf("%-12s | %-9s | %-9s | %-9s | %-9s \n",
                "interacion", "xi", "f(xi)", "f\'(xi)", "xi+1");
        System.out.println("-------------------------------------------------------------------------------------");

        // mientras el valor absoluto de xm sea mayor que el error  continuan las operaciones.
        while (Math.abs(fxi) > errMax) {

            // se calcula f(xi) con la funcion f(x)
            fxi = f(xi);
            // se calcula f(xd) con la funcion f(x)
            dfxi = derivada.apply(xi);

            // formato de la tabla usando 4 decimales.,  
            System.out.printf("x %-10d | %9.6f | %9.6f | %9.6f | %9.6f |\n",
                    contador, xi, fxi, dfxi,
                    /* se efectua la operacion para cambiar el valor de xi+1 */
                    xi = xi - (fxi / dfxi));
            System.out.println("-------------------------------------------------------------------------------------");

            contador++;

        }

    }

}
