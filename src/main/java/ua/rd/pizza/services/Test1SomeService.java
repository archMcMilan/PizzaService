package ua.rd.pizza.services;

/**
 * Created by Artem_Pryzhkov on 10/6/2016.
 */
public class Test1SomeService implements SomeService {
    @Override
    public String getString() {
        return "Test1";
    }

    public void destroy(){
        System.out.println("destroy");
    }
}
