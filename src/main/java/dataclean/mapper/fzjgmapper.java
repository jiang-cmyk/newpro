package main.java.dataclean.mapper;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;


public class fzjgmapper extends Mapper<LongWritable, Text, Text, Text>{


	private Text text =new Text();
	private String filed[];
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//过滤首行
		if(key.toString().equals("0")) {
			return;
		}
		
		String line=new String(value.getBytes(),"GBK");
		filed = line.toString().split(",");

		if(filed.length<6) {
			return;
		}
		context.write(new Text(filed[5]), text);
	}
}
