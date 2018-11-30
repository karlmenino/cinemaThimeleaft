package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

//pour dire a springboot qu'il est un controller web on écrit cette phrase
@Controller
public class MainController {
    ////////////////////////////////////////////////////////////////////////////
    //on créer un objet filmDao que l'on traitera dans le template afin d'afficher les films
    //FilmsDao filmsDao= new FilmsDao ();
    ////////////////////////////////////////////////////////////////////////////
    //on peut utiliser cette méthode avec autowired et component dans le servlet filmDao
    @Autowired
    FilmsDao filmsDao;

    //Pour mapper la servlet,ça remplace ce que l'on met dans web.xml.
    @GetMapping("/")
    public String main(Model M){
        //on ajoute a l'objet model la clef nom et karl
        M.addAttribute ("nom","karl" );
        M.addAttribute ("films",filmsDao.getLesFilms());
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }
    ///////////////////////////////////////////////////////////////
    //on map vers film donc dans l'url on ecrira /film?id=1
//    @GetMapping("/film")
    //on recupere le parametre id de l'url grace à raquestparam
//    public String detail(Model m, @RequestParam("id") String id){
    ////////////////////////////////////////////////////////////////
    @GetMapping("/film/{id}")
    //on recupere id grace à pathvariable
    public String detail(Model m, @PathVariable("id") String id){
        Integer idFilm = Integer.parseInt (id);
        m.addAttribute ("film", filmsDao.getById (idFilm));
        return"detail";
    }
    @GetMapping("/affiche/{id}")
    public String affiche(Model M,@PathVariable("id") String id){

        return "affiche";
    }


}
