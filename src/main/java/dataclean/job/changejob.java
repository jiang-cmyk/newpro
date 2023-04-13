package main.java.dataclean.job;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import main.java.dataclean.mapper.changemapper;
import main.java.dataclean.reduce.changereducer;


public class changejob extends Configured implements Tool{

	
	public static int work(String[] args) throws Exception {
		//启动job任务
		Configuration conf = new Configuration();
//		conf.set("mapred.textoutputformat.separator", ",");
		return ToolRunner.run(conf, new changejob(), args);
//		System.exit(run);
	}
	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//创建job任务对象

        FileSystem fs = FileSystem.get(super.getConf());
		nt.initLogRecord.initLog();
		Job job = Job.getInstance(super.getConf(), "chclean");
		job.setJarByClass(changejob.class);
		//配置job任务对象
		FileInputFormat.addInputPath(job, new Path("file:///"+new File(args[0]).listFiles()[0].getAbsolutePath()));
		//TextInputFormat.addInputPath(job, new Path(args[0]));
				//("file:///C:\\Users\\ABC\\Desktop\\Input\\变更情况表\\exportsql.20220925135151.gbk.csv"));

	      
		job.setInputFormatClass(TextInputFormat.class);
		

		//设置K2，V2类型
		job.setMapperClass(changemapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
				
		//reduce的处理方式
		job.setReducerClass(changereducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//设置输出类型
		job.setOutputFormatClass(TextOutputFormat.class);
		Path outpath = new Path(args[1]);
		  if (fs.exists(outpath)) {
	            fs.delete(outpath, true);
	        }
	        
		FileOutputFormat.setOutputPath(job,outpath);
				//("file:///C:\\Users\\ABC\\Desktop\\Output\\变更情况"));

		
		boolean waitForCompletion = job.waitForCompletion(true);
		
		return waitForCompletion?0:1;
	}


}
