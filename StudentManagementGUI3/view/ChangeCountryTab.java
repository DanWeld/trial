package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Student;
import model.StudentList;
import model.StudentModelManager;

/**
 * A GUI tab containing components for changing a students country.
 * @author Allan Henriksen
 * @version 2.0
 */
public class ChangeCountryTab extends Tab
{
   private VBox changeCountryPane;
   private HBox changeCountryTopPane;
   private FlowPane comboPane;

   private Label firstNameLabel;
   private Label lastNameLabel;
   private Label countryLabel;

   private GridPane changeCountryInputPane;

   private ComboBox<Student> studentBox;
   private TextField firstNameField;
   private TextField lastNameField;
   private TextField countryField;

   private FlowPane imagePane;
   private Image logo;
   private ImageView logoView;

   private Button updateButton;

   private MyActionListener listener;

   private StudentModelManager modelManager;

   /**
    * Constructor initializing the GUI components
    * @param title The title of the tab
    * @param modelManager StudentModelManager object used for retrieving and storing student information
    */
   public ChangeCountryTab(String title, StudentModelManager modelManager)
   {
      super(title);

      this.modelManager = modelManager;

      listener = new MyActionListener();

      updateButton = new Button("Update");
      updateButton.setOnAction(listener);

      changeCountryPane = new VBox(20);
      changeCountryPane.setPadding(new Insets(10));

      changeCountryTopPane = new HBox(20);

      studentBox = new ComboBox<Student>();
      studentBox.setOnAction(listener);

      comboPane = new FlowPane();
      comboPane.setAlignment(Pos.BASELINE_RIGHT);
      comboPane.setPrefWidth(200);
      comboPane.getChildren().add(studentBox);

      firstNameLabel = new Label("First name:");
      lastNameLabel = new Label("Last name:");
      countryLabel = new Label("Country:");

      firstNameField = new TextField();
      firstNameField.setEditable(false);
      lastNameField = new TextField();
      lastNameField.setEditable(false);
      countryField = new TextField();

      changeCountryInputPane = new GridPane();
      changeCountryInputPane.setHgap(5);
      changeCountryInputPane.setVgap(5);
      changeCountryInputPane.addRow(0, firstNameLabel, firstNameField);
      changeCountryInputPane.addRow(1, lastNameLabel, lastNameField);
      changeCountryInputPane.addRow(2, countryLabel, countryField);

      changeCountryTopPane.getChildren().add(changeCountryInputPane);
      changeCountryTopPane.getChildren().add(comboPane);

      logo = new Image("file:img/vialogoah.gif");
      logoView = new ImageView(logo);
      imagePane = new FlowPane();
      imagePane.setPrefHeight(200);
      imagePane.setAlignment(Pos.BOTTOM_CENTER);
      imagePane.getChildren().add(logoView);

      changeCountryPane.getChildren().add(changeCountryTopPane);
      changeCountryPane.getChildren().add(updateButton);
      changeCountryPane.getChildren().add(imagePane);

      super.setContent(changeCountryPane);

   }

   /**
    * Enables or disables editing of firstNameField and lastNameField.
    * @param bool if true then the fields will be editable, if false then they will not
    */
   public void changeEditableState(boolean bool)
   {
      firstNameField.setEditable(bool);
      lastNameField.setEditable(bool);
   }

   /**
    * Updates the studentBox ComboBox with information from the students file
    */
   public void updateStudentBox()
   {
      int currentIndex = studentBox.getSelectionModel().getSelectedIndex();

      studentBox.getItems().clear();

      StudentList students = modelManager.getAllStudents();
      for (int i = 0; i < students.size(); i++)
      {
         studentBox.getItems().add(students.get(i));
      }

      if (currentIndex == -1 && studentBox.getItems().size() > 0)
      {
         studentBox.getSelectionModel().select(0);
      }
      else
      {
         studentBox.getSelectionModel().select(currentIndex);
      }
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
         if (e.getSource() == updateButton)
         {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String country = countryField.getText();

            if (country.equals(""))
            {
               country = "?";
            }

            modelManager.changeCountry(firstName, lastName, country);
            updateStudentBox();
            countryField.setText("");
         }
         else if (e.getSource() == studentBox)
         {
            Student temp = studentBox.getSelectionModel().getSelectedItem();

            if (temp != null)
            {
               firstNameField.setText(temp.getFirstName());
               lastNameField.setText(temp.getLastName());
               countryField.setPromptText(temp.getCountry());
            }
         }
      }
   }
}
