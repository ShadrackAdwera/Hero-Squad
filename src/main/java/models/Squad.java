package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String name;
    private int maxSize;
    private String cause;
    private int id;
    private static List<Squad> allSquads = new ArrayList<>();
    private List<Hero> heroesInSquad;

    public Squad(String name, int maxSize, String cause) {
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
        allSquads.add(this);
        this.id = allSquads.size();
        this.heroesInSquad = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getCause() {
        return cause;
    }

    public static Squad findSquadById(int id){
        return allSquads.get(id-1);
    }
    public static void addSquad(Squad newSquad){
     allSquads.add(newSquad);
    }
    public void assignHeroToSquad(Hero newHero){
        heroesInSquad.add(newHero);
    }
    public List<Hero> getAllHeroesInSquad(){
        return heroesInSquad;
    }
    public static List<Squad> getAllSquads(){
        return allSquads;
    }

    public void deleteSquad(){
        allSquads.remove(id-1);
    }

    public static void deleteAllSquads(){
        allSquads.clear();
    }
}