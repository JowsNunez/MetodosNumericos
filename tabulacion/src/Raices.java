import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Raices {

    public static void main(String[ ]args) throws Exception  {
        double inicio;
        double fin;
        double incremento;
        double resultado;

        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Valor inicial: ");
        inicio = Double.valueOf( entrada.readLine());
        System.out.print("Valor Final: ");
        fin = Double.valueOf( entrada.readLine());
        System.out.print("Valor de Incremento: ");

        incremento = Double.valueOf( entrada.readLine());
        System.out.println("x    \t f(x)");


        while(inicio <= fin){


            resultado = ecuacion(inicio);

            System.out.printf("%.2f\t f(%.2f)= %.2f\n",inicio,inicio,resultado);

            inicio+=incremento;


        }

        System.out.println();


    }


    public static double ecuacion(double x){
        return  Math.pow(x,3) + 2*(Math.pow(x,2)) + (7*x) - 20;
    }


}
