package controller.customer;

import access.AccessAccount;
import controller.Information;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import library.ErrorAlert;
import library.Tool;
import library.WarningAlert;
import main.DialogStage;
import main.SecondaryStage;
import model.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller register.
 */
public final class ControllerRegister extends Information implements Initializable {
    @Override
    protected void submit() {
        String name = Tool.fixString(getName());
        StringBuilder accountName = new StringBuilder();
        for (String s : name.split(" ")) {
            accountName.append(s.charAt(0));
        }
        Account account = AccessAccount.getInstance().insert(
                accountName.toString().toLowerCase(), name, getMale(), getBirthday(),
                Tool.fixString(getAddress()), getPhone(), getEmail(), 1);
        if (account != null) {
            SecondaryStage.getInstance().setAccount(account);
            DialogStage.getInstance().getStage().hide();
            try {
                // Set up view ControllerOrder for customer on secondary Stage
                FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewOrder.fxml"));
                SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                DialogStage.getInstance().getStage().hide();
                SecondaryStage.getInstance().getStage().show();
            } catch (IOException e) {
                ErrorAlert.getInstance().showAndWait(e);
            }
        } else {
            WarningAlert.getInstance().showAndWait(
                    "Register failed", "Can not insert Customer's Information");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle("REGISTER");
        SecondaryStage.getInstance().getStage().hide();
        DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
