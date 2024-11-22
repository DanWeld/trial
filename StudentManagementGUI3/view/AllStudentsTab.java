package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.StudentList;
import model.StudentModelManager;

/**
 * A GUI tab containing components for displaying a list of students.
 * @author Allan Henriksen
 * @version 2.0
 */
public class AllStudentsTab extends Tab
{
   private VBox allStudentsPane;
   private TextArea allStudentsArea;

   private Button getButton;

   private MyActionListener listener;

   private StudentModelManager modelManager;

   /**
    * Constructor initializing the GUI components
    * @param title The title of the tab
    * @param modelManager StudentModelManager object used for retrieving and storing student information
    */
   public AllStudentsTab(String title, StudentModelManager modelManager)
   {
      super(title);

      this.modelManager = modelManager;

      listener = new MyActionListener();

      allStudentsArea = new TextArea();
      allStudentsArea.setPrefRowCount(16);
      allStudentsArea.setPrefColumnCount(50);
      allStudentsArea.setEditable(false);

      getButton = new Button("Get Students");
      getButton.setOnAction(listener);

      allStudentsPane = new VBox(10);
      allStudentsPane.setAlignment(Pos.CENTER);
      allStudentsPane.getChildren().add(allStudentsArea);
      allStudentsPane.getChildren().add(getButton);

      super.setContent(allStudentsPane);
   }

   /**
    * Enables or disables editing of the allStudentsArea.
    * @param bool if true then the area will be editable, if false then it will not
    */
   public void changeEditableState(boolean bool)
   {
      allStudentsArea.setEditable(bool);
   }

   /**
    * Updates the allStudentsArea TextArea with information from the students file
    */
   public void updateStudentArea()
   {
      StudentList students = modelManager.getAllStudents();
      allStudentsArea.setText(students.toString());
   }

   /*
    * Inner action listener class
    * @author Allan Henriksen
    * @version 3.0
    */
   private class MyActionListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         if (e.getSource() == getButton)
         {
            updateStudentArea();
         }
      }
   }
}