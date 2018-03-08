package nl.avans.ivh11.BlindWalls.crosscutting;

/**
 * Created by thomasdelhez on 08-03-18.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyExecutionTime { }
