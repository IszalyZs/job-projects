package com.company;

public class Island {
    private static int counter;
    private int identityNumber;
    private int length;
    private int mountainPeak;

    public Island(int length, int mountainPeak) {
        this.identityNumber = ++counter;
        this.length = length;
        this.mountainPeak = mountainPeak;
    }

    public int getIdentityNumber() {
        return identityNumber;
    }

    public int getLength() {
        return length;
    }

    public int getMountainPeak() {
        return mountainPeak;
    }
}
