import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input data in format: Surname Name Patronymic Birthdate(dd.MM.yyyy) Phonenumber Gender(f,m) splitted by spacebar: ");
            String input = scanner.nextLine();
            String[] data = input.split(" ");
            scanner.close();
            if (data.length != 6) {
                throw new IllegalArgumentException("Incorrect data number. Need 6, got  " + data.length);
            }
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (Exception e) {
                throw new IllegalArgumentException("Incorrect format of birthdate.", e);
            }
            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect format of phonenumber.", e);
            }
            char gender = data[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Incorrect format of gender.");
            }
            String fileName = lastName + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                String formattedData = lastName + " " + firstName + " " + middleName + " " +
                                       dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " +
                                       phoneNumber + " " + gender + "\n";
                writer.write(formattedData);
                writer.close();
                System.out.println("Data record successful.");
            } catch (IOException e) {
                System.err.println("Data record unsuccessful.");
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Data input failed. " + e.getMessage());
        }
    }
}
