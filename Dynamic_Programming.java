import java.util.Arrays;

public class Dynamic_Programming {
	
	public static int Fibonacci(int n) {
		if(n<=1) return 1;
		int[]result=new int[n+1];
		result[0]=1;
		result[1]=1;
		for(int i=2;i<=n;i++) {
			result[i]=result[i-1]+result[i-2];
		}
		return result[n];
	}
	
	public static int unique_path(int row,int column) {
		int[][] result=new int[row][column];
		for(int i=0;i<column;i++) { //初始化第一行
			result[0][i]=1;
		}
		for(int j=1;j<row;j++) { //初始化第一列
			result[0][j]=1;
		}
		for(int k=1;k<row;k++) {
			for(int l=1;l<row;l++) {
				result[k][l]=result[k-1][l]+result[k][l-1];
			}
		}
		return result[row-1][column-1];
	}
	
	public static int minimum_path_sum(int[][] grid) {
		int row=grid.length;
		int column=grid[0].length;
		//一个点只能由他的上方或左方达到，所以取小的那个就好了
		int[][] result=new int[row][column];
		result[0][0]=grid[0][0];
		for(int i=1;i<column;i++) {
			result[0][i]=grid[0][i]+result[0][i-1];
		}
		for(int j=1;j<row;j++) {
			result[j][0]=grid[j][0]+result[j-1][0];
		}
		for(int k=1;k<row;k++) {
			for(int l=1;l<column;l++) {
				result[k][l]=Math.min(result[k-1][l], result[k][l-1]) + grid[k][l];
			}
		}
		return result[row-1][column-1];
	}
	
	//使用递归的方法
	public static int knapsack(int capacity, int[] weights, int[] values) {
		return helper(capacity,weights,values,0);
	}
	public static int helper(int capacity, int[] weights, int[] values, int index) {
		if( index>=weights.length || weights[index]>capacity) return 0;
		else return Math.max(values[index]+helper(capacity-weights[index],weights,values, index+1),helper(capacity,weights,values,index+1));
	}
	//使用DP的方法
	public static int knapsack2(int capacity, int[] weights, int[] values) {
		// w[i][j] for the previous total i items, the max value it can have for capacity j
		int length=weights.length;
		if(capacity==0 || length==0) return 0;
		int[][] w=new int[length+1][capacity+1]; // 要多留出一行给capacity为0或total items为0的情况
		//不需要初始化第一行和第一列应为默认就是0
		for(int i=1;i<=length;i++) { //遍历每一个物品
			int index=i-1;//因为在weights和values数组中没有0，
			for(int j=1;j<=capacity;j++) { //遍历每一种capacity
				if(j<weights[index]) w[i][j]=w[index][j]; //如果capacity小于上一个物品的重量的话，这一行这一列的物品添加不进去
				else if(w[index][j-weights[index]]+values[index]>w[i-1][j]) { //如果要把当前的这一行这一列的values添加进去的情况下
					w[i][j]=w[index][j-weights[index]]+values[index];
				}
				else {
					w[i][j]=w[i-1][j];//不添加这一行这一列的物品，直接继承上一行这一列的值
				}
			}
		}
		return w[length][capacity];
	}
	
	
	// Coin change
	public int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);
		int length=coins.length;
		int[][] dp=new int[length][amount+1]; //把amount为0考虑进去
		for(int j=0;j<=amount;j++) { // 初始化第一行
			if(j%coins[0]==0) dp[0][j]=j/coins[0];
			else dp[0][j]=-1;
		}
		for(int i=1;i<length;i++) { //开始遍历剩下的行
			for(int j=0;j<=amount;j++) {
				if(j<coins[i]) dp[i][j]=dp[i-1][j]; //当amount小于当前coin面额的时候，放不下
				else {
					int temp=Integer.MAX_VALUE;
					for(int k=0;k<=j/coins[i];k++) {
						int remain=j-k*coins[i];
						if(dp[i-1][remain]!=-1 && dp[i-1][remain]+k<temp) {
							temp=dp[i-1][remain] +k;
						}
					}
					dp[i][j]=temp<Integer.MAX_VALUE? temp:-1;
				}
			}
		}
		return dp[length-1][amount];
	}
	
	// longest increasing subsequence
	public static int longest_increasing_subsequence(int[] nums) {
		if(nums.length==0) return 0;
		int[] result=new int[nums.length];
		result[0]=1;
		for(int i=1;i<nums.length;i++) {
			int temp=1;
			for(int j=0;j<i;j++) {
				if(nums[j]<nums[i] && result[j]+1>temp) {
					temp=result[j]+1;
				}
			}
			result[i]=temp;
		}
		int max=result[0];
		for(int k=1;k<result.length;k++) {
			if(result[k]>max) max=result[k];
		}
		return max;		
	}
	
	public static int longest_common_sequence(String a, String b) {
		// {abcfbc}
		// {abfcab}
		
	}
	
	// Matrix multiplication google面试原题
	// Scramble string
	
	
	public static void main(String[] args) {
		int[] nums= {3,1,4,5,7,6,8,2,9};
		System.out.println(longest_increasing_subsequence(nums));
	}

}
