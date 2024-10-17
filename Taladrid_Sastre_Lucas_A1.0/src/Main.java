import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String nombreAlumno="Lucas Taladrid Sastre";
        System.out.println("\tHola mundo");
        for(int i=0;i<19;i++){
        System.out.print("=");
        }
        System.out.println();
        System.out.println("Mi nombre es "+nombreAlumno);
        LocalDateTime fecha = LocalDateTime.now();
        System.out.println("Hoy es "+ fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear()+" "+fecha.getHour()+":"+fecha.getMinute());
    }
}
