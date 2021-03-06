import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tabula {
    // variable inicial
    public static double inicio;

    //variable final
    public static double fin;

    // variable incremento
    public static double incremento;

    public static void main(String[ ]args)   {


        try {
            // Objeto para recibir la entrada por consola
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            // Se ingresa el valor inicial y se guarda en la variable inicio
            System.out.print("Valor inicial: ");
            inicio = Double.parseDouble( entrada.readLine());

            // Se ingresa el valor final y se guarda en la variable fin
            System.out.print("Valor Final: ");
            fin = Double.parseDouble(entrada.readLine());

            // Se ingresa el valor del incremento
            System.out.print("Valor de Incremento: ");
            incremento = Double.parseDouble(entrada.readLine());

            tabula(inicio, fin, incremento);
        }catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
        }catch (NumberFormatException e){
            System.err.println("NumberFormat: "+e.getMessage());
        }
        finally {
            System.out.println("'-----------------------'");
        }
    }


    public static double f(double x){
        // Se calcula -2.1 + 6.21x – 3.9x^2+ 0.667x^3 y se devuelve el valor obtenido como un tipo double
        return  -2.1 + (6.21*x) - (3.9* Math.pow(x,2))+ (0.667 * Math.pow(x,3));
    }

    public static void tabula(double inicio,double fin, double incremento){
        double resultado;

        // Se imprime en consola la cabecera de la tabla.
        System.out.println("x    \t f(x)");

        //  mientras que inicio sea menorigual que fin el ciclo continua.
        while(inicio <= fin){

            // se calcula la ecuacion -2.1 + 6.21x – 3.9x^2+ 0.667x^3  y se guarda el valor en la variable resultado
            resultado = f(inicio);

            // se imprime el resultado
            System.out.printf("%.4f\t f(%.2f)= %.4f\n",inicio,inicio,resultado);

            // Se aumenta el valor de inicio de acuerdo al incremento establecido
            // y se guarda el nuevo valor.
            inicio+=incremento;

        }

    }


}
