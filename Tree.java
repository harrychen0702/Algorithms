import java.util.Stack;

public class Tree {
	public class TreeNode{
		public int data;
		public TreeNode leftNode;
		public TreeNode rightNode;
		public TreeNode(int data) {
			this.data=data;
		}
	}
	
	//使用递归实现前序遍历
	public void PreOrder(TreeNode root) { 
		if(root==null) return;
		System.out.println(root.data);
		PreOrder(root.leftNode);
		PreOrder(root.rightNode);
	}
	
	//使用stack实现前序遍历
	public void PreOrder1(TreeNode root) {
		Stack<TreeNode> mystack=new Stack<TreeNode>();
		// 先存右节点，再存左节点
		mystack.push(root);
		while(!mystack.isEmpty()) {
			TreeNode current=mystack.pop();
			System.out.println(current.data);
			if(current.rightNode!=null) mystack.push(current.rightNode);
			if(current.leftNode!=null) mystack.push(current.leftNode);
		}
	}
	
	//使用stack的优化版
	public void PreOrder2(TreeNode root) {
		Stack<TreeNode> mystack=new Stack<TreeNode>();
		TreeNode current=root; //指针
		mystack.push(root);
		while(!mystack.isEmpty()) {
			System.out.println(current.data);
			if(current.rightNode!=null) {
				mystack.push(current.rightNode);
			}
			if(current.leftNode!=null) {
				current=current.leftNode;
			}
			else {
				current=mystack.pop();
			}
		}
	}
	
	//使用递归实现InOrder traversal
	public void InOrder(TreeNode root) {
		if(root==null) return;
		InOrder(root.leftNode);
		System.out.println(root.data);
		InOrder(root.rightNode);
	}
	
	//使用stack来实现
	public void InOrder1(TreeNode root) {
		Stack<TreeNode> mystack=new Stack<TreeNode>();
		TreeNode current=root;
		while(!mystack.isEmpty() || current!=null) {
			if(current!=null) {
				mystack.push(current);
				current=current.leftNode;
			}
			else { //说明左边的都访问完了,current为null了，从stack中取出中间节点打印，并开始访问右节点
				current=mystack.pop();
				System.out.println(current.data);
				current=current.rightNode;
			}
		}
	}
	
	
	// PreOrder+InOrder => Construct a binary tree
	public TreeNode construct(int[] pre,int[] in) {
		int pre_length=pre.length;
		if(pre_length==0) return null;
		return helper(0,pre_length-1,0,pre_length-1,pre,in);
	}
	
	public TreeNode helper(int start_pre,int end_pre,int start_in, int end_in,int[] pre, int[] in) {
		if(start_pre>end_pre || start_in>end_in) return null;
		TreeNode root=new TreeNode(pre[start_pre]);
		int index=0;
		for(int i=start_in;i<=end_in;i++) {
			if(pre[start_pre]==in[i]) {
				index=i;
				break;
			}
		}
		root.leftNode=helper(start_pre+1,start_pre+index-start_in,start_in,index-1,pre,in);
		root.rightNode=helper(start_pre+index-start_in+1,end_pre,index+1,end_in,pre,in);
		return root;			
	}
	
	// PostOrder+InOrder => Construct a binary tree
	
	// PostOrder 用stack和HashSet来实现
	
	// public static boolean add (int value,TreeNode root){}      // BST的add操作
	
	// public static boolean remove(int value, TreeNode root)     // BST的remove操作
	
	// Implement Trie tree 
}	


