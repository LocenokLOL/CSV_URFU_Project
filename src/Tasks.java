import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;


public class Tasks {
    public void Task3(){
        Connection c = null;
        Statement stmt = null;
        String country = "";
        float sum = 10000;
        float curSum  = 0 ;
        float economy =0;
        float dystopia=0;
        float family=0;
        float happinessScore=0;
        float trust = 0;
        float standard = 0;
        float generosity = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT AVG(Economy) AS Economy," +
                    "AVG(HappinessScore) AS HappinessScore," +
                    "AVG(Standard) AS Standard," +
                    "AVG(Family) AS Family," +
                    "AVG(Trust) AS TRUST," +
                    "AVG(Generosity) AS Generosity," +
                    "AVG(Dystopia) AS Dystopia FROM Country");
            while (rs.next()){
                economy = rs.getFloat("Economy");
                family = rs.getFloat("Family");
                dystopia = rs.getFloat("Dystopia");
                trust = rs.getFloat("Trust");
                standard = rs.getFloat("Standard");
                generosity = rs.getFloat("Generosity");
                happinessScore = rs.getFloat("HappinessScore");
            }
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Country");
            while (resultSet.next()){
                float curEconomy = resultSet.getFloat("Economy");
                float curFamily = resultSet.getFloat("Family");
                float curDystopia = resultSet.getFloat("Dystopia");
                float curTrust = resultSet.getFloat("Trust");
                float curStandard = resultSet.getFloat("Standard");
                float curGenerosity = resultSet.getFloat("Generosity");
                float curHappinessScore = resultSet.getFloat("HappinessScore");
                String curCountry = resultSet.getString("Country");
                curSum = Math.abs(curEconomy-economy) +
                        Math.abs(curDystopia-dystopia) +
                        Math.abs(curFamily-family) +
                        Math.abs(curGenerosity-generosity) +
                        Math.abs(curStandard-standard) +
                        Math.abs(curTrust-trust) +
                        Math.abs(curHappinessScore-happinessScore);
                if (sum > curSum){
                    country=curCountry;
                    sum = curSum;
                }
            }
            System.out.println("Самая \"средняя\" страна: ");
            System.out.println(country);
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void Task2(){
        Connection c = null;
        Statement stmt = null;
        double result = 0.0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();
            System.out.println("Самая лучшая Экономика среди 2 Регионов: ");
            ResultSet rs = stmt.executeQuery("SELECT MAX(Economy) AS Economy FROM Country WHERE RegionID = '12' or RegionID= '38'");
            while (rs.next()){
                float economy = rs.getFloat("Economy");
                System.out.println(economy);
            }

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void Task1(){
        EventQueue.invokeLater(() -> {
            Chart ex = new Chart();
            ex.setVisible(true);
        });
    }
}
