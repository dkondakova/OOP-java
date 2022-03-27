package com.company;

class Word {
    public Word(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
    public String getWord() {
        return word;
    }
    public int getFreq() {
        return freq;
    }

    private String word;
    private int freq;
}