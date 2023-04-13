package TEST;

import java.util.ArrayList;
import java.util.List;

public class test6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int[] array = {15,96,85,88,18,58,68,16,6,99,88,11,8,36,82,44,55,66};
//		List<Integer> li=new ArrayList<Integer>();
//		li.add(15);
//		li.add(96);
//		li.add(85);
//		li.add(88);
//		li.add(18);
//		li.add(58);
//		li.add(68);
//		li.add(16);
//		li.add(6);
//		li.add(99);
//		li.add(88);
//		li.add(11);
//		li.add(8);
//		li.add(36);
//		li.add(82);
//		li.add(44);
//		li.add(55);
//		li.add(66);
//		int sum = 0;      
//		for(int i:li){
//		    sum += i;      //求出数组的总和
//		}
//		System.out.println(sum);  //939
//		double average = (sum+0.0)/li.size();  //求出数组的平均数
//		System.out.println(average);   //52.0
//		int total=0;
//		for(int i:li){
//		    total += (i-average)*(i-average);   //求出方差，如果要计算方差的话这一步就可以了
//		}
//		double standardDeviation = Math.sqrt(total/li.size());   //求出标准差
//		System.out.println(standardDeviation);    //32.55764119219941
//
		int[] array = {1,2,3,4,5,6,7,9};
		int sum = 0;      
		for(int i=0;i<array.length;i++){
		    sum += array[i];      //求出数组的总和
		}
		double average = (sum+0.0)/array.length;  //求出数组的平均数
		System.out.println(average);   //52.0
		double  total=0;
		for(int i=0;i<array.length;i++){
		    total += (array[i]-average)*(array[i]-average);   //求出方差，如果要计算方差的话这一步就可以了
		}
		double standardDeviation = Math.sqrt(total/(array.length-1));   //求出标准差
		System.out.println(standardDeviation);    //32.55764119219941

	}

}
