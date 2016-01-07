package experimental;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.clearlyspam23.jangle.entity.gui.EntityNode;
import com.clearlyspam23.jangle.entity.gui.handler.EntityMoveHandler;
import com.clearlyspam23.jangle.entity.gui.handler.EntityResizeHandler;

public class PlaygroundRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("example.png");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);
        Group sceneRoot = new Group();
        SubScene subscene = new SubScene(sceneRoot, 0, 0);
        subscene.heightProperty().bind(scene.heightProperty());
        subscene.widthProperty().bind(scene.widthProperty());
        root.getChildren().add(subscene);
        EntityNode entity = new EntityNode(200, 200, 260, 180, image);
        entity.setCursor(Cursor.OPEN_HAND);
        entity.addEntityHandler(new EntityMoveHandler());
        entity.addEntityHandler(new EntityResizeHandler());
        sceneRoot.getChildren().add(entity);
        String url = this.getClass().getClassLoader().getResource("Default.css").toExternalForm();
        System.out.println(url);
        scene.getStylesheets().add(url);
        primaryStage.show();
    }

}
