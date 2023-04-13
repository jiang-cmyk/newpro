package main.java.dataclean.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class freducercz extends Reducer<Text,Text, Text, Text>{

	Text text =new Text();
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {

		Iterator<Text> it=arg1.iterator();
		String con=it.next().toString();
		while(it.hasNext()) {
			con+=","+it.next().toString();
		}	
		text.set(con);
		arg2.write(arg0, text);
	}
}
