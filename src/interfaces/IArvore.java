/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author felipe
 */
public interface IArvore {
    
    public void Inserir(int valor);
    
    public void Remover(int valor);
    
    public void Buscar(int valor);
    
    public void Imprimir();
    
    public void ImprimirEmOrdem();
    
    public void Altura();
}
