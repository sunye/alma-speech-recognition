package fr.alma.entities;

import javax.persistence.*;

@Entity
/**
 * Classe Cours.
 */
public class Cours {

        @Id
        @GeneratedValue        
        /** Id. */
        private Long id;

        @Basic
        /** Nom. */
        private String nom;

        /** 
         * Constructeur par defaut.
         */
        public Cours() {}
        
        
        /**
         * Constructeur.
         * @param nom valeur de nom dans le Cours construit
         */
        public Cours(String nom) {
                this.nom = nom;
        }
                        
        
        /**
         * Getter de l'attribut nom.
         * @return nom 
         */
        public String getNom() {
                return this.nom;
        }
        
        /**
         * Setter de l'attribut nom.
         * @param nom 
         */
        public void setNom(String nom) {
                this.nom = nom;
        }
        
        /**
         * Methode getId.
         */        
        public Long getId(){
                return this.id;
        }
        
}
