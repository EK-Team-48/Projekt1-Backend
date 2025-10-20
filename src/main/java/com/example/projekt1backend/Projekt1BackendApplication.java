package com.example.projekt1backend;


import com.example.projekt1backend.seat.model.Seat;
import com.example.projekt1backend.seat.repository.SeatRepository;
import com.example.projekt1backend.customer.entity.Customer;
import com.example.projekt1backend.customer.repository.CustomerRepository;
import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.entity.EmployeeType;
import com.example.projekt1backend.employee.repository.EmployeeRespository;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.repository.ReservationRepository;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.repository.ScreeningRepository;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.repository.TheaterRepository;
import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.ageLimit.repository.AgeLimitRepository;
import com.example.projekt1backend.genre.entity.Genre;
import com.example.projekt1backend.genre.repository.GenreRepository;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.repository.MovieRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Projekt1BackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(Projekt1BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner loadTestData(MovieRepository movieRepo,
                                   GenreRepository genreRepo,
                                   AgeLimitRepository ageLimitRepo,
                                   TheaterRepository theaterRepo,
                                   SeatRepository seatRepo,
                                   ScreeningRepository screeningRepo,
                                   CustomerRepository customerRepo,
                                   ReservationRepository reservationRepo,
                                   EmployeeRespository employeeRepo) {

        return args -> {

            // --- GENRES ---
            Genre action = new Genre();        action.setGenre("Action");        genreRepo.save(action);
            Genre adventure = new Genre();     adventure.setGenre("Adventure");  genreRepo.save(adventure);
            Genre animation = new Genre();     animation.setGenre("Animation");  genreRepo.save(animation);
            Genre biography = new Genre();     biography.setGenre("Biography");  genreRepo.save(biography);
            Genre comedy = new Genre();        comedy.setGenre("Comedy");        genreRepo.save(comedy);
            Genre crime = new Genre();         crime.setGenre("Crime");          genreRepo.save(crime);
            Genre documentary = new Genre();   documentary.setGenre("Documentary"); genreRepo.save(documentary);
            Genre drama = new Genre();         drama.setGenre("Drama");          genreRepo.save(drama);
            Genre family = new Genre();        family.setGenre("Family");        genreRepo.save(family);
            Genre fantasy = new Genre();       fantasy.setGenre("Fantasy");      genreRepo.save(fantasy);
            Genre filmNoir = new Genre();      filmNoir.setGenre("Film-Noir");   genreRepo.save(filmNoir);
            Genre history = new Genre();       history.setGenre("History");      genreRepo.save(history);
            Genre horror = new Genre();        horror.setGenre("Horror");        genreRepo.save(horror);
            Genre music = new Genre();         music.setGenre("Music");          genreRepo.save(music);
            Genre musical = new Genre();       musical.setGenre("Musical");      genreRepo.save(musical);
            Genre mystery = new Genre();       mystery.setGenre("Mystery");      genreRepo.save(mystery);
            Genre romance = new Genre();       romance.setGenre("Romance");      genreRepo.save(romance);
            Genre sciFi = new Genre();         sciFi.setGenre("Sci-Fi");         genreRepo.save(sciFi);
            Genre sport = new Genre();         sport.setGenre("Sport");          genreRepo.save(sport);
            Genre thriller = new Genre();      thriller.setGenre("Thriller");    genreRepo.save(thriller);
            Genre war = new Genre();           war.setGenre("War");              genreRepo.save(war);
            Genre western = new Genre();       western.setGenre("Western");      genreRepo.save(western);

            // --- AGE LIMITS ---
            AgeLimit pg7 = new AgeLimit(); pg7.setAgeRating(7);
            AgeLimit pg13 = new AgeLimit(); pg13.setAgeRating(13);
            AgeLimit pg18 = new AgeLimit(); pg18.setAgeRating(18);
            ageLimitRepo.saveAll(Set.of(pg7, pg13, pg18));


            // --- MOVIES ---
            Movie dieHard = new Movie();
            dieHard.setMovieTitle("Die Hard");
            dieHard.setMovieImg("https://media.posterlounge.com/img/products/710000/705263/705263_poster.jpg");
            dieHard.setDescription("Classic 80s action with Bruce Willis.");
            dieHard.setDuration(120);
            dieHard.setTrailerLink("https://www.youtube.com/watch?v=gYWvwkXreaI");
            dieHard.setAgeLimit(pg18);
            dieHard.getGenres().add(action);
            movieRepo.save(dieHard);

            Movie forrest = new Movie();
            forrest.setMovieTitle("Forrest Gump");
            forrest.setMovieImg("https://storage.googleapis.com/pod_public/750/266241.jpg");
            forrest.setDescription("A simple man with an extraordinary life.");
            forrest.setDuration(140);
            forrest.setTrailerLink("https://www.youtube.com/watch?v=bLvqoHBptjg");
            forrest.setAgeLimit(pg13);
            forrest.getGenres().add(drama);
            forrest.getGenres().add(comedy);
            movieRepo.save(forrest);

            Movie darkKnight = new Movie();
            darkKnight.setMovieTitle("The Dark Knight");
            darkKnight.setMovieImg("https://static.posters.cz/image/1300/198201.jpg");
            darkKnight.setDescription("Batman faces the Joker in a gritty crime saga.");
            darkKnight.setDuration(152);
            darkKnight.setTrailerLink("https://www.youtube.com/watch?v=EXeTwQWrcwY");
            darkKnight.setAgeLimit(pg13);
            darkKnight.getGenres().add(action);
            darkKnight.getGenres().add(crime);
            movieRepo.save(darkKnight);

            Movie inception = new Movie();
            inception.setMovieTitle("Inception");
            inception.setMovieImg("https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg");
            inception.setDescription("A team dives into dreams to plant an idea.");
            inception.setDuration(148);
            inception.setTrailerLink("https://www.youtube.com/watch?v=YoHD9XEInc0&t=1s");
            inception.setAgeLimit(pg13);
            inception.getGenres().add(sciFi);
            inception.getGenres().add(action);
            movieRepo.save(inception);

            Movie godfather = new Movie();
            godfather.setMovieTitle("The Godfather");
            godfather.setMovieImg("https://m.media-amazon.com/images/M/MV5BNGEwYjgwOGQtYjg5ZS00Njc1LTk2ZGEtM2QwZWQ2NjdhZTE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");
            godfather.setDescription("Epic tale of a crime family and its legacy.");
            godfather.setDuration(175);
            godfather.setTrailerLink("https://www.youtube.com/watch?v=UaVTIH8mujA");
            godfather.setAgeLimit(pg18);
            godfather.getGenres().add(crime);
            godfather.getGenres().add(drama);
            movieRepo.save(godfather);

            Movie findingNemo = new Movie();
            findingNemo.setMovieTitle("Finding Nemo");
            findingNemo.setMovieImg("https://m.media-amazon.com/images/I/71-bSJo53DL._AC_UF894,1000_QL80_.jpg");
            findingNemo.setDescription("A timid clownfish braves the ocean to find his son.");
            findingNemo.setDuration(100);
            findingNemo.setTrailerLink("https://www.youtube.com/watch?v=9oQ628Seb9w");
            findingNemo.setAgeLimit(pg7);
            findingNemo.getGenres().add(animation);
            findingNemo.getGenres().add(family);
            movieRepo.save(findingNemo);

            Movie spiritedAway = new Movie();
            spiritedAway.setMovieTitle("Spirited Away");
            spiritedAway.setMovieImg("https://m.media-amazon.com/images/M/MV5BNTEyNmEwOWUtYzkyOC00ZTQ4LTllZmUtMjk0Y2YwOGUzYjRiXkEyXkFqcGc@._V1_.jpg");
            spiritedAway.setDescription("A girl enters a spirit world to save her parents.");
            spiritedAway.setDuration(125);
            spiritedAway.setTrailerLink("https://www.youtube.com/watch?v=ByXuk9QqQkk");
            spiritedAway.setAgeLimit(pg7);
            spiritedAway.getGenres().add(animation);
            spiritedAway.getGenres().add(fantasy);
            movieRepo.save(spiritedAway);

            Movie shawshank = new Movie();
            shawshank.setMovieTitle("The Shawshank Redemption");
            shawshank.setMovieImg("https://media.posterlounge.com/img/products/710000/706559/706559_poster.jpg");
            shawshank.setDescription("Hope sustains two prisoners through decades.");
            shawshank.setDuration(142);
            shawshank.setTrailerLink("https://www.youtube.com/watch?v=PLl99DlL6b4");
            shawshank.setAgeLimit(pg13);
            shawshank.getGenres().add(drama);
            movieRepo.save(shawshank);

            Movie parasite = new Movie();
            parasite.setMovieTitle("Parasite");
            parasite.setMovieImg("https://m.media-amazon.com/images/I/91KArYP03YL._AC_UF894,1000_QL80_.jpg");
            parasite.setDescription("Two families collide in a sharp social thriller.");
            parasite.setDuration(132);
            parasite.setTrailerLink("https://www.youtube.com/watch?v=SEUXfv87Wpk");
            parasite.setAgeLimit(pg18);
            parasite.getGenres().add(thriller);
            parasite.getGenres().add(drama);
            movieRepo.save(parasite);

            Movie laLaLand = new Movie();
            laLaLand.setMovieTitle("La La Land");
            laLaLand.setMovieImg("https://m.media-amazon.com/images/I/71-u32-oOaL.jpg");
            laLaLand.setDescription("Love and ambition in modern-day Los Angeles.");
            laLaLand.setDuration(128);
            laLaLand.setTrailerLink("https://www.youtube.com/watch?v=0pdqf4P9MB8");
            laLaLand.setAgeLimit(pg13);
            laLaLand.getGenres().add(romance);
            laLaLand.getGenres().add(musical);
            movieRepo.save(laLaLand);

            Movie matrix = new Movie();
            matrix.setMovieTitle("The Matrix");
            matrix.setMovieImg("https://m.media-amazon.com/images/I/71PfZFFz9yL._AC_UF894,1000_QL80_.jpg");
            matrix.setDescription("A hacker discovers reality is a simulation.");
            matrix.setDuration(136);
            matrix.setTrailerLink("https://www.youtube.com/watch?v=m8e-FF8MsqU");
            matrix.setAgeLimit(pg13);
            matrix.getGenres().add(sciFi);
            matrix.getGenres().add(action);
            movieRepo.save(matrix);

            Movie jurassicPark = new Movie();
            jurassicPark.setMovieTitle("Jurassic Park");
            jurassicPark.setMovieImg("https://filmartgallery.com/cdn/shop/products/Jurassic-Park-Vintage-Movie-Poster-Original.jpg?v=1738906074");
            jurassicPark.setDescription("Dinosaurs roam a theme park with deadly results.");
            jurassicPark.setDuration(127);
            jurassicPark.setTrailerLink("https://www.youtube.com/watch?v=QWBKEmWWL38");
            jurassicPark.setAgeLimit(pg13);
            jurassicPark.getGenres().add(adventure);
            jurassicPark.getGenres().add(sciFi);
            movieRepo.save(jurassicPark);

            Movie lionKing = new Movie();
            lionKing.setMovieTitle("The Lion King");
            lionKing.setMovieImg("https://i.ebayimg.com/images/g/Oi4AAOxy2CZTWozq/s-l1200.jpg");
            lionKing.setDescription("A young lion prince finds his place in the circle of life.");
            lionKing.setDuration(88);
            lionKing.setTrailerLink("https://www.youtube.com/watch?v=lFzVJEksoDY");
            lionKing.setAgeLimit(pg7);
            lionKing.getGenres().add(animation);
            lionKing.getGenres().add(family);
            movieRepo.save(lionKing);

            Movie casablanca = new Movie();
            casablanca.setMovieTitle("Casablanca");
            casablanca.setMovieImg("https://m.media-amazon.com/images/I/71OusuDiYGL._AC_UF894,1000_QL80_.jpg");
            casablanca.setDescription("Love and sacrifice during World War II.");
            casablanca.setDuration(102);
            casablanca.setTrailerLink("https://www.youtube.com/watch?v=MF7JH_54d8c");
            casablanca.setAgeLimit(pg7);
            casablanca.getGenres().add(romance);
            casablanca.getGenres().add(drama);
            movieRepo.save(casablanca);

            Movie furyRoad = new Movie();
            furyRoad.setMovieTitle("Mad Max: Fury Road");
            furyRoad.setMovieImg("https://m.media-amazon.com/images/I/A1Y9Cqo1FmL.jpg");
            furyRoad.setDescription("A high-octane chase across a wasteland.");
            furyRoad.setDuration(120);
            furyRoad.setTrailerLink("https://www.youtube.com/watch?v=hEJnMQG9ev8");
            furyRoad.setAgeLimit(pg18);
            furyRoad.getGenres().add(action);
            furyRoad.getGenres().add(adventure);
            movieRepo.save(furyRoad);

            Movie getOut = new Movie();
            getOut.setMovieTitle("Get Out");
            getOut.setMovieImg("https://m.media-amazon.com/images/I/51xp3ybXOiL._AC_UF894,1000_QL80_.jpg");
            getOut.setDescription("A tense visit turns into a surreal nightmare.");
            getOut.setDuration(104);
            getOut.setTrailerLink("https://www.youtube.com/watch?v=DzfpyUB60YY");
            getOut.setAgeLimit(pg18);
            getOut.getGenres().add(horror);
            getOut.getGenres().add(mystery);
            movieRepo.save(getOut);

            Movie socialNetwork = new Movie();
            socialNetwork.setMovieTitle("The Social Network");
            socialNetwork.setMovieImg("https://image.tmdb.org/t/p/original/n0ybibhJtQ5icDqTp8eRytcIHJx.jpg");
            socialNetwork.setDescription("The contentious rise of a tech giant.");
            socialNetwork.setDuration(120);
            socialNetwork.setTrailerLink("https://www.youtube.com/watch?v=lB95KLmpLR4");
            socialNetwork.setAgeLimit(pg13);
            socialNetwork.getGenres().add(drama);
            socialNetwork.getGenres().add(biography);
            movieRepo.save(socialNetwork);

            Movie nineteenSeventeen = new Movie();
            nineteenSeventeen.setMovieTitle("1917");
            nineteenSeventeen.setMovieImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZrU-t7pjIDooqpf1A57uqniKDPmslDpLCOQ&s");
            nineteenSeventeen.setDescription("Two soldiers race to deliver a lifesaving message.");
            nineteenSeventeen.setDuration(119);
            nineteenSeventeen.setTrailerLink("https://www.youtube.com/watch?v=YqNYrYUiMfg");
            nineteenSeventeen.setAgeLimit(pg18);
            nineteenSeventeen.getGenres().add(war);
            nineteenSeventeen.getGenres().add(drama);
            movieRepo.save(nineteenSeventeen);

            Movie princessBride = new Movie();
            princessBride.setMovieTitle("The Princess Bride");
            princessBride.setMovieImg("https://m.media-amazon.com/images/I/61E71gCC53L._AC_UF894,1000_QL80_.jpg");
            princessBride.setDescription("A fairy-tale adventure with true love and pirates.");
            princessBride.setDuration(98);
            princessBride.setTrailerLink("https://www.youtube.com/watch?v=O3CIXEAjcc8");
            princessBride.setAgeLimit(pg7);
            princessBride.getGenres().add(adventure);
            princessBride.getGenres().add(comedy);
            princessBride.getGenres().add(romance);
            movieRepo.save(princessBride);

            Movie interstellar = new Movie();
            interstellar.setMovieTitle("Interstellar");
            interstellar.setMovieImg("https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");
            interstellar.setDescription("Explorers journey through a wormhole to save humanity.");
            interstellar.setDuration(169);
            interstellar.setTrailerLink("https://www.youtube.com/watch?v=zSWdZVtXT7E&t=2s");
            interstellar.setAgeLimit(pg13);
            interstellar.getGenres().add(sciFi);
            interstellar.getGenres().add(drama);
            movieRepo.save(interstellar);

            Movie whiplash = new Movie();
            whiplash.setMovieTitle("Whiplash");
            whiplash.setMovieImg("https://i.etsystatic.com/36067604/r/il/4355d2/4230665308/il_fullxfull.4230665308_r13v.jpg");
            whiplash.setDescription("A drummer pushes himself under a ruthless mentor.");
            whiplash.setDuration(107);
            whiplash.setTrailerLink("https://www.youtube.com/watch?v=7d_jQycdQGo");
            whiplash.setAgeLimit(pg13);
            whiplash.getGenres().add(drama);
            whiplash.getGenres().add(music);
            movieRepo.save(whiplash);

            Movie bigLebowski = new Movie();
            bigLebowski.setMovieTitle("The Big Lebowski");
            bigLebowski.setMovieImg("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRhGcN6t_iLHQWRw0Le9VYQ-crzAbrnLBkMDtM0-NOOtCh1jpSAUhOwyJRRUDjDxCtzTMwSljKIYIyehsJ_cEczCz3dADoNU8rRBLU5HQuvpldYgUVQU905");
            bigLebowski.setDescription("The Dude gets tangled in a bizarre kidnapping.");
            bigLebowski.setDuration(117);
            bigLebowski.setTrailerLink("https://www.youtube.com/watch?v=cd-go0oBF4Y");
            bigLebowski.setAgeLimit(pg18);
            bigLebowski.getGenres().add(comedy);
            bigLebowski.getGenres().add(crime);
            movieRepo.save(bigLebowski);

            Movie grandBudapest = new Movie();
            grandBudapest.setMovieTitle("The Grand Budapest Hotel");
            grandBudapest.setMovieImg("https://m.media-amazon.com/images/I/713kiC-8JhL.jpg");
            grandBudapest.setDescription("A concierge and lobby boy in a caper across Europe.");
            grandBudapest.setDuration(100);
            grandBudapest.setTrailerLink("https://www.youtube.com/watch?v=1Fg5iWmQjwk");
            grandBudapest.setAgeLimit(pg13);
            grandBudapest.getGenres().add(comedy);
            grandBudapest.getGenres().add(crime);
            movieRepo.save(grandBudapest);

            Movie silenceLambs = new Movie();
            silenceLambs.setMovieTitle("The Silence of the Lambs");
            silenceLambs.setMovieImg("https://m.media-amazon.com/images/I/81SVDO6WcrL._AC_UF894,1000_QL80_.jpg");
            silenceLambs.setDescription("An FBI trainee seeks a killer with a cannibal’s help.");
            silenceLambs.setDuration(118);
            silenceLambs.setTrailerLink("https://www.youtube.com/watch?v=6iB21hsprAQ");
            silenceLambs.setAgeLimit(pg18);
            silenceLambs.getGenres().add(thriller);
            silenceLambs.getGenres().add(crime);
            movieRepo.save(silenceLambs);

            Movie seven = new Movie();
            seven.setMovieTitle("Se7en");
            seven.setMovieImg("https://m.media-amazon.com/images/I/71ivyTtPwoL._AC_UF894,1000_QL80_.jpg");
            seven.setDescription("Detectives hunt a killer using the seven deadly sins.");
            seven.setDuration(127);
            seven.setTrailerLink("https://www.youtube.com/watch?v=KPOuJGkpblk");
            seven.setAgeLimit(pg18);
            seven.getGenres().add(crime);
            seven.getGenres().add(mystery);
            seven.getGenres().add(thriller);
            movieRepo.save(seven);

            Movie rocky = new Movie();
            rocky.setMovieTitle("Rocky");
            rocky.setMovieImg("https://media.posterlounge.com/img/products/610000/605847/605847_poster.jpg");
            rocky.setDescription("An underdog boxer gets a shot at the title.");
            rocky.setDuration(120);
            rocky.setTrailerLink("https://www.youtube.com/watch?v=-Hk-LYcavrw");
            rocky.setAgeLimit(pg13);
            rocky.getGenres().add(sport);
            rocky.getGenres().add(drama);
            movieRepo.save(rocky);

            Movie goodBadUgly = new Movie();
            goodBadUgly.setMovieTitle("The Good, the Bad and the Ugly");
            goodBadUgly.setMovieImg("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQHI13QRtu5jYlSfoWk2LzW_5CfaAJOzfnkrbZM-bnEo4QhygGQ_KPWchgO1Lz90mxrPJHoK6IB9_A1vPiT7SPYoMR8hL66FopBSWExCQuHj3mwXRDuLNrF6A");
            goodBadUgly.setDescription("Three gunslingers chase buried gold in the Old West.");
            goodBadUgly.setDuration(161);
            goodBadUgly.setTrailerLink("https://www.youtube.com/watch?v=IFNUGzCOQoI");
            goodBadUgly.setAgeLimit(pg13);
            goodBadUgly.getGenres().add(western);
            goodBadUgly.getGenres().add(adventure);
            movieRepo.save(goodBadUgly);

            Movie noCountry = new Movie();
            noCountry.setMovieTitle("No Country for Old Men");
            noCountry.setMovieImg("https://m.media-amazon.com/images/I/51CyRJN-iWL._AC_UF894,1000_QL80_.jpg");
            noCountry.setDescription("A hunter, a hitman, and a sheriff cross paths over stolen cash.");
            noCountry.setDuration(122);
            noCountry.setTrailerLink("https://www.youtube.com/watch?v=38A__WT3-o0");
            noCountry.setAgeLimit(pg18);
            noCountry.getGenres().add(crime);
            noCountry.getGenres().add(thriller);
            movieRepo.save(noCountry);

            Movie exorcist = new Movie();
            exorcist.setMovieTitle("The Exorcist");
            exorcist.setMovieImg("https://storage.googleapis.com/pod_public/1300/262784.jpg");
            exorcist.setDescription("A possessed girl and a desperate fight against evil.");
            exorcist.setDuration(122);
            exorcist.setTrailerLink("https://www.youtube.com/watch?v=BU2eYAO31Cc");
            exorcist.setAgeLimit(pg18);
            exorcist.getGenres().add(horror);
            movieRepo.save(exorcist);

            Movie amelie = new Movie();
            amelie.setMovieTitle("Amélie");
            amelie.setMovieImg("https://m.media-amazon.com/images/M/MV5BOTNmYzY0MWQtZGZmNy00Y2Y4LWFmMDQtMTZjYTdiYzEwZGQ2XkEyXkFqcGc@._V1_.jpg");
            amelie.setDescription("A whimsical Parisian helps others find joy and love.");
            amelie.setDuration(122);
            amelie.setTrailerLink("https://www.youtube.com/watch?v=Py7cDXQae2U");
            amelie.setAgeLimit(pg13);
            amelie.getGenres().add(romance);
            amelie.getGenres().add(comedy);
            movieRepo.save(amelie);

            Movie beautifulMind = new Movie();
            beautifulMind.setMovieTitle("A Beautiful Mind");
            beautifulMind.setMovieImg("https://m.media-amazon.com/images/I/71qk53VbCnL._AC_UF894,1000_QL80_.jpg");
            beautifulMind.setDescription("A brilliant mathematician battles inner demons.");
            beautifulMind.setDuration(135);
            beautifulMind.setTrailerLink("https://www.youtube.com/watch?v=9wZM7CQY130");
            beautifulMind.setAgeLimit(pg13);
            beautifulMind.getGenres().add(biography);
            beautifulMind.getGenres().add(drama);
            movieRepo.save(beautifulMind);

            Movie gladiator = new Movie();
            gladiator.setMovieTitle("Gladiator");
            gladiator.setMovieImg("https://m.media-amazon.com/images/I/71sj8Yt20qL._AC_UF894,1000_QL80_.jpg");
            gladiator.setDescription("A betrayed general seeks justice in the arena.");
            gladiator.setDuration(155);
            gladiator.setTrailerLink("https://www.youtube.com/watch?v=P5ieIbInFpg");
            gladiator.setAgeLimit(pg18);
            gladiator.getGenres().add(action);
            gladiator.getGenres().add(history);
            movieRepo.save(gladiator);

            Movie backToTheFuture = new Movie();
            backToTheFuture.setMovieTitle("Back to the Future");
            backToTheFuture.setMovieImg("https://static.posters.cz/image/750/2795.jpg");
            backToTheFuture.setDescription("A teen travels through time to fix his family history.");
            backToTheFuture.setDuration(116);
            backToTheFuture.setTrailerLink("https://www.youtube.com/watch?v=qvsgGtivCgs");
            backToTheFuture.setAgeLimit(pg7);
            backToTheFuture.getGenres().add(adventure);
            backToTheFuture.getGenres().add(sciFi);
            movieRepo.save(backToTheFuture);

            Movie marchOfPenguins = new Movie();
            marchOfPenguins.setMovieTitle("March of the Penguins");
            marchOfPenguins.setMovieImg("https://cdn11.bigcommerce.com/s-ydriczk/images/stencil/1500x1500/products/82580/92423/MARCH-OF-THE-PENGUINS-SINGLE-SIDED-Regular-2005-ORIGINAL-CINEMA-POSTER__48439.1549383017.jpg?c=2");
            marchOfPenguins.setDescription("Emperor penguins endure an epic Antarctic journey.");
            marchOfPenguins.setDuration(80);
            marchOfPenguins.setTrailerLink("https://www.youtube.com/watch?v=ohL8rF_jluA");
            marchOfPenguins.setAgeLimit(pg7);
            marchOfPenguins.getGenres().add(documentary);
            marchOfPenguins.getGenres().add(family);
            movieRepo.save(marchOfPenguins);

            Movie freeSolo = new Movie();
            freeSolo.setMovieTitle("Free Solo");
            freeSolo.setMovieImg("https://m.media-amazon.com/images/I/81Jw991PP6L.jpg");
            freeSolo.setDescription("A climber attempts El Capitan without ropes.");
            freeSolo.setDuration(100);
            freeSolo.setTrailerLink("https://www.youtube.com/watch?v=urRVZ4SW7WU");
            freeSolo.setAgeLimit(pg13);
            freeSolo.getGenres().add(documentary);
            freeSolo.getGenres().add(sport);
            movieRepo.save(freeSolo);

            // --- THEATERS ---
            Theater theater1 = new Theater(); theater1.setTheaterName("Main Hall");
            Theater theater2 = new Theater(); theater2.setTheaterName("VIP Lounge");
            theaterRepo.saveAll(Set.of(theater1, theater2));

            // --- SEATS ---
            for (int row = 1; row <= 5; row++) {
                for (int num = 1; num <= 5; num++) {
                    seatRepo.save(new Seat(num, row, theater1));
                }
            }
            for (int row = 1; row <= 3; row++) {
                for (int num = 1; num <= 4; num++) {
                    seatRepo.save(new Seat(num, row, theater2));
                }
            }

            // --- SCREENINGS ---
            Screening screening1 = new Screening();
            screening1.setMovie(dieHard);
            screening1.setTheater(theater1);
            screening1.setScreeningDate(LocalDate.of(2025, 10, 29));
            screening1.setStartTime(2000);
            screening1.setPrice(95.0);
            screeningRepo.save(screening1);

            for (int b = 700; b <= 2300; b += 400) {
                LocalDate[] dates = {
                        LocalDate.now(),
                        LocalDate.of(2025, 10, 21),
                        LocalDate.of(2025, 10, 22),
                        LocalDate.of(2025, 10, 23),
                        LocalDate.of(2025, 10, 24)
//                        LocalDate.of(2025, 10, 25)
//                        LocalDate.of(2025, 10, 26),
//                        LocalDate.of(2025, 10, 27),
//                        LocalDate.of(2025, 10, 28)


                };

                for (LocalDate date : dates) {
                    Screening screening = new Screening();
                    screening.setMovie(dieHard);
                    screening.setTheater(theater1);
                    screening.setStartTime(b);
                    screening.setPrice(150.0);
                    screening.setScreeningDate(date);
                    screeningRepo.save(screening);
                }
            }

            Screening screening2 = new Screening();
            screening2.setMovie(forrest);
            screening2.setTheater(theater2);
            screening2.setScreeningDate(LocalDate.of(2025, 10, 20));
            screening2.setStartTime(1800);
            screening2.setPrice(120.0);
            screeningRepo.save(screening2);

            // --- CUSTOMERS ---
            var alice = new Customer();
            alice.setFirstName("Alice");
            alice.setLastName("Andersen");
            alice.setAge(Date.valueOf(LocalDate.of(1995, 4, 12)));
            alice.setNumber("12345678");
            customerRepo.save(alice);

            var bob = new Customer();
            bob.setFirstName("Bob");
            bob.setLastName("Berg");
            bob.setAge(Date.valueOf(LocalDate.of(2001, 11, 23)));
            bob.setNumber("87654321");
            customerRepo.save(bob);

            // --- RESERVATIONS ---
            var allSeats = seatRepo.findAll();
            var reservation1 = new Reservation();
            reservation1.setCustomer(alice);
            reservation1.setScreening(screening1);
            reservation1.setSeats(allSeats.subList(0, 3)); // Seats 1–3
            reservation1.setUserReservationId("12345");
            reservationRepo.save(reservation1);

            var reservation2 = new Reservation();
            reservation2.setCustomer(bob);
            reservation2.setScreening(screening2);
            reservation2.setSeats(allSeats.subList(3, 5)); // Seats 4–5
            reservation2.setUserReservationId("1234");
            reservationRepo.save(reservation2);

            //Employees
            var emp1 = new Employee();
            emp1.setEmployeeName("Zahaa");
            emp1.setEmployeePassword("1234");
            emp1.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
            emp1.setEmployeeType(EmployeeType.ADMIN);
            employeeRepo.save(emp1);

            var emp2 = new Employee();
            emp2.setEmployeeName("Kasper");
            emp2.setEmployeePassword("1234");
            emp2.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
            emp2.setEmployeeType(EmployeeType.ADMIN);
            employeeRepo.save(emp2);


            System.out.println("✅ Testdata indlæst med reservations og seats!");
        };
    }
}
