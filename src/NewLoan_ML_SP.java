
import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;

public class NewLoan_ML_SP extends VoltProcedure {

	private static final SQLStmt INSERT = new SQLStmt("INSERT INTO NEW_LOANS VALUES("
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
			+ ")");
	
	Model model = new Model();

	public long run(int id, String[] args) {
		Object[] procArgs = new Object[151];
		procArgs[0] = id;
		System.arraycopy(args, 0, procArgs, 1, 150);
		voltQueueSQL(INSERT, procArgs);
		
		double prediction = model.makePrediction(procArgs);
		double int_rate = Double.parseDouble((String) procArgs[6]);
		
		voltExecuteSQL();
		return (new Double(prediction)).longValue();
	}
}
