package br.com.tinnova.TinnovaVeiculo.exercicios;

public class Votacao {

    private final Integer totalEleitores;
    private final Integer votosValidos;
    private final Integer votosBrancos;
    private final Integer votosNulos;

    public Votacao(Integer totalEleitores, Integer votosValidos, Integer votosBrancos, Integer votosNulos) {
        this.totalEleitores = totalEleitores;
        this.votosValidos = votosValidos;
        this.votosBrancos = votosBrancos;
        this.votosNulos = votosNulos;
    }

    public double votosValidos() {
        return (votosValidos * 100.0) / totalEleitores;
    }

    public double votosBrancos() {
        return (votosBrancos * 100.0) / totalEleitores;
    }

    public double votosNulos() {
        return (votosNulos * 100.0) / totalEleitores;
    }

    public static void main(String[] args) {
        Votacao votacao = new Votacao(1000, 800, 150, 50);

        System.out.printf("Percentual de validos: %.2f%%\n", votacao.votosValidos());
        System.out.printf("Percentual de brancos: %.2f%%\n", votacao.votosBrancos());
        System.out.printf("Percentual de nulos: %.2f%%\n", votacao.votosNulos());
    }

}
