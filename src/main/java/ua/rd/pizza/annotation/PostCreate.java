package ua.rd.pizza.annotation;
import java.lang.annotation.*;
/**
 * Created by Artem_Pryzhkov on 10/5/2016.
 */
@Target(value=ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface PostCreate {
}