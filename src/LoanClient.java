import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.voltdb.VoltTable;
import org.voltdb.VoltType;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.NullCallback;

public class LoanClient {

	private static Client client;
	private static final String EMPTY_STRING = "";

	public static void main(String[] args) throws IOException {

		ClientConfig config = new ClientConfig();
		config.setMaxTransactionsPerSecond(100);
		client = ClientFactory.createClient(config);

		String filename = args[0];
		File csvFile = new File(filename);
		
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		CSVParser parser = CSVParser.parse(reader, CSVFormat.RFC4180);
			
		parser.forEach((record) -> {
			insertRecord(record);
		});
		parser.close();
	}
	
	private static void insertRecord(CSVRecord record) {
		int id = Integer.parseInt(record.get(0));
		String[] procArgs = new String[150];
		for(int i=1; i<151; i++) {
			String value = record.get(i);
			if(i == 1 || i == 24 || i == 67 || i == 69 || i == 73 || i == 80 || 
					i == 81 || i == 84 || i == 89 || i == 103 || i == 108) {
				if(value.equals(EMPTY_STRING))
					procArgs[i-1] = "0";
			} else {
				procArgs[i-1] = value;
			}
		}
		callProc(id, procArgs);
	}
	
	private static void callProc(int id, String[] procArgs) {
		try {
			client.createConnection("localhost");
//			client.callProcedure(new NullCallback(), "NewLoan_ML_SP", id, procArgs);
			ClientResponse response = client.callProcedure("NewLoan_ML_SP", id, procArgs);
			
			VoltTable result = response.getResults()[0];
			if(result.advanceRow())
			System.out.println(result.get(0, VoltType.BIGINT));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
