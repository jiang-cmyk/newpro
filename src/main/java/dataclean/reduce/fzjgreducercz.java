package main.java.dataclean.reduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class fzjgreducercz extends Reducer<Text, Text, Text, Text>{

	private String filed[];
	private String nbxh;
	private String X7;
	private Boolean f1;
	private Boolean f2;
	private Text t1 =new Text();
	private Text t2 =new Text();
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1, Context arg2) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		f1 = true;
		f2 = true;
//		i=0;
		for ( Text t:arg1 ) {
//			i++;
			filed =t.toString().split("\t");
			String name =filed[0];
			
			if(name.equals("~!ztdj")) {
				f1 = false;	
				nbxh = filed[1];
			}else if(name.equals("~!fzjg")) {
				f2 = false;
				X7 = filed[1];
			}
		}
//		System.out.println(i);
		if(f1||f2) {
			return;
		}
		for(String xh:nbxh.split(",")) {
			t1.set(xh);
			t2.set(X7);
			arg2.write(t1, t2);
		}
	}
}
