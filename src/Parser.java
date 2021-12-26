import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Parser {
    public ArrayList<Country> countries = new ArrayList<>();

    public Parser(String path) {
        ArrayList<String> fileLines = null;
        try {
            fileLines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : fileLines) {
            String[] column = line.split(",");
            Country country = new Country();
            country.Name = column[0];
            country.Region = column[1];
            country.HappinessRank = column[2];
            country.HappinessScore = column[3];
            country.StandardError = column[4];
            country.Economy = column[5];
            country.Family = column[6];
            country.Health = column[7];
            country.Freedom = column[8];
            country.Trust = column[9];
            country.Generosity = column[10];
            country.DystopiaResidual = column[11];
            countries.add(country);
        }
    }

    public void Insert() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();
            String sql = "";
            String sql1 = "";
            String sql2 = "";
            boolean count = true;
            for (Country country: countries) {
                if (count){
                    count = false;
                }
                else {
                    sql = "INSERT or IGNORE INTO Region (Region)" +
                            "VALUES ('" + country.Region + "')";
                    stmt.executeUpdate(sql);
                    sql1 = "INSERT INTO Country (Country,HappinessScore,Standard,Economy,Family,RegionID,Trust,Generosity,Dystopia) " +
                            "VALUES ('" + country.Name +
                            "','" + country.HappinessScore +
                            "','" + country.StandardError +
                            "','" + country.Economy +
                            "','" + country.Family +
                            "','" + country.Region +
                            "','" + country.Trust +
                            "','" + country.Generosity +
                            "','" + country.DystopiaResidual + "')";
                    stmt.executeUpdate(sql1);
                    }
                }
            sql2 = "UPDATE Country " +
                    "SET RegionID = (SELECT RegionID " +
                    "FROM Region " +
                    "WHERE Region.Region = Country.RegionID) " +
                    "WHERE EXISTS (SELECT RegionID " +
                    "FROM Region " +
                    "WHERE Region.Region = Country.RegionID)";
            stmt.executeUpdate(sql2);
            }
            catch ( Exception e )
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
    }
    public void InsertIntoRR() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();
            String sql = "";
            String sql1 = "";
            String sql2 = "";
            boolean count = true;
            for (Country country : countries) {
                if (count) {
                    count = false;
                } else {
                    sql = "INSERT INTO RegionRank (Region)" +
                            "VALUES ('" + country.Region + "')";
                    stmt.executeUpdate(sql);
                }
            }
        }
        catch(Exception e )
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
    }
}