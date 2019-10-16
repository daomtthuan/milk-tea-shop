package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AccountRoll {
    private int id;
    private int idAccount;
    private int idRoll;

    @Contract(pure = true)
    public AccountRoll(int id, int idAccount, int idRoll) {
        this.id = id;
        this.idAccount = idAccount;
        this.idRoll = idRoll;
    }

    public int getId() {
        return id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public int getIdRoll() {
        return idRoll;
    }
}
