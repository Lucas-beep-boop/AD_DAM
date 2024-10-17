Como ejecutar desde cmd:
rutaDelProyecto\src>javac -cp rutaDeLaLibreria\libs\commons-io-2.17.0.jar MenuOpciones.java
rutaDelProyecto\src>java MenuOpciones

v.0
Es necesario añadir la librería de Apache Commons
El menú pide un número para seleccionar una opción, en el caso de poner otra cosa que no sea un número el programa se cierra.
Por ahora están implementadas las primeras dos opciones del menú.

v.1
Implementadas las funciones hasta mover archivos
Se han creado nuevos métodos para hacer el código más legible y reducir el tamaño del main
Se cierran recursos correctamente