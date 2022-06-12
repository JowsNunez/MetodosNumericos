/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reglafalsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

/**
 * @Class ReglaFalsa 
 * Programa que determina una de las raíces reales de la
 * función: f(x) = (1 – 0.6x)/x, empleando el Método de la Regla Falsa. El
 * programa pide los valores inicial y final del intervalo de búsqueda y el
 * error aproximado máximo. El programa calcula y despliega los valores de la
 * raíz, el valor de la función para esa raíz y el número de iteraciones
 * requerida para encontrar la raíz.
 * @author José Alfredo Nuñez Aguirre
 */
public class ReglaFalsa {

    public static void main(String args[]) throws ParseException {

        System.err.println(" * Este Programa determina una de las raíces reales de la función: f(x) = (1 – 0.6x)/x,\n"
                + " * empleando el Método de la Regla Falsa. El programa pide los valores inicial y final del\n"
                + " * intervalo de búsqueda y el error aproximado máximo. El programa calcula y despliega\n"
                + " * los valores de la raíz, el valor de la función para esa raíz y el número de iteraciones requerida\n"
                + " * para encontrar la raíz.\n*********************************************\n");

        try {

            // objeto para obtener la entrada en consola
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Ingresa el valor x inicial: ");
            double xi = Double.parseDouble(entrada.readLine());

            System.out.print("Ingresa el valor x final: ");
            double xd = Double.parseDouble(entrada.readLine());

            System.out.print("Ingresa el valor del Error: ");
            double error = Double.parseDouble(entrada.readLine());

            // se llama al metodo reglaFalsa para realizar la busque la raiz
            reglaFalsa(xi, xd, error);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Solo se aceptan números de tipo Double");
        }

    }

    /**
     * Metodo f que calcula la ecuacion (1 – 0.6x)/x
     *
     * @param x representa el valor a sustituir en X
     * @return devuelve el resultado de tipo double obtenido en la ecuacion.
     */
    public static double f(double x) {

        return (1 - (0.6 * x)) / x;

    }

    /**
     * Metodo reglaFalsa, busca las raices de la ecuacion f(x)=(1 – 0.6x)/x e
     * imprime una tabla.
     *
     * @param xi Representa el valor de X izquierda o X inicial.
     * @param xd Representa el valor de X derecha o X final
     * @param error Representa el valor del error en decimales.
     */
    public static void reglaFalsa(double xi, double xd, double error) {

        // variable fxi donde se guarda el valor de el resultado de f(xi);
        double fxi;
        
        // variable fxd donde se guarda el valor de el resultado de f(xd);
        double fxd;
        
        // variable xm /= ((xi * fxd) - (xd * fxi)) / (fxd - fxi);
        double xm;
        
        // variable fxm donde se guarda el valor de el resultado de f(xm) se inicializa en uno como auxiliar 
        //para que entre a while y dentro de while se calcula el primer resultado de manera correcta.
        double fxm = 1;
        
        // contador auxiliar para el conteo de las iteraciones realizadas.
        int contador = 0;

        System.out.printf("%-11s | %-9s | %-9s | %-9s | %-9s | %-9s | %-9s\n",
                "interacion", "xini", "xfin", "xm", "f(xini)", "f(xfin)", "f(xm)");
        System.out.println("-------------------------------------------------------------------------------------");

        // mientras el valor absoluto de xm sea mayor que el error  continuan las operaciones.
        while (Math.abs(fxm) > error) {

            // se calcula f(xi) con la funcion f(x)
            fxi = f(xi);
            
            // se calcula f(xd) con la funcion f(x)
            fxd = f(xd);
            
            // se calcula xm con la ecuacion (xi * fxd) - (xd * fxi)) / (fxd - fxi)
            xm = ((xi * fxd) - (xd * fxi)) / (fxd - fxi);

            // se calcula el f(xm) con la funcion f(x)
            fxm = f(xm);

            // formato de la tabla usando 4 decimales.
            System.out.format("%-11d | %9.4f | %9.4f | %9.4f | %9.4f | %9.4f | %9.4f |  \n",
                    contador, xi, xd, xm, fxi, fxd, fxm);
            System.out.println("-------------------------------------------------------------------------------------");

            // Si f(xm) es menor a 0 entonces el valor de xm pasa a ser el nuevo valor de xi
            if (fxm < 0) {

                if (fxi < 0) {
                    xi = xm;
                } else {
                    xd = xm;
                }

            } else {
                // en caso contrario xm pasa aser el nuevo valor de xd
                if (fxi > 0) {
                    xi = xm;
                } else {
                    xd = xm;
                }

            }
            // se aumenta el número de iteraciones
            contador++;

        }

    }

}
