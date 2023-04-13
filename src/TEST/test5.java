package TEST;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.csvreader.CsvWriter;

import joinery.DataFrame;

public class test5 {

	public static void main(String[] args) {
		String a = "2022-03-23";
		String b = a.substring(5, 7);
		System.out.println(Integer.valueOf(b));
	}
}
