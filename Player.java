/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbcc;

/**
 *
 * @author Alex McLure
 */
public class Player {
    private String Name;
    private String Country;
    private int Score;
    
    public Player(){}

    Player(String name, String country, int score) {
        Name = name;
        Country = country;
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    
    
}
