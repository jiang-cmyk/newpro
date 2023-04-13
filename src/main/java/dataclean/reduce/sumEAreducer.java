package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class sumEAreducer extends Reducer<Text,Text, Text, Text>{

	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {

		for (Text t:arg1) {
			arg2.write(t, new Text());
			
		}
		
	}
}
