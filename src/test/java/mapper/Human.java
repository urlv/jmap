package mapper;

import java.util.List;

public class Human {
    private String name;
    private int age;
    private long phoneNumber;
    private List<String> food;

    public Human() {}

    public Human(String name, int age, long phoneNumber, List<String> food) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.food = food;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getFood() {
        return food;
    }

    public void setFood(List<String> food) {
        this.food = food;
    }
}
