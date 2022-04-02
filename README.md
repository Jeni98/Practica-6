# Practica 6: Testing de una aplicacion de Spring Boot

## Objetivo de la práctica

Dado un desarrollo de Spring Boot, es necesario anhadir tests a las siguientes clases:

- DNI & Telefono (Unit Tests) (Cada clase tiene un metodo y varias casuisticas para probar)
- ProcessController (E2E Tests) (2 endpoints)


# Contenido
El archivo contiene 3 archivos de testing: uno para DNI, uno para Telefono y otro para ProcessController.
La creacion de estos archivo se realizan con la misma estructura que tienen en el main.
Para los dos primeros archivos se realiza un Unit Tests.

##Testing DNI
En este archivo se crea el testing para la verificacion del archivo DNI. El primer Test es para el caso
en el que el usuario ingresa correctamente los datos. El segundo caso es para cuando el campo se encuentra
vacio y el ultimo caso es para cuando son valores nulos.

##Testing Telefono
En este archivo se encuentra igualmente que en el archivo de DNI los 3 casos que se pueden presentar, por tal
razon se encuentran alli los mismos @Test.

##Testing ProcessController
Para el controlador se realiza un Test End to End. Debido a que el controlador contiene 2 endpoints se realiza 
el respectivo testing para cada uno y de igual forma se establecen 3 casos para cada uno, como lo son: para el
caso en los que los valores son correctos, el caso para el que los valores son incorrectos y el caso para el
que los valores son nulos. En este testing se verifica Datarequest, para estar seguros que funciona correctamente.
Por otro lado se obtiene la direccion, los datos y con el postEntity obtenemos los resultados. Luego mostramos el
mensaje al usuario.
