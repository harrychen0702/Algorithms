import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BFS {
	public class TreeNode{  //定义树节点
		private TreeNode left;
		private TreeNode right;
		private int value;
	}
	
	//分层遍历：空指针法
	public static List<List<Integer>> levelOrder(TreeNode root){
		List<List<Integer>> results=new ArrayList<>();
		Queue<TreeNode> queue=new LinkedList<>();
		queue.offer(root);
		queue.offer(null);
		List<Integer> current=new ArrayList<>();
		while(!queue.isEmpty()) {
			TreeNode top=queue.poll();
			if(top==null) {
				results.add(current);
				if(!queue.isEmpty()) {
					queue.offer(null); // 重新放入一个null来区别当前层
				}
				current=new ArrayList<>();
			}
			else {
				if(top.left!=null) queue.offer(top.left);
				if(top.right!=null) queue.offer(top.right);
				current.add(top.value);
			}
		}
		return results;	
	}
	
	// Binary Tree Zigzag Level Order Traversal
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean is_odd=true;
        Stack<TreeNode> mystack=new Stack<>();
        mystack.add(root);
        List<List<Integer>> result=new ArrayList<>();
        if(root==null) return result;
        helper(result,is_odd,mystack);
        return result;        
    }
    
    public static void helper(List<List<Integer>> result, boolean is_odd, Stack<TreeNode> mystack){
        while(!mystack.isEmpty()){
            Stack<TreeNode> temp=new Stack<>();
            List<Integer> current1=new ArrayList<>();
            while(!mystack.isEmpty()){
                TreeNode current=mystack.pop();
                if(is_odd){ //先加左边
                    if(current.left!=null) temp.push(current.left);
                    if(current.right!=null) temp.push(current.right);
                }
                else if(!is_odd){
                    if(current.right!=null) temp.push(current.right);
                    if(current.left!=null) temp.push(current.left);
                }
                current1.add(current.value);
            }       
            mystack=temp;
            is_odd=!is_odd;
            result.add(current1);
        }
    }
    
	// Find Bottom Left Tree Value
    public int findBottomLeftValue(TreeNode root) {
        int current_left=root.value;
        Queue<TreeNode> myqueue=new LinkedList<>();
        myqueue.offer(root);
        while(!myqueue.isEmpty()){
            Queue<TreeNode> temp=new LinkedList<>();
            boolean is_left=true;
            while(!myqueue.isEmpty()){
                TreeNode current=myqueue.poll();
                if(is_left){
                    current_left=current.value;
                    is_left=false;
                }
                if(current.left!=null) temp.offer(current.left);
                if(current.right!=null) temp.offer(current.right);
            }
            myqueue=temp;
        }
        return current_left;
    }
    
	// Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int min=1;
        Queue<TreeNode> myqueue=new LinkedList<>();
        myqueue.offer(root);
        
        while(!myqueue.isEmpty()){
            Queue<TreeNode> temp=new LinkedList<>();
            while(!myqueue.isEmpty()){
                TreeNode current=myqueue.poll();
                if(current.left==null && current.right==null) return min;
                if(current.left!=null) temp.offer(current.left);
                if(current.right!=null) temp.offer(current.right);
            }
            min++;
            myqueue=temp;
        }
        return 0;
    }
    
	// The maze2
    public class Pair{
        int row;
        int column;
        public Pair(int row,int column){
            this.row=row;
            this.column=column;
        }
    }
    
  //这里的关键点在于，当一个点停下来的时候看一看走过了多少distance，如果比之前的还要长的话就没有继续走下去的必要了
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distance=new int[maze.length][maze[0].length];
        for(int i=0;i<maze.length;i++){
            for(int k=0;k<maze[0].length;k++){
                distance[i][k]=Integer.MAX_VALUE;
            }
        }
        distance[start[0]][start[1]]=0;
        Queue<Pair> myqueue=new LinkedList<>();
        myqueue.offer(new Pair(start[0],start[1]));
        int[] d_row={-1,1,0,0};//上下左右
        int[] d_column={0,0,-1,1};
        while(!myqueue.isEmpty()){
            Pair current=myqueue.poll();
            for(int j=0;j<4;j++){ //四个方向
                int new_row=current.row;
                int new_column=current.column;
                int dist=distance[new_row][new_column]; //取出当前点到起点的最短距离
                while(new_row+d_row[j]>=0 && new_row+d_row[j]<maze.length && new_column+d_column[j]>=0 && new_column+d_column[j]<maze[0].length && maze[new_row+d_row[j]][new_column+d_column[j]]!=1 ){
                    new_row=new_row+d_row[j];
                    new_column=new_column+d_column[j];
                    dist++;
                }
                if(dist<distance[new_row][new_column]) { //如果距离可以减小，意味着要更新
                    distance[new_row][new_column]=dist;
                    if(new_row!=destination[0] || new_column!=destination[1]){ //减支，到了终点就不要再出发遍历了
                        myqueue.offer(new Pair(new_row,new_column)); //注意，这个if放在外面的if里面是因为，只有当距离可以减小的时候，才让他在这个点重新出发一次，否则不用加到queue当中，应为没有意义。
                    }   
                }
                //如果距离没有减小，那么继续for循环
                       
            }
        }
        int result=distance[destination[0]][destination[1]];
        return result==Integer.MAX_VALUE ? -1:result;      
    }
    
    
    
    
	// The maze3
	
	
	//BFS和DFS都能解的题目
    
	// Surrounded region
    
	// Remove invalid parentheses

}
