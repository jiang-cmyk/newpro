package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class changereducer extends Reducer<Text,Text, Text, Text>{

	private int X5;
	private int X6;
	private int X12;
	private int X13;
	private int X15;
	private HashSet<String> filed =new HashSet<String>();
	private HashSet<String> filed12 =new HashSet<String>();
	public static String  bgsx_M=",120,170,700,134,118,155,117,127,136,154,914,131,923,";
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		Iterator<Text> it = arg1.iterator();
		
		X5=0;
		X6=0;
		X12=0;
		X13=0;
		X15=0;
		filed.clear();
		filed12.clear();
		for (Text t:arg1) {
			String con = t.toString();
			if(con.equals("165")) {
				X15++;
			}
			
			if(bgsx_M.contains(","+con+",")){
				filed12.add(con);
				X13++;
			}
			
			filed.add(con);
			X5++;
			
		}
		X6 = filed.size();
		X12 = filed12.size();
//		if(arg0.toString().equals("160000021823685113")){
//			System.out.println("********************************");
//			System.out.println("********************************");
//			System.out.println("********************************");
//			System.out.println("********************************");
//			System.out.println("********************************");
//			for(String s : filed12) {
//				System.out.println(s+"\t***");
//			}
//		}
		arg2.write(new Text(arg0.toString()), new Text(X5+","+X6+","+X12+","+X13+","+X15));
		
	}
}
