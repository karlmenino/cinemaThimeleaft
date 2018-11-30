package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
