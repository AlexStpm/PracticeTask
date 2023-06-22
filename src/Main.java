import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static FileManager fileManager;
    private static final Logger log =
            Logger.getLogger(FileManager.class.getName());
    public static void main(String[] args) {
        Log.addFileHandler();
        fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Выберите действие:
                    1. LOADFILE
                    2. SEARCH
                    3. ADDUSER
                    4. REMOVEUSER
                    5. SAVEFILE
                    6. SAVEFILEAS
                    7. NEWFILE
                    8. EXIT
                    Ваш выбор:\s""");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleLoadFile(scanner);
                case 2 -> handleSearch(scanner);
                case 3 -> handleAddUser(scanner);
                case 4 -> handleRemoveUser(scanner);
                case 5 -> handleSaveFile();
                case 6 -> handleSaveFileAs(scanner);
                case 7 -> handleCreateNewFile(scanner);
                case 8 -> { return; }
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
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
            log.warning("Ошибка чтения файла: " + e);
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            log.warning("Ошибка при чтении файла: " + e);
        }
    }

    private static void handleSearch(Scanner scanner) {
        System.out.println("Введите ФИО");
        String name = scanner.nextLine();
        User user = fileManager.searchUser(name);
        if (user != null) {
            System.out.println("Найден пользователь:");
            user.print();
        }
        else {
            System.out.println("Пользователь не найден");
            log.info("SEARCHUSER: Пользователь не найден");
        }
    }

    private static void handleAddUser(Scanner scanner) {

        try{
            System.out.println("Введите имя");
            String name = scanner.nextLine();

            System.out.println("Введите возраст");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Введите телефон");
            String phoneNumber = scanner.nextLine();

            System.out.println("Введите пол: MALE или FEMALE");
            String sex = scanner.nextLine().toUpperCase();

            System.out.println("Введите адрес");
            String address = scanner.nextLine();

            fileManager.addUser(name, age, phoneNumber, sex, address);
            System.out.println("Пользователь создан");
        }
        catch (InputMismatchException e){
            System.out.println("Ошибка ввода.");
            scanner.nextLine();
            log.warning("ADDUSER: Ошибка ввода.");
        }
        catch (Exception e){
            System.out.println("Ошибка при создании пользователя: " + e.getMessage());
            log.warning("Ошибка при создании пользователя:" + e);
        }
    }

    private static void handleRemoveUser(Scanner scanner) {
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        fileManager.removeUser(name);
    }

    private static void handleSaveFile() {
        try {
            fileManager.saveFile();
            System.out.println("Файл успешно сохранен");
        } catch (IOException e){
            System.out.println("Ошибка записи файла: " + e.getMessage());
            log.warning("Ошибка записи файла: " + e);
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
            log.warning("Ошибка записи файла: " + e);
        }
    }
    private static void handleCreateNewFile(Scanner scanner){
        System.out.println("Введите путь к файлу");
        String fileName = scanner.nextLine();
        try {
            fileManager.createNewFile(fileName);
            System.out.println("Файл успешно создан");
        } catch (Exception e){
            System.out.println("Ошибка при создании файла: " + e.getMessage());
            log.warning("Ошибка при создании файла: " + e);
        }
    }
}