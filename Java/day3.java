import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;

public class app extends Application {

    @Override
    public void start(Stage stage) {
// this is how to enter the  number from the keyboard
        Label label1 = new Label("Number 1");
        TextField tf1 = new TextField();
        tf1.setPromptText("Please enter your number here");

        Label label2 = new Label("Number 2");
        TextField tf2 = new TextField();
        tf2.setPromptText("Please enter your number here");

        Label label3 = new Label("Output");
        TextField tf3 = new TextField();
  // text Area
        TextArea tr = new TextArea();

        RadioButton rb1=new RadioButton("Male");
        RadioButton rb2=new RadioButton("Female");
        ToggleGroup tg=new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
// Choice used to many select
        ChoiceBox cb=new ChoiceBox();
        cb.getItems().addAll("Mango","Banana","Apple","Avocado");
// How i can write the menu item of  notepad
        MenuItem news=new MenuItem("New");
        MenuItem news_window=new MenuItem("New Window");
        MenuItem open=new MenuItem("Open");
        MenuItem save=new MenuItem("Save");

        Menu file=new Menu("File");
        file.getItems().addAll(news,news_window,open,save);

        MenuItem undo=new MenuItem("Undo");
        MenuItem cut=new MenuItem("Cut");
        MenuItem copy=new MenuItem("Copy");
        MenuItem paste=new MenuItem("Paste");

        Menu edit=new Menu("Edit");
        edit.getItems().addAll(undo,cut,copy,paste);
// This is the menu off Calculator
        MenuBar notepad=new MenuBar();
        notepad.getMenus().addAll(file,edit);
       Button sub =  new Button("Subtraction(-)");
       Button add =  new Button("Addition(+)");
       Button multi = new Button("Multpilication(*)");
       Button div =  new Button("Division(/)");
        Line line = new Line(200,100,400,100);
        Circle circle = new Circle(200,100,200);
        Rectangle rect = new Rectangle(120,140,200,140);

        GridPane grid=new GridPane();




        grid.add(label1, 0, 0);
        grid.add(tf1, 1,0);
        grid.add(label2, 0, 1);
        grid.add(tf2,1,1);
        grid.add(add, 2, 2);
        grid.add(label3,0,2);
        grid.add(sub,3,2);
        grid.add(multi,4,2);


        grid.add(line,1,5);
        grid.add(circle,1,6);
        grid.add(rect ,1,8);




        grid.add(div,5,2);
        grid.add(tf3,1,2);
        grid.add(tr, 0, 2);
        grid.add(rb1,0,3);
        grid.add(rb2,1,3);
        grid.add(cb,0,4);
        grid.add(notepad,1,4);
        // Different kind of How we calculate +,-,*,/
        // The submation two of number
        add.addEventHandler(MouseEvent.MOUSE_DRAGGED ,event->
        {
            int c =  Integer .parseInt(tf1.getText());
            int d  = Integer .parseInt(tf2.getText());
            int  sum = c + d;
            String s = String.valueOf(sum);
            tf3.setText("output = "+sum);
            tf3.setText(s);


        });
        // the subtraction of two number
  sub.setOnKeyPressed(event->
  {

            int c =  Integer .parseInt(tf1.getText());
            int d  = Integer .parseInt(tf2.getText());
            int  diff = c - d;
            String s = String.valueOf(diff);
            tf3.setText("output = "+diff);
            tf3.setText(s);


        });
  // the multiplication
  multi.setOnKeyReleased(keyEvent ->
  {

      int c =  Integer .parseInt(tf1.getText());
      int d  = Integer .parseInt(tf2.getText());
      int  times = c * d;
      String s = String.valueOf(times);
      tf3.setText("output = "+times);
      tf3.setText(s);

  });
  // the division of number
        //         add.addEventHandler(MouseEvent.MOUSE_DRAGGED ,event->
        div.setOnKeyTyped(event->
        {

            int c =  Integer .parseInt(tf1.getText());
            int d  = Integer .parseInt(tf2.getText());
            int  divi = c / d;
            String s = String.valueOf(divi);
            tf3.setText("output = "+divi);
            tf3.setText(s);
        });
//
        grid.setVgap(15);
        grid.setHgap(15);
        Scene scene=new Scene(grid,400,500);
        stage.setTitle("This is javafx first class");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}