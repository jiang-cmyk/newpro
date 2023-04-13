package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import main.java.adjustm;

import org.apache.hadoop.mapreduce.Mapper.Context;

public class tostdreducer extends Reducer<Text, Text, Text, Text>{

	
	private String[]filed;

	private HashMap<String,ArrayList<Double>> X_min ;
	private HashMap<String,ArrayList<Double>> X_max ;
	
	List<Double> Xi_min;
	List<Double> Xi_max;
	Text t1=new Text();
	Text t2=new Text();
	@Override
	 protected void setup(Context context) throws IOException {
		List<HashMap<String,ArrayList<Double>>> min_max=adjustm.getmin_max();
		X_min=min_max.get(0);
		X_max=min_max.get(1);
	}
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		if(arg0.toString().equals("000000#")) {
			t1.set("hydm");
			t2.set("nbxh,jjhklb,jyzt,time_scale,city,three,scale,x4,x5,x6,x7,x10,x11,x12,x13,x14,x18");
			arg2.write(t1, t2);
			return;
		}
		Xi_min = X_min.get(arg0.toString());
		Xi_max = X_max.get(arg0.toString());
		for(Text t :arg1) {
			filed=t.toString().split(",");
//			text:hydm,nbxh,jjhklb,jyzt,time_scale,city,three,scale,
//			,X4,X5,X6,X7,X10,X11,X12,X13,X14,X18
			String res=filed[0]+","+filed[1]+","+filed[2]+","+filed[3]+","+filed[4]
						+","+filed[5]+","+filed[6]+","+filed[7];
			for(int i = 8;i<filed.length;i++) {
				Double Xi=Double.valueOf(filed[i]);
				if(Xi>0) {
					Xi=Xi<Xi_min.get(i-8)?Xi_min.get(i-8):Xi;
					Xi=Xi>Xi_max.get(i-8)?Xi_max.get(i-8):Xi;
				}
				res=res+","+Xi;
			}
//			t1.set(filed[0]);
			t2.set(res);
			arg2.write(arg0, t2);
		}	
		Xi_min.clear();
		Xi_max.clear();
	}	
}
