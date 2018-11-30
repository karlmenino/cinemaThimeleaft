package fr.laerce.cinema.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//pour dire a springboot qu'il est un controller web on écrit cette phrase
@Controller
public class MainController {

    //Pour mapper la servlet,ça remplace ce que l'on met dans web.xml.
    @GetMapping("/")
    public String main(Model M){
        //on ajoute a l'objet model la clef nom et karl
        M.addAttribute ("nom","karl" );
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }

}
