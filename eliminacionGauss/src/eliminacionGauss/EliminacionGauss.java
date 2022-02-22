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

        Integer ecuaciones[][] = leeEcuaciones();

    
        
        pivotea(ecuaciones);
        
            for (int i = 0; i < ecuaciones.length; i++) {
            for (int j = 0; j < ecuaciones.length; j++) {
              //  Arrays.sort(ecuaciones[i]);
                System.out.printf("[%s][%s]= %s ", i, j, ecuaciones[i][j]);

            }
            System.out.println("");
        }
        

        // TODO code application logic here
    }

    public static Integer[][] leeEcuaciones() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el numero de ecuaciones (Máximo 10): ");
        Integer tamanio = entrada.nextInt();

        if (tamanio > 10) {
            System.out.println("");
            return null;
        } else {
            Integer ecuaciones[][] = new Integer[tamanio][tamanio];

            for (int i = 0; i < ecuaciones.length; i++) {
                for (int j = 0; j < ecuaciones.length; j++) {

                    System.out.printf("ingresa el valor [%s] de la ecuacion [%s]: ", j, i);

                    ecuaciones[i][j] = entrada.nextInt();

                }
            }

            return ecuaciones;
        }
    }

    public static void pivotea(Integer[][] ecuaciones) {
        for (int i = 0; i < ecuaciones.length; i++) {
            for (int j = 0; j < ecuaciones.length; j++) {
                if(j+1< ecuaciones.length){
                    if(ecuaciones[j][0] < ecuaciones[j+1][0]){
                        Integer aux[]= ecuaciones[j];
                        ecuaciones[j] = ecuaciones[j+1];
                        ecuaciones[j +1] = aux;
                    }
                
            }
            }
            
        }
        
       
        
        

    }

}
