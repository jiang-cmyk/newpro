package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class integrationreducer extends Reducer<Text, Text, Text, Text>{
//	 private String nbxh;
//	 private String jjhklb ;
//	 private String hydm ;
//	 private String jyzt  ;
//	 private String qylx  ;
//	 private String time_scale ;
//	 private String city  ;
//	 private String three ;
//	 private String scale ;
//	 private String X1_2 ;
//	 private String X3   ;
//	 private String X4 ;
	 private String ztdj ;
	 private String X5 ;
	 private String X6 ;
	 private String X7  ;
	 private String X8  ; 
	 private String X9  ;
	 private String X10  ;
	 private String X11 ;
	 private String X12 ;
	 private String X13 ;
	 private String X14  ;
	 private String X15 ; 
	 private String X16 ; 
	 private String X17 ; 
	 private int X18 ;
	 
	 private String filed[];

	Random r = new Random(new Date().getTime());
	 private Text text= new Text();
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		Iterator<Text> it = arg1.iterator();

		if(arg0.toString().equals("0000000")) {
			text.set("jjhklb,hydm,jyzt,qylx,time_scale,city,three,scale,X1_2,X3,"
					+ "X4,X5,X6,X7,X8,X9,X10,X11,X12,X13,X14,X15,X16,X17,X18");
			arg2.write(new Text("nbxh"), text);
		}
		
		Boolean f =true;
		X5="0";
		X6="0";
		X7="0";
		X8="0";
		X9="0";
		X10="0";
		X11="0";
		X12="0";
		X13="0";
		X14="0";
		X15="0";
		X16="0";
		X17="0";
		for ( Text t:arg1 ) {
			filed =t.toString().split(",");
			String name =filed[0];
			
			if(name.equals("~!ztdj")) {
				f =false;
				ztdj = t.toString().substring(7);
			}else if(name.equals("~!bgqk")) {
				X5 = filed[1];
				X6 = filed[2];
				X12 = filed[3];
				X13 = filed[4];
				X15 =filed[5];

			}else if(name.equals("~!fzjg")) {

				X7 = filed[1];

			}else if(name.equals("~!qygxr")) {

				X10 = filed[1];
				X11 = filed[2];

			}else if(name.equals("~!qyxx")) {

				X14 = filed[1];

			}
		}
		if(f) {
			return;
		}
		X18 = r.nextInt(10);
		text.set(ztdj+","+X5+","+X6+","+X7+","+X8+","+X9+","+X10
				+","+X11+","+X12+","+X13+","+X14+","+X15
				+","+X16+","+X17+","+X18);
		arg2.write(arg0, text);
	}
}
