package models;

import etu1759.annotation.url;
import etu1759.framework.servlet.ModelView;

public class Emp{
    String nom;
    String prenom;

    @url(value="emp-nomComplet")
    public ModelView getNomComplet(){
        ModelView modelView = new ModelView();
        modelView.setUrl("nomComplet.jsp");
        String nomComplet = this.nom +" "+ this.prenom;
        return modelView;
    }
}