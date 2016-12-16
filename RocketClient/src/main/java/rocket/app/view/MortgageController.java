package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;

	@FXML
	private TextField txtHouseCost;

	@FXML
	private ComboBox cboxLoanTerm;

	@FXML
	private Label lblIncome;

	@FXML
	private Label lblExpenses;

	@FXML
	private Label lblCreditScore;

	@FXML
	private Label lblHouseCost;

	@FXML
	private Label lblPayment0;

	@FXML
	private Label lblPayment;

	@FXML
	private Label lblLoanTerm;

	@FXML
	private Button calcButton;

	@FXML
	private Label lblErrors0;

	@FXML
	private Label lblErrors;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() {
		cboxLoanTerm.getItems().removeAll(cboxLoanTerm.getItems());
		cboxLoanTerm.getItems().addAll("15", "30");
		cboxLoanTerm.getSelectionModel().select("15");
	}

	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();

		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		String output = cboxLoanTerm.getSelectionModel().getSelectedItem().toString();
		lq.setiTerm(Integer.parseInt(output) * 12);

		a.setLoanRequest(lq);

		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		double PITI;
		double calc1;
		double calc2;
		double roundPITI;

		calc1 = lRequest.getdIncome() * 0.28;
		calc2 = lRequest.getdIncome() * 0.36 - lRequest.getdExpenses();

		if (calc1 < calc2)
			PITI = calc1;
		else
			PITI = calc2;

		roundPITI = (double) Math.round(PITI * 100) / 100;

		if (roundPITI < lRequest.getdPayment())
			lRequest.setdPayment(roundPITI);

		lblPayment.setText(String.valueOf(lRequest.getdPayment()));

	}
}
