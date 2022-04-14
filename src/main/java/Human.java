import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Human {
    private String name;
    private int age;

    void say(){}
}
