package jvm.four;

/**
 * +UnlockCommercialFeatures -XX:+FlightRecorder
 */
public class JMCTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start runtime");
        Thread.sleep(1000000);
        System.out.println("dead main Thread");
    }
}
