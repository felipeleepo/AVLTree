package entidades;

public class ArvoreAVL {

    int size;
    No root;

    public ArvoreAVL() {
        this.root = null;
        this.size = 0;
    }

    public No insert(No n, int v, No pai, boolean isRight) {

        if (size == 0) {
            root = new No(v, null);
            size++;
            System.out.println(root.e);
            return root;
        } else {
            if (n == null) {
                n = new No(v, pai);
                if (isRight) {
                    pai.Dir = n;
                } else {
                    pai.Esq = n;
                }
                System.out.println(n.e);
                updateNew(pai, isRight, true);
                size++;
            } else if (v > n.e) {
                insert(n.Dir, v, n, true);
            } else {
                insert(n.Esq, v, n, false);
            }
        }
        return n;
    }

    public String search(No n, int v, boolean externa) {

        if (n == null) {
            if (externa) {
                if (size == 0) {
                    return "Árvore Vazia";
                } else {
                    return "Valor " + v + " não encontrado";
                }
            } else {
                return "";
            }
        } else if (n.e == v) {
            if (externa) {
                return "Valor encontrato " + n.e;
            } else {
                return "";
            }
        } else if (v > n.e) {
            return "" + search(n.Dir, v, externa);
        } else {
            return "" + search(n.Esq, v, externa);
        }
    }

    public void remove(int o, No n, No pai, boolean isRight) {

        if (size == 0) {
            System.out.println("Arvore Vazia");
        }

        if (n.e == o) {
            System.out.println("Valor removido " + n.e);
            // Não possui Filhos
            if (n.Dir == n.Esq) {
                if (isRight) {
                    pai.Dir = null;
                } else {
                    pai.Esq = null;
                }
                updateNew(pai, isRight, false);
            // Possui Filho Direito
            } else if (n.Esq == null) {
                if (isRight) {
                    pai.Dir = n.Dir;
                } else {
                    pai.Esq = n.Dir;
                }
                n.Dir = n.pai;
                updateNew(pai, isRight, false);
            // Possui Filho Esquerdo
            } else if (n.Dir == null) {
                if (isRight) {
                    pai.Dir = n.Esq;
                } else {
                    pai.Esq = n.Esq;
                }
                n.Esq = n.pai;
                updateNew(pai, isRight, false);
            // Sucessor
            } else {
                if (pai != null) {
                    pai.Dir = nextNode(n, pai);
                } else {
                    root = nextNode(n, null);
                }
                updateNew(n, isRight, false);
            }
            size--;
        } else if (n.e > o) {
            remove(o, n.Esq, n, false);
        } else {
            remove(o, n.Dir, n, true);
        }
    }

    public void printInOrder(No n) {
        if (n != null) {
            printInOrder(n.Esq);
            System.out.println(n.e + " - FB = " + n.FB);
            printInOrder(n.Dir);
        }
    }

    public void updateNew(No n, boolean isRight, boolean isInsertion) {

        if (n != null) {
            if ((isRight && isInsertion) || (!isRight && !isInsertion)) {
                n.FB -= 1;
            } else {
                n.FB += 1;
            }

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
                if (n.pai.Dir == n) {
                    isRight = true;
                } else {
                    isRight = false;
                }
            }
            if (isInsertion && n.FB != 0) {
                updateNew(n.pai, isRight, true);
            } else if (!isInsertion && n.FB == 0) {
                updateNew(n.pai, isRight, false);
            }
        }
    }

    public No RES(No n) {
        n.FB = n.FB + 1 - Math.min(n.Dir.FB, 0);
        n.Dir.FB = n.Dir.FB + 1 + Math.max(n.FB, 0);
        No subD = n.Dir;
        n.Dir = subD.Esq;
        if (n.Dir != null) {
            n.Dir.pai = n;
        }

        subD.Esq = n;
        subD.pai = n.pai;
        n.pai = subD;

        if (subD.pai != null) {
            subD.pai.Dir = subD;
        } else {
            root = subD;
        }

        return subD.pai;
    }

    public No RDS(No n) {
        n.FB = n.FB - 1 - Math.max(n.Esq.FB, 0);
        n.Esq.FB = n.Esq.FB - 1 + Math.min(n.FB, 0);
        No subE = n.Esq;
        n.Esq = subE.Dir;
        if (n.Esq != null) {
            n.Esq.pai = n;
        }
        subE.Dir = n;
        subE.pai = n.pai;
        n.pai = subE;

        // Caso a rota��o seja feita na raiz
        if (subE.pai != null) {
            subE.pai.Dir = subE;
        } else {
            root = subE;
        }

        return subE;
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

    // SUCESSOR : O filho mais a esquerda do filho direito do nó a ser removido
    private No nextNode(No n, No pai) {
        No aux = n.Dir;
        while (aux.Esq != null) {
            aux = aux.Esq;
        }

        if (n != root) {
            // Retirando as Refências que os nós faziam ao sucessor
            if (aux != n.Dir) {
                aux.pai.Esq = null;
                aux.Dir = n.Dir;
            }
            // Substituindo o sucessor à posição do no removido
            aux.Esq = n.Esq;
            aux.pai = pai;
        } // Caso RAIZ
        else {
            if (aux != n.Dir) {
                aux.pai.Esq = null;
                aux.Dir = n.Dir;
            }
            aux.Esq = n.Esq;
            aux.pai = null;
            root = aux;
        }

        return aux;
    }
}
