import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;

class Piso {
    public int ID;
    public String Direccion;
    public String Ciudad;
    public int Superficie;
    public int NumeroHabitaciones;
    public int PrecioAlquiler;
}

class Propietario {
    public int ID;
    public String Nombre;
    public String Telefono;
    public String Correo;
    public int Propiedades;
}

class Inquilino {
    public int ID;
    public String Nombre;
    public String Telefono;
    public String Correo;
    public int PisoAlquiladoID;
    public int DuracionContrato;
}

class Data {
    public List<Piso> Pisos;
    public List<Propietario> Propietarios;
    public List<Inquilino> Inquilinos;
}





public class MenuOpciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Creo un objeto scanner y lo uso en los métodos que requieran que el usuario introduzca algo
        try{
            int opcion =-1;

            while (opcion!=0){

                System.out.println("**Menú de Opciones**");
                System.out.println("1.Listar archivos ");
                System.out.println("2.Crear carpeta");
                System.out.println("3.Copiar archivo");
                System.out.println("4.Mover archivo");
                System.out.println("5.Leer archivo XML");
                System.out.println("6.Escribir archivo XML");
                System.out.println("7.Eliminar archivo XML");
                System.out.println("8.Escribir archivo JSON");
                System.out.println("9.Leer archivo JSON");
                System.out.println("10.Eliminar archivo JSON");
                System.out.println("0.Salir");
                opcion=scanner.nextInt();
                scanner.nextLine();//necesario para consumir el salto de linea que deja pendiente nextInt

                switch (opcion){
                    case 0:
                        System.out.println("Fin del programa.");
                        break;
                    case 1:
                       listar(scanner);
                        break;
                    case 2:
                       crearCarpeta(scanner);
                        break;
                    case 3:
                        copiarArchivo(scanner);
                        break;
                    case 4:
                       moverArchivo(scanner);
                        break;
                    case 5:
                        System.out.println("¿Que quieres leer?");
                        System.out.println("1.Piso");
                        System.out.println("2.Inquilino");
                        System.out.println("3.Propietario");
                        opcion=scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion){
                            case 1:
                                leerPisos(scanner);
                                break;
                            case 2:
                                leerInquilinos(scanner);
                                break;
                            case 3:
                                leerPropietarios(scanner);
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                        break;
                    case 6:
                        System.out.println("¿Que quieres escribir?");
                        System.out.println("1.Piso");
                        System.out.println("2.Inquilino");
                        System.out.println("3.Propietario");
                        opcion=scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion){
                            case 1:
                                escribirPisos(scanner);
                                break;
                            case 2:
                                escribirInquilinos(scanner);
                                break;
                            case 3:
                                escribirPropietarios(scanner);
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                        break;
                    case 7:
                        System.out.println("¿Que quieres eliminar?");
                        System.out.println("1.Piso");
                        System.out.println("2.Inquilino");
                        System.out.println("3.Propietario");
                        opcion=scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion){
                            case 1:
                                eliminarPisos(scanner);
                                break;
                            case 2:
                                eliminarInquilinos(scanner);
                                break;
                            case 3:
                                eliminarPropietarios(scanner);
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                        System.out.println("");
                        break;
                    case 8:
                        System.out.println("Opción no válida");
                        break;
                    case 9:
                        System.out.println("¿Que quieres leer?");
                        System.out.println("1.Piso");
                        System.out.println("2.Inquilino");
                        System.out.println("3.Propietario");
                        opcion=scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion) {
                            case 1:
                                leerJSONPiso();
                                break;
                            case 2:
                                leerJSONInquilinos();
                                break;
                            case 3:
                                leerJSONPropietarios();
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                        break;
                    case 10:
                        System.out.println("Opción no válida");
                        break;
                    default:
                        System.out.println("Opción no válida");
                        System.out.println("");
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            scanner.close();
        }
    }


   //Métodos para el menú de opciones

    //Listar archivos solo por el nombre
    public static void listarArchivos(File ruta){
            File [] directorio = ruta.listFiles();
            for(File f: directorio){
                System.out.println(f.getName());
            }
    }

    //Listar un fichero
    public static void listarFichero(File archivo){
        System.out.println(archivo.getName());
    }

    //Listar un fichero INDIVIDUAL de forma detallada
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

    //Listar los ficheros de un directorio de forma detallada
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

    //Usa los métodos de listar ficheros, toma una ruta y la analiza.
    public static void listar( Scanner scanner){
        System.out.println("1.Descripción simple");
        System.out.println("2.Descripcíon detallada");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion){
            case 1:
                System.out.println("Escribe la ruta del directorio:");
                String ruta = scanner.nextLine();
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
                ruta = scanner.nextLine();
                directorio = new File(ruta);
                if(directorio.isFile()){
                    listarFicheroDetallado(directorio);
                }else{
                    verPermiso(directorio);
                    System.out.println("");
                }
                break;
        }
    }

    //Creación de carpeta, toma una ruta y crea una carpeta.
    public static void crearCarpeta( Scanner scanner){
        try{
            System.out.println("Escribe la ruta dónde vas a crear la carpeta seguido por el nombre de la carpeta:");
            String ruta = scanner.nextLine();
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
        }catch (Exception e){
            System.out.println("Algo ha ido mal al crear la carpeta.");
        }
    }

    //Copia de archivo, usa el método copyFiles de la clase FileUtils, toma una ruta y requiere un destino con el nuevo nombre del archivo.
    public static void copiarArchivo( Scanner scanner){
        try{
            System.out.println("Introduce la ruta del archivo que quieres copiar.");
            String origen = scanner.nextLine();
            File rutaOrigen = new File(origen);

            System.out.println("Introduce la ruta de destino seguido por el nuevo nombre del archivo");
            String destino=scanner.nextLine();
            File rutaDestino = new File(destino);

            FileUtils.copyFile(rutaOrigen,rutaDestino);
            System.out.println("Archivo copiado.");
            System.out.println("");
        }catch (Exception e){
            System.out.println("Algo ha ido mal al copiar el archivo.");
        }
    }

    //Usa  el método moveFile de la clase FileUtils, toma una ruta de origen y requiere otra ruta de destino.
    public static void moverArchivo( Scanner scanner){
        try{
            System.out.println("Introduce la ruta del archivo que quieres mover:");
            String origen=scanner.nextLine();
            File origenArchivo = new File(origen);
            System.out.println("Introduce la ruta del destino del archivo:");
            String destino = scanner.nextLine();
            File destinoArchivo = new File(destino);
            FileUtils.moveFile(origenArchivo,destinoArchivo);
            System.out.println("Operación realizada correctamente");
            System.out.println("");
        }catch (Exception e){
            System.out.println("Algo ha ido mal al mover el archivo.");
        }
    }
    //Usamos BufferReader para la lectura de archivos. Dentro de este creamos un FileReader con la ruta del archivo. Por último con un bucle
    //Vamos recorreiendo el archivo linea a linea e imprimiendolas por consola.



    public static void contarElementosXML(String ruta){
        try{
            File archivo = new File(ruta);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            Element raiz=doc.getDocumentElement() ;
            NodeList hijos = raiz.getChildNodes();
            int contadorHijos=0;
            for(int i=0;i<hijos.getLength();i++){
                if(hijos.item(i).getNodeType() == Node.ELEMENT_NODE){
                    contadorHijos++;
                }
            }
            System.out.println("Número de elementos: "+contadorHijos);
        }catch (Exception e){
            System.out.println("Algo ha salido mal.");
        }
    }

    public static void escribirPisos(Scanner scanner){
        try{
            String ruta=".\\BD\\pisos.xml";
            File archivo = new File(ruta);
            contarElementosXML(ruta);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            Element nuevoNodo = doc.createElement("Piso");
            System.out.println("Introduce el ID del piso:");
            Element id=doc.createElement("ID");
            id.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(id);
            System.out.println("Introduce la dirección del piso:");
            Element direccion=doc.createElement("Direccion");
            direccion.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(direccion);
            System.out.println("Introduce el nombre de la ciudad:");
            Element ciudad=doc.createElement("Ciudad");
            ciudad.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(ciudad);
            System.out.println("Introduce la superficie del piso(m2)");
            Element superficie=doc.createElement("Superficie");
            superficie.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(superficie);
            System.out.println("Introduce el número de habitaciones");
            Element numHab=doc.createElement("NumeroHabitaciones");
            numHab.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(numHab);
            System.out.println("Introduce el precio del alquiler (€)");
            Element precioAlquiler=doc.createElement("PrecioAlquiler");
            precioAlquiler.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(precioAlquiler);
            doc.getDocumentElement().appendChild(nuevoNodo);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);
            contarElementosXML(ruta);
            borrarRetornoDeCarroAMayores(ruta);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        }



    public static void escribirInquilinos(Scanner scanner){
        try{
            String ruta=".\\BD\\inquilinos.xml";
            File archivo = new File(ruta);
            contarElementosXML(ruta);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            Element nuevoNodo = doc.createElement("Inquilino");
            System.out.println("Introduce el ID del inquilino:");
            Element id=doc.createElement("ID");
            id.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(id);
            System.out.println("Introduce el nombre del inquilino:");
            Element nombre=doc.createElement("Nombre");
            nombre.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(nombre);
            System.out.println("Introduce el telefono del inquilino:");
            Element telefono=doc.createElement("Telefono");
            telefono.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(telefono);
            System.out.println("Introduce el correo del inquilino");
            Element correo=doc.createElement("Correo");
            correo.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(correo);
            System.out.println("Introduce el ID del piso que tiene alquilado");
            Element alquilado=doc.createElement("PisoAlquiladoID");
            alquilado.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(alquilado);
            System.out.println("Introduce la duración del contrato");
            Element contrato=doc.createElement("DuracionContrato");
            contrato.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(contrato);
            doc.getDocumentElement().appendChild(nuevoNodo);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);
            contarElementosXML(ruta);
            borrarRetornoDeCarroAMayores(ruta);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void escribirPropietarios(Scanner scanner){
        try{
            String ruta=".\\BD\\propietarios.xml";
            File archivo = new File(ruta);
            contarElementosXML(ruta);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            Element nuevoNodo = doc.createElement("Propietario");
            System.out.println("Introduce el ID del propietario:");
            Element id=doc.createElement("ID");
            id.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(id);
            System.out.println("Introduce el nombre del propietario:");
            Element nombre=doc.createElement("Nombre");
            nombre.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(nombre);
            System.out.println("Introduce el telefono del propietario:");
            Element telefono=doc.createElement("Telefono");
            telefono.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(telefono);
            System.out.println("Introduce el correo del propietario");
            Element correo=doc.createElement("Correo");
            correo.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(correo);
            System.out.println("Introduce el número de propiedades");
            Element propiedades=doc.createElement("Propiedades");
            propiedades.setTextContent(scanner.nextLine());
            nuevoNodo.appendChild(propiedades);
            doc.getDocumentElement().appendChild(nuevoNodo);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);
            contarElementosXML(ruta);
           borrarRetornoDeCarroAMayores(ruta);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
    public static void leerArchivo(Scanner scanner){
        try{
            System.out.println("Introduce la ruta del archivo que quieres leer:");
            String archivo=scanner.nextLine();
            File archivoLectura = new File(archivo);
            if(archivoLectura.isDirectory()){ //Comprobamos que no sea un directorio
                System.out.println("No se puede leer un directorio, prueba con la opción para listar archivos");
            }else{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea=bufferedReader.readLine())!=null){
                    System.out.println(linea);
                }
                bufferedReader.close();
            }
            contarElementosXML(archivo);
        }catch (Exception e){
            System.out.println("Algo ha salido mal");
        }
    }
    */
    public static void leerPropietarios(Scanner scanner){
        try{
            String ruta=".\\BD\\propietarios.xml";
            File archivo = new File(ruta);
            System.out.println("Introduce el número de propiedades que posee el propietario");
            int numPropiedades = scanner.nextInt();
            scanner.nextLine();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            NodeList propietarios  = doc.getElementsByTagName("Propietario");
            for(int i=0;i<propietarios .getLength();i++){
                Element propietario = (Element) propietarios.item(i);
                int propiedades =Integer.parseInt (propietario.getElementsByTagName("Propiedades").item(0).getTextContent());
                if(propiedades==numPropiedades){
                    String id = propietario.getElementsByTagName("ID").item(0).getTextContent();
                    String nombre = propietario.getElementsByTagName("Nombre").item(0).getTextContent();
                    String telefono = propietario.getElementsByTagName("Telefono").item(0).getTextContent();
                    String correo = propietario.getElementsByTagName("Correo").item(0).getTextContent();
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Teléfono: " + telefono);
                    System.out.println("Correo: " + correo);
                    System.out.println("Propiedades: " + propiedades);
                    System.out.println("-----------");
                }
        }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public static void leerPisos(Scanner scanner){
        try{
            String ruta=".\\BD\\pisos.xml";
            File archivo = new File(ruta);
            System.out.println("Introduce el número de habitaciones del piso");
            int numHabitaciones = scanner.nextInt();
            scanner.nextLine();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            NodeList pisos  = doc.getElementsByTagName("Piso");
            for(int i=0;i<pisos .getLength();i++){
                Element piso = (Element) pisos.item(i);
                int habitaciones =Integer.parseInt (piso.getElementsByTagName("NumeroHabitaciones").item(0).getTextContent());
                if(habitaciones>numHabitaciones){
                    String id = piso.getElementsByTagName("ID").item(0).getTextContent();
                    String direccion = piso.getElementsByTagName("Direccion").item(0).getTextContent();
                    String ciudad = piso.getElementsByTagName("Ciudad").item(0).getTextContent();
                    String superficie = piso.getElementsByTagName("Superficie").item(0).getTextContent();
                    String precioAlquiler = piso.getElementsByTagName("PrecioAlquiler").item(0).getTextContent();
                    System.out.println("ID: " + id);
                    System.out.println("Direccion: " + direccion);
                    System.out.println("Ciudad: " + ciudad);
                    System.out.println("Superficie: " + superficie);
                    System.out.println("NumeroHabitaciones: "+habitaciones);
                    System.out.println("PrecioAlquiler: " + precioAlquiler);
                    System.out.println("-----------");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void leerInquilinos(Scanner scanner){
        try{
            String ruta=".\\BD\\inquilinos.xml";
            File archivo = new File(ruta);
            System.out.println("Introduce la duración del contrato");
            int duracionContrato = scanner.nextInt();
            scanner.nextLine();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            NodeList inquilinos  = doc.getElementsByTagName("Inquilino");

            for(int i=0;i<inquilinos .getLength();i++){
                Element inquilino = (Element) inquilinos.item(i);
                int tiempoContrato =Integer.parseInt (inquilino.getElementsByTagName("DuracionContrato").item(0).getTextContent());
                if(tiempoContrato>duracionContrato){
                    String id = inquilino.getElementsByTagName("ID").item(0).getTextContent();
                    String nombre = inquilino.getElementsByTagName("Nombre").item(0).getTextContent();
                    String telefono = inquilino.getElementsByTagName("Telefono").item(0).getTextContent();
                    String correo = inquilino.getElementsByTagName("Correo").item(0).getTextContent();
                    String pisoAlquiladoID = inquilino.getElementsByTagName("PisoAlquiladoID").item(0).getTextContent();
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Telefono: " + telefono);
                    System.out.println("Correo: " + correo);
                    System.out.println("PisoAlquiladoID: " + pisoAlquiladoID);
                    System.out.println("NumeroHabitaciones: "+tiempoContrato);
                    System.out.println("-----------");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public static void eliminarPropietarios(Scanner scanner){
        String archivo =".\\BD\\propietarios.xml";
        contarElementosXML(archivo);
        System.out.println("Introduce el ID del propietario a eliminar:");
        int idPropietario= scanner.nextInt();
        scanner.nextLine();
        try{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(archivo);
        doc.normalizeDocument();
        NodeList propietarios = doc.getElementsByTagName("Propietario");
        for(int i=0;i<=propietarios.getLength()-1;i++){
            Element propietario = (Element) propietarios.item(i);
            if(Integer.parseInt(propietario.getElementsByTagName("ID").item(0).getTextContent())==idPropietario){
                System.out.println("¿Seguro que quieres borrar este propietario?");
                System.out.println("1.Si");
                System.out.println("2.No");
                int opcion=scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        doc.getDocumentElement().removeChild(propietario);
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty("indent", "yes");
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(archivo);
                        transformer.transform(source, result);
                        System.out.println("Propietario con ID: "+idPropietario+" eliminado.");
                        contarElementosXML(archivo);
                        borrarRetornoDeCarroAMayores(archivo);
                        break;
                    case 2:
                        System.out.println("Operación cancelada");
                        break;
                }

            }
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void eliminarInquilinos(Scanner scanner){
        String archivo =".\\BD\\inquilinos.xml";
        contarElementosXML(archivo);
        System.out.println("Introduce el ID del inquilino a eliminar:");
        int idInquilino= scanner.nextInt();
        scanner.nextLine();
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            NodeList inquilinos = doc.getElementsByTagName("Inquilino");
            for(int i=0;i<=inquilinos.getLength()-1;i++){
                Element inquilino = (Element) inquilinos.item(i);
                if(Integer.parseInt(inquilino.getElementsByTagName("ID").item(0).getTextContent())==idInquilino){
                    System.out.println("¿Seguro que quieres borrar este inquilino?");
                    System.out.println("1.Si");
                    System.out.println("2.No");
                    int opcion=scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion){
                        case 1:
                    doc.getDocumentElement().removeChild(inquilino);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty("indent", "yes");
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(archivo);
                    transformer.transform(source, result);
                            System.out.println("Inquilino con ID: "+idInquilino+" eliminado.");
                    contarElementosXML(archivo);
                    borrarRetornoDeCarroAMayores(archivo);
                    break;
                        case 2:
                            System.out.println("Operación cancelada");
                            break;
                    }

                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarPisos(Scanner scanner){
        String archivo =".\\BD\\pisos.xml";
        contarElementosXML(archivo);
        System.out.println("Introduce el ID del piso a eliminar:");
        int idPiso= scanner.nextInt();
        scanner.nextLine();
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.normalizeDocument();
            NodeList pisos = doc.getElementsByTagName("Piso");
            for(int i=0;i<=pisos.getLength()-1;i++){
                Element piso = (Element) pisos.item(i);
                if(Integer.parseInt(piso.getElementsByTagName("ID").item(0).getTextContent())==idPiso){
                    System.out.println("¿Seguro que quieres borrar este piso?");
                    System.out.println("1.Si");
                    System.out.println("2.No");
                    int opcion=scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion){
                        case 1:
                            doc.getDocumentElement().removeChild(piso);
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty("indent", "yes");
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(archivo);
                            transformer.transform(source, result);
                            System.out.println("Piso con ID: "+idPiso+" eliminado.");
                            contarElementosXML(archivo);
                            borrarRetornoDeCarroAMayores(archivo);
                            break;
                        case 2:
                            System.out.println("Operación cancelada");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void borrarRetornoDeCarroAMayores(String ruta){
        try {
            // Leer las líneas y filtrarlas
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        lines.add(line);
                    }
                }
            }
            // Escribir las líneas filtradas de nuevo en el mismo archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void leerJSONPiso(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Data data = mapper.readValue(new File(".\\BD\\json pisos propietarios inquilinos.json"),Data.class);
            System.out.printf("%-10s %-30s %-15s %-15s %-30s %-15s%n", "ID", "Direccion", "Ciudad","Superficie","Número de habitaciones","Precio de alquiler");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Número de pisos: "+data.Pisos.size());

            for(Piso piso: data.Pisos){
                System.out.printf("%-10s %-30s %-15s %-15s %-30s %-15s%n",piso.ID,piso.Direccion,piso.Ciudad,piso.Superficie,piso.NumeroHabitaciones,piso.PrecioAlquiler);

            }
            System.out.println("");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void leerJSONPropietarios(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Data data = mapper.readValue(new File(".\\BD\\json pisos propietarios inquilinos.json"),Data.class);
            System.out.printf("%-10s %-20s %-15s %-25s %-15s%n", "ID", "Nombre", "Telefono","Correo","Propiedades");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Número de propietarios: "+data.Propietarios.size());
            for(Propietario propietario: data.Propietarios){
                System.out.printf("%-10s %-20s %-15s %-25s %-15s%n",propietario.ID,propietario.Nombre,propietario.Telefono,propietario.Correo,propietario.Propiedades);

            }
            System.out.println("");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void leerJSONInquilinos(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Data data = mapper.readValue(new File(".\\BD\\json pisos propietarios inquilinos.json"),Data.class);
            System.out.printf("%-10s %-20s %-15s %-25s %-25s %-15s%n", "ID", "Nombre", "Telefono","Correo","ID Piso Alquilado","Duración del contrato");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Número de inquilinos: "+data.Inquilinos.size());
            for(Inquilino inquilino: data.Inquilinos){
                System.out.printf("%-10s %-20s %-15s %-25s %-25s %-15s%n",inquilino.ID,inquilino.Nombre,inquilino.Telefono,inquilino.Correo,inquilino.PisoAlquiladoID,inquilino.DuracionContrato);

            }
            System.out.println("");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

//Usamos el método readFileToString de FileUtils. En este caso nos ahorramos hacer el bucle.
//Por ahora voy a dejar este método en uso, pero son intercambiable ya que comparten el mismo nombre
    /*
    public static void leerArchivo(Scanner scanner){
        try{
            System.out.println("Introduce la ruta del archivo que quieres leer:");

            String archiv=scanner.nextLine();
            File archivo = new File(archiv);
            if(archivo.isDirectory()){
                System.out.println("No se puede leer un directorio, prueba con la opción listar archivos.");
            }else{
                System.out.println(FileUtils.readFileToString(archivo));
            }
        }catch (Exception e){
            System.out.println("Algo ha salido mal al leer el archivo.");
        }
    }

     */




