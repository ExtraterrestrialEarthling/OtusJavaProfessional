/**
 * Created by ExtraterrestrialEarthling on 18.10.2023.
 */

package implementation;

import interfaces.InputValidator;
import interfaces.WithdrawalValidator;

import java.math.BigDecimal;

public class Validator implements WithdrawalValidator, InputValidator {

    @Override
    public boolean isWithdrawalValid(BigDecimal withdrawalAmount, BigDecimal accountBalance) {
        return withdrawalAmount.compareTo(accountBalance)<=0;
    }

    @Override
    public boolean isInputValid(String input) {
        return input.matches("\\d+");
    }
}
