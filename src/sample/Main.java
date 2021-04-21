package sample;

import java.io.Serializable;
import java.lang.Math;
import java.util.*;
import java.util.stream.Collectors;


public class Main implements Serializable {

    //Konstruktør for å starte timer
    public Main() {
        timerUpdate();
    }

    //Singleton
    private static class SingletonHelper {
        //Ikkje final, fordi vil kunne sette bestemme main fra lagring og ved newGame()
        private final static Main instance = new Main();
    }
    //Getter for instansen
    public static Main getInstance() {
        return Main.SingletonHelper.instance;
    }

    //Variabler
    private double totalBitcoins;
    private double bitcoinsPerSec;
    private int bitcoinsPerClick = 1;
    private final Shop cursor = new Shop("Cursor",1,500,1);
    private final Shop abakus = new Shop("Abakus",0,20,0.1);
    private final Shop pascaline = new Shop("Pascaline",0,110,1);
    private final Shop eniac = new Shop("ENIAC",0,1400,7);
    private final Shop tradic = new Shop("TRADIC",0,13000,46);
    private final Shop appleII = new Shop("Apple II",0,150000,255);
    private final Shop commodore64 = new Shop("Commodore 64",0,1400000, 1360);
    private final Shop appleMacintosh = new Shop("Apple Macintosh",0,21000000,7800);

    //Liste med alle shop-objekt for enkel aksessering med getShopObject
    private final List<Shop> shopList = new ArrayList<>(Arrays.asList(cursor,abakus,pascaline,eniac,tradic,appleII,commodore64,appleMacintosh));

    // Getters og setters
    public Shop getShopObject(String shopName) {
        return shopList.stream().filter(s -> shopName.equals(s.getName())).findFirst().get();
    }
    public int getCount(String shopName) {
        Shop shop = getShopObject(shopName);
        return shop.getCount();
    }
    public double getPrice(String shopName) {
        Shop shop = getShopObject(shopName);
        return shop.getPrice();
    }
    public double getTotalBitcoins() {
        return totalBitcoins;
    }
    public double getBitcoinsPerSec() {
        return bitcoinsPerSec;
    }
    public void setTotalBitcoins(double totalBitcoins) {
        this.totalBitcoins = totalBitcoins;
    }
    public List<Shop> getShopList() {
        return shopList;
    }

    //Kjøp-funksjon for oppgraderinger
    public void buyShop(String shopName) {
        Shop shop = getShopObject(shopName);
        if (totalBitcoins >= shop.getPrice()) {
            totalBitcoins -= shop.getPrice();
            shop.setCount(shop.getCount()+1);
            shop.setPrice(shop.getPrice()*Math.pow(1.15,shop.getCount()));
            updateAtBuy();
        }
    }

    //Kjøp-funksjon for oppgradering av bitcoinsPerClick
    public void buyCursor() {
        if (totalBitcoins >= cursor.getPrice()) {
            totalBitcoins -= cursor.getPrice();
            cursor.setCount(cursor.getCount()+1);
            cursor.setPrice(cursor.getPrice()*Math.pow(1.4,cursor.getCount()));
            bitcoinsPerClick = (int) (cursor.getCount()*cursor.getPerSec());
        }
    }

    //Kaller på funksjoner som oppdaterer felter etter kjøp
    public void updateAtBuy() {
        //Lager liste uten cursor for enkel bruk nedenfor
        List<Shop> upgradesExclCursor = shopList.stream().filter(s -> !s.getName().equals("Cursor")).collect(Collectors.toList());

        double bitcoinsTempPerSec = 0;
        //Legger til bitcoinsPerSec * count
        for (Shop s : upgradesExclCursor) {
            bitcoinsTempPerSec += (s.getPerSec()*s.getCount());
        }
        //Oppdaterer bitcoinsPerSec
        bitcoinsPerSec = bitcoinsTempPerSec;
        bitcoinsPerClick = (int) (cursor.getCount()*cursor.getPerSec());
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


