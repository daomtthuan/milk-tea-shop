package controller.customer;

import app.Controller;
import app.alert.AlertWarning;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import javafx.fxml.Initializable;
import model.Account;
import tool.Regex;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

class Login extends controller.general.Login implements Controller, Initializable {
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		getSubmitButton().setOnAction(actionEvent -> {
			if (getAccount().matches(Regex.ACCOUNT) && getPassword().matches(Regex.PASSWORD)) {
				Account account = api.Account.getInstance().login("Customer", getAccount(), getPassword());
				if (account != null) {
					SecondaryStage.getInstance().setAccount(account);
					if (!SecondaryStage.getInstance().isOrdering()) {
						SecondaryStage.getInstance().setBillDetails(new ArrayList<>());
						SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
					}
					SecondaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
			}
		});

		SecondaryStage.getInstance().getStage().hide();
	}
}