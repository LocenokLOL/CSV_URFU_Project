import java.awt.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*
       Parser parser = new Parser("C:\\Users\\Locenok\\Downloads\\Показатель счастья по странам 2015 (1).csv");
       parser.Insert();
       */
        Tasks tasks = new Tasks();
        tasks.Task2();
        tasks.Task3();
        tasks.Task1();
    }
}