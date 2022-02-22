/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eliminacionGauss;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author el_fr
 */
public class EliminacionGauss {

    Integer ecuaciones[][];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Double ecuaciones[][] = leeEcuaciones();

        pivotea(ecuaciones);

        for (int i = 0; i < ecuaciones.length; i++) {
            for (int j = 0; j < ecuaciones[i].length; j++) {
                //  Arrays.sort(ecuaciones[i]);
                System.out.printf("[%s][%s]= %s ", i, j, ecuaciones[i][j]);

            }
            System.out.println("");
        }
        
        eliminacionAdelante(ecuaciones);

        // TODO code application logic here
    }

    public static Double[][] leeEcuaciones() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el numero de ecuaciones (Máximo 10): ");
        Integer tamanio = entrada.nextInt();
        
        if (tamanio > 10) {
            System.out.println("");
            return null;
        } else {
            Double ecuaciones[][] = new Double[tamanio][tamanio + 1];

            for (int i = 0; i < tamanio; i++) {
                for (int j = 0; j < tamanio +1; j++) {
                    if(j < tamanio){
                        System.out.printf("ingresa el valor coeficiente [%s] de la ecuacion [%s]: ", j, i);
                    ecuaciones[i][j] = entrada.nextDouble();
                    }else{
                        System.out.printf("ingresa el valor Independiente [%s] de la ecuacion [%s]: ", j, i);

                    ecuaciones[i][j] = entrada.nextDouble();
                    }

                    

                }
            }

            return ecuaciones;
        }
    }

    public static void pivotea(Double[][] ecuaciones) {
        for (int i = 0; i < ecuaciones.length; i++) {
            for (int j = 0; j < ecuaciones[i].length ; j++) {
                if (j + 1 < ecuaciones.length ) {
                    
                    if (ecuaciones[j][0] < ecuaciones[j + 1][0]) {
                        Double aux[] = ecuaciones[j];
                        ecuaciones[j] = ecuaciones[j + 1];
                        ecuaciones[j + 1] = aux;
                    }

                }
            }

        }

    }
    
    public static void eliminacionGauss(){
        
    }
    
    
    public static void eliminacionAdelante(Double[][] ecuaciones) {
        Double pivote;
        int contador = 0;
        while (true) {
            pivote = ecuaciones[contador][contador];
                    
            for (int i = 0; i < ecuaciones.length; i++) {
                for (int j = 1; j < ecuaciones.length; j++) {
                    if (i + 1 < ecuaciones.length) {
                        pivote = (ecuaciones[i + 1][i]/ pivote);
                        
                    }
                }
            }
            
            contador++;
            if(contador == ecuaciones.length-1){
                break;
            }
        }

    }

}
