package ua.kiev.prog.shared;

public class Client {
    @Id
    private int id;

    private String name;
    private int age;
    private double balance;


    public Client() {
    }

    public Client(String name, int age, double balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                '}';
    }
}
