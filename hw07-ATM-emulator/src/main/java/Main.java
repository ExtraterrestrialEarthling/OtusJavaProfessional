import implementation.AmountAskerImpl;
import implementation.CashBoxImpl;
import implementation.MoneyCounterImpl;
import enums.Denomination;
import implementation.Validator;
import interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputValidator inputValidator = new Validator();
        AmountAsker asker = new AmountAskerImpl(inputValidator);
        List<Denomination> denominations = new ArrayList<>(List.of(
                Denomination.FIVE_THOUSAND,
                Denomination.ONE_THOUSAND,
                Denomination.FIVE_HUNDRED,
                Denomination.ONE_HUNDRED
        ));
        MoneyCounter counter = new MoneyCounterImpl(denominations);
        WithdrawalValidator withdrawalValidator = new Validator();
        CashBox cashBox = new CashBoxImpl(counter, asker, withdrawalValidator);
        cashBox.refill();
        cashBox.giveOutMoney();
    }
}
