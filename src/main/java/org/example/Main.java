package org.example;

import java.util.HashMap;
import java.util.Random;

/**
 * Создать свой Java Maven/Gradle проект;
 * Реализовать прикладную задачу - приложение для демонстрации парадокса Монти Холла;
 * Можно добавить любые библиотеки которые считают необходимыми
 * Результаты должны быть сохранены в HashMap (шаг цикла -> результат)
 * Необходимо вывести статистику по результату -
 * количество позитивных и негативных результатов,
 * процент от общего количества шагов цикла.
 */

public class Main {
    public static void main(String[] args) {
        int countDoNotChange = 0;
        int countChange = 0;
        HashMap<Integer, Boolean> resultChange = new HashMap<>();
        HashMap<Integer, Boolean> resultDoNotChange = new HashMap<>();
        Random gen = new Random();
        int games = 100000;
        for (int game = 0; game < games; game++) {
            int[] doors = {0, 0, 0};//0 is a goat, 1 is a car
            doors[gen.nextInt(3)] = 1;//put a winner in a random door
            int choice = gen.nextInt(3); //pick a door, any door
            int shown; //the shown door
            do {
                shown = gen.nextInt(3);
                //don't show the winner or the choice

            } while (doors[shown] == 1 || shown == choice);

            int newChoice; //change selection
            do{
                newChoice = gen.nextInt(3);
                //don't equal the shown and the choice
            }while (newChoice == choice || newChoice == shown);


            if(doors[choice] == 1){
                resultDoNotChange.put(game, true);
                countDoNotChange++;
            }else if(doors[newChoice] == 1) {
                resultChange.put(game, true);
                countChange++;
            }else{
                if(doors[choice] != 1){
                    resultDoNotChange.put(game, false);
                }else if(doors[newChoice] != 1) {
                    resultChange.put(game, false);
                }
            }
        }
        float procent =countChange*100/games;
        float procentDoNot = countDoNotChange*100/games;
        System.out.printf("не меняем выбор: %f\n", procentDoNot);
        System.out.println("Позитив: " + countDoNotChange + ", Негатив: " + (games - countDoNotChange));
        System.out.printf("меняем выбор %f\n", procent);
        System.out.println("Позитив: " + countChange + ", Негатив: " + (games - countChange));
    }
}
