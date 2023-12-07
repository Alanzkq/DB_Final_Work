package Pojo;

public class User {
    private  String name;
    private  String account;
    private  Integer age;
    private String job;
    private String password;
    private String newpassword;

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    private final static User user=new User();
    private User(){}
    public static User getUser()
    {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
