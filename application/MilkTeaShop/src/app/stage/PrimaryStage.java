package app.stage;

import model.Account;
import org.jetbrains.annotations.Contract;

public final class PrimaryStage extends Stage {
    private static PrimaryStage instance;
    private Account account;

    public static PrimaryStage getInstance() {
        if (instance == null) {
            setInstance(new PrimaryStage());
        }
        return instance;
    }

    private static void setInstance(PrimaryStage instance) {
        PrimaryStage.instance = instance;
    }

    @Contract(pure = true)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}