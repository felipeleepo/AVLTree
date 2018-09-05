package entidades;
import interfaces.IArvore;

public class Arvore implements IArvore{
    ArvoreAVL a;
    
    public Arvore(){
        a = new ArvoreAVL();
    }
    
    @Override
    public void Inserir(int valor) {
        System.out.print("INSERÇÃO: ");
        a.insert(a.root, valor, a.root, true);
        Imprimir();
        System.out.println("______________________________________");
        
    }

    @Override
    public void Remover(int valor) {
        a.remove(valor, a.root);
        Imprimir();
    }

    @Override
    public void Buscar(int valor) {
        System.out.println("BUSCA: " + a.search(a.root, valor, true));
        System.out.println("______________________________________");
    }

    @Override
    public void Imprimir() {
        a.printTree(a.root, true, " ");
    }

    @Override
    public void ImprimirEmOrdem() {
        a.printInOrder(a.root);
    }

    @Override
    public void Altura() {
        System.out.println("Altura da árvore = " + a.height(a.root));
    }
    
}
