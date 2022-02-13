#
# newthonRaphson.py
#
# @Author Jose Alfredo Nuñez Aguirre
#
# Programa que calcula las Raices utilizando el metodo de Newthon Raphson
# utilizando la funcion 0.95x ^ 3 - 5.9 x ^ 2 + 10.9 x - 6


print("\033[4;35m")
print(' Programa que calcula las Raices utilizando el metodo de Newthon Raphson')
print(' utilizando la funcion 0.95x ^ 3 - 5.9 x ^ 2 + 10.9 x - 6')
print("\033[0;32m" + "\n")


# Metodo que recibe una variable X de tipo Flotante para calcular la formula
# 0.95 (x ^ 3) - 5.9 (x ^ 2) + 10.9 (x)  - 6
# y devuelve el resultado como flotante 
def f(x):
    return (0.95 * (x**3)) - (5.9 *  (x**2)) + (10.9 * (x))  -6 

# 
# Método que calcula la raíz de la funcion  através de NEWTHON RAPHSON 
# xi: Representa el valor inicial 
# errMAx: representa el maximo error 
def newthonRaphson(xi, errMax):

    # Variable que representa la cantidad de iteraciones 
    contador = 0
    # Variable donde se guardara el ErrorAbsoluto
    errorAbs = 0

    # Cabecera de la tabla
    print('\n' +'\033[1;31m')
    print(f'iteracion |   xi   | f(xi)  | f\'(xi) | Error')
    print('----------------------------------------------')

    # Ciclo while donde se iteraran las operaciones 
    while True:
        # Variable fxi Representa el resultado obtenido del metodo f(x) 
        fxi = f(xi)
        
        # Variable derivada que guarda el resultado de la deriva de la funcion
        # 0.95 (x ^ 3) - 5.9 (x ^ 2) + 10.9 (x)  - 6  -> f'(xi)
        derivada =  (2.85 * (xi**2)) - (11.8 * (xi)) + 10.9

        # se realiza la operacion para obtener el valor de  xr
        xr = xi - (fxi/derivada)

       
        # si el contador es mayor a 0  se calcula el error Absoluto
        if contador>0:   
            
            # Se calcula el erroAbsoluto
            errorAbs = abs((xr - xi) / xr) * 100 

            ## Se muestran los resultados en consola
            print(f'{contador:>9d} | {xi:3.4f} | {fxi:3.4f} | {derivada:3.4f} | {errorAbs:3.4f}')  

            # si el errorMax es mayor a el error Absoluto entonces terminan las iteraciones    
            if errorMax > errorAbs:
                break  
        else:
            # Se muestra el primer resultado  en consola 
             print(f'{contador:>9d} | {xi:3.4f} | {fxi:3.4f} | {derivada:3.4f} |  N/A')        

            
        

              
        #  se Guarda el valor de xr en la variable xi para la siguiente iteracion
        xi = xr
        # el contador aumenta en uno             
        contador +=1



        


# El usuario ingresa el valor inicial    
inicial = float(input("Escribe el valor inicial: "))
print()
# el usuario ingresa el errorMaximo
errorMax = float(input("Escribe el valor de Error Maximo: ")) 

print("\n \033[0;37m Resultados * * * * * * * * * * * * * * * * * ")



# Se llama ala funcion newthonrapson para iniciar las iteraciones
newthonRaphson(inicial,errorMax)