import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class VisualRB extends Pane{
    GridPane paneTree = new GridPane();
    public ArrayList<Circle> circles = new ArrayList<Circle>();
    public ArrayList<Node> circle_value = new ArrayList<Node>(); 
    //public static int x = 1;
    //public static int y = 1;
    
	public Circle drawNode(Integer value,int color) 
	{
		 Circle circle = new Circle(150,150,30); 
		 String v = value.toString();
	     Text text = new Text(v);
	     text.setFont(new Font(45));
	     text.setFill(Color.WHITE);
	     Color colorNode;
	     if(color == 0)
	   	  colorNode = Color.BLACK;
	     else
	   	  colorNode = Color.RED;
	     circle.setFill(colorNode);
	     return circle;
	}
	public ArrayList drawRBTree(Node root) {
		VisualRB visual = new VisualRB();
	    circle_value.add(root);
	    circles.add(drawNode(root.value,root.color));
		if (root.left.value != null){
			drawRBTree(root.left);
		}
		if(root.right.value !=null){
			drawRBTree(root.right);
		}
		return circles;
	}
}       
