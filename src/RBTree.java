public class RBTree 
{
	private Node root;
	private Node nullLeaf;
	static int size = 0;
	
	public RBTree() 
	{
		 nullLeaf = new Node();
		 root = nullLeaf;
		 nullLeaf.color = 0;
		 nullLeaf.left = null;
		 nullLeaf.right = null;
	}
	
	public Node get_nullLeaf() 
	{
		return nullLeaf;
	}
	public void Set_Root(Node node) 
	{
		this.root = node;
	}
	
	public Node Get_Root() 
	{
		return this.root;
	}
	
	public void Right_Rotate(Node node) 
	{
		 Node x = node.left;
		 node.left = x.right;
		 if(x.right != nullLeaf)
		 {
		   x.right.parent = node;
		 }
		 x.parent = node.parent;
		 if(node.parent == null)
		 {
		   this.root = x;
		 }
		 else if(node == node.parent.right)
		 {
			 node.parent.right = x;
		 }
		 else
		 {
			 node.parent.left = x;
		 }
		 x.right = node;
		 node.parent = x;
	}
	
	public void Left_Rotate(Node node) 
	{
		 Node x = node.right;
		 node.right = x.left;
		 if(x.left != nullLeaf)
		 {
		   x.left.parent = node;
		 }
		 x.parent = node.parent;
		 if(node.parent == null)
		 {
		   this.root = x;
		 }
		 else if(node == node.parent.left)
		 {
			 node.parent.left = x;
		 }
		 else
		 {
			 node.parent.right = x;
		 }
		 x.left = node;
		 node.parent = x;
	}
	
	
	public void Insert(int val) 
	{
		 size++;
		 Node node = new Node();
		 node.value = val;
		 node.color = 1; //red=1
		 node.parent = null;
		 node.left = nullLeaf;
		 node.right = nullLeaf;
		 
		 Node current = root;
		 Node node_parent = null;
		 while(current != nullLeaf)
		 {
			 node_parent = current;
		     if(current.value>node.value)
		     {
		    	 current = current.left;
		     }
		     else
		     {
		    	 current = current.right;
		     }
		 }
		 node.parent = node_parent;
		 if(node_parent == null)
		 {
			 root = node;
		 }
		 else if(node.value>node_parent.value)
		 {
			 node_parent.right = node;
		 }
		 else
		 {
			 node_parent.left = node;
		 }
		 
		 if(node.parent == null)
		 {
			 node.color = 0; //black=0
			 return;
		 }
		 if(node.parent.parent == null)
		 {
			 return;
		 }
		
		 //Insert Cases
		 while(node.parent.color == 1)
		 {
			 if(node.parent == node.parent.parent.left)
			 {
				 Node uncle = node.parent.parent.right;
			     if(uncle.color == 1)
			     {
			    	 uncle.color = 0;
			         node.parent.color = 0;
			         node.parent.parent.color = 1;
			         node = node.parent.parent;
			     }
			     else
			     {
			       if(node == node.parent.right)
			       {
			    	   node = node.parent;
			           Left_Rotate(node);
			       }
			       node.parent.color = 0;
			       node.parent.parent.color = 1;
			       Right_Rotate(node.parent.parent);
			     }
			 }
			 else
			 {
				 Node uncle = node.parent.parent.left;
			     if(uncle.color == 1)
			     {
			    	 uncle.color = 0;
			         node.parent.color = 0;
			         node.parent.parent.color = 1;
			         node = node.parent.parent;
			     }
			     else
			     {
			    	 if(node == node.parent.left)
				      {
				    	 node = node.parent;
				         Right_Rotate(node);
				      }
				      node.parent.color = 0;
				      node.parent.parent.color = 1;
				      Left_Rotate(node.parent.parent);
			     }
			 }
		     if(node == root)
		     {
		    	 break;
		     }
		 }
		 if(root.color != 0)
		 {
			 root.color = 0;
		 }
	}
	
	
	public void Delete(int val) 
	{
		size--;
		Node current = root;
		Node required_node = nullLeaf;
		while(current != nullLeaf)
		{
		    if(current.value == val)
		    {
		    	required_node = current;
		    }
		    if(val >= current.value)
		    {
			   current = current.right;
		    }
		    else
		    {
			   current = current.left;
		    }
		}
		if(required_node == nullLeaf)
		{
		    System.out.println("Not Found..");
		    return;
		}
		
		Node replacement_node = required_node;
		int replacement_color = replacement_node.color;
		Node x; //replacement child
		
		if(required_node.left == nullLeaf)
		{
		    x = required_node.right;
		    Replace_Node(required_node, required_node.right);
		}
		else if(required_node.right == nullLeaf)
		{
		    x = required_node.left;
		    Replace_Node(required_node, required_node.left);
		}
		else //have 2 childs
		{
			Node node = required_node.right;
			while (node.left != nullLeaf) //get successor
			{
				node = node.left;
			}
			replacement_node = node;
			replacement_color = replacement_node.color;
		    x = replacement_node.right;
		    if(replacement_node.parent == required_node)
		    {
		        x.parent = replacement_node;
		    }
		    else
		    {
		    	Replace_Node(replacement_node, replacement_node.right);
		    	replacement_node.right = required_node.right;
		    	replacement_node.right.parent = replacement_node;
		    }
		    Replace_Node(required_node,replacement_node);
		    replacement_node.left = required_node.left;
		    replacement_node.left.parent = replacement_node;
		    replacement_node.color = required_node.color;
		}
		
		//Delete Cases
		if(replacement_color == 0)
		{
			Node node;
			while(x != root && x.color == 0)
			{
			    if(x == x.parent.left)
			    {
			    	node = x.parent.right;
			        if(node.color == 1)
			        {
			        	node.color = 0;
			        	x.parent.color = 1;
			        	Left_Rotate(x.parent);
			        	node = x.parent.right;
			        }
			        if(node.left.color == 0 && node.right.color == 0)
			        {
			        	node.color = 1;
			        	x = x.parent;
			        }
			        else
			        {
			        	if(node.right.color == 0)
			        	{
			        		node.left.color = 0;
			        		node.color = 1;
			        		Right_Rotate(node);
			        		node = x.parent.right;
			        	}
			        	node.color = x.parent.color;
			        	x.parent.color = 0;
			        	node.right.color = 0;
			        	Left_Rotate(x.parent);
			        	x = root;
			        }
			    }
			    else
			    {
			    	node = x.parent.left;
			    	if(node.color == 1)
			    	{
			    		node.color = 0;
			    		x.parent.color = 1;
			    		Right_Rotate(x.parent);
			    		node = x.parent.left;
			    	}
			    	if(node.right.color == 0 && node.right.color == 0)
			    	{
			    		node.color = 1;
			    		x = x.parent;
			    	}
			    	else
			    	{
			    		if (node.left.color == 0)
			    		{
			    			node.right.color = 0;
			    			node.color = 1;
			    			Left_Rotate(node);
			    			node = x.parent.left;
			    		}
			    		node.color = x.parent.color;
			    		x.parent.color = 0;
			    		node.left.color = 0;
			    		Right_Rotate(x.parent);
			    		x = root;
			    	}
			    }
			}
			if(root.color != 0)
			{
				root.color = 0;
			}
		}
	}
	
	
	public void Replace_Node(Node req, Node req_right) 
	{
		 if (req.parent==null)
		 {
			 root = req_right;
		 }
		 else if(req==req.parent.left)
		 {
			 req.parent.left = req_right;
		 }
		 else
		 {
			 req.parent.right = req_right;
		 }
		 req_right.parent = req.parent;
	}
	
	
	public void Print() 
	{
		Node node = root;
		String str = "";
		boolean choice = true;
		Check_Tree_Paths(node, str, choice);
	}

	
	public void Check_Tree_Paths(Node node, String str, boolean choice) 
	{
		if(node != nullLeaf)
		{
		    System.out.print(str);
		    if(node == null)
		    {
		    	System.out.println("Empty Tree !!!");
		    	return;
		    }
		    if(choice)
		    {
		    	System.out.print("Right->");
		    	str = str + "   ";
		    }
		    else
		    {
		    	System.out.print("Left->");
		    	str = str + "|  ";
		    }
		    String Get_Color = node.color == 1 ? "RED" : "BLACK";
		    System.out.println(node.value + "(" + Get_Color + ")");
		    Check_Tree_Paths(node.left,str,false);
		    Check_Tree_Paths(node.right,str,true);
		 }
	}
	
	public void Clear()
	{
		root = null;
	}
	
}