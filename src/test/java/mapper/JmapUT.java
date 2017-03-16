package mapper;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class JmapUT {
    @Test
    public void example() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        Monkey monkey = new Monkey("Orangutan", 3, 20, "1234567890", "banana,peanuts");
        Human human = new Jmap().with(HelperMapping.class).map(monkey, Human.class);

        Assert.assertEquals(monkey.getName(), human.getName());
        Assert.assertEquals(monkey.getAge(), human.getAge());

        Assert.assertEquals(monkey.getPhone(), "1234567890");
        Assert.assertEquals(human.getPhoneNumber(), 1234567890);

        Assert.assertEquals(monkey.getFood(), "banana,peanuts");
        Assert.assertEquals(human.getFood(), Arrays.asList("hamburger", "pizza", "banana", "peanuts"));
    }
}
