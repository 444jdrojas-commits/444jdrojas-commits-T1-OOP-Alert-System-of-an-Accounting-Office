package ec.edu.espe.alertsystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
                } else {
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
            if (num > 0) {
                return num;
            } else {
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

    public static boolean confirm(String prompt) {
        String resp;
        do {
            System.out.print(prompt);
            resp = scanner.nextLine().trim().toLowerCase();
        } while (!resp.equals("s") && !resp.equals("n"));
        return resp.equals("s");
    }

    public static String readStringWithPattern(String prompt, String regex, String errorMsg) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.matches(regex)) {
                System.out.println(errorMsg);
            }
        } while (!input.matches(regex));
        return input;
    }

    public static String readOptionalPattern(String prompt, String regex, String errorMsg) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return "";
        }
        if (!input.matches(regex)) {
            System.out.println(errorMsg);
            return readOptionalPattern(prompt, regex, errorMsg);
        }
        return input;
    }

    public static String readOptional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static Date readDateOptional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            System.out.println("Formato inválido. Use yyyy-MM-dd.");
            return readDateOptional(prompt);
        }
    }

    public static Date readDateRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("Error: La fecha no puede estar vacía. Intente de nuevo.");
                continue;
            }
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(s);
            } catch (ParseException e) {
                System.out.println("Formato inválido. Use yyyy-MM-dd.");
            }
        }
    }

    public static Date truncateToDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String readTimeRequired(String prompt) {
        String regex = "^(?:[01]\\d|2[0-3]):[0-5]\\d$";
        while (true) {
            String input = readString(prompt + ": ").trim();
            if (input.matches(regex)) {
                return input;
            }
            System.out.println("Hora inválida. Use formato HH:mm (24h). Ejemplo: 14:00");
        }
    }

    public static float readPositiveFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                float value = Float.parseFloat(input);
                if (value <= 0) {
                    System.out.println("El monto debe ser mayor que 0. Intente nuevamente.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un numero válido (ejemplo: 120.50).");
            }
        }
    }

    public static boolean isDateNotPast(Date date) {
        Date today = truncateToDate(new Date());
        return !date.before(today);
    }

    public static boolean isAlphabetic(String input) {
        return input != null && input.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean isInteger(String input) {
        return input != null && input.matches("^\\d+$");
    }

    public static boolean isEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean isHour(String input) {
        return input != null && input.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d$");
    }

    public static boolean isFutureDate(LocalDate date) {
        if (date == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }

}
