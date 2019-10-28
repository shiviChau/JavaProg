import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TestUI extends Application{
    @Override
    public void start(Stage stage) {
        //Drawing a Circle
        Rectangle rectangle = new Rectangle();

        //Setting the properties of the circle
        rectangle.setX(150.0);
        rectangle.setY(75.0);
        rectangle.setWidth(300.0);
        rectangle.setHeight(150.0);

        //Setting color to the circle
        rectangle.setFill(Color.LIGHTSTEELBLUE);

        //Setting the stroke width
        rectangle.setStrokeWidth(3);

        //Setting color to the stroke
        rectangle.setStroke(Color.BLACK);

        //Drawing a text
        Text text = new Text("UI is down dawg");

        //Setting the font of the text
        text.setFont(Font.font("Edwardian Script ITC", 50));

        //Setting the position of the text
        text.setX(155);
        text.setY(50);

        //Setting color to the text
        text.setFill(Color.BEIGE);
        text.setStrokeWidth(2);
        text.setStroke(Color.DARKSLATEBLUE);

        //setting the linear gradient
        Stop[] stops = new Stop[]{
                new Stop(0, Color.LIGHTSTEELBLUE);
                new Stop(1, Color.FIREBRICK);
        };
        LinearGradient linearGradient = new LinearGradient(0,0,1,0, true, CycleMethod.NO_CYCLE, stops);
        //Creating a Group object
        Group root = new Group(rectangle, text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Color Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
