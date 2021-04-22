package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BitcoinClickerTest {

    public Main main;

    @BeforeEach
    public void setup() {
        main = new Main();
    }

    @Test
    @DisplayName("Test init")
    public void testInit() {
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),0);
        org.junit.jupiter.api.Assertions.assertEquals(main.getBitcoinsPerSec(),0);
    }

    @Test
    @DisplayName("Test click")
    public void testClick() {
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),0);
        main.bitcoinOnClick();
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),1);
    }

    @Test
    @DisplayName("Test shop")
    public void testShop() {
        main.setTotalBitcoins(20);
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),20);
        main.buyShop("Abakus");
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),0);
        org.junit.jupiter.api.Assertions.assertEquals(main.getBitcoinsPerSec(),0.1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        org.junit.jupiter.api.Assertions.assertTrue(main.getTotalBitcoins()>0);
    }

    @Test
    @DisplayName("Test cursor")
    public void testCursor() {
        main.setTotalBitcoins(500);
        main.buyCursor();
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),0);
        main.bitcoinOnClick();
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),2);
    }

    @Test
    @DisplayName("Test not able to buy shop")
    public void testNotAbleToBuyShop() {
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),0);
        org.junit.jupiter.api.Assertions.assertEquals(main.getBitcoinsPerSec(),0);
        main.buyShop("Pascaline");
        org.junit.jupiter.api.Assertions.assertEquals(main.getTotalBitcoins(),0);
        org.junit.jupiter.api.Assertions.assertEquals(main.getBitcoinsPerSec(),0);
    }

}
