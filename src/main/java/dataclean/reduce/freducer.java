package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class freducer extends Reducer<Text,Text, Text, Text>{

	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Iterator<Text> it = arg1.iterator();

		arg2.write(arg0,it.next());
		
	}
}
