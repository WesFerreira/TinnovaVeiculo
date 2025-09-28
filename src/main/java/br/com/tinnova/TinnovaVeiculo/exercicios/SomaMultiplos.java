package br.com.tinnova.TinnovaVeiculo.exercicios;

import java.util.Scanner;

public class SomaMultiplos {

    public static int somaMultiplos(int limite) {
        int soma = 0;
        for (int i = 1; i < limite; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                soma += i;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um numero limite: ");
        int limite = scanner.nextInt();

        int resultado = somaMultiplos(limite);
        System.out.println("Soma dos multiplos de 3 ou 5 abaixo de " + limite + " = " + resultado);

        scanner.close();
    }

}
