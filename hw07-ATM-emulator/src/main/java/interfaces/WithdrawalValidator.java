package interfaces;

import java.math.BigDecimal;

public interface WithdrawalValidator {
    boolean isWithdrawalValid(BigDecimal withdrawalAmount, BigDecimal accountBalance);
}
