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
public class ArvoreAVL {

  int size;
  No root;

  public ArvoreAVL() {
    this.root = null;
    this.size = 0;
  }

  public No insert(No n, int v, No higher, boolean dir) {

    if (size == 0) {
      root = new No(v, null);
      size++;
      System.out.println(root.e);
      return root;
    } else {
      if (n == null) {
        n = new No(v, higher);
        
        if (dir)
          higher.Dir = n;
        else
          higher.Esq = n;
        
        System.out.println(n.e);
        
        updateNew(higher, dir, n);
        size++;
      } else if (v > n.e)
        insert(n.Dir, v, n, true);
      else
        insert(n.Esq, v, n, false);
    }
    return n;
  }

  public String search(No n, int v, boolean externa) {

    if (n == null) {
      if (externa) {
        if (size == 0)
          return "Árvore Vazia";
        else
          return "Valor " + v + " não encontrado";
      } else {
        return "";
      }
    } else if (n.e == v)
      if (externa)
        return "Valor encontrato " + n.e;
      else {
        update(n.pai);
        return "";
      }
    else if (v > n.e)
      return "" + search(n.Dir, v, externa);
    else
      return "" + search(n.Esq, v, externa);
  }

  public No remove(int o, No n) {

    if (size == 0) {
      System.out.println("Arvore Vazia");
      return null;
    }

    if (n.e == o) {
      System.out.println("Valor removido " + n.e);
      if (n.Dir == n.Esq)
        return null;
      else if (n.Esq == null)
        return n.Dir;
      else if (n.Dir == null)
        return n.Esq;
      else {
        No d, e, aux = null;
        d = n.Dir;
        e = n.Dir;
        while (e.Esq != null) {
          aux = e;
          e = e.Esq;
        }
        n.e = e.e;
        e = null;
        aux.Esq = null;
        size--;
      }
    } else if (n.e > o)
      n.Esq = remove(o, n.Esq);
    else
      n.Dir = remove(o, n.Dir);
    return n;
  }

  public void printInOrder(No n) {
    if (n != null) {
      printInOrder(n.Esq);
      System.out.println(n.e + " - FB = " + n.FB);
      printInOrder(n.Dir);
    }
  }

  public int height(No n) {
    if (n == null)
      return -1;
    int left = height(n.Esq);
    int right = height(n.Dir);
    if (left > right)
      return left + 1;
    else
      return right + 1;
  }

  public void update(No pai) {
    if (pai != null) {
      pai.FB = updateNode(pai);
      int fb = pai.FB;
      // System.out.println(pai.e + " FB = " + pai.FB);
      if (fb == 2) {
        System.out.println("Encontramos FB + 2 em " + pai.e);
        if (pai.Esq.FB >= 0) {
          RDS(pai);
        } else {
          System.out.println("Dupla Direita");
          RES(pai.Esq);
          RDS(pai);
        }
      } else if (fb == -2) {
        System.out.println("Encontramos FB -2 em " + pai.e);
        if (pai.Dir.FB <= 0) {
          RES(pai);
        } else {
          System.out.println("Dupla Esquerda");
          RDS(pai.Dir);
          RES(pai);
        }
      }
      update(pai.pai);
    }
  }

  public void updateNew(No n, boolean dir, No novo) {

    if (n != null) {
      if (dir) n.FB -= 1; else n.FB += 1;

      if (n.FB == 2) {
        System.out.println("Encontramos FB + 2 em " + n.e);
        if (n.Esq.FB >= 0) {
          System.out.println("Rotação Direta Simples");
          RDS(n);
        } else {
          System.out.println("Dupla Direita");
          RES(n.Esq);
          RDS(n);
        }
      } else if (n.FB == -2) {
        System.out.println("Encontramos FB -2 em " + n.e);
        if (n.Dir.FB <= 0) {
          System.out.println("Rotação Esquerda Simples");
          RES(n);
        } else {
          System.out.println("Dupla Esquerda");
          RDS(n.Dir);
          RES(n);
        }
      }
      if (n.pai != null) {
        if (n.pai.Dir == n)
          dir = true;
        else
          dir = false;
      }
      if (n.FB != 0)
        updateNew(n.pai, dir, n);
    }
  }

  public No RES(No n) {
    n.FB = n.FB + 1 - Math.min(n.Dir.FB, 0);
    n.Dir.FB = n.Dir.FB + 1 + Math.max(n.FB, 0);
    No subD = n.Dir;
    n.Dir = subD.Esq;
    if (n.Dir != null)
      n.Dir.pai = n;

    subD.Esq = n;
    subD.pai = n.pai;
    n.pai = subD;

    if (subD.pai != null)
      subD.pai.Dir = subD;
    else
      root = subD;

      
//    printTree(root, true, "   ");
    return subD.pai;
  }

  public No RDS(No n) {
    n.FB = n.FB - 1 - Math.max(n.Esq.FB, 0);      
    n.Esq.FB = n.Esq.FB - 1 + Math.min(n.FB, 0);
    No subE = n.Esq;
    n.Esq = subE.Dir;
    if (n.Esq != null)
      n.Esq.pai = n;
    subE.Dir = n;
    subE.pai = n.pai;
    n.pai = subE;

    // Caso a rota��o seja feita na raiz
    if (subE.pai != null)
      subE.pai.Dir = subE;
    else
      root = subE;
//    printTree(root, true, "   ");
    return subE;
  }

  private void updateFB(No n) {
    if (n != null) {
      updateFB(n.Esq);
      n.FB = updateNode(n);
      System.out.println(n.e + " - FB = " + n.FB);
      updateFB(n.Dir);
    }
  }

  private int updateNode(No n) {
    return (height(n.Esq) - height(n.Dir));
  }

  private void printNodeValue(No n) {

    if (n == null) {
      System.out.print("<null>");
    } else {
      System.out.print(n.e + " (" + n.FB + ")");
    }
    System.out.print('\n');
  }

  public void printTree(No n, boolean isRight, String indent) {
    if (n.Dir != null) {
      printTree(n.Dir, true, indent + (isRight ? "        " : " |      "));
    }
    System.out.print(indent);
    if (isRight) {
      System.out.print(" /");
    } else {
      System.out.print(" \\");
    }
    System.out.print("----- ");
    printNodeValue(n);
    if (n.Esq != null) {
      printTree(n.Esq, false, indent + (isRight ? " |      " : "        "));
    }
  }
}
