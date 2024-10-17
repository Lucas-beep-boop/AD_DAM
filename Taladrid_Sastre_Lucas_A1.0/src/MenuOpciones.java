import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuOpciones {
    public static void main(String[] args) {

        System.out.println("Bienvenido.");
        Scanner scanner = new Scanner(System.in);
        try{
       int opcion =-1;

        while (opcion!=0){

            System.out.println("1.Listar archivos ");
            System.out.println("2.Crear carpeta");
            System.out.println("3.Copiar archivo");
            System.out.println("4.Mover archivo");
            System.out.println("5.Leer archivo");
            System.out.println("6.Escribir archivo");
            System.out.println("7.Eliminar archivo");
            System.out.println("0.Salir");


            opcion=scanner.nextInt();

            switch (opcion){
                case 0:
                    System.out.println("Fin del programa.");
                    break;

                case 1:
                    System.out.println("1.Descripción simple");
                    System.out.println("2.Descripcíon detallada");
                    int seleccion = scanner.nextInt();
                    switch (seleccion){
                        case 1:
                            System.out.println("Escribe la ruta del directorio:");
                            String ruta = scanner.next();
                            File directorio = new File(ruta);
                            if(directorio.isFile()){
                                System.out.println("Lo que has escrito es un fichero.");
                                listarFichero(directorio);
                                System.out.println("");
                            }else{
                                listarArchivos(directorio);
                                System.out.println("");
                            }

                            break;
                        case 2:
                            System.out.println("Escribe la ruta del directorio:");
                            ruta = scanner.next();
                            directorio = new File(ruta);
                            if(directorio.isFile()){
                                listarFicheroDetallado(directorio);
                            }else{
                                verPermiso(directorio);
                                System.out.println("");
                            }
                            break;
                    }
                case 2:
                    System.out.println("Escribe la ruta dónde vas a crear la carpeta seguido por el nombre de la carpeta:");
                    String ruta = scanner.next();
                    File file = new File(ruta);
                    if(file.isFile()){
                        System.out.println("No se puede crear una carpeta en un archivo");
                    }else{
                        if(file.mkdir()){
                            System.out.println("Carpeta creada");
                        }else {
                            System.out.println("Error al crear la carpeta");
                        }
                        System.out.println("");
                    }

                    break;
                case 3:
                    System.out.println("Opción aún no implementada");
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Opción aún no implementada");
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("Opción aún no implementada");
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("Opción aún no implementada");
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("Opción aún no implementada");
                    System.out.println("");
                    break;
            }
        }
            }catch (Exception e){
            System.out.println("Algo ha salido mal");
        }
    }



    public static void listarArchivos(File ruta){
        File [] directorio = ruta.listFiles();
        for(File f: directorio){
            System.out.println(f.getName());
        }
    }
    public static void listarFichero(File archivo){
        System.out.println(archivo.getName());
    }
    public static void listarFicheroDetallado(File archivo){
        System.out.print(archivo.isFile()?"(_)":"(/)");//Archivo o directorio
        System.out.print(archivo.getName()+"[");//Nombre
        System.out.print(archivo.length()>0?archivo.length()+" bytes,":"");//Tamaño

        System.out.print(archivo.canRead()?"r":"-");
        System.out.print(archivo.canWrite()?"w":"-");
        System.out.print(archivo.canExecute()?"x":"-");

        System.out.print(", ");
        long fecha = archivo.lastModified();
        Date f = new Date(fecha);
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(f)+"]");
    }
    public static void verPermiso(File ruta){
        File [] directorio = ruta.listFiles();
        for(File archivo: directorio){
        boolean leer =archivo.canRead();
        boolean escribir=archivo.canWrite();
        boolean ejecutar=archivo.canExecute();

        System.out.print(archivo.isFile()?"(_)":"(/)");//Archivo o directorio
        System.out.print(archivo.getName()+"[");//Nombre
        System.out.print(archivo.length()>0?archivo.length()+" bytes,":"");//Tamaño

        System.out.print(leer?"r":"-");
        System.out.print(escribir?"w":"-");
        System.out.print(ejecutar?"x":"-");

        System.out.print(", ");
        long fecha = archivo.lastModified();
        Date f = new Date(fecha);
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(f)+"]");
        }

    }
}

