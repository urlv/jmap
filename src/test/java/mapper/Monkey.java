package mapper;

import annontation.FieldMap;
import annontation.FunctionMap;
import annontation.IgnoreField;

@IgnoreField(false)
public class Monkey {
    private String name;
    private int age;

    @IgnoreField(true)
    private int tailLength;

    @FieldMap("phoneNumber")
    @FunctionMap("phoneMapping")
    private String phone;

    @FunctionMap("foodMapping")
    private String food;


    public Monkey(String name, int age, int tailLength, String phone, String food) {
        this.name = name;
        this.age = age;
        this.tailLength = tailLength;
        this.phone = phone;
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

    public int getTailLength() {
        return tailLength;
    }

    public void setTailLength(int tailLength) {
        this.tailLength = tailLength;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
