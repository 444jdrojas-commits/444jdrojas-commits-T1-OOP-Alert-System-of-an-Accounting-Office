
package ec.edu.espe.AlertSystem.controller;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */
public class Validation {
        private static Scanner scanner = new Scanner(System.in);
        public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero. Intente de nuevo.");
            }
        }
    }

    public static int readIntRange(String prompt, int min, int max) {
        while (true) {
            int num = readInt(prompt);

            if (num >= min && num <= max) {
                return num;
            } else {
                if (min == max) {
                    System.out.println("Error: El anio debe ser el actual (2025). Intente de nuevo");
                }else{
                    System.out.println("Error: El numero debe estar entre " + min + " y " + max + ". Intente de nuevo.");  
                }              
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero (ej. 40.50). Intente de nuevo.");
            }
        }
    }

public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();          
            if (input == null || input.trim().isEmpty()) {
                
                if (prompt.isEmpty()) {
                    return "";
                } 
                System.out.println("Error: La entrada no puede estar vacia. Intente de nuevo.");
            } else {
                return input.trim();
            }
        }
    }

    public static Date readDate(String title) {
    System.out.println(title);
    int year = readIntRange("  Anio (2025): ", 2025, 2025);
    int month = readIntRange("  Mes (1-12): ", 1, 12);

    int maxDay = 31;
    if (month == 2) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            maxDay = 29;
        } else {
            maxDay = 28;
        }
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
        maxDay = 30;
    }
    int day = readIntRange("  Dia (1-" + maxDay + "): ", 1, maxDay);
    int hour = readIntRange("  Hora (0-23): ", 0, 23);
    int minute = readIntRange("  Minuto (0-59): ", 0, 59);
    return new Date(day, month, year, hour, minute);
    }
    public static int readPositiveInt(String prompt) {
        while (true) {
            int num = readInt(prompt);
            if (num > 0 ){
                return num;
            }else{
                System.out.println("Error: El numero debe ser positivo (mayor que 0). Intente de nuevo");
            }
        }
    }
    public static double readPositiveDouble(String prompt) {
    while (true) {
        double num = readDouble(prompt);
        if (num > 0) {
            return num;
        } else {
            System.out.println("Error: El numero debe ser positivo (mayor que 0). Intente de nuevo.");
        }
    }
    }
}
