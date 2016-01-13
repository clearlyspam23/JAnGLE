package experimental;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.clearlyspam23.jangle.entity.gui.EntityNode;
import com.clearlyspam23.jangle.entity.gui.handler.EntityMoveHandler;
import com.clearlyspam23.jangle.entity.gui.handler.EntityResizeHandler;
import com.clearlyspam23.jangle.layer.gui.LayerNode;
import com.clearlyspam23.jangle.layer.gui.OverlayNode;
import com.clearlyspam23.jangle.layer.gui.handler.MouseMoveHandler;
import com.clearlyspam23.jangle.layer.gui.handler.MouseZoomHandler;

public class PlaygroundRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public class ForwardingDispatcher implements EventDispatcher {

        public final Parent dispatchee;
        public final EventDispatcher defaultDispatcher;

        public ForwardingDispatcher(Parent dispatchee, EventDispatcher defaultDispatcher) {
            this.dispatchee = dispatchee;
            this.defaultDispatcher = defaultDispatcher;
        }

        @Override
        public Event dispatchEvent(Event arg0, EventDispatchChain arg1) {
            arg1.append(dispatchee.getEventDispatcher());
            return arg1.dispatchEvent(arg0);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("example.png");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        LayerNode sceneRoot = new LayerNode();
        OverlayNode overlay = new OverlayNode(sceneRoot);
        new MouseMoveHandler().register(sceneRoot, overlay);
        new MouseZoomHandler().register(sceneRoot, overlay);

        SubScene subscene = new SubScene(sceneRoot, 0, 0);
        subscene.heightProperty().bind(scene.heightProperty());
        subscene.widthProperty().bind(scene.widthProperty());
        root.getChildren().add(subscene);

        EntityNode entity = new EntityNode(200, 200, 260, 180, image);
        entity.setCursor(Cursor.OPEN_HAND);
        new EntityMoveHandler().register(entity, sceneRoot, overlay);
        new EntityResizeHandler().register(entity, sceneRoot, overlay);
        sceneRoot.getChildren().add(entity);

        EntityNode entity2 = new EntityNode(400, 400, 260, 180, image);
        entity2.setCursor(Cursor.OPEN_HAND);
        new EntityMoveHandler().register(entity2, sceneRoot, overlay);
        new EntityResizeHandler().register(entity2, sceneRoot, overlay);
        sceneRoot.getChildren().add(entity2);
        sceneRoot.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                entity.setRotate(entity.getRotate() + 9);
                System.out.println("e1 = " + entity.getX() + ", " + entity.getY());
                System.out.println("e1t = " + entity.getEntityRectangle().getTranslateX() + ", "
                        + entity.getEntityRectangle().getTranslateY());
                System.out.println("e2 = " + entity2.getX() + ", " + entity2.getY());
                System.out.println("e2t = " + entity2.getEntityRectangle().getTranslateX() + ", "
                        + entity2.getEntityRectangle().getTranslateY());
                System.out.println("gt = " + sceneRoot.getTranslateX() + ", "
                        + sceneRoot.getTranslateY());
            }

        });

        String url = this.getClass().getClassLoader().getResource("Default.css").toExternalForm();
        System.out.println(url);
        scene.getStylesheets().add(url);
        scene.setEventDispatcher(new ForwardingDispatcher(sceneRoot, scene.getEventDispatcher()));
        primaryStage.show();
    }

}
