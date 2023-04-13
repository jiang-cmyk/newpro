package TEST;

import org.apache.hadoop.io.Text;
import java.util.regex.Pattern;
import java.io.File;
import java.util.regex.Matcher;
public class testy3 {

public static void main(String[] args) {
//	String a[]= {"wsfd","ddf","sd","asd"};
//	String m= "sdfsfs";
////	Iterator it =m.it
//	 String str="03";
//         Pattern pattern = Pattern.compile("[0-9]*");
//         Matcher isNum = pattern.matcher(str);
//         if( !isNum.matches() ){
//             System.out.println(false);
//         }else {
//        	 System.out.println(true);
//         }
//  }
	String path="";
	System.out.println(new File(args[0]).listFiles()[0].getAbsolutePath());
}

}
