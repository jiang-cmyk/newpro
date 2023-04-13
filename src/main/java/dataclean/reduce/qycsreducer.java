package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class qycsreducer extends Reducer<Text, NullWritable, Text, IntWritable>{


	int X14;
	@Override
	protected void reduce(Text arg0, Iterable<NullWritable> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		X14 = 0;
		for ( NullWritable t:arg1) 
			X14++;
	
		arg2.write(arg0, new IntWritable(X14));
	}
}
