public class Node
{
	Integer value;
	int color;
	Node parent;
	Node left;
	Node right;
	
	public int getValue()
	{
		return value;
	}
	public void setValue(int value) 
	{
		this.value = value;
	}
	
	public int getColor() 
	{
		return color;
	}
	public void setColor(int color) 
	{
		this.color = color;
	}
	
	public Node getParent() 
	{
		return parent;
	}
	public void setParent(Node parent) 
	{
		this.parent = parent;
	}
	
	public Node getLeft() 
	{
		return left;
	}
	public void setLeft(Node left) 
	{
		this.left = left;
	}
	
	public Node getRight() 
	{
		return right;
	}
	public void setRight(Node right) 
	{
		this.right = right;
	}
	
}