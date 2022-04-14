public class Student extends Human{
    @Override
    void say(){
        System.out.println("I student " + this.getName() + " age = " + this.getAge());
    }

}
