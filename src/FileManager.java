import java.io.*;

public class FileManager {
    private String fileName;
    private String checksum;
    private UserData userData;
    public FileManager(){
        this.fileName = null;
        this.checksum = null;
        this.userData = new UserData();
    }
    public void loadFile(String fileName) throws IOException, Exception {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException("Файл не найден");
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            line = reader.readLine();
            int calculatedChecksum = 1563328060; //добавить функцию для вычисления checksum
            if (line == null || !line.equals(String.valueOf(calculatedChecksum))) {
                throw new Exception("Контрольная сумма не совпадает"); //добавить своё исключение
            }
            checksum = line;

            while((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length != 5) {
                    throw new Exception("Формат файла поврежден"); //добавить своё исключение
                }

                String name = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                String phoneNumber = fields[2].trim();
                String sex = fields[3].trim();
                String address = fields[4].trim();

                if(!(sex.equals("MALE") || sex.equals("FEMALE"))){
                    throw new Exception("Неверный формат поля \"ПОЛ\""); //добавить своё исключение
                }

                User user = new User(name, age, phoneNumber, sex, address);
                userData.addUser(user);
            }
            this.fileName = fileName;
        }
        finally {
            if (reader != null){
                reader.close();
            }
        }
    }

    public void saveFileAs(String newFileName) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(newFileName));

            int calculatedChecksum = 1563328060; //добавить функцию для вычисления checksum
            writer.write(String.valueOf(calculatedChecksum));
            writer.newLine();

            for (User user : userData.getAllUsers()) {
                writer.write(user.getName() + ";");
                writer.write(user.getAge() + ";");
                writer.write(user.getPhoneNumber() + ";");
                writer.write(user.getSex() + ";");
                writer.write(user.getAddress());
                writer.newLine();
            }
            fileName = newFileName;
        }
        finally {
            if (writer != null){
                writer.close();
            }
        }
    }
    public void saveFile() throws IOException {
        if (fileName != null) {
            saveFileAs(fileName);
        }
    }

    public void createNewFile(String newFileName) throws Exception{

            File newFile = new File(newFileName);
            if (newFile.createNewFile()) {
                System.out.println("Файл создан." );
            }
            else
            {
                throw new Exception("Ошибка при создании файла!"); //
            }

    }

    public User searchUser(String name) {
        return userData.getUser(name);
    }

    public void addUser(String name, int age, String phoneNumber, String sex, String address)throws Exception {
        if (name.isEmpty() || age <= 0 || phoneNumber.isEmpty() || sex.isEmpty() || address.isEmpty() ||
        name.contains(";") || phoneNumber.contains(";") || sex.contains(";") || address.contains(";")){
            throw new Exception("Неверный ввод!");
        }
        if(!(sex.equals("MALE") || sex.equals("FEMALE"))){
            throw new Exception("Неверный формат поля \"ПОЛ\""); //добавить своё исключение
        }
        User user = new User(name, age, phoneNumber, sex, address);
        userData.addUser(user);
    }

    public void removeUser(String name) {
        userData.removeUser(name);
    }
}
