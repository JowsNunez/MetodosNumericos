

import math



"""
Función "f" 

x -- recibe el valor de la abscisa
 
devuelve el valor de la ordenada
"""

def f(x):

 return math.sin(x) - (x**2) 


"""
Función "tabula" 

lim_inf -- representa el limite inferor
lim_sup -- representa el limite superior
increment -- representa el incremento
"""
 

def tabula(lim_inf, lim_sup, increment):
            
       
    while lim_inf <= lim_sup:
        
     
        x = f(lim_inf)
        
        lim_inf += increment
        


resultados = tabula(-2,5,1)

for fila in resultados:
        for valor in fila:
            print("\t", valor, end=" ")
        print()

