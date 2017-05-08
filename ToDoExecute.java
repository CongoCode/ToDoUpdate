/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToDoUpdate;

/**
 *
 * @author benismunganga
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ToDoExecute extends Application 
{

   private GridPane pane = new GridPane();
     Scene prompt;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) 
    {    
        
        //Creating the objects 
         MasterToDo todoDefault = new MasterToDo();
         LingalaToDo todoLin = new LingalaToDo();
         KirundiToDo todoKir = new KirundiToDo();
         FrenchToDo todoFre = new FrenchToDo();
         SwahiliToDo todoSwa = new SwahiliToDo();
         
         pane = todoDefault.stageSetUp(todoDefault.getDaysofWeek());
         Scene defaultScene = new Scene(pane);
         /*
         Scene defaultScene = new Scene(todoDefault.stageSetUp(todoDefault.getDaysofWeek()));
         */
         primaryStage.setScene(defaultScene); 
        //assigning a task to the ComboBox
        
        todoDefault.getChooseBox().setOnAction(e -> 
      
       {      
            Scene prompt;
             GridPane pane = new GridPane();
            switch(todoDefault.chooseBox.getValue())
            {
                case "Swahili": 
                 pane = todoSwa.stageSetUp(todoSwa.getSiku()); 
                 todoSwa.chooseBox.setValue("Swahili"); 
                 break;
                 
                case "English":
                 pane = todoDefault.stageSetUp(todoDefault.getDaysofWeek());
                 todoDefault.chooseBox.setValue("English");   
                 break;
                 
                case "Français":
                 pane = todoFre.stageSetUp(todoFre.getJours());   
                 todoFre.chooseBox.setValue("Français");  
                 break;
    
                case "Kirundi":
                 pane = todoKir.stageSetUp(todoKir.getUmunsi()); 
                 todoKir.chooseBox.setValue("Kirundi"); 
                 break;

                case "Lingala":
                 pane = todoLin.stageSetUp(todoLin.getMikolo()); 
                 todoLin.chooseBox.setValue("Lingala"); 
                 break;
           }
            prompt = new Scene(pane);
            primaryStage.setScene(prompt); 
          
         
           
        } 
        );
       
         primaryStage.setTitle("To-Do List");
         primaryStage.show(); 
    }
    
    public static void main(String[] args) 
    {
         launch(args);
    }

}


