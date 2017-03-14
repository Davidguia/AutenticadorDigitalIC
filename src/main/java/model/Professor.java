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
public class Professor extends Pessoa{
    private String siape;
    private String rga;
    private int tipo = 2;

    public int getTipo() {
        return tipo;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }
}
