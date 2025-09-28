package br.com.tinnova.TinnovaVeiculo.exercicios;

public class BubbleSort {

    public static void main(String[] args) {
        Integer[] vetor = {5, 93, 2, 4, 7, 18, 1, 8, 6};

        System.out.println("Vetor original:");
        imprimirVetor(vetor);

        bubbleSort(vetor);

        System.out.println("\nVetor ordenado:");
        imprimirVetor(vetor);
    }

    public static void bubbleSort(Integer[] array) {
        int n = array.length;
        boolean trocou;

        for (int i = 0; i < n - 1; i++) {
            trocou = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    Integer temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    trocou = true;
                }
            }

            if (!trocou) break;
        }
    }

    public static void imprimirVetor(Integer[] array) {
        for (Integer num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
