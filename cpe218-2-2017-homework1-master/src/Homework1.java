
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class Homework1 extends JPanel
                      implements TreeSelectionListener {
	static Node root = null;
	
	static String infix="" ;
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

   static DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("");
    public Homework1() {
        super(new GridLayout(1,0));

        
        //createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
       // initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        
        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));

        
        ImageIcon nodeIcon = createImageIcon("middle.gif");
        if (nodeIcon != null) {
            DefaultTreeCellRenderer renderer =
                    new DefaultTreeCellRenderer();
            renderer.setClosedIcon(nodeIcon);
            renderer.setOpenIcon(nodeIcon);
            tree.setCellRenderer(renderer);
        }
        //Add the split pane to this panel.
        add(splitPane);
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Homework1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
//Returns the last path element of the selection.
//This method is useful only when the selection model allows a single selection.
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null) {
        	  htmlPane.setText("xxssxx");
            return;
        }
        infix="";
        Object nodeInfo = node.getUserObject();
        NodeInfo n = (NodeInfo) nodeInfo;
         inorder(n.nn,top);
         n.infix=infix;
        htmlPane.setText(n.infix);
 infix="";
        // TODO: Set text by infix(node n)
        /*if (node.isLeaf()) {
           NodeInfo n = (NodeInfo) nodeInfo;
            htmlPane.setText(infix(n.nn));
        } else {
            NodeInfo n = (NodeInfo) nodeInfo;
            htmlPane.setText(infix(n.nn));
        }*/
    }

 
 

  

   
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Homework1());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ScriptException {
    	

		
		
	Node root = null;
		
		
		for(int i=0;i<args.length;i++) {
			Stack<Node> mem = new Stack();
			
			
		for(int j=0;j<args[i].length();j++) {
       mem.push(new Node(args[i].toCharArray()[j]));
  
		}
		 

		root = constructTree(args[i].toCharArray());
		 root.isRoot=true;
		 System.out.print(infix(root));
	   System.out.printf("=");
	   System.out.println(calculate(root));
	 
	// infix="" ;
		} 
		
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                createAndShowGUI();
                //System.out.println(root.data);
            }
        });
    }
    
    
 public static void inorder(Node n,DefaultMutableTreeNode parent) {
    	DefaultMutableTreeNode child;
		if(n !=  null) {
			if(!(n.data=='1' || n.data=='2' || n.data=='3' || n.data=='4' || n.data=='5' ||
					n.data=='6' ||  n.data=='7' ||  n.data=='8' ||  n.data=='9' ||  n.data=='0') ) {
				if(n.isRoot==false)
		//	System.out.printf("(");
				infix=infix+"(";	
			}
			child = new DefaultMutableTreeNode(new NodeInfo(n.data,n,infix(n)));
			//child = new DefaultMutableTreeNode(n.data);
	        parent.add(child);
			inorder(n.left,child);
			
			//Visit the node by Printing the node data  
		 
			
			
		//	System.out.printf("%c",n.data);
			infix=infix+n.data;
			inorder(n.right,child);
			
			if(!(n.data=='1' || n.data=='2' || n.data=='3' || n.data=='4' || n.data=='5' ||
					n.data=='6' ||  n.data=='7' ||  n.data=='8' ||  n.data=='9' ||  n.data=='0')  ) {
				if(n.isRoot==false)
				//System.out.printf(")");
				infix=infix+")";}
				
	}
	}
	
	public static String infix(Node n) {
		if(n !=  null) {
			if(!(n.data=='1' || n.data=='2' || n.data=='3' || n.data=='4' || n.data=='5' ||
					n.data=='6' ||  n.data=='7' ||  n.data=='8' ||  n.data=='9' ||  n.data=='0') ) {
				if(n.isRoot==false)
			System.out.printf("(");
				//infix=infix+"(";	
			}
			infix(n.left);
			//Visit the node by Printing the node data  
		 
			
			
			System.out.printf("%c",n.data);
			//infix=infix+n.data;
			infix(n.right);
			
			if(!(n.data=='1' || n.data=='2' || n.data=='3' || n.data=='4' || n.data=='5' ||
					n.data=='6' ||  n.data=='7' ||  n.data=='8' ||  n.data=='9' ||  n.data=='0')  ) {
				if(n.isRoot==false)
				System.out.printf(")");
				//infix=infix+")";
				}
				
	}
		return infix;
	}
	

	
	public static  Object calculate(Node n) throws ScriptException {
		//infix(n);
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		inorder(n,top);
		top=top.getNextNode();
		//System.out.println(engine.eval(infix));
		return engine.eval(infix);
		
	}
	
	static boolean isOperator(char c) {
		if (c == '+' || c == '-'|| c == '*' || c == '/') {
			return true;
		}
		return false;
	}
	
	static Node constructTree(char postfix[]) {
		Stack<Node> stackMem = new Stack();
		Node mem, mem1, mem2;
     for (int i = 0; i < postfix.length; i++) {
         if (!isOperator(postfix[i])) {
				mem = new Node(postfix[i]);
				stackMem.push(mem);
			} else 
			{
				mem = new Node(postfix[i]);
                mem1 = stackMem.pop();	 
				mem2 = stackMem.pop();
                mem.right = mem1;
				mem.left = mem2;
				stackMem.push(mem);
			}
		}
       mem = stackMem.peek();
		stackMem.pop();

		return mem;
	}
	
	
}