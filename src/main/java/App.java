import models.*;
import java.util.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[]args){
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        //get: show all heroes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = Squad.getAllSquads();
            model.put("squads", squads);
            List<Hero> heroes = Hero.getAllHeroes();
            model.put("heroes", heroes);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete all squads and all heroes
        get("/squads/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.deleteAllSquads();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/heroes/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.deleteAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: delete a hero
        get("/squads/:squadId/heroes/:heroId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHero = Integer.parseInt(request.params("heroId")); //pull id - must match route segment
            Hero deleteHero = Hero.findById(idOfHero); //use it to find hero
            deleteHero.deleteHero();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a squad
        get("/squads/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquad = Integer.parseInt(request.params("id"));
            int idOfHero = Integer.parseInt(request.params("id"));
            Squad deleteSquad = Squad.findSquadById(idOfSquad);
            Hero deleteHero = Hero.findById(idOfHero);
            deleteSquad.deleteSquad();
            deleteHero.deleteHero();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: display new squad form
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = new ArrayList<>();
            model.put("squads", squads);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process squads form
        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String squadName = request.queryParams("name");
            int squadSize = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            Squad newSquad = new Squad(squadName, squadSize, cause);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: view squads and heroes
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = Squad.getAllSquads();
            model.put("squads", squads);
            int idOfSquadToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesFromSquad = foundSquad.getAllHeroesInSquad();
            model.put("heroes", allHeroesFromSquad);
            model.put("squads", Squad.getAllSquads());
            return new ModelAndView(model, "squads-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show new heroes form
        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = Squad.getAllSquads();
            model.put("squads", squads);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new hero form
        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = Squad.getAllSquads();
            model.put("squads", allSquads);
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String superPower = request.queryParams("superPower");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero newHero = new Hero(name,age,superPower,weakness,squadId);
            Squad newSquadMember = Squad.findSquadById(squadId);
            newSquadMember.assignHeroToSquad(newHero);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: show an individual hero
        get("/squads/:squadId/heroes/:heroId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idHeroToFind = Integer.parseInt(request.params("heroId"));
            Hero foundHero = Hero.findById(idHeroToFind);
            int idSquadToFind = Integer.parseInt(request.params("squadId"));
            Squad foundSquad = Squad.findSquadById(idSquadToFind);
            model.put("hero", foundHero);
            model.put("squad", foundSquad);
            model.put("squads", Squad.getAllSquads());
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: squad and hero members
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesInSquad = foundSquad.getAllHeroesInSquad();
            model.put("hero", allHeroesInSquad);
            model.put("squads", Squad.getAllSquads());
            return new ModelAndView(model, "squads-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display coming soon
        get("/wars",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = Squad.getAllSquads();
            model.put("squads", squads);
            return new ModelAndView(model, "wars.hbs");
        }, new HandlebarsTemplateEngine());

    }
}