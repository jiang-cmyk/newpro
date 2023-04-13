package main.java.dataclean.mapper;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class fmappercz extends Mapper<LongWritable, Text,Text, Text>{
	
	 private String filed[];
	 private Text arg0 = new Text();
	 private Text arg1 = new Text();
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		filed=value.toString().split("\t");
		if(filed.length!=3) {
			return;
		}
		arg0.set(filed[2]);
		arg1.set(filed[0]);
		context.write(arg0,arg1);
	}
}
