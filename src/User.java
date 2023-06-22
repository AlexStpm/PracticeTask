public class User {
    private String name;
    private int age;
    private String phoneNumber;
    private String sex;
    private String address;

    public User(String name, int age, String phoneNumber, String sex, String address){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public void print(){
        System.out.println("ФИО = " + name +"\n" +
                "Возраст = " + age + "\n" +
                "Телефон = " + phoneNumber + "\n" +
                "Пол = " + sex + "\n" +
                "Адрес = " + address);
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = result + name.hashCode();
        result = result + age;
        result = result + phoneNumber.hashCode();
        result = result + sex.hashCode();
        result = result + address.hashCode();
        return result;
    }
}
