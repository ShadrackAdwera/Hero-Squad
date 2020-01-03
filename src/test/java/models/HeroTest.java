package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
        Hero.deleteAllHeroes();
    }

    @Test
    public void heroObjectInstantiatesCorrectly_boolean() throws Exception {
        Hero newHero = setUpHero();
        assertTrue(newHero instanceof Hero);
    }
    @Test
    public void savesAgeCorrectly_int() throws Exception {
        Hero newHero = setUpHero();
        assertEquals(42, newHero.getAge());
    }
    @Test
    public void savesPowersCorrectly_String() throws Exception {
        Hero newHero = setUpHero();
        assertEquals("The Punisher", newHero.getSuperPowers());
    }
    @Test
    public void savesWeaknessCorrectly_String() throws Exception {
        Hero newHero = setUpHero();
        assertEquals("The kid", newHero.getWeakness());
    }
    @Test
    public void savesNameCorrectly_String() throws Exception {
        Hero newHero = setUpHero();
        assertEquals("Pete Casteglioni", newHero.getName());
    }
    @Test
    public void savesAllHeroes_Hero() throws Exception{
        List<Hero> allHeroes = new ArrayList<>();
        Hero newHero = setUpHero();
        Hero newHero2 = new Hero("Pete Casteglioni", 42,"The Punisher","The kid",2);
        allHeroes.add(newHero);
        allHeroes.add(newHero2);
        assertEquals(2, Hero.getAllHeroes().size());
    }
    @Test
    public void newHeroInstantiatesWithId_int() throws Exception{
        Hero newHero = setUpHero();
        assertEquals(1, newHero.getId());
    }
    @Test
    public void findReturnsCorrectHero() throws Exception {
        Hero newHero = setUpHero();
        assertEquals(1, Hero.findById(newHero.getId()).getId());
    }
    //helper method
    private Hero setUpHero(){
        return new Hero("Pete Casteglioni", 42,"The Punisher","The kid",1);
    }
}