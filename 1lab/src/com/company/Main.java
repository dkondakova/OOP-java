package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
     static int readIn(Map<String, Integer> mp, String fin) {
        int n = 0;
        try (Reader reader = new InputStreamReader(new FileInputStream(fin))){

            int c;
            StringBuilder word = new StringBuilder();
            String tmp;
            while ((c = reader.read()) != -1) {
                if (Character.isLetterOrDigit((char)c)) {
                    word.append((char)c);
                    continue;
                }
                if (word.length() == 0) {
                    continue;
                }

                tmp = word.toString();
                //tmp = new String(word);
                mp.put(tmp, mp.getOrDefault(tmp, 0) + 1);
                n++;

                word.setLength(0);
            }
            tmp = new String(word);
            mp.put(tmp, mp.getOrDefault(tmp, 0) + 1);
            n++;
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }

        return n;
    }

    static void writeOut(int n, List<Word> list, String fo) throws IOException {
        File fout = new File(fo);
        fout.createNewFile();

        try (PrintWriter pw = new PrintWriter(fout)){

            for (Word word : list) {
                float f = (float) word.getFreq() / (float) n * 100;
                pw.println(word.getWord() + "," + word.getFreq() + "," + f + "%");
            }

        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public static void main(String[] str) throws IOException {
        Map<String, Integer> mp = new HashMap<>();

        int n = readIn(mp, str[0]);

        List<Word> sortedList = new ArrayList<>();
        for (Map.Entry<String, Integer> it : mp.entrySet()) {
            sortedList.add(new Word(it.getKey(), it.getValue()));
        }

        sortedList.sort((left, right) -> {
            if (right.getFreq() == left.getFreq()) {
                return - right.getWord().compareTo(left.getWord());
            } else {
                return Integer.compare(right.getFreq(), left.getFreq());
            }
        });

        writeOut(n, sortedList, str[1]);
     }
}