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
        int wrongCommandCounter = 0;
        while (true) {
            System.out.println("""
                    Список доступных комманд:
                     LOADFILE
                     SEARCH
                     ADDUSER
                     REMOVEUSER
                     SAVEFILE
                     SAVEFILEAS
                     NEWFILE
                     EXIT
                    Ваш выбор:\s""");

            String choice = scanner.nextLine();


            switch (choice) {
                case "LOADFILE" -> handleLoadFile(scanner);
                case "SEARCH" -> handleSearch(scanner);
                case "ADDUSER" -> handleAddUser(scanner);
                case "REMOVEUSER" -> handleRemoveUser(scanner);
                case "SAVEFILE" -> handleSaveFile();
                case "SAVEFILEAS" -> handleSaveFileAs(scanner);
                case "NEWFILE" -> handleCreateNewFile(scanner);
                case "EXIT" -> { return; }
                default -> {
                    if (wrongCommandCounter < 3){
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                        wrongCommandCounter++;
                    }
                    else{
                        return;
                    }
                }
            }
        }
    }

    private static void handleLoadFile(Scanner scanner) {
        System.out.println("Введите путь к файлу");
        String fileName = scanner.nextLine();
        try {
            fileManager.loadFile(fileName);
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

        } catch (Exception e){
            System.out.println("Ошибка записи файла: " + e.getMessage());
            log.warning("Ошибка записи файла: " + e);
        }
    }

    private static void handleSaveFileAs(Scanner scanner) {
        System.out.println("Введите путь к файлу");
        String fileName = scanner.nextLine();
        try {
            fileManager.saveFileAs(fileName);
        } catch (Exception e){
            System.out.println("Ошибка записи файла: " + e.getMessage());
            log.warning("Ошибка записи файла: " + e);
        }
    }

    private static void handleCreateNewFile(Scanner scanner){
        System.out.println("Введите путь к файлу");
        String fileName = scanner.nextLine();
        try {
            fileManager.createNewFile(fileName);
        } catch (Exception e){
            System.out.println("Ошибка при создании файла: " + e.getMessage());
            log.warning("Ошибка при создании файла: " + e);
        }
    }
}