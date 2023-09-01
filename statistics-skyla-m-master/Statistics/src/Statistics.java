
public class Statistics {

	public static void main(String[] args) {
		// Create a DataSet object
		DataSet data = new DataSet();
		// Call the readData method to get data from the file
		//data.readData("data/numbers.txt");
		//data.readData("data/numbers2.txt");
		data.readData("data/numbers3.txt");
		//data.readData("data/numbers4.txt");
		//data.readData("data/compact.txt");
		data.removeVal(0);
		// Optionally, print the DataSet object to validate that data was read correctly
		System.out.println(data);
		// Compute statistics and print the results

	}

}
