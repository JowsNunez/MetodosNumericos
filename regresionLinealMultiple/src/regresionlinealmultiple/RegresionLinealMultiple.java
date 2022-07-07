/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Jose Alfredo Nuñez Aguirre
 *
 * 
 */
public class RegresionLinealMultiple {

    // total de coeficientes X
    public static int nX = 2;
    // total de puntos
    public static int cantidad;

    // matriz que almacena Xn's y la independiente Y.
    public static double puntos[][];
    // matriz donde se expanden las sumatoiras

    public static double sumatorias[][];

    // para jordan
    public static double ecuacion[][];
    public static double ecuacionAux[][];
    public static double resultados[];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        leePuntos();
        regresionLinealMultiple();
        gaussJordan();
        despliegaSolucion();
        // TODO code application logic here
    }

    public static void leePuntos() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
//                System.out.println("Ingresa el numero total de X [Coeficientes]");
//                nX = Integer.parseInt(entrada.readLine());

                System.out.println("Ingresa el numero total de coordenadas");
                cantidad = Integer.parseInt(entrada.readLine());

                if (cantidad < 100 && cantidad > 1) {
                    break;
                }
                System.out.println("\n* * * * * No se permiten 0 o mas de 100 puntos * * * * *\n");
            }

            // se crea la matriz de punto tamaño 3 x n
            puntos = new double[nX + 1][cantidad];
            for (int i = 0; i < puntos.length; i++) {

                for (int j = 0; j < cantidad; j++) {
                    if (i < nX) {
                        System.out.printf("Escribe el valor de X%d: ", i + 1);
                    }
                    if (i == nX) {
                        System.out.printf("Escribe el valor de Y%d: ", j + 1);
                    };

                    puntos[i][j] = Double.parseDouble(entrada.readLine());
                    System.out.println("");
                }

            }

            System.out.println("\n * * * * * * * * * * *  Datos   * * * * * * * * * * *\n");

            for (int i = 0; i < puntos.length; i++) {

                if (i == nX) {
                    System.out.printf("| %-8s\n", "Y");

                } else {
                    System.out.printf("| %-8s |", "X" + (i + 1));

                }

            }
            System.out.println("- - - - - - - - - - - - - - - - - - - - ");
            for (int i = 0; i < puntos[0].length; i++) {
                for (int j = 0; j < puntos.length; j++) {
                    System.out.printf("| %f |", puntos[j][i]);

                }
                System.out.println("");
            }

//
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void creaMatrizSumatorias() {

        int numFilas = nX;
        for (int i = 0; i < nX; i++) {
            numFilas *= nX;
        }
        // se obtiene el numero de filas de la matriz expandida, en este caso se eleva 2 ala 3 ya que necesitamos
        // 8 filas y N columnas
        sumatorias = new double[numFilas][cantidad + 1];
        int exponente = 2;
        double suma = 0;

        for (int i = 0; i < sumatorias.length; i++) {

            for (int j = 0; j < sumatorias[0].length - 1; j++) {
                // si es menor o igual a nX es decir al numero de coeficientes, en este caso 2
                if (i <= nX) {
                    // se realiza la sumatoria de Xn obteniendo los valores de la matriz puntos
                    sumatorias[i][j] = puntos[i][j];
                    suma += sumatorias[i][j];

                    //si no , si i es mayor a el numero de coeficientes(nX) y menor a el tamanio de la matriz sumatorias - 1 y menos nX
                    // se elevan  de X1 y X2 al cuadrado
                } else if (i > nX && i < (sumatorias.length - 1) - nX) {

                    // se realiza una comparacion usando el operador ternario para saber a X le corresponde
                    sumatorias[i][j] = Math.pow(puntos[i > puntos.length ? 1 : 0][j], exponente);
                    suma += sumatorias[i][j];

                    // si no se multiplican las X por las Y
                } else {
                    // se realiza una comparacion usando el operador ternario para saber a X le corresponde
                    sumatorias[i][j] = puntos[(sumatorias.length - i) - nX < 0 ? 1 : 0][j] * puntos[nX][j];
                    suma += sumatorias[i][j];
                }

            }
            // se guarda el valor de la suma la ultima posicion de la columna de la fila i
            sumatorias[i][sumatorias[0].length - 1] = suma;

            suma = 0;

        }

        // se realiza la multiplicaicon entre X1 y X2
        for (int j = 0; j < sumatorias[0].length - 1; j++) {
            sumatorias[5][j] = puntos[0][j] * puntos[1][j];

            suma += sumatorias[5][j];

        }
        sumatorias[5][sumatorias[0].length - 1] = suma;
        suma = 0;

        System.out.println("");

        for (int i = 0; i < sumatorias[0].length; i++) {
            if (i == sumatorias[0].length - 1) {
                System.out.println("\n----------------------------------------------------------------------------------------"
                        + "----------------------------------------------------------------------------------------");
            }

            for (int j = 0; j < sumatorias.length; j++) {

                System.out.printf("| %10.2f |", sumatorias[j][i]);

            }
            System.out.println("");
        }

    }

    public static void regresionLinealMultiple() {
        creaMatrizSumatorias();

        // se llena de forma manual la matriz que sera usada con el metodo de Gauss Jordan
        ecuacion = new double[3][4];
        ecuacionAux = new double[3][4];

        int posResult = sumatorias[0].length - 1;

        ecuacion[0][0] = puntos[0].length;
        ecuacion[0][1] = sumatorias[0][posResult];
        ecuacion[0][2] = sumatorias[1][posResult];
        ecuacion[0][3] = sumatorias[2][posResult];

        ecuacion[1][0] = sumatorias[0][posResult];
        ecuacion[1][1] = sumatorias[3][posResult];
        ecuacion[1][2] = sumatorias[5][posResult];
        ecuacion[1][3] = sumatorias[6][posResult];

        ecuacion[2][0] = sumatorias[1][posResult];
        ecuacion[2][1] = sumatorias[5][posResult];
        ecuacion[2][2] = sumatorias[4][posResult];
        ecuacion[2][3] = sumatorias[7][posResult];

        ecuacionAux = ecuacion.clone();

        muestraArreglo();

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
        System.out.println("\n* * * * *Arreglo* * * **");
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
