package models;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String name;
    private int age;
    private String superPowers;
    private String weakness;
    private static List<Hero> allHeroes = new ArrayList<>();
    private int id;
    private int squadId;
    public Hero(String name, int age, String superPowers, String weakness, int squadId){
        this.name = name;
        this.age = age;
        this.superPowers = superPowers;
        this.weakness = weakness;
        allHeroes.add(this);
        this.id = allHeroes.size();
        this.squadId = squadId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSuperPowers() {
        return superPowers;
    }
    public String getWeakness() {
        return weakness;
    }

    public static List<Hero> getAllHeroes() {
        return allHeroes;
    }

    public int getSquadId() {
        return squadId;
    }
    public static void deleteAllHeroes(){
        allHeroes.clear();
    }
    public void deleteHero(){
        allHeroes.remove(id-1);
    }

    public int getId() {
        return id;
    }
    public static Hero findById(int id){
        return allHeroes.get(id-1);
    }
}
