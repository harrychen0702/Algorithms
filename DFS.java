import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {
	
	public class TreeNode{  //定义树节点
		private TreeNode left;
		private TreeNode right;
		private int value;
	}
	
	
	public static int get_depth(TreeNode x) {
		if(x==null) {
			return 0;
		}
		else {
			return 1+Math.max(get_depth(x.left), get_depth(x.right));
		}
	}
	
   // 分析：不太好，因为有冗余计算
	public static boolean is_balanced_tree(TreeNode x) {
		if(x==null) {
			return true;
		}
		if(Math.abs((get_depth(x.right)-get_depth(x.left)))>1 || !is_balanced_tree(x.right) || !(is_balanced_tree(x.left))) {
			return false;
		}
		return true;	
	}
	
	//第二种方法
	public static boolean is_balanced_tree2(TreeNode x) {
		return depth(x)==-1? false:true;
	}
	//这里需要改写depth函数
	public static int depth(TreeNode x) {
		if(x==null) {
			return 0;
		}
		else if(depth(x.left)==-1 || depth(x.right)==-1 || Math.abs(depth(x.left)-depth(x.right))>1) {
			return -1;
		}
		else return Math.max(depth(x.right), depth(x.left)) +1;
	}
	
	//对称二叉树
	public static boolean symmetric_tree(TreeNode x) {
		if(x==null) {
			return true;
		}
		return is_sym(x.left,x.right);
		
	}
	public static boolean is_sym(TreeNode left, TreeNode right) {
		if(left==null && right!=null) return false;
		if(left!=null && right==null) return false;
		if(left.value!=right.value) return false;
		if(!is_sym(left.right,right.left) || !is_sym(left.left,right.right)) return false;
		return true;
		
	}
	
	
	public static int min_depth(TreeNode x) {
		if(x==null) return 0;
		int left=min_depth(x.left);
		int right=min_depth(x.right);
		if(x.left==null && x.right!=null) return 1+right;
		else if(x.left!=null && x.right==null) return 1+left;
		return Math.min(left, right)+1; //如果两个children都不为null
	}
	
	public static boolean path_sum(TreeNode x, int sum) {
		if(x==null) {
			return false;
		}
		if(x.left==null && x.right==null) {
			if(x.value==sum) return true;
			return false;
		}
		return path_sum(x.right,sum-x.value) || path_sum(x.left,sum-x.value);	
	}
	
	public static List<String> findAllConcatenatedWordsInADict(String[] words){
		Set<String> dict=new HashSet<>();
		List<String> result=new ArrayList<>();
		if(words.length<=1) return result; //只有一个或0个元素的时候无法拼接
		for(String word:words) {
			dict.add(word);
		}
		for(String word:words) {
			if(helper(dict,word,0)) {
				result.add(word);  //对words数组中的每一个成员都去判断他能不能被拼接
			}
		}
		return result;
	}
	
	public static boolean helper(Set<String> dict,String word, int start) {
		if(word.length()==0) return false; //空字符串无法被拼接
		if(start==word.length()) return true; //整个字符串都被扫描完成了
		boolean result=false; //默认是false；
		for(int i=word.length();i>start;i--) { //这里的i是子串的最后一位index＋1；倒过来i－－可以优化算法，避免time limit exceed
			String sub=word.substring(start, i);
			if(dict.contains(sub) && !sub.equals(word)) {
			result= result || helper(dict,word,i);  //这里使用or是因为 如果第一次尝试就通过了，第二次尝试没有通过不能造成影响，
			//只要有一种拼接方法能行就应该返回true
			}
		}
		return result;
	}
	
	// Surrounded region
	public void solve(char[][] board) {
        if(board.length==0 || board[0].length==0) return;
        for(int i=0;i<board.length;i++){
            if(board[i][0]=='O') search(i,0,board);  //左
            if(board[i][board[0].length-1]=='O') search(i,board[0].length-1,board); //右
        }
        for(int j=0;j<board[0].length;j++){
            if(board[0][j]=='O') search(0,j,board); //上
            if(board[board.length-1][j]=='O') search(board.length-1,j,board); //下
        }
        for(int x=0;x<board.length;x++){
            for(int y=0;y<board[0].length;y++){
                board[x][y]= board[x][y]=='F'? 'O':'X';
            }
        }
        
    }
    public static void search(int row,int column,char[][] board){ //这里用了DFS
        if(board[row][column]!='O') return; //这一步用来减支，避免死循环
        board[row][column]='F';
        int[] d_row={-1,1,0,0}; //上下左右
        int[] d_column={0,0,-1,1};
        for(int k=0;k<4;k++){
            int new_row=row;
            int new_column=column;
            if(new_row+d_row[k]>0 && new_row+d_row[k]<board.length-1 && new_column+d_column[k]>0 && new_column+d_column[k]<board[0].length-1){   //注意这里要用if不能用while！！！
                search(new_row+d_row[k],new_column+d_column[k],board);
            }
        }
    }
	
	// Binary tree max path sum
	// Remove invalid parentheses
	// Coin change
	// Remove boxes
    // Can I win 第七个视频的练习讲解,不懂
	
	
	public static void main(String[] args)
	{	
		String[] test= {"cat","cats","catsdogcats","dog","dogcatsdog","rat","ratcatdogcat"};
		System.out.println(findAllConcatenatedWordsInADict(test));

		
	}
	
	

}
