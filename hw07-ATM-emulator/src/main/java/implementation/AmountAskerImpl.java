/**
 * Created by ExtraterrestrialEarthling on 18.10.2023.
 */

package implementation;

import interfaces.AmountAsker;
import interfaces.InputValidator;

import java.math.BigDecimal;
import java.util.Scanner;

public class AmountAskerImpl implements AmountAsker {
    private final InputValidator inputValidator;

    public AmountAskerImpl(InputValidator inputValidator){
        this.inputValidator = inputValidator;
    }
    @Override
    public BigDecimal askForAmount() {
        String amount;
        Scanner scan = new Scanner(System.in);
        System.out.println("How much money would you like to withdraw?");
        while(true){
            try{
                amount = scan.nextLine();
                if (!inputValidator.isInputValid(amount)){
                    throw new IllegalArgumentException();
                }
                return new BigDecimal(amount);
            } catch(IllegalArgumentException e) {
                System.out.println("Please, enter a positive multiple of 100.");
            }
        }


    }
}
