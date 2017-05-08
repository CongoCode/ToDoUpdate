/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToDoUpdate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author benismunganga
 */
public class MasterToDo {

    private ArrayList<String> DaysofWeek = new ArrayList();
    private ArrayList<CheckBox> cbDays = new ArrayList<>();
    private ArrayList<PrintWriter> pwOut = new ArrayList<>();
    private ArrayList<TextArea> txt = new ArrayList<>();
    private ArrayList<TextArea> taDays = new ArrayList<>();
    public ComboBox<String> chooseBox;
    public static ArrayList<String> langChoice = new ArrayList();
   
    

    public MasterToDo() {

        //array list of printwriters 
        try {
            for (int k = 0; k < 7; k++) {
                PrintWriter outP = new PrintWriter(new File("ToDo" + k + ".txt"));
                pwOut.add(outP);
            }
        } catch (FileNotFoundException ex) {
            System.out.print("File Not Found");
        }

        DaysofWeek.add("Monday");
        DaysofWeek.add("Tuesday");
        DaysofWeek.add("Wednesday");
        DaysofWeek.add("Thursday");
        DaysofWeek.add("Friday");
        DaysofWeek.add("Saturday");
        DaysofWeek.add("Sunday");
    }

    /**
     * @return the chooseBox
     */
    public ComboBox<String> getChooseBox() {
        return chooseBox;
    }

    public ArrayList<String> getDaysofWeek() {
        return DaysofWeek;
    }

    //method implementation that allows adding days
    private void days(ArrayList<String> DaysofWeek, GridPane daysPane) {
        for (int k = 0; k < 7; k++) {
            daysPane.add(new Label("" + DaysofWeek.get(k)), 1, k);
            TextArea t = new TextArea();
            taDays.add(t);
            daysPane.add(t, 2, k);
            CheckBox ck = new CheckBox();
            cbDays.add(ck);
            daysPane.add(ck, 3, k);
        }
    }

    /* This method returns a pane that will be used in the "ToDoExecute class" while
    setting up the stage for the scene
     */
    public GridPane stageSetUp(ArrayList<String> weekdays) {
        Stage stage1 = new Stage(); // Create a new stage
        stage1.setTitle("Set To Do"); // Set the stage title
        //grid pane/text enter
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: DarkGrey;");
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        chooseBox = new ComboBox();
        langChoice.add("English");
        langChoice.add("Français");
        langChoice.add("Swahili");
        langChoice.add("Lingala");
        langChoice.add("Kirundi");

        //loop that adds items to ComboBox
        for (int k = 0; k < 5; k++) {
            getChooseBox().getItems().addAll(langChoice.get(k));
        }
        getChooseBox().setValue("English");

        Button addBtn = new Button("Add");
        addBtn.setAlignment(Pos.BOTTOM_RIGHT);
        Button clear = new Button("Clear");
        clear.setAlignment(Pos.BOTTOM_CENTER);
        Button showToDoBtn = new Button("Show ToDo");

        //method call that adds days of the week
        days(weekdays, pane);

        //adding buttons to the GridPane
        pane.add(addBtn, 1, 8);
        pane.add(clear, 2, 8);
        pane.add(showToDoBtn, 3, 8);
        pane.add(getChooseBox(), 0, 0);
        pane.setPrefSize(1000, 600);
        showToDoBtn.setOnAction(e -> ShowAdded());
        addBtn.setOnAction(e -> SendOut());
        clear.setOnAction(e -> Clear());

        return pane;

    }

    public void ShowAdded() {
        Stage showstage = new Stage();
        showstage.setTitle("ShowToDo");
        VBox showDays = new VBox(80);
        showDays.getChildren().addAll(new Label("Monday: "), new Label("Tuesday: "),
                new Label("Wednesday: "), new Label("Thursday: "), new Label("Friday: "),
                new Label("Saturday: "), new Label("Sunday: "));

        showDays.setPadding(new Insets(20, 20, 20, 20));
        GridPane showPane = new GridPane();

       VBox showtext = new VBox();
     
     // String[] toDoMessage = new String[7];
      ArrayList<String> toDoMessage = new ArrayList<>();
      try{ 
        for (int k = 0; k < 7; k++) 
        {
          
             Scanner scanFiles = new Scanner(new File("ToDo" + k + ".txt"));
             TextArea t = new TextArea();
             t.setEditable(false);
             String s;
             while(scanFiles.hasNextLine())
             {
             //toDoMessage[k] = scanFiles.nextLine();
                 s=(""+scanFiles.nextLine()+","+scanFiles.nextLine()+","+scanFiles.nextLine()+"");
                 toDoMessage.add(s);
                 t.setText(toDoMessage.get(k)); 
             }
             
             
            // t.setText(toDoMessage[k]);    
               
//             System.out.println(toDoMessage.get(k));
            // System.out.println(toDoMessage[k]);
             showtext.getChildren().add(t); 
             scanFiles.close();
             
       }
        showPane.add(showtext, 2, 1); 
      }
    catch(FileNotFoundException e)
    {
     System.out.println("File Not Found");
                
             }
     catch(NoSuchElementException d)
     {
         System.out.println("Add More Lines (three in each day)");
         Scene catchScene = new Scene(new Button("Add More Lines (three in each day)"), 300,300);
         Stage catchStage = new Stage();
         catchStage.setScene(catchScene);
         catchStage.show();
     }
        // t.setEditable(false);
     
        showPane.add(showDays, 1, 1);
        
        showPane.setHgap(10);
        showPane.setVgap(10);
        Scene show = new Scene(showPane);
        showstage.setScene(show);
        showstage.show();
    }

    public void SendOut() {
        {
            for (int k = 0; k < 7; k++) {
                if (cbDays.get(k).isSelected()) {

                    pwOut.get(k).println("" + taDays.get(k).getText());
                    pwOut.get(k).close();
                }
            }
        }

    }


    private void Clear() {
        for (int k = 0; k < 7; k++) {
            if (cbDays.get(k).isSelected()) {
                taDays.get(k).setText("");
            }
        }
    }
}

