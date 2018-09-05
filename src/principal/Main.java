package principal;
import entidades.*;

/**
 * @author 20161014040004
 */
public class Main {
    
    public static void main(String[] args){
        
        Arvore avl = new Arvore();
        
        // Teste RES
//        avl.Inserir(32);
//        avl.Inserir(31);
//        avl.Inserir(35);
//        avl.Inserir(33);
//        avl.Inserir(36);
//        avl.Inserir(38);       
        //Teste RDS
//        avl.Inserir(50);
//        avl.Inserir(70);
//        avl.Inserir(20);
//        avl.Inserir(10);
//        avl.Inserir(30);
//        avl.Inserir(5);
        // Teste Dupla E
       avl.Inserir(50);
        avl.Inserir(20);
        avl.Inserir(80);
        avl.Inserir(70);
        avl.Inserir(90);
        avl.Inserir(65);
//        avl.Buscar(65);
//        avl.Remover(65);    
//        avl.Buscar(65);
        
    }
}
