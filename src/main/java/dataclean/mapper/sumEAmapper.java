package main.java.dataclean.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;

public class sumEAmapper extends Mapper<LongWritable, Text,Text, Text>{


	
	 private String filed[];
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		if(key.toString().equals("0")) {
			return;
		}
		  
		  filed= value.toString().split(",");
		 Text t= new Text(filed[6]);
		context.write(t, t);
	}
}
