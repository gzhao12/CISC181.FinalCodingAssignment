package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	RateDomainModel rateException;
	
	public RateException(RateDomainModel rate) {
		this.rateException = rate;
	}
	
	public RateDomainModel getRateException() {
		return rateException;
	}
}
