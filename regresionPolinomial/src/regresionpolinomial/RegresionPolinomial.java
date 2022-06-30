/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionpolinomial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author el_fr
 */
public class RegresionPolinomial {

    public static int cantidad;
    public static int grado = 3;

    // ejemplo por defecto
    public static double puntos[][] = {
        {0, 2, 4, 6, 8, 12, 16, 20, 24, 28, 30, 34},
        {10, 12, 18, 22, 20, 30, 26, 30, 26, 28, 22, 20}};

    public static double sumX;
    public static double sumY;

    // matriz donde se guardan los valores los coeficientes elevados a n
    public static double valorXn[][];

    // para jordan
    public static double ecuacion[][];
    public static double ecuacionAux[][];
    public static double resultados[];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        regresionLineal();
        despliegaSolucion();

        // TODO code application logic here
    }

    public static void leePuntos() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Ingresa el grado ");
                grado = Integer.parseInt(entrada.readLine());
                System.out.println("Ingresa el numero total de coordenadas");
                cantidad = Integer.parseInt(entrada.readLine());

                if (cantidad < 100 && cantidad > 1) {
                    break;
                }
                System.out.println("\n* * * * * No se permiten 0 o mas de 100 puntos * * * * *\n");
            }
//
            puntos = new double[2][cantidad];
            for (int i = 0; i < puntos.length; i++) {
                if (i == 0) {
                    System.out.println("_____ Valores de X _________");
                }
                if (i == 1) {
                    System.out.println("_____ Valores de Y _________");
                }

                for (int j = 0; j < cantidad; j++) {
                    if (i == 0) {
                        System.out.printf("Escribe el valor de X%d: ", j + 1);
                    }
                    if (i == 1) {
                        System.out.printf("Escribe el valor de Y%d: ", j + 1);
                    };

                    puntos[i][j] = Double.parseDouble(entrada.readLine());
                    System.out.println("");
                }

            }

            System.out.println("X \t Y");
            for (int i = 0; i < puntos[0].length; i++) {
                for (int j = 0; j < puntos.length; j++) {
                    System.out.printf("%f ", puntos[j][i]);

                }
                System.out.println("");
            }

            // obtenemos las columnas para 2do grado por 2 mas grado - 1;
            valorXn = new double[(grado * 2) + (grado - 1)][puntos[0].length + 1];
            // calculamos el valor de las filas y columnas de acuerdo al grado de la ecuacion
            ecuacion = new double[grado + 1][grado + 2];
            // copiamos la ecuacion para al final usar jordan
            ecuacionAux = ecuacion.clone();
//
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void regresionLineal() {
        leePuntos();

        double suma = 0;

        // sumando X
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < puntos[0].length; j++) {
                // recorremos el arreglo en la posicion de las filas 0 y vamos sumando mientras el 
                // j va aumentando en uno en uno, acumulando los valores en la variable suma
                suma += puntos[i][j];
            }

            sumX = suma;
            suma = 0;

        }
        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < puntos[0].length; j++) {
                // recorremos el arreglo en la posicion de las filas 1 y vamos sumando mientras el 
                // j va aumentando en uno en uno, acumulando los valores en la variable suma
                suma += puntos[i][j];
            }

            sumY = suma;
            suma = 0;

        }

        // Elevando X, y acumulando los resultados. 
        // variable exponemte que inicia en 2 es decir al cuadrado
        int exponente = 2;

        // (grado * 2- (grado-1) ) representa la ultima fila a elevar X al exponente
        for (int k = 0; k <= (grado * 2) - (grado - 1); k++) {

            for (int j = 0; j < valorXn[0].length - 1; j++) {
                // elevamos al valor actual de la variable elevacion
                valorXn[k][j] = Math.pow(puntos[0][j], exponente);
                suma += valorXn[k][j];
                System.out.println(k + "  " + grado * 2);

            }
            valorXn[k][valorXn[0].length - 1] = suma;
            suma = 0;

            // se aumenta el exponente
            exponente++;

        }
        sumatorias();

        // multiplicando X*Y hasta X^n*Y
        // variable auxiliar para tomar las filas de la matriz donde guardaremos los valores 
        int aux = 0;

        // el contador inicia en la fila 
        for (int i = (grado * 2) - 1; i < valorXn.length; i++) {

            for (int j = 0; j < valorXn[0].length - 1; j++) {
                // si aux es igual a 0 multiplicamos el valor 0,j * 1,j de nuestros puntos es decir x*y
                if (aux == 0) {
                    valorXn[i][j] = puntos[0][j] * puntos[1][j];
                    suma += valorXn[i][j];

                    // si aux es  menor igual al grado de la ecuacion multiplicamos el valor  puntos 1,j * valorXn [aux-1,j]  x^n*y
                    // donde tomamos la fila de X^n en la matriz
                } else if (aux <= grado) {

                    valorXn[i][j] = puntos[1][j] * valorXn[aux - 1][j];
                    suma += valorXn[i][j];

                }

            }
            // guardamos el valor de la suma en el ultimo elemento de la matriz
            valorXn[i][valorXn[0].length - 1] = suma;

            suma = 0;

            aux++;

        }

        sumatorias();

        // llenar independientes
        System.out.println("* * * * * Llenando Independientes * * * * * ");

        ecuacion[0][ecuacion.length] = sumY;
        for (int i = 0; i < ecuacion.length - 1; i++) {

            int indice = (ecuacion.length - 1) - i;
            // buscamos la fila (valorXn.length - 1) - i
            ecuacion[indice][ecuacion.length] = valorXn[(valorXn.length - 1) - i][valorXn[0].length - 1];

        }

        // llenando Coeficientes
        System.out.println("* * * * * Llenando Coeficientes * * * * * ");

        for (int i = ecuacion.length - 1; i >= 0; i--) {
            for (int j = ecuacion[0].length - 2; j >= 0; j--) {

                if (i != (ecuacion.length - 1)) {
                    if (i > 0) {
                        if ((j - 1) != -1) {

                            ecuacion[i][j] = valorXn[j - 1][puntos[0].length];

                        }

                    } else {
                        ecuacion[i][j] = valorXn[0][puntos[0].length];

                    }

                } else {

                    ecuacion[i][j] = valorXn[j][puntos[0].length];

                }

            }
        }
        ecuacion[0][0] = puntos[0].length;
        ecuacion[0][1] = sumX;
        ecuacion[1][0] = sumX;

        muestraArreglo();

        gaussJordan();

    }

    public static void sumatorias() {
        // imprimiendo sumatorias
        for (int i = 0; i < 1; i++) {

            for (int j = 0; j < valorXn.length; j++) {

                if (j < valorXn.length - grado) {
                    System.out.printf("|  %18s  |", "X" + (j + 2));
                } else {
                    System.out.printf("|  %18s  |", "X^" + (j - (grado * 2) + 2) + "* Y");

                }

            }
            System.out.println("");
        }
        for (int i = 0; i < valorXn[0].length; i++) {

            if (i == valorXn[0].length - 1) {
                System.out.println("---------- \t sumatorias\t\t --------- \tsumatorias\t\t----------\tsumatorias\t\t-----------");

            }
            for (int j = 0; j < valorXn.length; j++) {

                System.out.printf("|  %-18.4f  |", valorXn[j][i]);

            }
            System.out.println("");
        }
        System.out.println("---------- \t sumatorias\t\t --------- \tsumatorias\t\t----------\tsumatorias\t\t-----------");

    }

    public static void gaussJordan() {

        // contador auxiliar para realizar la repeticion de las operaciones donde se convierten los elementos del arreglo 1 y 0
        int contador = 0;
        // mientras el contador sea menor al tamaño numero de filas de nuestro arreglo se realizan las operaciones
        while (contador < ecuacion.length) {
            // si el contador es menor a el numero de columnas entonces podemos continuear
            if (contador < ecuacion[0].length) {
                // se obtiene el elemento [contador][contador] es decir si nuestro contador es 0 los elementos de la columna se dividiran entre 
                // el elemento [0][0]
                double divisor = ecuacion[contador][contador];

                System.out.println(divisor);

                System.out.println("**************  Hacer 1 **************");

                for (int i = 0; i < ecuacion[0].length; i++) {
                    // si el contador es 0 nuestro dividendo sera [0][i]/ [0][0] es decir [contador][i] / [contador][contador]
                    // para esto tomamos los valores de el arreglo auxiliar
                    double dividendo = ecuacionAux[contador][i];
                    // se hace la division
                    double operacion = dividendo / divisor;
                    System.out.printf(" Haciendo 1: [" + contador + "," + i + "]  %8.6f /  [" + contador + "," + contador + "] %8.6f \n", dividendo, divisor);
                    // se establece el valor al elemento que sera cambiador por el resultado de la operacion
                    ecuacion[contador][i] = operacion;
                    // se muestra el resultado de la operacion y se como se intercambio el valor.
                    muestraArreglo();

                }
                // cambiamos el valor de nuestro arreglo auxiliar igualandolo a nuestro arreglo modificado .
                ecuacionAux = ecuacion.clone();

                System.out.println("**************  Hacer 0 **************");

                for (int fil = 0; fil < ecuacion.length; fil++) {
                    // si la fila es diferente de nuestro contador podemos realizar la operacion para convertir a 0 y normalizar la ecuacion.
                    if (fil != contador) {

                        // obtenemos el valor que será 0 el cual sera [fila][contador]  si nuestro contador es 0 entonces es el valor [0][0]
                        double zero = ecuacion[fil][contador];
                        // recorremos las columnas de la posicion [fil][col]
                        for (int col = 0; col < ecuacion[0].length; col++) {

                            System.out.printf(" Haciendo 0: %8.6f = %8.6f - ( %8.6f * %8.6f )\n",
                                    ecuacion[fil][col], ecuacion[fil][col], ecuacionAux[contador][col], zero);
                            // se realiza el calculp obteniendo los valores de la fila y columnas
                            ecuacion[fil][col] = ecuacion[fil][col] - (ecuacionAux[contador][col] * zero);
                            muestraArreglo();
                        }
                    }

                }
                // se aumenta el contador para repetir el proceso hasta llegar al resultado.
                contador++;
            }

        }
    }

    public static void muestraArreglo() {

        // imprimimos el arreglo en su estado actual.
        for (int i = 0; i < ecuacion.length; i++) {
            for (int j = 0; j < ecuacion[i].length; j++) {
                System.out.printf("[%3s % -15.6f  %3s]", "*", ecuacion[i][j], "*");

            }
            System.out.println("");
        }
        System.out.println("====================");

    }

    public static void despliegaSolucion() {

        // imprimimos los valores de X
        resultados = new double[ecuacion.length];

        for (int i = 0; i < resultados.length; i++) {
            resultados[i] = ecuacion[i][ecuacion[0].length - 1];

            if (i > 0) {
                System.out.printf("  %.6f X^%d", resultados[i], i);

            } else {
                System.out.printf("Ecuacion:  %.6f", resultados[i]);

            }

        }
        System.out.println("\n====================");

    }

}
