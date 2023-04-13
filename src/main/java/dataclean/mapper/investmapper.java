package main.java.dataclean.mapper;


import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;


public class investmapper extends Mapper<LongWritable, Text, Text,FloatWritable>{


	private Text text =new Text();
	private String filed[];
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//过滤首行
		if(key.toString().equals("0")) {
			return;
		}
		
		String line=new String(value.getBytes(),0,value.getLength(),"gb18030");
//		System.out.println(line.toString());
//		  CSVParser csvParser = new CSVParser();
//		  filed= csvParser.parseLine(line);
		filed =line.split(",");
		if(filed[0].equals("")||filed[1].equals("")) {
			return;
		}

		text.set(filed[0]);
//		System.out.println("f1"+filed[1]);
		context.write(text, new FloatWritable(Float.valueOf(filed[1])));
	}
}
