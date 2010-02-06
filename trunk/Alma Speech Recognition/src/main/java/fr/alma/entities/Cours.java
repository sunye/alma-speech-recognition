package fr.alma.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Classe Cours.
 */
@Entity
public class Cours implements Serializable {

        /** Id. */
        @Id
        @GeneratedValue
        private Long id;

        /** Nom. */
        @Basic
        private String name;

        /** 
         * Constructeur par defaut.
         */
        public Cours() {}
        
        
        /**
         * Constructeur.
         * @param nom valeur de nom dans le Cours construit
         */
        public Cours(String nom) {
                this.name = nom;
        }
                        
        
        /**
         * Getter de l'attribut nom.
         * @return nom 
         */
        public String getNom() {
                return this.name;
        }
        
        /**
         * Setter de l'attribut nom.
         * @param nom le nom.
         */
        public void setNom(String nom) {
                this.name = nom;
        }
        
        /**
         * Methode getId.
		 * @return l'id.
         */        
        public Long getId(){
                return this.id;
        }
        
}
