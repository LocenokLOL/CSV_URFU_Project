import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class Chart extends JFrame {
    public Chart() {

        initUI();
    }

    private void initUI() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() {
        Connection c = null;
        Statement stmt = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:UrfuProject.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Economy AS Economy, Country AS Country FROM Country");
            while (rs.next()) {
                double economy = rs.getFloat("Economy");
                String country = rs.getString("Country");
                dataset.setValue(economy, "Economy Number", country);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Economy 2015",
                "",
                "Economy",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

}