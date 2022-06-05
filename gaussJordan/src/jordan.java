import java.util.Scanner;

public class jordan{

    public static double ecuaciones[][];

    public static Scanner entrada = new Scanner(System.in);
    public static void  main(String[] args) {
        
    }


    public static void leeEcuaciones(){

        int tamanio =  entrada.nextInt();


        if (tamanio> 10  || tamanio < 1){
            System.out.println("El indice no puede ser mayor a 10 o Menor a 1");
            return;
        } 

        for(int i = 0; i< tamanio ; i++){
            for(int j = 0; j< tamanio; j++){
                if (j < tamanio) {
                    System.out.printf("ingresa el valor coeficiente   [%s] de la ecuacion [%s]: ", j, i);
                    ecuaciones[i][j] = entrada.nextDouble();
                } else {
                    System.out.printf("ingresa el valor Independiente [%s] de la ecuacion [%s]: ", j, i);

                    ecuaciones[i][j] = entrada.nextDouble();
                }
            }
        }

    }

}