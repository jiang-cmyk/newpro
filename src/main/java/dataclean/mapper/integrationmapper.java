package main.java.dataclean.mapper;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class integrationmapper extends Mapper<LongWritable, Text, Text, Text>{



	FileSplit fs =null;
	Text arg0 =new Text();
	Text arg1 =new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		if(key.toString().equals("0")) {
			arg0.set("0000000");
			arg1.set("");
			context.write(arg0, arg1);
		}
		
		fs = (FileSplit) context.getInputSplit();
		String fn[] = fs.getPath().toString().split("/");
		String fna = fn[fn.length-2];
//		System.out.println(fna);
		String nva[]=value.toString().split("\t");
		if(nva.length<1) {
			System.out.println(fna+"\t"+value.toString());
		}
		String nbxh =nva[0];
		String val =nva[1];
		if(fna.equals("主体登记")) {
			arg1.set("~!ztdj,"+val);
		}else if(fna.equals("变更情况")) {
			arg1.set("~!bgqk,"+val);
		}else if(fna.equals("分支机构")) {
			arg1.set("~!fzjg,"+val);
		}else if(fna.equals("企业关系人")) {
			arg1.set("~!qygxr,"+val);
		}else if(fna.equals("迁移信息")) {
			arg1.set("~!qyxx,"+val);
		}
		arg0.set(nbxh);
		context.write(arg0, arg1);
	}
}
