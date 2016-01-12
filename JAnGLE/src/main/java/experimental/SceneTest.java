package experimental;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SceneTest extends Application{
	
	public static void main(String[] args){
		launch(args);
	}
	
	private static class MyDragHandler implements EventHandler<MouseEvent>{
		private double relativeX;
		private double relativeY;

		@Override
		public void handle(MouseEvent arg0) {
			if(!(arg0.getSource() instanceof Rectangle)){
				return;
			}
			Rectangle source = (Rectangle) arg0.getSource();
			if(MouseEvent.MOUSE_PRESSED.equals(arg0.getEventType())){
				System.out.println(arg0.getEventType());
				relativeX = source.getX() - arg0.getSceneX();
				relativeY = source.getY() - arg0.getSceneY();
			}
			else if(MouseEvent.MOUSE_DRAGGED.equals(arg0.getEventType())){
				System.out.println(arg0.getEventType());
				source.setX(arg0.getSceneX() + relativeX);
				source.setY(arg0.getSceneY() + relativeY);
			}
		}
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image image = new Image("example.png");
		Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        Group sceneRoot = new Group();
        SubScene subscene = new SubScene(sceneRoot, 0, 0);
        subscene.heightProperty().bind(scene.heightProperty());
        subscene.widthProperty().bind(scene.widthProperty());
        root.getChildren().add(subscene);
        Rectangle rectangle = new Rectangle(260, 180);
        System.out.println(rectangle);
        MyDragHandler handler = new MyDragHandler();
        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        rectangle.setCursor(Cursor.MOVE);
        primaryStage.setScene(scene);
        rectangle.setX(150);
        rectangle.setY(150);
        rectangle.setFill(Color.RED);
        sceneRoot.getChildren().add(rectangle);
        ImagePattern pattern = new ImagePattern(image);
        Rectangle rect2 = new Rectangle(260, 180);
        MyDragHandler handler2 = new MyDragHandler();
        rect2.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler2);
        rect2.addEventHandler(MouseEvent.MOUSE_PRESSED, handler2);
        rect2.setCursor(Cursor.MOVE);
        rect2.setX(200);
        rect2.setY(200);
        rect2.setFill(pattern);
        sceneRoot.getChildren().add(rect2);
        primaryStage.show();
	}

}
