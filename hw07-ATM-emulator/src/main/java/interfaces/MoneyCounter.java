package interfaces;

import enums.Denomination;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MoneyCounter {

    HashMap<Denomination, Integer> countBanknotes(BigDecimal amount);

    BigDecimal countAmount(Map<Denomination, Integer> notes);

    List<Denomination> getDenominations();

    void setDenominations(List<Denomination> denominations);
}
