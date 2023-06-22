import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserData {
    private List<User> users;
    private static final Logger log =
            Logger.getLogger(FileManager.class.getName());
    public UserData(){
        users = new ArrayList<>();
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User getUser(String name){
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void addUser(User userToAdd){
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userToAdd.getName())){
                System.out.println("Пользователь с таким именем уже существует");
                log.info("Пользователь с таким именем уже существует");
                return;
            }
            }
        users.add(userToAdd);
    }
    public void removeUser(String name){
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)){
                users.remove(user);
                System.out.println("Пользователь "+user.getName()+ " удалён");
                return;
            }
        }
        System.out.println("Пользователь с таким именем не существует");
        log.info("Пользователь с таким именем не существует");

    }

    public void removeAllUsers(){
        users.clear();
    }
}
