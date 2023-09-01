import java.util.Arrays;

public class DataSet {
	
	private final int INITIAL_MAX_VALUE;

	private int[] data;
	private int numValues;
	
	public DataSet() {
		INITIAL_MAX_VALUE = 10;
		data = new int[INITIAL_MAX_VALUE];
	}
	
	//public DataSet(int max) {
	//  this.INITIAL_MAX_VALUE = max;
	//	data = new int[max];
	//}
	
	public void readData(String filename) {
		ArrayReader reader = new ArrayReader(filename);
		numValues = reader.fillArray(this);
	}
	
	public String toString() {
		String out="";
		for (int d=0; d<numValues; d++) {
			out += data[d] + ",";
		}
		out += "\naverage="+ this.findAverage();
		for(int d: findModes()) {
			out += "\nmode="+ d;
		}
		out += "\nstandard deviation=" + this.findStandardDeviation();
		return out;
	}
	
	public double findAverage() {
		double sum = 0;
		for(int i=0; i<numValues; i++) {
			sum += data[i];
		}
		return sum/numValues;
	}
	
	public int[] findModes() {
		int [] modes = new int [numValues];
		int max = Integer.MIN_VALUE;
		int modeCount = 0;
		for(int i=0; i<numValues; i++) {
			int count = 0;
			for(int j=0; j<numValues; j++) {
				if(data[i]==data[j] && i != j) {
					count++;
				}
			}
			if(count > max) {
				max = count;
				modes[i] = max;
				modeCount = 1;
			}
			else if (count == max) {
				boolean duplicate = false;
				for (int j=0; j<i; j++) {
				   if (data[j] == data[i] && modes[j] == max)
					   duplicate = true;
		
				}
				if (!duplicate) {
					modes[i] = max;
					modeCount++;
				}
			}
		}
		
		int [] result = new int [modeCount];
		int curIndex = 0;
		for(int i=0; i<numValues; i++) {
			if(modes[i]==max) {
				result[curIndex++] = data[i];
			}
		}
		return result;
	}
	

	public double findStandardDeviation() {
		double sum = 0;
		for(int i=0; i<numValues; i++) {
			double diff = data[i] - findAverage();
			sum += diff*diff;
		}
		return Math.sqrt(sum/(numValues-1));
		
	}
	
	public void insert(int val, int i){
		if(numValues == data.length) {
			this.resize();
		}
		for (int j = numValues-1; j>=i; j--){
			data[j+1] = data[j];
		}
		data [i] = val;
		numValues++;
	}
	
	public void removeVal(int val) {
		int count = 0;
		for (int j=numValues-1; j>=0; j--) {
			if(data[j]==val) {
				for(int i=j;i<numValues;i++) {
					data[i]=data[i+1];
				}
				count++;
			}
		}
		numValues-=count;
	}
	
	private void resize() {
		int [] data2 = new int[data.length*2];
		for (int i=0; i<data.length; i++) {
			data2[i] = data[i];
		}
		this.data = data2;
	}
	
	public void add(int x) {
		if(numValues == data.length) {
			this.resize();
		}
		data[numValues] = x;
		numValues++;
	}
	
	public int get(int i) {
		return data[i];
	}
	
	public void set(int i, int val) {
		data[i] = val;
	}
	
	
}
