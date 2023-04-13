package main.java.dataclean.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;

public class changemapper extends Mapper<LongWritable, Text,Text, Text>{


	
	 private String filed[];
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//过滤首行
		if(key.toString().equals("0")) {
			return;
		}
		
		  CSVParser csvParser = new CSVParser();
		  filed= csvParser.parseLine(value.toString());
		context.write(new Text(filed[0]), new Text(filed[1]));
	}
}
