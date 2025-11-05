package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import java.util.*;

public class CityStatisticsController {
    @FXML
    private PieChart pieChart;
    @FXML
    private void initialize() {
   // pieChart.setData(cityList);
    }
    public void setPersonData(List<Person> persons) {
        // Use a Map to count occurrences of each city.
        Map<String, Integer> cityCounts = new HashMap<>();
        for (Person p : persons) {
            // Assumes Person.java has a getCity() method
            String city = p.getCity();
            cityCounts.put(city, cityCounts.getOrDefault(city, 0) + 1);
        }

        // Create the PieChart.Data list from the map.
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : cityCounts.entrySet()) {
            // The PieChart.Data constructor takes (String name, double value)
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        // Set the data on the pie chart
        pieChart.setData(pieChartData);

        // Optional: Add a title and make it look nice
        pieChart.setTitle("Persons by City");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(30);
        pieChart.setLabelsVisible(true);
    }
}
