package sample;

import java.io.Serializable;
import java.lang.Math;
import java.util.*;


public class Main implements Serializable {

    //Variabler
    private double totalBitcoins;
    private double bitcoinsPerSec;
    private int bitcoinsPerClick = 1;
    Shop cursor = new Shop("Cursor",1,500,1);
    Shop abakus = new Shop("Abakus",0,20,0.1);
    Shop pascaline = new Shop("Pascaline",0,110,1);
    Shop eniac = new Shop("ENIAC",0,1400,7);
    Shop tradic = new Shop("TRADIC",0,13000,46);
    Shop appleII = new Shop("Apple II",0,150000,255);
    Shop commodore64 = new Shop("Commodore 64",0,1400000, 1360);
    Shop appleMacintosh = new Shop("Apple Macintosh",0,21000000,7800);

    // Getters og setters
    public int getCount(Shop shop) {
        return shop.getCount();
    }
    public double getPrice(Shop shop) {
        return shop.getPrice();
    }
    public double getTotalBitcoins() {
        return totalBitcoins;
    }
    public double getBitcoinsPerSec() {
        return bitcoinsPerSec;
    }


    //Kjøp-funksjon for oppgraderinger
    public void buyShop(Shop shop) {
        if (totalBitcoins >= shop.getPrice()) {
            totalBitcoins -= shop.getPrice();
            shop.setCount(shop.getCount()+1);
            shop.setPrice(shop.getPrice()*Math.pow(1.15,shop.getCount()));
            updateAtBuy();
        }
    }

    //Kjøp-funksjon for oppgradering av bitcoinsPerClick
    public void buyCursor(Shop shop) {
        if (totalBitcoins >= shop.getPrice()) {
            totalBitcoins -= shop.getPrice();
            shop.setCount(shop.getCount()+1);
            shop.setPrice(shop.getPrice()*Math.pow(1.4,shop.getCount()));
            bitcoinsPerClick = (int) (shop.getCount()*shop.getPerSec());
        }
    }

    //Liste med oppgraderinger for enklere bruk under
    List<Shop> upgrades = new ArrayList<>(Arrays.asList(abakus,pascaline,eniac,tradic,appleII,commodore64,appleMacintosh));

    //Kaller på funksjoner som oppdaterer felter etter kjøp
    public void updateAtBuy() {
        double bitcoinsTempPerSec = 0;
        //Legger til bitcoinsPerSec * count
        for (Shop s : upgrades) {
            bitcoinsTempPerSec += (s.getPerSec()*s.getCount());
        }
        //Oppdaterer bitcoinsPerSec
        bitcoinsPerSec = bitcoinsTempPerSec;
    }


    //Liste med listeners (forsåvidt bare controller)
    private List<MainListener> mainListeners = new ArrayList<>();

    //Legger til listeners (Controller kaller denne)
    public void addListener(MainListener mainListener) {
        if (!mainListeners.contains(mainListener)) {
            mainListeners.add(mainListener);
        }
    }

    //Sletter alle listeners (Nødvendig for lagring)
    public void clearListeners() {
        mainListeners.clear();
    }

    //Oppdaterer totalBitcoins med bitcoinsPerClick
    public void bitcoinOnClick() {
        totalBitcoins += bitcoinsPerClick;

        //Gir beskjed til observatør (Controller) om at totalBitcoins er endret
        for (MainListener m : mainListeners) {
            m.mainChanged(totalBitcoins);
        }
    }

    //Timer oppdaterer totalBitcoins med bitcoinsPerSec
    public void timerUpdate() {
        Timer timer = new Timer();
        TimerTask updateRate = new TimerTask() {
            @Override
            public void run() {
                //Legger til bitcoinsPerSec/50 ettersom dette blir utført 50 ganger i sekundet
                totalBitcoins+=(bitcoinsPerSec/50);

                //Gir beskjed til observatør (Controller) om at totalBitcoins er endret
                for (MainListener m : mainListeners) {
                    m.mainChanged(totalBitcoins);
                }
            }
        };
        timer.schedule(updateRate, 0, 20);
    }

}


