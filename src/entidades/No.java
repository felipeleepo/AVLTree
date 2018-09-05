/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author felipe
 */
public class No {
    int e;
    No pai, Dir, Esq;
    int FB;
    
    public No (int s){
        e = s;
        Dir = null;
        Esq = null;
        FB = 0;
    }
    public No (int s, No pai){
        this.pai = pai;
        e = s;
        Dir = null;
        Esq = null;
        FB = 0;
    } 
}
