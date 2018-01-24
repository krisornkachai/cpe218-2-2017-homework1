
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

public class newHomework {
	static String infix="" ;
	
	
	
	
	public static void main(String[] args) throws ScriptException {
	
		Node root = null;
		
		
		for(int i=0;i<args.length;i++) {
			Stack<Node> mem = new Stack();
			
			
		for(int j=0;j<args[i].length();j++) {
       mem.push(new Node(args[i].toCharArray()[j]));
  
		}
		 

		root = constructTree(args[i].toCharArray());
		 root.isRoot=true;
		 System.out.printf(infix(root));
	   System.out.printf("=");
	   System.out.println(calculate(root));
	 //infix="" ;
		}
		
		}
	

	
	public static void inorder(Node n) {
		if(n !=  null) {
			if(!(n.data=='1' || n.data=='2' || n.data=='3' || n.data=='4' || n.data=='5' ||
					n.data=='6' ||  n.data=='7' ||  n.data=='8' ||  n.data=='9' ||  n.data=='0') ) {
				if(n.isRoot==false)
		//	System.out.printf("(");
				infix=infix+"(";	
			}
			inorder(n.left);
			//Visit the node by Printing the node data  
		 
			
			
		//	System.out.printf("%c",n.data);
			infix=infix+n.data;
			inorder(n.right);
			
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
		inorder(n);
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