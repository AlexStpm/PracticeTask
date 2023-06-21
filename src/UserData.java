import java.util.ArrayList;
import java.util.List;

public class UserData {
    private List<User> users;

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
                //логирование
                return;
            }
        }
        users.add(userToAdd);
    }
    public void removeUser(String name){
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)){
                users.remove(user);
                return;
            }
        }
        System.out.println("Пользователь с таким именем не существует");
        //логирование
    }
}
