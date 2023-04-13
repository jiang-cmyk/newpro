package main.java.mapred;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class testreducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	@Override
	protected void reduce(Text arg0, Iterable<LongWritable> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		long res = 0;
		for(LongWritable l :arg1) {
			res += l.get();
		}
		arg2.write(arg0, new LongWritable(res));
		
	}
}
