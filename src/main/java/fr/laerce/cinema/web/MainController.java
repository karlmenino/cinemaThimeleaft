package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.DataModel;
import fr.laerce.cinema.dao.FilmsDao;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

//pour dire a springboot qu'il est un controller web on écrit cette phrase
@Controller
public class MainController{
    //on peut utiliser cette méthode avec autowired et component dans le servlet DataModel
    @Autowired
    DataModel dataModel;

    //Pour mapper la servlet,ça remplace ce que l'on met dans web.xml.
    @GetMapping("/")
    public String main(Model M){
        //on ajoute a l'objet model la clef nom et karl
        M.addAttribute ("nom","karl" );
        M.addAttribute ("films",dataModel.getFilms());
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
        m.addAttribute ("film", dataModel.getById(idFilm));
        return"detail";
    }
    //on créer une methode affiche qui est mapper /affiche/id avec id la variable que tu recupere qui s'avere etre le nom
    //de l'affiche du film
//    @GetMapping("/affiche/{id}")
//    public void affiche (HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws IOException {
//
////merci patrick
//        /////////////////////////////////////////////////////
//        //on copie colle le code du prof et on adapte
//        // Chemin absolu de l'image
//        String url="C:\\Users\\CDI\\Pictures\\affiches\\";
//        //chemin relatif
//        String filename =url+id;
//        // Type mime associé à l'image d'après le nom de fichier
//        //on a besoin de request d'ou request et response dans les parametre de la methode
//        //on recupere a partir de la request le context du servlet et la methode getmine
//        String mime = request.getServletContext().getMimeType(filename);
//        //gestion du null
//        if (mime == null) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return;
//        }
//        //1on defini le type dans response
//        response.setContentType(mime);
//        //on créée lefichier voulu
//        File file = new File(filename);
//        //2 on definit la Longeur de la réponse
//        response.setContentLength((int)file.length());
//        //on converti le fichier en fileinputstream
//        FileInputStream in = new FileInputStream(file);
//        //on recupere l'objet outputstream de response qui est bien configurer grace a 1 et 2
//        OutputStream out = response.getOutputStream();
//
//        // Copie le contenu du fichier vers le flux de sortie(demander rien je comprend pas a partir de là)
//        byte[] buf = new byte[1024];
//        int count = 0;
//        while ((count = in.read(buf)) >= 0) {
//            out.write(buf, 0, count);
//        }
//        out.close();
//        in.close();
//    }
//    on utilise properties et on recupere la valeur de l'url
    @Value( "${url}" )
    private String url;
    //deuxieme methode pour affichezr  image
    @GetMapping("/affiche/{id}")
    public ResponseEntity<byte[]> getImageAsResponseEntity (HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename=url+id;
            File i = new File (filename);
            FileInputStream in = new FileInputStream(i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache ().getHeaderValue ());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
       return null;
 }
    @Value( "${url2}" )
    private String url2;
    //deuxieme methode pour affichezr  image
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageAsResponseEntity2 (HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename=url2+id;
            File i = new File (filename);
            FileInputStream in = new FileInputStream(i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache().getHeaderValue());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }
    @GetMapping("/acteur/{id}")
    //on recupere id grace à pathvariable
    public String acteur(Model m, @PathVariable("id") String id){
        m.addAttribute ("actor", dataModel.getByAf(id));
        return"acteur";}
}
