package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.StudentModelManager;

/**
 * A user interface that allows for displaying and modifying information about students.
 * @author Allan Henriksen
 * @version 3.0
 */
public class StudentManagementGUI3 extends Application
{
   private StudentModelManager modelManager;

   private VBox mainPane;

   private TabPane tabPane;
   private AllStudentsTab allStudentsTab;
   private ChangeCountryTab changeCountryTab;

   private MenuBar menuBar;

   private Menu fileMenu;
   private Menu editMenu;
   private Menu aboutMenu;

   private MenuItem exitMenuItem;
   private MenuItem aboutMenuItem;

   private CheckMenuItem editAreaMenuItem;
   private CheckMenuItem editFieldsMenuItem;

   private MyActionListener listener;
   private MyTabListener tabListener;

   /**
    * @param window The Stage object that will be displayed
    */
   public void start(Stage window)
   {
      window.setTitle("Student Management GUI 3");

      modelManager = new StudentModelManager("students.bin");

      listener = new MyActionListener();
      tabListener = new MyTabListener();

      tabPane = new TabPane();
      tabPane.getSelectionModel().selectedItemProperty().addListener(tabListener);

      allStudentsTab = new AllStudentsTab("All Students", modelManager);
      changeCountryTab = new ChangeCountryTab("Change Country", modelManager);

      tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
      tabPane.getTabs().add(allStudentsTab);
      tabPane.getTabs().add(changeCountryTab);

      exitMenuItem = new MenuItem("Exit");
      exitMenuItem.setOnAction(listener);

      aboutMenuItem = new MenuItem("About");
      aboutMenuItem.setOnAction(listener);

      editAreaMenuItem = new CheckMenuItem("Edit student area");
      editAreaMenuItem.setOnAction(listener);

      editFieldsMenuItem = new CheckMenuItem("Edit name fields");
      editFieldsMenuItem.setOnAction(listener);

      fileMenu = new Menu("File");
      editMenu = new Menu("Edit");
      aboutMenu = new Menu("About");

      fileMenu.getItems().add(exitMenuItem);

      editMenu.getItems().add(editAreaMenuItem);
      editMenu.getItems().add(editFieldsMenuItem);

      aboutMenu.getItems().add(aboutMenuItem);

      menuBar = new MenuBar();

      menuBar.getMenus().add(fileMenu);
      menuBar.getMenus().add(editMenu);
      menuBar.getMenus().add(aboutMenu);

      mainPane = new VBox();
      mainPane.getChildren().add(menuBar);
      mainPane.getChildren().add(tabPane);

      Scene scene = new Scene(mainPane, 520, 390);

      window.setScene(scene);
      window.setResizable(false);
      window.show();
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
         if (e.getSource() == exitMenuItem)
         {
            Alert alert = new Alert(AlertType.CONFIRMATION,
                  "Do you really want to exit the program?", 
                  ButtonType.YES, ButtonType.NO);
            alert.setTitle("Exit");
            alert.setHeaderText(null);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
            {
               System.exit(0);
            }
         }

         else if (e.getSource() == editAreaMenuItem)
         {
            if (editAreaMenuItem.isSelected())
            {
               allStudentsTab.changeEditableState(true);
            }
            else
            {
               allStudentsTab.changeEditableState(false);
            }
         }

         else if (e.getSource() == editFieldsMenuItem)
         {
            if (editFieldsMenuItem.isSelected())
            {
               changeCountryTab.changeEditableState(true);
            }
            else
            {
               changeCountryTab.changeEditableState(false);
            }
         }

         else if (e.getSource() == aboutMenuItem)
         {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("About");
            alert.setContentText(
                  "This is just a little program that demonstrates some of the GUI features in Java");
            alert.showAndWait();
         }
      }
   }

   /*
    * Inner change listener class
    * @author Allan Henriksen
    * @version 3.0
    */
   private class MyTabListener implements ChangeListener<Tab>
   {
      public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab)
      {
         if (newTab == allStudentsTab)
         {
            allStudentsTab.updateStudentArea();
         }
         else if (newTab == changeCountryTab)
         {
            changeCountryTab.updateStudentBox();
         }
      }
   }
}
