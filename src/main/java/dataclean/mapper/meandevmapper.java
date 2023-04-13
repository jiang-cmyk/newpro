package main.java.dataclean.mapper;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;


public class meandevmapper extends Mapper<LongWritable, Text, Text, Text>{


	private Text text1 =new Text();
	private Text text2 =new Text();
	private String filed[];

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//过滤首行
				if(key.toString().equals("0")) {
					text1.set("000000#");
					context.write(text1,text1);
					return;
				}

		filed = value.toString().split(",");
	
		
		text1.set(filed[2]);
		text2.set(filed[12]+","+filed[13]+","+filed[14]+","+filed[17]+","+filed[18]
				+","+filed[19]+","+filed[20]+","+filed[21]+","+filed[25]);

		context.write(text1, text2);
	}
}
