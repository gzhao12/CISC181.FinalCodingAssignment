package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			try {
				double rate = 0;
				double payment = 0;
				double roundPMT = 0;
				rate = RateBLL.getRate(lq.getiCreditScore())/1200;
				lq.setdRate(rate);
				
				payment = -1*RateBLL.getPayment(rate,  lq.getiTerm(), lq.getdAmount() - lq.getiDownPayment(), 0, false);
				roundPMT = (double)Math.round(payment*100)/100;
				lq.setdPayment(roundPMT);
				sendToAll(lq);
			}
			catch (RateException e) {
				e.printStackTrace();
				sendToAll(lq);
			}
		}
	}
}
