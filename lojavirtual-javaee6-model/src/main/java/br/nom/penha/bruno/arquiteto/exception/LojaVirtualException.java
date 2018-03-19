package br.nom.penha.bruno.arquiteto.exception;


public class LojaVirtualException extends Exception {
  public LojaVirtualException() {
  }

  public LojaVirtualException(String msg) {
    super(msg);
  }
  public LojaVirtualException(String msg, Throwable t) {
    super(msg, t);
  }
}