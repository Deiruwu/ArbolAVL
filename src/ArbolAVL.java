public class ArbolAVL<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public void eliminarArbol() {
        this.raiz = null;
    }

    public void insertar(T elemento){
        if (raiz == null)
            raiz = new Nodo<T>(elemento);
        else
            raiz = insertar(raiz, elemento);
    }

    public Nodo<T> insertar(Nodo<T> n, T elemento) {
        if (n == null)
            return new Nodo<T>(elemento);

        if (elemento.compareTo(n.getElemento()) < 0)
            n.setIzquierda(insertar(n.getIzquierda(), elemento));

        else if (elemento.compareTo(n.getElemento()) > 0)
            n.setDerecha(insertar(n.getDerecha(), elemento));

         else
            return n;


         actualizarAltura(n);


        // Caso 1: Rotación derecha, Izquierda-Izquierda (LL)
        if (n.getFe() > 1 && elemento.compareTo(n.getIzquierda().getElemento()) < 0) {
            System.out.println("Se ejecuta una rotación derecha");
            n = rotacionDerecha(n);
        }

        // Caso 2: Rotación izquierda, Derecha-Derecha (RR)
        if (n.getFe() < -1 && elemento.compareTo(n.getDerecha().getElemento()) > 0) {
            System.out.println("Se ejecuta una rotación izquierda");
            n = rotacionIzquierda(n);
        }

        // Caso 3: Rotación doble izquierda, Izquierda-Derecha (LR)
        if (n.getFe() > 1 && elemento.compareTo(n.getIzquierda().getElemento()) > 0) {
            System.out.println("Se ejecuta una rotación izquierda-derecha");
            n.setIzquierda(rotacionIzquierda(n.getIzquierda()));
            n = rotacionDerecha(n);
        }

        // Caso 4: Rotación doble derecha, Derecha-Izquierda (RL)
        if (n.getFe() < -1 && elemento.compareTo(n.getDerecha().getElemento()) < 0) {
            System.out.println("Se ejecuta una rotación derecha-izquierda");
            n.setDerecha(rotacionDerecha(n.getDerecha()));
            return rotacionIzquierda(n);
        }

        return n;
    }

    // Rotación Simple a la Izquierda
    // Nombres comunes para las variables:
    // padre/alpha: nodo que baja de nivel
    // hijoDer/beta: nodo que sube de nivel
    // subArbolIzq/gamma: subárbol que se mueve

    /* Ejemplo que provoca una rotación simple a la izquierda:

        10                  20
         \                 /  \
          20      ->     10    30
           \
            30

            Antes:          Después:
               α              β
                \            / \
                 β    ->    α   C
                  \          \
                   C          γ
    */

    private Nodo<T> rotacionIzquierda(Nodo<T> alpha){
        Nodo<T> beta = alpha.getDerecha();
        Nodo<T> gamma = beta.getIzquierda();

        beta.setIzquierda(alpha);
        alpha.setDerecha(gamma);


        actualizarAltura(alpha);
        actualizarAltura(beta);

        return beta;
    }


    // Rotación Simple a la Derecha
    // padre/alpha: nodo que baja de nivel
    // hijoIzq/beta: nodo que sube de nivel
    // subArbolDer/gamma: subárbol que se mueve

    /* Ejemplo que provoca una rotación simple a la derecha:

            30                20
           /                 /  \
          20      ->       10    30
         /
        10

        Antes:     Después:
         α            β
        /           / \
       β     ->    A   α
      /                 \
     A                   γ

    */

    private Nodo<T> rotacionDerecha(Nodo<T> alpha){
        Nodo<T> beta = alpha.getIzquierda();
        Nodo<T> gamma = beta.getDerecha();

        beta.setDerecha(alpha);
        alpha.setIzquierda(gamma);


        actualizarAltura(alpha);
        actualizarAltura(beta);

        return beta;
    }


    private int altura(Nodo<T> n) {
        return (n == null) ? 0 : n.getAltura();

    }

    private void actualizarFE(Nodo<T> n) {
        if (n == null) return;

        n.setFe(altura(n.getIzquierda()) - altura(n.getDerecha()));
    }

    private void actualizarAltura(Nodo<T> n) {
        if (n == null) return;

        n.setAltura(Math.max(altura(n.getIzquierda()), altura(n.getDerecha())) + 1);
        actualizarFE(n);
    }

    public void print(String orden) {
        // Encabezado de la tabla
        System.out.println("╔════════╦═══════════════════╦═══════════════╦═══════════════╦═══════════════╗");
        System.out.println("║ Altura ║       Valor       ║    Balance    ║   Izquierdo   ║    Derecho    ║");
        System.out.println("╠════════╬═══════════════════╬═══════════════╬═══════════════╬═══════════════╣");

        switch (orden.toLowerCase()) {
            case "inorder":
                inOrder(raiz);
                break;
            case "preorder":
                preOrder(raiz);
                break;
            case "postorder":
                postOrder(raiz);
                break;
            default:
                System.out.println("Orden no válido. Usa 'inOrder', 'preOrder' o 'postOrder'.");
        }

        // Línea final de la tabla
        System.out.println("╚════════╩═══════════════════╩═══════════════╩═══════════════╩═══════════════╝");
    }

    private void preOrder(Nodo<T> nodo) {
        if (nodo != null) {
            imprimirNodo(nodo);
            preOrder(nodo.getIzquierda());
            preOrder(nodo.getDerecha());
        }
    }
    private void inOrder(Nodo<T> nodo) {
        if (nodo != null) {
            inOrder(nodo.getIzquierda());
            imprimirNodo(nodo);
            inOrder(nodo.getDerecha());
        }
    }


    private void postOrder(Nodo<T> nodo) {
        if (nodo != null) {
            postOrder(nodo.getIzquierda());
            postOrder(nodo.getDerecha());
            imprimirNodo(nodo);
        }
    }

    private void imprimirNodo(Nodo<T> nodo) {
        System.out.printf("║   %-4d ║         %-9s ║       %-7s ║     %-9s ║     %-9s ║%n",
                nodo.getAltura(),
                nodo.getElemento() instanceof Number && ((Number) nodo.getElemento()).doubleValue() < 0 ? " " + nodo.getElemento() : nodo.getElemento(),
                nodo.getFe() < 0 ? nodo.getFe() :  " " + nodo.getFe(),
                nodo.getIzquierda() != null ? (" " + nodo.getIzquierda().getElemento()) : "null",
                nodo.getDerecha() != null ? (" " + nodo.getDerecha().getElemento()) : "null");
    }



    // Agregar este metodo getter para la raíz
    public Nodo<T> getRaiz() {
        return raiz;
    }

    // Agregar este metodo para mostrar la visualización
    public void mostrarArbolGrafico() {
        VisualizadorArbolAVL visualizador = new VisualizadorArbolAVL((ArbolAVL<Integer>) this);
        visualizador.setVisible(true);
    }


}