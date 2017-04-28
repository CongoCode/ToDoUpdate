/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author benismunganga
 */
public class MasterToDo extends Application 
{

  
    private ArrayList <String> DaysofWeek = new ArrayList();
    public ComboBox <String> chooseBox;
    public static ArrayList<String> langChoice = new ArrayList();

//ToDo no-arg constructor that allows to create the default stage in English
    public MasterToDo()
    {
        
        DaysofWeek.add("Monday");
        DaysofWeek.add("Tuesday");
        DaysofWeek.add("Wednesday");
        DaysofWeek.add("Thursday");
        DaysofWeek.add("Friday");
        DaysofWeek.add("Saturday");
        DaysofWeek.add("Sunday");
    }

    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) 
    {
      //method that sets up the stage and displays it  
        stageSetUp(getDaysofWeek());      
    }

    
    public ArrayList <String> getDaysofWeek() 
    {
         return DaysofWeek;
    }
    
    public void setDaysofWeek(ArrayList <String> DaysofWeek) 
    {
        this.DaysofWeek = DaysofWeek;
    }
    
//method implementation that allows adding days
    private void days(ArrayList<String> weekdays, GridPane daysPane)
    {        
             for(int k=0;k<7;k++)
                 {
                     daysPane.add(new Label(""+weekdays.get(k)),1,k);
                     daysPane.add(new TextArea(),2,k);
                     daysPane.add(new CheckBox(), 3, k);
                 }
    }
      
 //method implementation that allows to set up the stage  
      
    public void stageSetUp(ArrayList <String> weekdays)
    {
        Stage stage1 = new Stage(); // Create a new stage
        stage1.setTitle("Set To Do"); // Set the stage title
        //grid pane/text enter
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        VBox chckBoxPane = new VBox(20);
        chooseBox = new ComboBox();
        langChoice.add("English");
        langChoice.add("Français");
        langChoice.add("Swahili");
        langChoice.add("Lingala");
        langChoice.add("Kirundi");
        //loop that adds items to ComboBox
        for(int k = 0; k < 5; k++)
             {
                 chooseBox.getItems().addAll(langChoice.get(k));
             }
        chooseBox.setValue("English");
        Button addBtn = new Button("Add");
        addBtn.setAlignment(Pos.BOTTOM_RIGHT);
        Button clear = new Button("Clear");
        clear.setAlignment(Pos.BOTTOM_CENTER);
        Button save = new Button("Save File");
      
        //method call that adds days of the week
        days(weekdays, pane);
        
        //adding buttons to the GridPane
        pane.add(addBtn, 1, 8);
        pane.add(clear, 2, 8);
        pane.add(save, 3, 8);
        pane.add(chooseBox, 0, 0);
        Scene prompt = new Scene(pane);
        stage1.setScene(prompt);
        stage1.show();
  
        
        addBtn.setOnAction(e -> SendOut());
        clear.setOnAction(e -> Clear());

    }
/*
    public static void main(String[] args) 
    {
        launch(args);
    }
*/
    private void ShowAdded()
    {
        
    }
    
    public void SendOut() 
    {
        
       
    }

    private void Clear() 
    {
        
    }
}

