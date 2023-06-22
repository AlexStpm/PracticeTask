import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FileManager {
    private String fileName;
    private UserData userData;

    private static final Logger log =
            Logger.getLogger(FileManager.class.getName());
    public FileManager(){
        this.fileName = null;
        this.userData = new UserData();
    }
    public void loadFile(String fileName) throws IOException, ChecksumException, FileFormatException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException("Файл не найден");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String checksumLine = reader.readLine();
            if (checksumLine == null) {
                this.fileName = fileName;
                return;
            }
            int fileChecksum = Integer.parseInt(checksumLine);
            Map<String, User> userMap = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length != 5) {
                    throw new FileFormatException("Формат файла поврежден");
                }

                String name = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                String phoneNumber = fields[2].trim();
                String sex = fields[3].trim();
                String address = fields[4].trim();

                User user = new User(name, age, phoneNumber, sex, address);
                if (userMap.containsKey(name)) {
                    System.out.println("Найден пользователь с одинаковым ФИО: " + name);
                    log.info("Найден пользователь с одинаковым ФИО: " + name);
                }
                userMap.put(name, user);
            }
            int calculatedChecksum = calculateChecksum(new ArrayList<>(userMap.values()));
            if (fileChecksum != calculatedChecksum) {
                throw new ChecksumException("Контрольная сумма не совпадает");
            }
            this.fileName = fileName;
            for (User user : new ArrayList<>(userMap.values())) {
                this.userData.addUser(user);
            }
        }
    }

    private int calculateChecksum(List<User> users) {
        int checksum = 0;
        for (User user : users) {
            checksum += user.hashCode();
        }
        return checksum;
    }

    public void saveFileAs(String newFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {

            int calculatedChecksum = calculateChecksum(userData.getAllUsers());
            writer.write(String.valueOf(calculatedChecksum));
            writer.newLine();

            for (User user : userData.getAllUsers()) {
                writer.write(user.getName() + ";");
                writer.write(user.getAge() + ";");
                writer.write(user.getPhoneNumber() + ";");
                writer.write(user.getSex() + ";");
                writer.write(user.getAddress() + ";");
                writer.newLine();
            }
            fileName = newFileName;
        }
    }
    public void saveFile() throws IOException {
        if (fileName != null) {
            saveFileAs(fileName);
        }
    }

    public void createNewFile(String newFileName) throws Exception{
            File newFile = new File(newFileName);
            if (!newFile.createNewFile()) {
                throw new Exception("Ошибка при создании файла!");
            }
    }

    public User searchUser(String name) {
        return userData.getUser(name);
    }

    public void addUser(String name, int age, String phoneNumber, String sex, String address)throws IOException {
        if (name.isEmpty()||name.contains(";")){
            throw new IOException("Неверный формат поля \"ФИО\".");
        }

        if (age <= 0 ){
            throw new IOException("Неверный формат поля \"возраст\".");
        }

        if (phoneNumber.isEmpty() || phoneNumber.contains(";")){
            throw new IOException("Неверный формат поля \"номер телефона\".");
        }

        if(!(sex.equals("MALE") || sex.equals("FEMALE"))){
            throw new IOException("Неверный формат поля \"ПОЛ\"."); //добавить своё исключение
        }

        if ( address.isEmpty() || address.contains(";")){
            throw new IOException("Неверный формат поля \"адресс\".");
        }

        User user = new User(name, age, phoneNumber, sex, address);
        userData.addUser(user);
    }

    public void removeUser(String name) {
        userData.removeUser(name);
    }
}
class ChecksumException extends Exception{
    public ChecksumException(String s){
        super(s);
    }
}

class FileFormatException extends Exception{
    public FileFormatException(String s){
        super(s);
    }
}


