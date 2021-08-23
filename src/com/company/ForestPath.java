package com.company;

import java.util.Scanner;

public class ForestPath {
    private int percentageOfWater;
    private int jumpSize;
    private int[] list = new int[100];

    public void init() {
        dataEntry();
        createList();
        printList();
        crossingOverWater();
    }

    private void dataEntry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kérem adja meg a víz előfordulási valószínűségét százalékban (1-100): ");
        boolean valid = true;
        do {
            try {
                percentageOfWater = scanner.nextInt();
                if (percentageOfWater < 1 || percentageOfWater > 100)
                    throw new Exception();
                valid = false;
            } catch (Exception ex) {
                System.out.println("Kérem számot adjon meg(1-100):");
            }
            scanner.nextLine();
        } while (valid);

        valid = true;
        System.out.println("Kérem adja meg a lépésközt (1-25):");
        do {
            try {
                jumpSize = scanner.nextInt();
                if (jumpSize < 1 || jumpSize > 25)
                    throw new Exception();
                valid = false;
            } catch (Exception ex) {
                System.out.println("Kérem számot adjon meg(1-25):");
            }
            scanner.nextLine();
        } while (valid);
    }

    private void createList() {
        list[0] = 1;
        list[99] = 1;
        for (int i = 1; i < list.length - 1; i++) {
            int random = (int) (Math.random() * 100);
            if (random <= percentageOfWater) list[i] = 2;
            else list[i] = 1;
        }
    }

    private void printList() {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 1)
                System.out.print("#");
            else System.out.print("_");
        }
        System.out.println();
    }

    private void crossingOverWater() {
        int max = 0;
        int counter = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 2) counter++;
            else {
                if (counter > max) max = counter;
                counter = 0;
            }
        }
        if (max > jumpSize) System.out.println("Piroska vizes lett.");
        else System.out.println("Piroska nem lett vizes.");
    }

}
