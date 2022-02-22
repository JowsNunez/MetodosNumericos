#
# Metodo de eliminacion de Gauss
#
# Programa que pide el número de ecuaciones (máximo 10) y después los valores de los
# coeficientes y término independiente para cada ecuación. El programa  calcula y
# despliega los valores de la solución.
#



print('\n# Programa que pide el número de ecuaciones (máximo 10) y después los valores de los')
print('# coeficientes y término independiente para cada ecuación. El programa  calcula y')
print('# despliega los valores de la solución.\n')


# Variables Globales
ecuacionList=[]
# constante global
TOTAL_EC = int(input('Ingrese el numero de ecuaciones, (Máximo 10): '))


def leeEcuaciones():
    for i in range(TOTAL_EC):
        ecuacionList.append([])
        for j in range(TOTAL_EC):
            ecuacionList[i].append(input( f'introduce el valor {j+1}  de la ecuacion {i+1}: '))


    return ecuacionList

# TotalEc representa la cantidad de ecuaciones a ingresar


def eliminacionGauss():
    return
    


def pivotea():

    for i in range(TOTAL_EC):
            for j in range(TOTAL_EC-i):
                if(j+1 < TOTAL_EC):
                    if(ecuacionList[j+1][0] > ecuacionList[j][0]):
                        aux=ecuacionList[j];
                        ecuacionList[j]=ecuacionList[j+1];
                        ecuacionList[j+1]=aux;
                        


leeEcuaciones()

aux = pivotea()

print(ecuacionList)