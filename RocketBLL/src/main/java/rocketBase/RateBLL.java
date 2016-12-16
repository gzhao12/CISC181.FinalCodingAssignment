package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double returnRate = 0;
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();

		for (int counter = 0; counter < rates.size(); counter ++) {
			if(GivenCreditScore >= rates.get(counter).getiMinCreditScore()) {
				returnRate = rates.get(counter).getdInterestRate();
			}
		}
		
		if (returnRate == 0) {
			throw new RateException(rates.get(0));
		}
		
		return returnRate;
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
