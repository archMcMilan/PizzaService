package ua.rd.pizza.services;

/**
 * Created by Artem_Pryzhkov on 10/6/2016.
 */
public class UsageSomeService {
    private SomeService someService;

    public UsageSomeService(SomeService someService) {
        this.someService = someService;
    }

    public void init(){
        System.out.println("call="+someService.getString());
    }
}
