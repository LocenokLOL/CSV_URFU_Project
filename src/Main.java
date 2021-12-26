import java.awt.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       Parser parser = new Parser("C:\\Users\\Locenok\\Downloads\\Показатель счастья по странам 2015 (1).csv");
       Tasks tasks = new Tasks();
       tasks.Task2();
       CreateDB db  = new CreateDB();
    }
}