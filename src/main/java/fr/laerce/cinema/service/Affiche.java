package fr.laerce.cinema.service;

import fr.laerce.cinema.dao.FilmsDao;
import fr.laerce.cinema.model.Film;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by fred on 03/02/2016.
 */
@WebServlet(name = "Affiche")
public class Affiche extends HttpServlet {
        public affiche(){
        // Récupération du nom de l'affiche dans le système de fichiers
        Integer id = Integer.parseInt( request.getParameter("id"));
        FilmsDao fd = new FilmsDao ();
        Film film = fd.getById(id);

        ServletContext cntx= getServletContext();
        // Chemin absolu de l'image
        String url= getInitParameter("url");
        String filename =url+film.getAfficheNom ();
        // Type mime associé à l'image d'après le nom de fichier
        String mime = cntx.getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setContentType(mime);
        File file = new File(filename);
        // Longeur de la réponse
        response.setContentLength((int)file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        // Copie le contenu du fichier vers le flux de sortie
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();

    }
}
