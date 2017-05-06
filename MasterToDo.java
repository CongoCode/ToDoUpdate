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
public class MasterToDo
{

    private ArrayList <String> DaysofWeek = new ArrayList();
    public  ComboBox <String> chooseBox;
    public static ArrayList<String> langChoice = new ArrayList();
    private ArrayList<TextArea> taDays = new ArrayList<>();
    private ArrayList<CheckBox> cbDays = new ArrayList<>();
    
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
                 TextArea t=new TextArea();
                 taDays.add(t);
                 daysPane.add(t,2,k);
                 CheckBox ck=new CheckBox();
                 cbDays.add(ck);
                 daysPane.add(ck, 3, k);  
            }
    }
    
     /* This method returns a pane that will be used in the "ToDoExecute class" while
    setting up the stage for the scene
    */
    public GridPane stageSetUp(ArrayList <String> weekdays)       
    {
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
        pane.setPrefSize(1000, 600);
        
        addBtn.setOnAction(e -> SendOut());
        clear.setOnAction(e -> Clear());
        
        return pane;

    }
    
    
   public Stage ShowAdded()
      {
        Stage showstage= new Stage();
         showstage.setTitle("ShowToDo");
         HBox showDays=new HBox();
         VBox showtext=new VBox();
         GridPane showPane=new GridPane();
          
         TextArea txt=new TextArea();
         txt.setEditable(false);
         Scanner getText=new Scanner("todo.txt");
         
         showPane.getChildren().addAll(showDays,showtext);
         Scene show=new Scene(showPane);
         showstage.setScene(show);
         return showstage;
      }
    
      public void SendOut() 
      {
        try 
       {
            PrintWriter outP= new PrintWriter(new File("todo.txt"));
             for(int k=0;k<7;k++)
                {
                     if(cbDays.get(k).isSelected()){
                     outP.println(""+taDays.get(k).getText());
                }
             outP.close();
             }  
         }
        catch(FileNotFoundException ex) 
             {
                 System.out.println("file not found");          
             }
       }

    private void Clear() 
    {
        for(int k=0;k<7;k++)
                {
                     if(cbDays.get(k).isSelected())
                     taDays.get(k).setText("");
                }
    }
  }

