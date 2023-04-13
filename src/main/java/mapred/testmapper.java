package main.java.mapred;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.log4j.Logger;
/*
 * keyin:	K1
 * valuein: V1
 * 
 * 
 * keyout:   K2
 * valueout: V2
 * * 
 * */
public class testmapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Text text =new Text();
		LongWritable longw =new LongWritable(1);
		for(String s:value.toString().split(",")) {
			text.set(s);;
			context.write(text, longw);
		}
	}
}
