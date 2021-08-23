package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. feladat: Mágikus négyzet-e?");
        MagicSquare magicSquare = new MagicSquare("square.txt");
        magicSquare.init();
        System.out.println();
        System.out.println("2. feladat: Erdei ösvény");
        ForestPath forestPath = new ForestPath();
        forestPath.init();
        System.out.println("3. feladat: Szigetek");
        Flight flight = new Flight();
        flight.init();
    }
}




