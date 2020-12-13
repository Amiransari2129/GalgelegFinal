package com.example.galgelegreallyfinal;

public class ScoreExample {
    private String ascore;
    private String aword;

    public ScoreExample(String score, String word){
        ascore = score;
        aword = word;
    }

    public String getScore(){
        return ascore;
    }

    public String getWord(){
        return aword;
    }
}
