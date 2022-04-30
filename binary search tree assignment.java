import java.io.*;
import java.util.*;
import java.util.Scanner;

/*=============================================================================
|   Assignment:  HW 01 - Building and managing a BST
|
|       Author:  Noah Schrock
|     Language:  Java
|
|   To Compile:  javac BinarySearchTree.java
|
|   To Execute:  java BinarySearchTree < filename
|                     where filename is in the current directory and contains
|                           commands to insert, delete, print.
|
|        Class:  COP3503 - CS II Spring 2021
|   Instructor:  McAlpin
|     Due Date:  2/28/2021
|
+=============================================================================*/

class Hw01
{
/*============================================================Set-Up============================================================*/
    static class Node
    {
        int data;
        Node left, right;
    };
   
    static Node newNode(int item)
    {
        Node temp = new Node();
        temp.data = item;
        temp.left = temp.right = null;
       
        return temp;
    }
 
    static Node root;
   
    Hw01()  
    {  
         root = null;  
    }
   
/*============================================================Insert============================================================*/    
    void insert(int data)  
    {  
         root = insertRec(root, data);  
    }
   
    static Node insertRec(Node Node, int data)
    {
        if(Node == null)
        {
        	return newNode(data);
        }
       
        if(data < Node.data)
        {
        	Node.left = insertRec(Node.left, data);
        }    
        else if(data >= Node.data)
        {
        	Node.right = insertRec(Node.right, data);
        }
       
        return Node;
    }
   
/*============================================================Delete============================================================*/  
    void delete(int data)
    {
    root = deleteRec(root, data);
    }
   
    static Node deleteRec(Node root, int data)
    {
    	if(root == null)
    	{
    		return root;
    	}
   
    	if(data < root.data)
    	{
    		root.left = deleteRec(root.left, data);
    	}
    	else if(data > root.data)
    	{
    		root.right = deleteRec(root.right, data);
    	}
    	else
    	{
    		if(root.left == null)
    		{
    			return root.right;
    		}
    		else if(root.right == null)
    		{
    			return root.left;
    		}
   
    		root.data = min(root.right);
    		root.right = deleteRec(root.right, root.data);
    	}
   
    	return root;
    }
   
    static int min(Node root)
    {
        int temp = root.data;
        while (root.left != null)
        {
            temp = root.left.data;
            root = root.left;
        }
       
        return temp;
    }
   
/*============================================================print============================================================*/
    void print()  
    {  
         printRec(root);
         System.out.println("");
    }
 
    void printRec(Node root)
    {
        if (root != null)
        {
            printRec(root.left);
            System.out.print(" " + root.data);
            printRec(root.right);
        }
    }
   
/*============================================================search============================================================*/
    static boolean search(Node root, int num)
    {
        while (root != null)
        {
            if (num > root.data)
            {
            	root = root.right;
            }
            else if (num < root.data)
            {
            	root = root.left;
            }
            else
            {
            	return true;
            }
        }
        return false;
    }
   
/*============================================================count depth============================================================*/
    public static int depth(Node root)
    {
        if(root == null)
        {
        	return 0;
        }
       
        int left = depth(root.left);
        int right = depth(root.right);

        if(left > right)
        {
            return left + 1;
        }
        else
        {
        	return right + 1;
        }
    }
   
/*============================================================count children============================================================*/
    public int countKids(Node root)
    {
        int count = 1;
       
        if(root == null)
        {
        return 0;
        }
        	else
        {
        	count += countKids(root.left);
        	count += countKids(root.right);
        }

        return count;
    }
   
/*============================================================Complexity============================================================*/
    public static void complexityIndicator()
    {
        System.err.println("no545885;3.9;19.0");
    }
   
/*============================================================Main============================================================*/  
    public static void main(String[] args) throws FileNotFoundException
    {
        Hw01 tree = new Hw01();
       
        for (int i = 0; i < args.length; i++)
        {
            System.out.println(args[i] + " contains:");
        }
       
        // Scanner scan = new Scanner(System.in);
        Scanner scan = new Scanner(new File(args[0]));
       
        String temp = null;
       
        ArrayList<String> input = new ArrayList<>();
       
        while(scan.hasNextLine())
        {
            input.add(scan.nextLine());
        }
       
        for(int i = 0; i < input.size(); i++)
        {
            temp = input.get(i);
            System.out.println(temp);
        }
       
        for(int i = 0; i < input.size(); i++)
        {
        	temp = input.get(i);
       
        	if(temp.equals("i"))
        	{
        		System.out.println("i command:missing numeric parameter");
        	}
        	else if(temp.equals("s"))
        	{
        		System.out.println("s command:missing numeric parameter");
        	}
        	else if(temp.equals("d"))
        	{
        		System.out.println("d command:missing numeric parameter");
        	}
        	else if(temp.equals("p"))
        	{
        		tree.print();
        	}
        	else if(temp.equals("q"))
        	{
        		System.out.println("left children:\t\t" + tree.countKids(tree.root.left));
        		System.out.println("left depth:\t\t" + tree.depth(tree.root.left));
        		System.out.println("right children:\t\t" + tree.countKids(tree.root.right));
        		System.out.println("right depth:\t\t" + tree.depth(tree.root.right));
       
        		break;
        	}
        	else
        	{
        		String[] line;
        		String delimiter = " ";
        		line = temp.split(delimiter);
        		String command = line[0];
        		int num = Integer.parseInt(line[1]);

        		if(command.equals("i"))
        		{
        			tree.insert(num);
                }                            
                else if(command.equals("s"))
                {
                	if(search(root, num))
                	{
                		System.out.println(num + ": found");
                	}
                	else
                	{
                		//System.out.printf("%s %d: integer %d NOT found\n", command, num, num);
                		System.out.println(command + " " + num + ": integer " + num + " NOT found");
                	}
                }
                else if(command.equals("d"))
                {
                	if(search(root, num))
                	{
                		tree.delete(num);
                	}
                	else
                	{
                		//System.out.printf("%s %d: integer %d NOT found - NOT deleted\n", command, num, num);
                		System.out.println(command + " " + num + ": integer " + num + " NOT found - NOT deleted");
                	}
                }
        	}
        }
        complexityIndicator();
        scan.close();
    }
}

/*=============================================================================
|     I Noah Schrock (no545885) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied  or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/