package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class meandevreducer extends Reducer<Text, Text, Text, Text>{

	
	private int X5;
	private int X6;
	private int X7;
	private int X10;
	private int X11;
	private int X12;
	private int X13;
	private int X14;
	private int X18;
	private String[]filed;
	List<Integer> mdX5=new ArrayList<Integer>();
	List<Integer> mdX6=new ArrayList<Integer>();
	List<Integer> mdX7=new ArrayList<Integer>();
	List<Integer> mdX10=new ArrayList<Integer>();
	List<Integer> mdX11=new ArrayList<Integer>();
	List<Integer> mdX12=new ArrayList<Integer>();
	List<Integer> mdX13=new ArrayList<Integer>();
	List<Integer> mdX14=new ArrayList<Integer>();
	List<Integer> mdX18=new ArrayList<Integer>();
	Text t2=new Text();
	
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		if(arg0.toString().equals("000000#")) {
			t2.set("X5_mean"+","+"X6_mean"+","+"X7_mean"+","+"X10_mean"+","+"X11_mean"+","+"X12_mean"
					+","+"X13_mean"	+","+"X14_mean"+","+"X18_mean"+
					","+"X5_std"+","+"X6_std"+","+"X7_std"+","+"X10_std"+","+"X11_std"+","+"X12_std"
					+","+"X13_std"	+","+"X14_std"+","+"X18_std");
			arg2.write(arg0, t2);
			return;
		}
		mdX5.clear();
		mdX6.clear();
		mdX7.clear();
		mdX10.clear();
		mdX11.clear();
		mdX12.clear();
		mdX13.clear();
		mdX14.clear();
		mdX18.clear();
		for(Text t :arg1) {
			filed = t.toString().split(",");
			X5 = Integer.valueOf(filed[0]);
			X6 = Integer.valueOf(filed[1]);
			X7 = Integer.valueOf(filed[2]);
			X10 = Integer.valueOf(filed[3]);
			X11 = Integer.valueOf(filed[4]);
			X12 = Integer.valueOf(filed[5]);
			X13 = Integer.valueOf(filed[6]);
			X14 = Integer.valueOf(filed[7]);
			X18 = Integer.valueOf(filed[8]);
			if(X5>0) {
				mdX5.add(X5);
			}
			if(X6>0) {
				mdX6.add(X6);
			}
			if(X7>0) {
				mdX7.add(X7);
			}
			if(X10>0) {
				mdX10.add(X10);
			}
			if(X11>0) {
				mdX11.add(X11);
			}
			if(X12>0) {
				mdX12.add(X12);
			}
			if(X13>0) {
				mdX13.add(X13);
			}
			if(X14>0) {
				mdX14.add(X14);
			}
			if(X18>0) {
				mdX18.add(X18);
			}
		}
		double meanX5 = mean(mdX5);
		double meanX6 = mean(mdX6);
		double meanX7 = mean(mdX7);
		double meanX10 = mean(mdX10);
		double meanX11 = mean(mdX11);
		double meanX12 = mean(mdX12);
		double meanX13 = mean(mdX13);
		double meanX14 = mean(mdX14);
		double meanX18 = mean(mdX18);
		

		double devX5 = dev(mdX5,meanX5);
		double devX6 = dev(mdX6,meanX6);
		double devX7 = dev(mdX7,meanX7);
		double devX10 = dev(mdX10,meanX10);
		double devX11 = dev(mdX11,meanX11);
		double devX12 = dev(mdX12,meanX12);
		double devX13 = dev(mdX13,meanX13);
		double devX14 = dev(mdX14,meanX14);
		double devX18 = dev(mdX18,meanX18);
		t2.set(meanX5+","+meanX6+","+meanX7+","+meanX10+","+meanX11+","+meanX12+","+meanX13+
				","+meanX14+","+meanX18+","+devX5+","+devX6+","+devX7+","+devX10+","+devX11+","+
				devX12+","+devX13+","+devX14+","+devX18);
		
		arg2.write(arg0, t2);
	}
	
	
	public static double mean(List<Integer> li) {
		if(li.size()==0) {
			return 0;
		}
		int sum = 0;
		for(int i:li){
			sum += i;      //求出数组的总和
		}
		double average = (sum+0.0)/li.size();  //求出数组的平均数
		return average;   //52.0
	}
	
	public static double dev(List<Integer> li,double average) {
		
		if(li.size()==0) {
			return 0;
		}

		double total=0;
		for(int i:li){
			total += (i-average)*(i-average);   //求出方差，如果要计算方差的话这一步就可以了
		}
		 //求出标准差
		return Math.sqrt(total/(li.size()-1));    //32.55764119219941
	}
	
}
