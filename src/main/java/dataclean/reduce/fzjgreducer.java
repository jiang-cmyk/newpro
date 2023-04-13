package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class fzjgreducer extends Reducer<Text, Text, Text, LongWritable>{

	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Iterator<Text> it = arg1.iterator();
		long X7 = 0;
		for ( ; it.hasNext() ; ++X7 ) it.next();
		arg2.write(arg0, new LongWritable(X7));
	}
}
