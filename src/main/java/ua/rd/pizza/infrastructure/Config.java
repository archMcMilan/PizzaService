package ua.rd.pizza.infrastructure;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public interface Config {
    Class<?> getImpl(String name);
}
