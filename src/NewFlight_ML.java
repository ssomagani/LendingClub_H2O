import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;

import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;

public class NewFlight_ML extends VoltProcedure {

	private static final SQLStmt INSERT = new SQLStmt(
			"insert into FLIGHTS values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
	public long run(
			String year,
			String month,
			String dayOfMonth,
			String dayOfWeek,
			int depTime,
			double crsDepTime,
			int arrTime,
			int crsArrTime,
			String uniqueCarrier,
			String flightNum,
			String tailNum,
			int actualElapsedTime,
			String crsElapsedTime,
			String airTime,
			int arrDelay,
			int depDelay,
			String origin,
			String dest,
			double distance,
			String taxiIn,
			String taxiOut,
			String cancelled,
			String cancellationCode,
			String diverted,
			String carrierDelay,
			String weatherDelay,
			String nasDelay,
			String securityDelay,
			String lateAircraftDelay,
			String isArrDelayed,
			String isDepDelayed
			) {
		hex.genmodel.GenModel rawModel;
		try {
			rawModel = (hex.genmodel.GenModel) Class.forName("GLMLinearModel").newInstance();
			EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);
			
			RowData row = new RowData();
			row.put("Year", year);
			row.put("Month", month);
			row.put("DayofMonth", dayOfMonth);
			row.put("DayOfWeek", dayOfWeek);
			row.put("depTime", depTime);
			row.put("CRSDepTime", crsDepTime);
			row.put("ArrTime", arrTime);
			row.put("UniqueCarrier", uniqueCarrier);
			row.put("Origin", uniqueCarrier);
			row.put("Dest", flightNum);
			row.put("TailNum", tailNum);
			row.put("ActualElapsedTime", actualElapsedTime);
			row.put("CRSElapsedTime", crsElapsedTime);
			row.put("AirTime", airTime);
			row.put("ArrDelay", arrDelay);
			row.put("DepDelay", depDelay);
			row.put("Origin", origin);
			row.put("Dest", dest);
			row.put("Distance", distance);
			row.put("TaxiIn", taxiIn);
			row.put("TaxiOut", taxiOut);
			row.put("Cancelled", cancelled);
			row.put("CancellationCode", cancellationCode);
			row.put("Diverted", diverted);
			row.put("CarrierDelay", carrierDelay);
			row.put("WeatherDelay", weatherDelay);
			row.put("NASDelay", nasDelay);
			row.put("SecurityDelay", securityDelay);
			row.put("LateAircraftDelay", lateAircraftDelay);
			row.put("IsArrDelayed", isArrDelayed);
			row.put("IsDepDelayed", isDepDelayed);

			BinomialModelPrediction p = null;
			try {
				p = model.predictBinomial(row);
			} catch (PredictException e) {
				e.printStackTrace();
			}

			System.out.println("Label (aka prediction) is flight departure delayed: " + p.label);
			System.out.print("Class probabilities: ");
			for (int i = 0; i < p.classProbabilities.length; i++) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print(p.classProbabilities[i]);
			}
			System.out.println("");
			System.out.println("#####################");
			
			voltQueueSQL(INSERT, year, month, dayOfMonth, dayOfWeek, depTime, crsDepTime, arrTime, 
					crsArrTime, uniqueCarrier, flightNum, tailNum, actualElapsedTime, crsElapsedTime, 
					airTime, arrDelay, depDelay, origin, dest, distance, taxiIn, taxiOut, cancelled, 
					cancellationCode, diverted, carrierDelay, weatherDelay, nasDelay, securityDelay, 
					lateAircraftDelay, p.label, isDepDelayed);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
