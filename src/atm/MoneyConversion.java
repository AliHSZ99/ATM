package atm;

/**
 *
 * @author Ali Zoubeidi
 */
public class MoneyConversion {

    private String currency;
    private String exchangeCurrency;
    private double amount;

    public MoneyConversion() {
        currency = "unkown";
    }

    public MoneyConversion(String currency, double amount, String exchangeCurrency) {
        if (currency.equalsIgnoreCase("cad") || currency.equalsIgnoreCase("usd")
                || currency.equalsIgnoreCase("eur")) {
            this.currency = currency;
        }
        if (amount > 0) {
            this.amount = amount;
        }
        if (exchangeCurrency.equalsIgnoreCase("cad") || exchangeCurrency.equalsIgnoreCase("usd")
                || exchangeCurrency.equalsIgnoreCase("eur")) {
            this.exchangeCurrency = exchangeCurrency;
        }
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    public MoneyConversion setCurrency(String currency) {
        if (currency.equalsIgnoreCase("cad") || currency.equalsIgnoreCase("usd")
                || currency.equalsIgnoreCase("eur")) {
            this.currency = currency;
        }
        return this;
    }

    public MoneyConversion setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        }
        return this;
    }

    public MoneyConversion setExchangeCurrency(String exchangeCurrency) {
        if (exchangeCurrency.equalsIgnoreCase("cad") || exchangeCurrency.equalsIgnoreCase("usd")
                || exchangeCurrency.equalsIgnoreCase("eur")) {
            this.exchangeCurrency = exchangeCurrency;
        }
        return this;
    }

    public double toUsd() {
        if (amount > 0 && currency.equalsIgnoreCase("cad")) {
            return amount * 0.71;
        } else if (amount > 0 && currency.equalsIgnoreCase("eur")) {
            return amount * 1.09;
        }
        return amount;
    }

    public double toCad() {
        if (amount > 0 && currency.equalsIgnoreCase("usd")) {
            return amount * 1.41;
        } else if (amount > 0 && currency.equalsIgnoreCase("eur")) {
            return amount * 1.54;
        }
        return amount;
    }

    public double toEur() {
        if (amount > 0 && currency.equalsIgnoreCase("usd")) {
            return amount * 0.92;
        } else if (amount > 0 && currency.equalsIgnoreCase("cad")) {
            return amount * 0.65;
        }
        return amount;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
    }

    public boolean equals(Object o) {
        if (!(o instanceof MoneyConversion)) {
            return false;
        } else {
            MoneyConversion objConv = (MoneyConversion) o;
            if (exchangeCurrency.equalsIgnoreCase(objConv.exchangeCurrency)
                    && currency.equalsIgnoreCase(objConv.currency)
                    && amount == objConv.amount) {
                return true;
            } else {
                return false;
            }
        }
    }
}
