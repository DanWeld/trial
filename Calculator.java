import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Calculator extends Application
{
   private GridPane buttons;
   private VBox mainPane;
   private Scene scene;

   private TextField input;

   private Button b1;
   private Button b2;
   private Button b3;
   private Button b4;
   private Button b5;
   private Button b6;
   private Button b7;
   private Button b8;
   private Button b9;
   private Button b0;
   private Button bcomma;
   private Button bplus;
   private Button bminus;
   private Button bdivide;
   private Button bmultiply;
   private Button bequals;

   // extra functionality
   private Button bclear;
   private Button bmemory;
   private Button bmemoryrecall;
   private Button bxy;

   private double value1;
   private double value2;
   private char operator;

   private double memory;

   private Listener listener;

   public void start(Stage window)
   {
      window.setTitle("Calculator");

      listener = new Listener();

      value1 = 0;
      value2 = 0;

      input = new TextField();
      input.setEditable(false);

      // in most calculators the text is right-aligned
      input.setAlignment(Pos.BASELINE_RIGHT);

      b1 = new Button("1");
      b2 = new Button("2");
      b3 = new Button("3");
      b4 = new Button("4");
      b5 = new Button("5");
      b6 = new Button("6");
      b7 = new Button("7");
      b8 = new Button("8");
      b9 = new Button("9");
      b0 = new Button("0");
      bcomma = new Button(".");
      bplus = new Button("+");
      bminus = new Button("-");
      bdivide = new Button("/");
      bmultiply = new Button("*");
      bequals = new Button("=");

      bclear = new Button("C");
      bmemory = new Button("M");
      bmemoryrecall = new Button("MR");
      bxy = new Button("X^Y");

      buttons = new GridPane();
      buttons.setVgap(2);
      buttons.setHgap(2);

      buttons.addRow(0, bmemory, bmemoryrecall, bxy, bclear);
      buttons.addRow(1, b7, b8, b9, bdivide);
      buttons.addRow(2, b4, b5, b6, bmultiply);
      buttons.addRow(3, b1, b2, b3, bminus);
      buttons.addRow(4,  b0, bcomma, bequals, bplus);

      //Run through all the Buttons in the GridPane, to set their size and add a listener
      for (int i = 0; i < buttons.getChildren().size(); i++)
      {
         ((Button)buttons.getChildren().get(i)).setPrefSize(60,50);
         ((Button)buttons.getChildren().get(i)).setOnAction(listener);
      }

      mainPane = new VBox(input, buttons);
      scene = new Scene(mainPane, 230, 250);

      window.setScene(scene);
      window.setResizable(false);
      window.show();
   }

   private class Listener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         if (e.getSource() == b1)
         {
            input.setText(input.getText() + "1");
         }
         else if (e.getSource() == b2)
         {
            input.setText(input.getText() + "2");
         }
         else if (e.getSource() == b3)
         {
            input.setText(input.getText() + "3");
         }
         else if (e.getSource() == b4)
         {
            input.setText(input.getText() + "4");
         }
         else if (e.getSource() == b5)
         {
            input.setText(input.getText() + "5");
         }
         else if (e.getSource() == b6)
         {
            input.setText(input.getText() + "6");
         }
         else if (e.getSource() == b7)
         {
            input.setText(input.getText() + "7");
         }
         else if (e.getSource() == b8)
         {
            input.setText(input.getText() + "8");
         }
         else if (e.getSource() == b9)
         {
            input.setText(input.getText() + "9");
         }
         else if (e.getSource() == b0)
         {
            input.setText(input.getText() + "0");
         }
         else if (e.getSource() == bcomma)
         {
            // if there is not already a dot in the input field then add one
            if (!input.getText().contains("."))
            {
               input.setText(input.getText() + ".");
            }
         }
         else if (e.getSource() == bplus)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               value1 = Double.parseDouble(input.getText());
               operator = '+';
               input.setText("");
            }
         }
         else if (e.getSource() == bminus)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               value1 = Double.parseDouble(input.getText());
               operator = '-';
               input.setText("");
            }
         }
         else if (e.getSource() == bmultiply)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               value1 = Double.parseDouble(input.getText());
               operator = '*';
               input.setText("");
            }
         }
         else if (e.getSource() == bdivide)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               value1 = Double.parseDouble(input.getText());
               operator = '/';
               input.setText("");
            }
         }
         else if (e.getSource() == bxy)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               value1 = Double.parseDouble(input.getText());
               operator = '^';
               input.setText("");

            }
         }
         else if (e.getSource() == bequals)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               value2 = Double.parseDouble(input.getText());

               if (operator == '+')
               {
                  input.setText((value1 + value2) + "");
               }
               else if (operator == '-')
               {
                  input.setText(value1 - value2 + "");
               }
               else if (operator == '*')
               {
                  input.setText(value1 * value2 + "");
               }
               else if (operator == '/')
               {
                  input.setText(value1 / value2 + "");
               }
               else if (operator == '^')
               {
                  input.setText(Math.pow(value1, value2) + "");
               }
            }
         }
         else if (e.getSource() == bmemory)
         {
            // if the input field is not empty and does not only contain a '.'
            if (!input.getText().equals("") && (input.getText().length() > 1
                  || !input.getText().contains(".")))
            {
               memory = Double.parseDouble(input.getText());
            }
         }
         else if (e.getSource() == bmemoryrecall)
         {
            input.setText(memory + "");
         }
         else if (e.getSource() == bclear)
         {
            input.setText("");
            operator = '?';
            value1 = 0;
            value2 = 0;
         }
      }
   }
}
