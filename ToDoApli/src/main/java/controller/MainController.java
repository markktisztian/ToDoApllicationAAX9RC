package controller;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Check;
import dto.Task;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import org.h2.util.json.JSONArray;
import org.tinylog.Logger;


import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class MainController {

    @FXML
    private TextField dp_task;

    @FXML
    private DatePicker dp_startdate;

    @FXML
    private DatePicker dp_finishdate;

    @FXML
    private TableView<Task> tv_tasks;

    @FXML
    private TableColumn<Task, String> cl_task;

    @FXML
    private TableColumn<Task, LocalDate> cl_startdate;

    @FXML
    private TableColumn<Task, LocalDate> cl_finishdate;

    private Check check = new Check();

    public ObservableList<Task> data =
            FXCollections.observableArrayList(
                   // new Task("Item1", LocalDate.now(), LocalDate.now()),
                    // new Task("Item2", LocalDate.now(), LocalDate.now())
            );




    /**
     * Method for initializing the connection and setting the clicked row data into the text field and data pickers
     */

    @FXML
    void initialize() {
        List<Task> rawData= Collections.emptyList();

        cl_task.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        cl_startdate.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("startDate"));
        cl_finishdate.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("finishDate"));
    /**
     * Making the json file ,then transferring the raw data to data
     */
        File file = new File("tasks.json");
        var objectmapper = new ObjectMapper();
        try {
            rawData = objectmapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = rawData.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
        tv_tasks.setItems(data);

    }

    /**
     * Method to insert data into json
     */


    @FXML void Insert() {


        final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (dp_startdate.getValue() == null || dp_finishdate.getValue() == null || dp_task.getText().equals("")) return;

        LocalDate sd = LocalDate.parse(dp_startdate.getValue().toString(), dtFormatter);
        LocalDate ed = LocalDate.parse(dp_finishdate.getValue().toString(), dtFormatter);

        // Task task = new Task(dp_task.getText(), sd, ed);
//        Task task = Task.builder()
//                .taskName(dp_task.getText())
//                .startDate(sd)
//                .finishDate(ed)
//                .build();


//        data.add(task);
//        tv_tasks.setItems(data);
//
//        /**
//         *Writing data to json file
//         */
//        var objectMapper = new ObjectMapper();
//        FileWriter fileWriter = null;


        tv_tasks.setItems(data.stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        try {
            /**
             * Checking if the entered data's are correct
             */
            if (check.checkIfEmpty(dp_task.getText(), dp_startdate.getValue(), dp_finishdate.getValue())) {
                Logger.error("Blank field detected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank field");
                alert.setHeaderText(null);
                alert.setContentText("Dont leave blank fields");
                alert.showAndWait();
            }
            /**
             * Checking if the entered data's are correct
             */
            else if (check.checkDates(dp_startdate.getValue(), dp_finishdate.getValue())) {
                Logger.error("Wrong Dates");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Dates");
                alert.setHeaderText(null);
                alert.setContentText("StartDate date need's to bee frist then finishDate");
                alert.showAndWait();
            } else {
                Task task = Task.builder()
                        .taskName(dp_task.getText())
                        .startDate(sd)
                        .finishDate(ed)
                        .build();

                data.add(task);
                tv_tasks.setItems(data);

                /**
                 *Writing data to json file
                 */
                var objectMapper = new ObjectMapper();
                FileWriter fileWriter = null;
                Logger.info("Write in file");
                fileWriter = new FileWriter("tasks.json");
                objectMapper.writeValue(fileWriter, data);
            }
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Logger.error("Invalid data detected");


        }
        ;
    }




    /**
     * Method to Delete task from the tabble and json file
     */


    @FXML
    void Delete(ActionEvent event) {
        tv_tasks.getItems().removeAll(tv_tasks.getSelectionModel().getSelectedItems());
        /*
        var objectMapper = new ObjectMapper();
        FileReader fileReader = null;

        try {
            fileReader = new FileReader("tasks.json");
            objectMapper.readValue(fileReader, (JavaType) data);
            tv_tasks.getItems().remove(tv_tasks.getSelectionModel().getSelectedItem());
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /**
     *Method to close the apllication
     */

    @FXML
    void Exit(ActionEvent event) {
       Platform.exit();
        System.out.println("Apllication closed");
        System.exit(0);

    }

}
