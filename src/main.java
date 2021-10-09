import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class main extends Application{
	public int key;
	ArrayList<Circle>circles = new ArrayList<Circle>();
	ArrayList<Integer>deleteCircles = new ArrayList<Integer>();
	@FXML Button insertButton = new Button("Insert"); 
    @FXML Button deleteButton = new Button("Delete");
    @FXML Button resetButton = new Button("Clear");
    GridPane pane = new GridPane();
    BorderPane buttonsPane = new BorderPane();
    BorderPane rootPane = new BorderPane();
    Circle c = new Circle(100,100,30);
	static int x = 10;
	static int y = 10;
	private RBTree rb =  new RBTree();
	private VisualRB visual = new VisualRB();
	private TextField keyText = new TextField();
	
	public void start(Stage stage) 
    { 
		HBox hBox = new HBox(15);
		buttonsPane.setTop(hBox);
		buttonsPane.setMargin(hBox,new Insets(10,10,10,10));
		TextField keyText = new TextField();
		Label nullLabel = new Label();
		nullLabel.setPrefWidth(30);
		keyText.setPrefWidth(60);
		keyText.setAlignment(Pos.BASELINE_RIGHT);
		resetButton.setId("reset");
		resetButton.setStyle("-fx-base: red;");
		hBox.getChildren().addAll(new Label("Enter a number: "),keyText,insertButton, deleteButton,resetButton);
		hBox.setAlignment(Pos.CENTER);		
		rootPane.setTop(buttonsPane);
		
		insertButton.setOnAction(e->{
			StackPane stackPane = new StackPane();
			GridPane pane = new GridPane();
			key = insertValue(keyText);
			circles = visual.drawRBTree(rb.Get_Root());
			for(int i = 0 ; i < visual.circle_value.size();i++)
			{	
				String v = visual.circle_value.get(i).value.toString();
				Text text = new Text(v);
				text.setFont(new Font(40));
			    text.setFill(Color.WHITE);
			    x = x+2;
				y = y+1;
			    if(visual.circle_value.get(i).getParent()!=null)
				{
					if(visual.circle_value.get(i).getParent().getLeft() == visual.circle_value.get(i))
						x = x-4;
					else
						x = x+4;
				}
			    pane.add(circles.get(i) ,x,y);
				pane.add(text,x,y);
			}
			pane.setAlignment(Pos.TOP_CENTER);
			stackPane.getChildren().add(pane);
			rootPane.setCenter(stackPane);
			visual.circle_value.clear();
			circles.clear();
			deleteCircles.add(key);
		});
		
		deleteButton.setOnAction(e->{
			StackPane stackPane = new StackPane();
			GridPane pane = new GridPane();
			key = deleteValue(keyText);
			circles = visual.drawRBTree(rb.Get_Root());
			for(int i = 0 ; i < visual.circle_value.size();i++)
			{
				if(visual.circle_value.get(i).value == key)
					pane.getChildren().remove(i);
			}
			for(int i = 0 ; i < visual.circle_value.size();i++)
			{
				String v = visual.circle_value.get(i).value.toString();
				Text text = new Text(v);
				text.setFont(new Font(40));
			    text.setFill(Color.WHITE);
			    x = x+2;
				y = y+1;
			    if(visual.circle_value.get(i).getParent()!=null)
				{
					if(visual.circle_value.get(i).getParent().getLeft() == visual.circle_value.get(i))
						x = x-4;
					else
						x = x+4;
				}
				pane.add(circles.get(i) ,x,y);
				pane.add(text,x,y);
			}
			pane.setAlignment(Pos.TOP_CENTER);
			stackPane.getChildren().add(pane);
			rootPane.setCenter(stackPane);
			circles.clear();
			visual.circle_value.clear();
		});
		
		resetButton.setOnAction(e->{
			reset();
		});
		
		Scene scene = new Scene(rootPane, 720, 360);
		scene.getStylesheets().add(getClass().getResource("RBtreeStyle.css").toExternalForm());
		stage.setTitle("RB-Tree Visualization");
		stage.setScene(scene); 
        stage.show();
    }	
	
	public int insertValue(TextField keyText){
		key = Integer.parseInt(keyText.getText());
		System.out.println(key);
		keyText.setText("");
		rb.Insert(key);
		return key;
	}
	
	public int deleteValue(TextField keyText){
		key = Integer.parseInt(keyText.getText());
		keyText.setText("");
		rb.Delete(key);
		return key;
	}
	
	public void reset() 
	{
		rb.Set_Root(rb.get_nullLeaf());
		circles.clear();
		visual.circle_value.clear();
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}