package main.java.dataclean.mapper;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class fzjgmappercz extends Mapper<LongWritable, Text, Text, Text>{


	private FileSplit fs;
	private Text arg0 =new Text();
	private Text arg1 =new Text();
	private String filed[];
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		fs = (FileSplit) context.getInputSplit();
		String fn[] = fs.getPath().toString().split("/");
		String fna = fn[fn.length-2];

		filed = value.toString().split("\t");
		if(filed.length<2) {
			return;
		}
		arg0.set(filed[0]);
//		System.out.println(filed[0]);
		if(fna.equals("分支机构tep")) {
			arg1.set("~!fzjg\t"+filed[1]);
		}else if(fna.equals("主体登记tep")) {
			arg1.set("~!ztdj\t"+filed[1]);
		}
		context.write(arg0, arg1);
	}
}
