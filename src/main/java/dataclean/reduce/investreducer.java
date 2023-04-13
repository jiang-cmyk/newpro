package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class investreducer extends Reducer<Text, FloatWritable, Text, Text>{

	@Override
	protected void reduce(Text arg0, Iterable<FloatWritable> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//		Iterator<Text> it = arg1.iterator();
		int X10 = 0;
		int X11 =0;
		for ( FloatWritable t:arg1 ) {
			X10++;
			X11+=t.get();
		}
		arg2.write(arg0, new Text(X10+","+X11));
	}
}
