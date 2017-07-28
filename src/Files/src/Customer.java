import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by ladislav on 17/03/17.
 */
public class Customer implements Serializable {

    private String name;
    private String surname;
    private int age;
    public Customer(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " " +surname +" age: " +age;
    }
}
