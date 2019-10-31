package controller.customer;

import api.AccountApi;
import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import app.string.Regex;
import app.string.Tool;
import controller.Controller;
import controller.Information;
import model.Account;

import java.net.URL;
import java.util.ResourceBundle;

public final class Register extends Information implements Controller {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle("REGISTER");
        SecondaryStage.getInstance().getStage().hide();
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });

        setDefaultButton("Submit");
        getDefaultButton().setDefaultButton(true);
        getDefaultButton().setOnAction(event -> {
            String name = Tool.fixString(getName());
            boolean gender = isMale();
            String birthday = getBirthday();
            String address = Tool.fixString(getAddress());
            String phone = getPhone();
            String email = getEmail().toLowerCase();

            if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL)) {
                Account account = AccountApi.getInstance().insert(Tool.createAcronym(name), 1, name, gender, birthday, address, phone, email);

                if (account != null) {
                    SecondaryStage.getInstance().setAccount(account);

                    if (!SecondaryStage.getInstance().isOrdering()) {
                        SecondaryStage.getInstance().setScene("/view/customer/Order.fxml");
                    }

                    AlertInformation.getInstance().showAndWait("Success!",
                            "Your Customer Account: " + account.getAccount() + "\nYour default Password: 1\nPlease change your Password at next Login times.\n" +
                                    "Thanks for coming to our shop!");
                    DialogSecondaryStage.getInstance().getStage().hide();
                    SecondaryStage.getInstance().getStage().show();
                } else {
                    AlertWarning.getInstance().showAndWait("Fail!", "Can not register.\nPlease notify staff.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
            }
        });
    }
}