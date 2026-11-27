import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXShapes extends Application {

    @Override
    public void start(Stage stage) {

        // 1. LINE + ROTATE
        Line line = new Line(50, 100, 180, 150);
        line.setStroke(Color.RED);
        line.setStrokeWidth(4);

        RotateTransition rotate = new RotateTransition(Duration.seconds(3), line);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.play();

        // 2. CIRCLE + SCALE
        Circle circle = new Circle(320, 120, 40);
        circle.setFill(Color.LIGHTBLUE);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(2), circle);
        scale.setToX(1.5);
        scale.setToY(1.5);
        scale.setAutoReverse(true);
        scale.setCycleCount(Animation.INDEFINITE);
        scale.play();

        // 3. RECTANGLE + FADE
        Rectangle rect = new Rectangle(500, 80, 120, 70);
        rect.setFill(Color.GREEN);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), rect);
        fade.setFromValue(1);
        fade.setToValue(0.2);
        fade.setAutoReverse(true);
        fade.setCycleCount(Animation.INDEFINITE);
        fade.play();

        // 4. ROUNDED RECTANGLE + FILL
        Rectangle rounded = new Rectangle(50, 250, 130, 70);
        rounded.setArcWidth(20);
        rounded.setArcHeight(20);
        rounded.setFill(Color.ORANGE);

        FillTransition fill = new FillTransition(Duration.seconds(2), rounded);
        fill.setFromValue(Color.ORANGE);
        fill.setToValue(Color.PURPLE);
        fill.setAutoReverse(true);
        fill.setCycleCount(Animation.INDEFINITE);
        fill.play();

        // 5. ELLIPSE + STROKE
        Ellipse ellipse = new Ellipse(320, 280, 60, 35);
        ellipse.setFill(Color.LIGHTYELLOW);
        ellipse.setStroke(Color.BLACK);

        StrokeTransition stroke = new StrokeTransition(Duration.seconds(2), ellipse);
        stroke.setFromValue(Color.BLACK);
        stroke.setToValue(Color.RED);
        stroke.setAutoReverse(true);
        stroke.setCycleCount(Animation.INDEFINITE);
        stroke.play();

        // 6. POLYGON + SEQUENTIAL
        Polygon polygon = new Polygon(520.0,250.0, 650.0,250.0,
                                      680.0,320.0, 550.0,330.0);
        polygon.setFill(Color.GREEN);

        RotateTransition pr = new RotateTransition(Duration.seconds(2), polygon);
        pr.setByAngle(90);

        PauseTransition pp = new PauseTransition(Duration.seconds(1));

        FadeTransition pf = new FadeTransition(Duration.seconds(2), polygon);
        pf.setToValue(0.2);

        SequentialTransition seq = new SequentialTransition(pr, pp, pf);
        seq.setCycleCount(Animation.INDEFINITE);
        seq.play();

        // 7. POLYLINE + PARALLEL
        Polyline polyline = new Polyline(50.0,450.0, 90.0,500.0,
                130.0,450.0, 170.0,500.0, 210.0,450.0);
        polyline.setStroke(Color.BLUE);
        polyline.setStrokeWidth(3);
        polyline.setFill(Color.TRANSPARENT);

        ScaleTransition ps = new ScaleTransition(Duration.seconds(2), polyline);
        ps.setToX(1.3);
        ps.setToY(1.3);
        ps.setAutoReverse(true);
        ps.setCycleCount(Animation.INDEFINITE);

        RotateTransition pro = new RotateTransition(Duration.seconds(2), polyline);
        pro.setByAngle(30);
        pro.setAutoReverse(true);
        pro.setCycleCount(Animation.INDEFINITE);

        new ParallelTransition(ps, pro).play();

        // 8. CUBIC CURVE
        CubicCurve cubic = new CubicCurve(300,470, 350,380,
                                           430,550, 500,470);
        cubic.setStroke(Color.PURPLE);
        cubic.setStrokeWidth(3);
        cubic.setFill(Color.TRANSPARENT);

        // 9. QUAD CURVE
        QuadCurve quad = new QuadCurve(580,470, 650,380, 750,470);
        quad.setStroke(Color.DARKGREEN);
        quad.setStrokeWidth(3);
        quad.setFill(Color.TRANSPARENT);

        // 10. PAUSE TRANSITION
        Text message = new Text(300, 600, "JavaFX Animations");

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> message.setText("Animation Started!"));
        pause.play();

        // 11. PATH TRANSITION
        Path path = new Path(new MoveTo(600,400),
                new CubicCurveTo(650,330, 750,520, 850,400));

        Circle mover = new Circle(5, Color.RED);

        PathTransition pt = new PathTransition(Duration.seconds(4), path, mover);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();

        // GROUP + SCENE + STAGE
        Group root = new Group(line, circle, rect, rounded, ellipse,
                polygon, polyline, cubic, quad, path, mover, message);

        stage.setScene(new Scene(root, 900, 650));
        stage.setTitle("JavaFX Shapes and Transitions");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}