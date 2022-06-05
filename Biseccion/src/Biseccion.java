import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Autor Jose Alfredo Nuñez Aguirre
 * 00000181930 Metodos Numericos (Verano)
 *
 * Programa que determina una de las raíces reales de la función:
 * f(x) = -2.1 + 6.21x – 3.9x2 + 0.667x3
 */
public class Biseccion {

    public static void main(String args[]){

        try{

        // objeto para obtener la entrada en consola
        BufferedReader entrada =  new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ingresa el valor x inicial: ");
        double xi = Double.parseDouble(entrada.readLine());

        System.out.print("Ingresa el valor x final: ");
        double xd = Double.parseDouble(entrada.readLine());

        System.out.print("Ingresa el valor del Error Maximo: ");
        double error = Double.parseDouble(entrada.readLine());

        // se llama al metodo biseccion para hacer
        biseccion(xi,xd,error);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.err.println("Solo se aceptan números de tipo Double");
        }


    }


    /**
     * Metodo f que calcula la ecuaciojn -2.1 + 6.21x  - 3.9 (x)^2 + 0.667(x)^3
     * @param x representa el valor a sustituir en X
     * @return devuelve el resultado de tipo double obtenido en la ecuacion.
     */

    public static double f(double x){
        return -2.1 + (6.21* x) - (3.9 *Math.pow(x,2)) + (0.667*Math.pow(x,3));
    }

    /**
     * Metodo biseccion, busca las raices de la ecuacion -2.1 + 6.21x  - 3.9 (x)^2 + 0.667(x)^3 e imprime una tabla.
     * @param xi Representa el valor de X izquierda o X inicial.
     * @param xd Representa el valor de X derecha o X final
     * @param errMax Representa el valor del máximo error en porcentaje
     */
    public static void biseccion(double xi,double xd,double errMax){

        // el valor ingresado se divide entre 100 para obtener el error maximo en decimales
        // ejemplo se ingresa si se ingresa 0.1, se calcula el error -> 0.1/100 = 0.001.
        errMax = errMax/100;

        // variable fxi donde se guarda el valor de el resultado de f(xi);
        double fxi = 0;
        // variable fxd donde se guarda el valor de el resultado de f(xd);
        double fxd = 0;
        // variable xm donde se guarda el valor de la suma xi +  xd sobre 2
        double xm = ( xi + xd ) / 2;

        // variable fxm donde se guarda el valor de el resultado de f(xm)
        double fxm = f(xm);

        // contador auxiliar para el conteo de las iteraciones realizadas.
        int contador =0;


        System.out.printf("%-11s | %-9s | %-9s | %-9s | %-9s | %-9s | %-9s\n",
                "interacion", "xini", "xfin","xm","f(xini)","f(xfin)","f(xm)");
        System.out.println("-------------------------------------------------------------------------------------");


        // mientras el valor absoluto de xm sea mayor que el error Maximo continuan las operaciones.
        while(Math.abs(fxm)>errMax){

            // se calcula xm
            xm = ( xi + xd ) / 2;
            // se calcula f(xi) con la funcion f(x)
            fxi = f(xi);
            // se calcula f(xd) con la funcion f(x)
            fxd =  f(xd);
            // se calcula f(xm) con la funcion f(x)
            fxm = f(xm);


            // formato de la tabla usando 6 decimales.
            System.out.printf("%-11d | %9.6f | %9.6f | %9.6f | %9.6f | %9.6f | %9.6f | %.2f %% \n" ,
                    contador,xi,xd,xm,fxi,fxd,fxm,(Math.abs(fxm)*100));
            System.out.println("-------------------------------------------------------------------------------------");


            // Si f(xm) es menor a 0 entonces el valor de xm pasa a ser el nuevo valor de xi
            if(fxm<0){
                    xi = xm;
            }else{
            // en caso contrario xm pasa aser el nuevo valor de xd
                    xd=xm;
            }

            contador++;

        }

    }

}
