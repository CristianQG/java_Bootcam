
// Dado un número n, crear una lista que me muestre de forma ascendente impares seguidos de pares
// Ejem: [1,4,3,10,9,12,17,16]

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> pares = new ArrayList<>();
        List<Integer> impares = new ArrayList<>();
        List<Integer> resultado = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de la lista: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el número: ");
            int num = Integer.parseInt(sc.nextLine());
            if (num % 2 == 0) {
                pares.add(num);
            } else {
                impares.add(num);
            }
        }
        Collections.sort(pares);
        Collections.sort(impares);
        int tamanio = (pares.size() > impares.size()) ? pares.size() : impares.size();
        for (int i = 0; i < tamanio; i++) {
            if (i < impares.size()) {
                resultado.add(impares.get(i));
            }
            if (i < pares.size()) {
                resultado.add(pares.get(i));
            }
        }
        System.out.println(resultado.toString());
    }
}