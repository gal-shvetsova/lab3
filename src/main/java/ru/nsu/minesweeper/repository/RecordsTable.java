package ru.nsu.minesweeper.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecordsTable {
    private ArrayList<String> player;
    private ArrayList<Integer> record;
    private String size;
    public ArrayList<String> getPlayer() {
        return player;
    }

    public ArrayList<Integer> getRecord() {
        return record;
    }

    public RecordsTable(String size) {    //TODO make it for all slozhnostei
        this.size = size;
        player = new ArrayList<>();
        record = new ArrayList<>();
        try {
            FileReader reader = new FileReader("/home/galya/ru/nsu/ccfit/shvetsova/lab3/src/main/resources/records" + size + ".txt");
            int letter = reader.read();
            String word;
            int counter = 0;
            while (counter < 5) {
                word = "";
                while (Character.isLetter(letter)) {
                    word += (char) letter;
                    letter = reader.read();
                }
                this.player.add(word);
                letter = reader.read();
                counter++;
            }
            letter = reader.read();
            counter = 0;
            while (counter < 5) {
                word = "";
                while (Character.isDigit(letter)) {
                    word += (char) letter;
                    letter = reader.read();
                }
                this.record.add(Integer.parseInt(word));
                letter = reader.read();
                counter++;
            }
            reader.close();
        } catch (IOException e) { //TODO throw exception

            e.printStackTrace();
        }
    }

    public void addNewRecord(String name, int newRecord) {
        int counter = 0;

        while (record.get(counter) < newRecord && counter < 5) {
            counter++;
        }

        if (counter < 5) {
            record.remove(4);
            player.remove(4);
            record.add(counter, newRecord);
            player.add(counter, name);
        }

        try {
                FileWriter writer = new FileWriter("/home/galya/ru/nsu/ccfit/shvetsova/lab3/src/main/resources/records" + size + ".txt", false);

            for (int i = 0; i < 5; i++) {
                writer.write(player.get(i) + " ");
            }

            writer.write('\n');

            for (int i = 0; i < 5; i++) {
                writer.write(record.get(i) + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean isRecord(int time) {
        return time <= record.get(4);
    }
}
