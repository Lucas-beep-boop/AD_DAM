import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoFicheroYDirectorio {
    //Recorrer el directorio
    //Mostrar los permisos de cada fichero
    //Si hay lectura r, si hay escritura W, permiso de ejeccución X, si no tienen alguno de los permisos -
    public static void verPermiso(File archivo){
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
    public static void main(String[] args){
        try{
            File archivo = new File("C:\\Users\\DAM2_Alu19\\IdeaProjects\\Taladrid_Sastre_Lucas_A1.0");
            File[] archivos= archivo.listFiles();
            for(File f: archivos){
                verPermiso(f);
                System.out.println();
            }
        }catch (NullPointerException e){
            System.out.println("Ruta no encontrada");
        }

    }
}
