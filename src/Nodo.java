class Nodo<T> {
    private T elemento;
    private Nodo<T> izquierda, derecha;
    private int altura, fe;

    public Nodo(T elemento) {
        this.elemento = elemento;
        this.altura = 1; // Inicializa altura en 0 para un nodo hoja
        this.fe = 0; // Inicializa el factor de equilibrio en 0

    }


    public T getElemento() {
        return elemento;
    }

    public Nodo<T> getIzquierda() {
        return izquierda;
    }

    public Nodo<T> getDerecha() {
        return derecha;
    }

    public int getAltura() {
        return altura;
    }

    public int getFe() {
        return fe;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }
}

