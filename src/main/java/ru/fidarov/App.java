package ru.fidarov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.fidarov.model.Actor;
import ru.fidarov.model.Movie;
import ru.fidarov.model.Passport;
import ru.fidarov.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();



        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction(); //начало транзакции в бд

            Actor actor = session.get(Actor.class,2);
            System.out.println(actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0);//получаем фильм для удаления
            actor.getMovies().remove(0);//на списке фильмов актёра удаляем наш фильм
            movieToRemove.getActors().remove(actor);//удаляем на стороне фильма




//            Movie movie = new Movie("Resouveir Dogs",1992);
//            Actor actor = session.get(Actor.class,1);
//
//            movie.setActors(new ArrayList<>(Collections.singleton(actor)));
//            actor.getMovies().add(movie);
//
//            session.save(movie);

//            Movie movie = session.get(Movie.class,1);//позовём фильм под id=1
//            System.out.println(movie.getActors());

//            Movie movie = new Movie("Pulp function",1994);
//            Actor actor1 = new Actor("Harvei Keitel",81);
//            Actor actor2 = new Actor("Samueil Jackson", 72);
//
//            movie.setActors(new ArrayList<>(List.of(actor1,actor2)));
//
//            actor1.setMovies(new ArrayList<>(Collections.singleton(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singleton(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

            session.getTransaction().commit();//конец транзакции

        }finally {
            sessionFactory.close();
        }


    }
}
