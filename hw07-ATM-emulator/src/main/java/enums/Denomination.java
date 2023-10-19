/**
 * Created by ExtraterrestrialEarthling on 18.10.2023.
 */

package enums;

public enum Denomination {
    FIVE_THOUSAND(5000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100);

    private final int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

}
