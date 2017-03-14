/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author 004820350
 */
public class Documentos implements Serializable{
    
    private String nome ;
    private int id;
    private int id_pessoa;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Documentos(int id, int id_pessoa, String nome, String url){
        this.id = id;
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.url = url;
    }
    
    public Documentos (){
        this.nome = "";
        this.url = "";
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Documentos other = (Documentos) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
