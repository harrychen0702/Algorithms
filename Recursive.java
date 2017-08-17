import java.util.List;

public class Recursive {
	
	// 0-1背包3，给定target s， int[] array, 找出能实现的最大重量，且这个重量小于等于s
	public static int knapsack(int[] weights,int index, int target) {
		if(target==0 || index>=weights.length) {
			return 0;
		}
		if(weights[index]>target) {
			return knapsack(weights,index+1,target);
		}
		else {
			return Math.max(knapsack(weights,index+1,target), knapsack(weights,index+1,target-weights[index])+weights[index]);
		}
	}
	
	
	//Lucky numbers，真题
	public List<String> LuckyNumbers (String num,int target){
		
	}
	
	
	public static void main(String[] args) {
		int[] array= {1,3,7};
		int result=knapsack(array,0,5);
		System.out.println(result);
	}

}
