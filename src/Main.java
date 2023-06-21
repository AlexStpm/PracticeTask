import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static FileManager fileManager;
    public static void main(String[] args) {
        fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:\n" +
                    "1. LOADFILE\n" +
                    "2. SEARCH\n" +
                    "3. ADDUSER\n" +
                    "4. REMOVEUSER\n" +
                    "5. SAVEFILE\n" +
                    "6. SAVEFILEAS\n" +
                    "7. EXIT\n" +
                    "Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleLoadFile(scanner);
                    break;
                case 2:
                    handleSearch(scanner);
                    break;
                case 3:
                    handleAddUser(scanner);
                    break;
                case 4:
                    handleRemoveUser(scanner);
                    break;
                case 5:
                    handleSaveFile();
                    break;
                case 6:
                    handleSaveFileAs(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    private static void handleLoadFile(Scanner scanner) {
        System.out.println("Введите путь к файлу");
        String fileName = scanner.nextLine();
        try {
            fileManager.loadFile(fileName);
            System.out.println("Файл успешно загружен.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Другая ошибка: " + e.getMessage());//будут другие исключения
        }
    }

    private static void handleSearch(Scanner scanner) {

    }

    private static void handleAddUser(Scanner scanner) {

    }

    private static void handleRemoveUser(Scanner scanner) {

    }

    private static void handleSaveFile() {
        try {
            fileManager.saveFile();
            System.out.println("Файл успешно сохранен");
        } catch (IOException e){
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }

    private static void handleSaveFileAs(Scanner scanner) {
        System.out.println("Введите путь к файлу");
        String fileName = scanner.nextLine();
        try {
            fileManager.saveFileAs(fileName);
            System.out.println("Файл успешно сохранен");
        } catch (IOException e){
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}