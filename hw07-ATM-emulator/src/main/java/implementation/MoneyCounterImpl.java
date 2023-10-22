/**
 * Created by ExtraterrestrialEarthling on 18.10.2023.
 */

package implementation;

import enums.Denomination;
import interfaces.MoneyCounter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MoneyCounterImpl implements MoneyCounter {
    private List<Denomination> denominations;

    public MoneyCounterImpl(List<Denomination> denominations){
        this.denominations = denominations;
    }

    public List<Denomination> getDenominations(){
        return denominations;
    }

    public void setDenominations(List<Denomination> denominations){
        this.denominations = denominations;
    }

    @Override
    public HashMap<Denomination, Integer> countBanknotes(BigDecimal amount) {
        int remainder = amount.intValue();
        HashMap<Denomination, Integer> result = new HashMap<>();
        for (Denomination denomination : denominations) {
            result.put(denomination, (remainder / denomination.getValue()));
            remainder = remainder % denomination.getValue();
        }
        return result;
    }

    public BigDecimal countAmount(Map<Denomination, Integer> notes){
        int sum = 0;
        for (var entry : notes.entrySet()){
            sum += entry.getKey().getValue()*entry.getValue();
        }
        return new BigDecimal(sum);
    }
}
