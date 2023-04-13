package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class mediareducer extends Reducer<LongWritable, Text, LongWritable, Text>{

	@Override
	protected void reduce(LongWritable arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		Iterator<Text> it = arg1.iterator();
		if(arg0.toString().equals("0")) {
			arg2.write(arg0, new Text("jjhklb,hydm,djjg,zczb,jyzt,clrq,hzrq"));
		}
		arg2.write(arg0, it.next());
	}
}
