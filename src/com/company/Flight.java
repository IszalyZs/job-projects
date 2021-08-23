package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Flight {
    private List<Integer> data = new ArrayList<>();
    private final int PERCENTAGE_OF_WATER = 70;
    private final int MAX_LENGTH = 25;
    private final int MAX_HEIGHT_ISLAND = 1000;
    private final int MIN_HEIGHT_ISLAND = 501;
    private final int MAX_HEIGHT = 500;
    private final int MIN_HEIGHT = 2;
    private final int NUMBER_OF_LOTTERY = 13;
    private List<Island> islands = new ArrayList<>();


    public void init() {
        createData();
        createIslandList();
        statistics();
    }

    private void statistics() {
        if (islands.size() == 0) System.out.println("Nincs sziget Amerika és Európa között.");
        else {
            int maxHeight = islands.stream()
                    .map(Island::getMountainPeak)
                    .mapToInt(Integer::intValue)
                    .max()
                    .getAsInt();
            List<Integer> maxHeightList = islands.stream()
                    .filter(island -> island.getMountainPeak() == maxHeight)
                    .map(Island::getIdentityNumber)
                    .collect(Collectors.toList());
            int maxLength = islands.stream().map(Island::getLength).mapToInt(Integer::intValue).max().getAsInt();
            System.out.printf("Amerika és Európa között %s darab sziget van.", islands.size());
            System.out.println();
            System.out.printf("A leghosszabb sziget: %s egység.", maxLength);
            System.out.println();
            System.out.print("A legmagasabb pont a következő sorszámú szigeten található: ");
            maxHeightList.forEach(number -> System.out.print(number + ". "));
        }
    }

    private void createIslandList() {
        int maxHeight = 0;
        int length = 0;
        boolean startIsland = false;
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index) == 0) {
                if (length != 0)
                    islands.add(new Island(length, maxHeight));
                length = 0;
                maxHeight = 0;
                startIsland = true;
            } else if (startIsland) {
                if (data.get(index) > maxHeight) maxHeight = data.get(index);
                length++;
            }
        }
    }

    private void createData() {
        data.add(1);
        int counter = 0;
        for (int i = 0; i < NUMBER_OF_LOTTERY; i++) {
            int random = (int) (Math.random() * 100);
            int length = (int) (Math.random() * MAX_LENGTH);
            if (random <= PERCENTAGE_OF_WATER) {
                createSea(length);
                counter++;
            } else {
                createIsland(counter, length);
            }
        }
        data.add(1);
        changeLastLand();
        for (Integer item : data) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private void changeLastLand() {
        int pointer = data.lastIndexOf(0);
        if (pointer > 0) {
            if (data.get(pointer + 1) != 1) {
                for (int i = pointer + 1; i < data.size() - 1; i++) {
                    data.remove(i);
                    data.add(i, (int) ((Math.random() * (MAX_HEIGHT - MIN_HEIGHT)) + MIN_HEIGHT));
                }
            }
        }
    }

    private void createSea(int length) {
        for (int index = 0; index < length; index++) {
            data.add(0);
        }
    }

    private void createIsland(int counter, int length) {
        for (int index = 0; index < length; index++) {
            if (counter == 0)
                data.add((int) ((Math.random() * (MAX_HEIGHT - MIN_HEIGHT)) + MIN_HEIGHT));
            else
                data.add((int) ((Math.random() * (MAX_HEIGHT_ISLAND - MIN_HEIGHT_ISLAND)) + MIN_HEIGHT_ISLAND));
        }
    }
}
