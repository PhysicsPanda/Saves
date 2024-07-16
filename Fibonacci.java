import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

public class Fibonacci {
	
	public static final long[] fibonacci = new long[93];
	
	public static long getFibonacci(int n) {
		if(n==1 || n==2)
			return 1;
		
		if(fibonacci[n-1] == 0)
			fibonacci[n-1] = getFibonacci(n-1) + getFibonacci(n-2);
		
		return fibonacci[n-1];
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		DecimalFormat df = new DecimalFormat("###,###");
		
		boolean DecimalFormat = false;
		boolean pass = false;
		String str;
		while(!pass) {
			bw.write("Decimal Format (Y) : 10,000, (N) : 10000\n");
			bw.write("Decimal Format (Y/N) : ");
			bw.flush();
			
			str = br.readLine();
			if(str.equals("Y")) {
				pass = true;
				DecimalFormat = true;
			}else if(str.equals("N")) {
				pass = true;
				DecimalFormat = false;
			}else {
				bw.write("\n\n\n\n\n");
				bw.write("Invaild Input!\n");
			}
		}
		
		if(DecimalFormat) {
			for(int i=0;i<93;i++)
				fibonacci[i] = 0;
			
			for(int i = 1;i<93;i++)
				bw.write(i+":\t" + df.format(getFibonacci(i)) + "\n");
			bw.write("93:\tOverflow Error");
			
			bw.flush();
			bw.close();
			return;
		}
		
		for(int i=0;i<93;i++)
			fibonacci[i] = 0;
		
		for(int i = 1;i<93;i++)
			bw.write(i+":\t" + getFibonacci(i) + "\n");
		bw.write("93:\tOverflow Error");
		
		bw.flush();
		bw.close();
	}

}
