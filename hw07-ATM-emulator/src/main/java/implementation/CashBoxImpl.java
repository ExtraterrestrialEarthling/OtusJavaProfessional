/**
 * Created by ExtraterrestrialEarthling on 18.10.2023.
 */

package implementation;

import enums.Denomination;
import interfaces.AmountAsker;
import interfaces.CashBox;
import interfaces.MoneyCounter;
import interfaces.WithdrawalValidator;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CashBoxImpl implements CashBox {
    private final Map<Denomination, Integer> bankNotesCount;
    private final MoneyCounter moneyCounter;
    private final AmountAsker amountAsker;
    private final WithdrawalValidator withdrawalValidator;

    public CashBoxImpl(MoneyCounter moneyCounter, AmountAsker amountAsker,
                       WithdrawalValidator withdrawalValidator) {
        bankNotesCount = new HashMap<>();
        for (Denomination denomination : moneyCounter.getDenominations()){
            bankNotesCount.put(denomination, 0);
        }
        this.moneyCounter = moneyCounter;
        this.amountAsker = amountAsker;
        this.withdrawalValidator = withdrawalValidator;
    }

    @Override
    public void refill() {
        for (var entry : bankNotesCount.entrySet()){
            entry.setValue(1000);
        }
    }

    @Override
    public void giveOutMoney() {
        this.displayCurrentAmount();
        BigDecimal withdrawalAmount = amountAsker.askForAmount();
        if(!withdrawalValidator.isWithdrawalValid(withdrawalAmount, moneyCounter.countAmount(bankNotesCount))){
            System.out.println("The ATM doesn't have enough money. Come back later when it's refilled " +
                    "or search for another ATM nearby.");
            return;
        }
        HashMap<Denomination, Integer> notesWithdrawn = moneyCounter.countBanknotes(withdrawalAmount);

        for (var entry : bankNotesCount.entrySet()) {
            int amountOfDenomination = notesWithdrawn.get(entry.getKey());
            entry.setValue(entry.getValue() - amountOfDenomination);
        }

        System.out.println("The following sum has been given out: " + withdrawalAmount);
        this.displayCurrentAmount();
    }

    private void displayCurrentAmount() {
        System.out.println("Cashbox current amount: " + moneyCounter.countAmount(bankNotesCount));
    }
}


