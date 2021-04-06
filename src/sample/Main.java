package sample;

import java.io.Serializable;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;


public class Main implements Serializable {

    //Variabler
    private double totalBitcoins;
    private double bitcoinsPerSec;
    private int bitcoinsPerClick = 1;

    // Kor mange kjøpte
    private int cursorLevelCount = 1;
    private int abakusCount;
    private int pascalineCount;
    private int eniacCount;
    private int tradicCount;
    private int appleIICount;
    private int commodore64Count;
    private int appleMacintoshCount;

    // Startpris på oppgraderinger
    private final double CURSOR_START_PRICE = 1800;
    private final double ABAKUS_START_PRICE = 20;
    private final double PASCALINE_START_PRICE = 110;
    private final double ENIAC_START_PRICE = 1300;
    private final double TRADIC_START_PRICE = 14000;
    private final double APPLEII_START_PRICE = 150000;
    private final double COMMODORE64_START_PRICE = 1400000;
    private final double APPLEMACINTOSH_START_PRICE = 21000000;

    // Pris på oppgraderinger
    private double cursorPrice;
    private double abakusPrice;
    private double pascalinePrice;
    private double eniacPrice;
    private double tradicPrice;
    private double appleIIPrice;
    private double commodore64Price;
    private double appleMacintoshPrice;

    // bicoinsPerSec per oppgradering
    private final double ABAKUS_PER_SEC = 0.1;
    private final double PASCALINE_PER_SEC = 1;
    private final double ENIAC_PER_SEC = 8;
    private final double TRADIC_PER_SEC = 47;
    private final double APPLEII_PER_SEC = 260;
    private final double COMMODORE64_PER_SEC = 1400;
    private final double APPLEMACINTOSH_PER_SEC = 7800;

    // Getters og setters
    public double getCursorPrice() {
        return cursorPrice;
    }
    public double getAbakusPrice() {
        return(abakusPrice);
    }
    public double getPascalinePrice() {
        return(pascalinePrice);
    }
    public double getEniacPrice() {
        return eniacPrice;
    }
    public double getTradicPrice() {
        return tradicPrice;
    }
    public double getAppleIIPrice() {
        return appleIIPrice;
    }
    public double getCommodore64Price() {
        return commodore64Price;
    }
    public double getAppleMacintoshPrice() {
        return appleMacintoshPrice;
    }
    public int getCursorLevelCount() {
        return cursorLevelCount;
    }
    public int getAbakusCount() {
        return abakusCount;
    }
    public int getPascalineCount() {
        return pascalineCount;
    }
    public int getEniacCount() {
        return eniacCount;
    }
    public int getTradicCount() {
        return tradicCount;
    }
    public int getAppleIICount() {
        return appleIICount;
    }
    public int getCommodore64Count() {
        return commodore64Count;
    }
    public int getAppleMacintoshCount() {
        return appleMacintoshCount;
    }
    public double getTotalBitcoins() {
        return totalBitcoins;
    }
    public double getBitcoinsPerSec() {
        return bitcoinsPerSec;
    }
    public double getBitcoinsPerClick() {
        return bitcoinsPerClick;
    }
    public void setBitcoinsPerSec(double bitcoinsPerSec) {
        this.bitcoinsPerSec = bitcoinsPerSec;
    }
    public void setBitcoinsPerClick(int bitcoinsPerClick) {
        this.bitcoinsPerClick = bitcoinsPerClick;
    }
    public void setTotalBitcoins(double totalBitcoins) {
        this.totalBitcoins = totalBitcoins;
    }

    //Oppdaterer totalBitcoins med bitcoinsPerClick
    public void bitcoinOnClick() {
        setTotalBitcoins(getTotalBitcoins() + getBitcoinsPerClick());
    }

    // Oppdaterer bitcoinsShopPerSec
    public void updateShopPerSec() {
        double abakusTotalPerSec = ABAKUS_PER_SEC*abakusCount;
        double pascalineTotalPerSec = PASCALINE_PER_SEC*pascalineCount;
        double eniacTotalPerSec = ENIAC_PER_SEC*eniacCount;
        double tradicTotalPerSec = TRADIC_PER_SEC*tradicCount;
        double appleIITotalPerSec = APPLEII_PER_SEC*appleIICount;
        double commodore64TotalPerSec = COMMODORE64_PER_SEC*commodore64Count;
        double appleMacintoshTotalPerSec = APPLEMACINTOSH_PER_SEC*appleMacintoshCount;
        setBitcoinsPerSec(abakusTotalPerSec+pascalineTotalPerSec+eniacTotalPerSec+tradicTotalPerSec+appleIITotalPerSec+commodore64TotalPerSec+appleMacintoshTotalPerSec);
    }

    // Oppdaterer prisen for oppgraderinger
    public void updateShopPrice() {
        this.cursorPrice = Math.round(CURSOR_START_PRICE*(Math.pow(1.4,cursorLevelCount)));
        this.abakusPrice = Math.round(ABAKUS_START_PRICE*(Math.pow(1.15,abakusCount)));
        this.pascalinePrice = Math.round(PASCALINE_START_PRICE*(Math.pow(1.15,pascalineCount)));
        this.eniacPrice = Math.round(ENIAC_START_PRICE*(Math.pow(1.15,eniacCount)));
        this.tradicPrice = Math.round(TRADIC_START_PRICE*(Math.pow(1.15,tradicCount)));
        this.appleIIPrice = Math.round(APPLEII_START_PRICE*(Math.pow(1.15,appleIICount)));
        this.commodore64Price = Math.round(COMMODORE64_START_PRICE*(Math.pow(1.15,commodore64Count)));
        this.appleMacintoshPrice = Math.round(APPLEMACINTOSH_START_PRICE*(Math.pow(1.15,appleMacintoshCount)));
    }

    //Kaller på funksjoner som oppdaterer felter etter kjøp
    public void updateShopAtBuy() {
        updateShopPrice();
        updateShopPerSec();
    }

    // Kjøp funksjoner
    public void buyCursor() {
        if (getTotalBitcoins() >= cursorPrice) {
            cursorLevelCount++;
            setTotalBitcoins(getTotalBitcoins() - cursorPrice);
            setBitcoinsPerClick(cursorLevelCount);
            updateShopAtBuy();
        }
    }

    public void buyAbakus() {
        if (getTotalBitcoins() >= abakusPrice) {
            abakusCount++;
            setTotalBitcoins(getTotalBitcoins()-abakusPrice);
            updateShopAtBuy();
        }
    }

    public void buyPascaline() {
        if (getTotalBitcoins() >= pascalinePrice) {
            pascalineCount++;
            setTotalBitcoins(getTotalBitcoins()-pascalinePrice);
            updateShopAtBuy();
        }
    }

    public void buyEniac() {
        if (getTotalBitcoins() >= eniacPrice) {
            eniacCount++;
            setTotalBitcoins(getTotalBitcoins()-eniacPrice);
            updateShopAtBuy();
        }
    }

    public void buyTradic() {
        if (getTotalBitcoins() >= tradicPrice) {
            tradicCount++;
            setTotalBitcoins(getTotalBitcoins() - tradicPrice);
            updateShopAtBuy();
        }
    }

    public void buyAppleII() {
        if (getTotalBitcoins() >= appleIIPrice) {
            appleIICount++;
            setTotalBitcoins(getTotalBitcoins() - appleIIPrice);
            updateShopAtBuy();
        }
    }

    public void buyCommodore64() {
        if (getTotalBitcoins() >= commodore64Price) {
            commodore64Count++;
            setTotalBitcoins(getTotalBitcoins() - commodore64Price);
            updateShopAtBuy();
        }
    }

    public void buyAppleMacintosh() {
        if (getTotalBitcoins() >= appleMacintoshPrice) {
            appleMacintoshCount++;
            setTotalBitcoins(getTotalBitcoins() - appleMacintoshPrice);
            updateShopAtBuy();
        }
    }


    //Timer oppdaterer totalBitcoins med bitcoinsPerSec
    public void timerUpdate() {
        Timer timer = new Timer();
        TimerTask updateRate = new TimerTask() {
            @Override
            public void run() {
                setTotalBitcoins(getTotalBitcoins()+(getBitcoinsPerSec()/50));
            }
        };
        timer.schedule(updateRate, 0, 20);
    }

}


