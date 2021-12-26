import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDB {
    public static void CreateDB( )
    {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("database successfully created");
    }


    public void CreateCountryTable() throws ClassNotFoundException, InstantiationException,  IllegalAccessException{
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c =  DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();

            String sql = "CREATE TABLE Country " +
                    "(HappinessRank INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Country TEXT NOT NULL, " +
                    " HappinessScore REAL NOT NULL, " +
                    " Standard REAL, " +
                    " Economy REAL, " +
                    " Family REAL, " +
                    " RegionID INTEGER, " +
                    " Trust REAL, " +
                    " Generosity REAL, " +
                    " Dystopia REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public  void CreateRegionTable() throws ClassNotFoundException, InstantiationException,  IllegalAccessException{
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c =  DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();

            String sql = "CREATE TABLE Region " +
                    "(RegionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Region TEXT NOT NULL UNIQUE)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void DropTable(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c =  DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();

            String sql = "drop table RegionRank";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void CreateTableRegionRank(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c =  DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();

            String sql = "CREATE TABLE RegionRank " +
                    "(Rank INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Region TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
