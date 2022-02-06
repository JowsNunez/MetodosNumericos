# 
# reglaFalsa.py
#
# @Author Jose Alfredo Núñez Aguirre - 000000181930
#
# Programa que encuentra las Raices utilizando el método de la Regla Falsa 
# de la funcion f(x) = x^4 - 8x^3 -  35x^2 + 450x - 1001, 


print("\033[4;35m")
print('* * Programa que encuentra las Raices utilizando el método de Biseccion')
print('de la funcion función f(x) = sen(x) - x^2, * * ')
print("\033[0;32m")

# Método que recibe una variable x para calcular la función  f(x) = x^4 - 8x^3 -  35x^2 + 450x - 1001,
# y devuelve el resultado como flotante
def f (x):

    return x**4 - 8 * (x**3) - 35*(x**2) + 450*(x) - 1001



# Método que calcula la raíz de la funcion  através de REGLA FALSA 
# xi: Representa el valor inicial 
# xf: Representa el valor final
# errMAx: representa el maximo error 
def reglaFalsa(xi,xf,errMax): 

    # Variable que guarda el valor de cada iteracion empezando de 0
    iterador = 0

    # vector donde se almacena el valro de xr actual de acuerdo al iterador
    actual = []

    # variable error que guarda el error con punto flotante
    error = 0

    # variable que guarda el el error multiplicado * 100 y lo convierte en un valor absoluto
    errorAbs =0

    while True:
       

        # se realiza el calculo de xr usando la formula xr = ( (xf - xi)  / f(xf) - f(xi)) * f(xi)
        xr = xi -  ( xf - xi)  /  ( f(xf) -  (f(xi))  ) * f(xi)

        # se guarda el valor de xr en el arreglo actual para determinar el xr anterior de acuerdo al indice menos 1    
        actual.append(xr)
        
        # se calcula la multiplicacion de la funciones:  f(xi) * f(xr)  y se guarda en la variable xixr
        xixr =f(xi) * f(xr)

        
        # Si el iterador es mayor a cero se realiza la operación que nos da el valor de el Error Absoluto
        if iterador > 0:
            
            # Se calcula el error con xrNueva - xrAnterior / xrNueva 
            # Se extrae el valor anterior del arreglo con nombre actual, deacuerdo  iterador - 1 para encontrar la posicion del valor.
            error = ( ( xr - actual[iterador -1] ) / xr ) 

            # Se convierta % y a valor absoluto
            errorAbs = abs(error) * 100

            # Formato de resultado en pantalla
            print(f'{iterador + 1}|\t{xi:>8.4f}|{xf:>8.4f}|{xr:>8.4f}|{f(xi):>8.4f}|{f(xf):8.4f}|{f(xr):8.4f}|{xixr:>12.4f}|{errorAbs:>7.4f} % | {error:>8.4f}|')
             
            # Si el error absoluto es menor a el error Maximo el programa cierra.
            if errorAbs < errMax:
               break
        
        # si la condicion no se cumple imprime en pantalla la cabezera de la tabla y la primera resolucioón donde no se calcula el error.
        else: 
    
            print('\033[1;31m')
            print(f'i|\t{"Xi":>8}|{"Xf":>8}|{"Xr":>8}|{"F(xi)":>8}|{"F(xf)":>8}|{"F(xr)":>8}| {"F(Xi)*F(xr)":>4}|{"Eamax %":>10}| {"EaMax":>8}| \033[0;37m ')
    
            print("----------------------------------------------------------------------------------------------")

            print(f'{iterador + 1}|\t{xi:>8.4f}|{xf:>8.4f}|{xr:>8.4f}|{f(xi):>8.4f}|{f(xf):8.4f}|{f(xr):8.4f}|{xixr:>12.4f}|{"N/A":>10}| {"N/A":>8}|')

        
        # si F(xi) * F(xr) es mayor a eso le asignamos el valor de xr a xi
        if xixr > 0:         
            xi = xr

        # si F(xi) * F(xr) es menor a eso le asignamos el valor de xr a xf    
        if xixr < 0:
            xf = xr

        # aumenta el valor del iterador en 1.    
        iterador+=1



inicial = float(input("Escribe el valor inicial: "))
print()
final = float(input("Escribe el valor final: "))
print()
errorMax = float(input("Escribe el valor de Error Maximo: ")) 
print("\033[0;37m")




reglaFalsa(inicial,final,errorMax)