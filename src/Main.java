import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Insertar datos");
            System.out.println("2. Ver árbol inOrden");
            System.out.println("3. Visualizar árbol gráficamente");
            System.out.println("4. Borrar árbol");
            System.out.println("8. Insertar elementos aleatorios");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa los datos (presiona Enter para volver al menú):");
                    while (true) {
                        System.out.print("Ingresa un número: ");
                        String input = sc.nextLine();
                        if (input.isEmpty()) {
                            break;
                        }
                        try {
                            int elemento = Integer.parseInt(input);
                            arbol.insertar(elemento);
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingresa un número válido.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Recorrido inOrden del árbol:");
                    arbol.inOrden();
                    break;
                case 3:
                    System.out.println("Mostrando visualización gráfica del árbol...");
                    arbol.mostrarArbolGrafico();
                    break;
                case 4:
                    System.out.println("Eliminando arbol...");
                    arbol.eliminarArbol();
                    break;
                case 8:
                    System.out.print("¿Cuántos elementos aleatorios deseas agregar? ");
                    int cantidad = sc.nextInt();
                    agregarElementosAleatorios(arbol, cantidad);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);
    }

    private static void agregarElementosAleatorios(ArbolAVL<Integer> arbol, int cantidad) {
        Random rand = new Random();
        Set<Integer> elementosGenerados = new HashSet<>();

        while (elementosGenerados.size() < cantidad) {
            int numAleatorio = rand.nextInt(100000); // Números entre 0 y 999
            if (elementosGenerados.add(numAleatorio)) { // Solo agrega si no existe
                arbol.insertar(numAleatorio);
                System.out.println("Elemento agregado: " + numAleatorio);
            }
        }
    }
}