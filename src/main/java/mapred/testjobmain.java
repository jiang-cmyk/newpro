package main.java.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.apache.log4j.Logger;

import nt.initLogRecord;

public class testjobmain extends Configured implements Tool{

	
	public static void main(String[] args) throws Exception {
		//启动job任务
		//initLogRecord initLogRecord = new initLogRecord();
		nt.initLogRecord.initLog();
		Configuration conf = new Configuration();
		int run = ToolRunner.run(conf, new testjobmain(), args);
		System.exit(run);
	}
	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//创建job任务对象
		Job job = Job.getInstance(super.getConf(), "wordcount");
		job.setJarByClass(testjobmain.class);
		//配置job任务对象
		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(job, new Path("file:///C:\\Users\\ABC\\Desktop\\words\\qqq.txt"));
		job.setMapperClass(testmapper.class);
		//设置K2，V2类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		
		//
		
		
		//reduce的处理方式
		job.setReducerClass(testreducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		//设置输出类型
		job.setOutputFormatClass(TextOutputFormat.class);
		
		TextOutputFormat.setOutputPath(job, new Path("file:///C:\\Users\\ABC\\Desktop\\Output"));
		
		boolean waitForCompletion = job.waitForCompletion(true);
		
		return waitForCompletion?0:1;
	}
	///newpro/src/main/java/mapred/testjobmain.java

}
