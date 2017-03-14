/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 004820350
 */
public class Aluno extends Pessoa{
    private String rga;
    private int tipo = 1;

    public int getTipo() {
        return tipo;
    }
    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }
}
