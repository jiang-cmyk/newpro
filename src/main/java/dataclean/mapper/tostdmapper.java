package main.java.dataclean.mapper;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;


public class tostdmapper extends Mapper<LongWritable, Text, Text, Text>{


	private Text text1 =new Text();
	private Text text2 =new Text();
	private String filed[];

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//过滤首行
				if(key.toString().equals("0")) {
					text1.set("000000#");
					context.write(text1, text1);
					return;
				}

		filed = value.toString().split(",");
	
		
//		nbxh,jjhklb,hydm,jyzt,qylx,time_scale,city,three,scale,
//		X1_2,X3,X4,X5,X6,X7,X8,X9,X10,X11,X12,X13,X14,X15,X16,X17,X18
		text1.set(filed[2]);
		text2.set(filed[0]+","+filed[1]+","+filed[3]+","+filed[5]+","+filed[6]+","+filed[7]
				+","+filed[8]+","+filed[11]+","+filed[12]+","+filed[13]+","+filed[14]+","+filed[17]
				+","+filed[18]+","+filed[19]+","+filed[20]+","+filed[21]+","+filed[25]);

		
//	text:	nbxh,jjhklb,jyzt,time_scale,city,three,scale,
//		,X4,X5,X6,X7,X10,X11,X12,X13,X14,X18

		context.write(text1, text2);
	}
}
