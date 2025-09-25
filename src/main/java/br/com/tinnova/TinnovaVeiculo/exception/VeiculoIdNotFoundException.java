package br.com.tinnova.TinnovaVeiculo.exception;

public class VeiculoIdNotFoundException extends RuntimeException{
    public VeiculoIdNotFoundException(String message) {
        super(message);
    }
}
