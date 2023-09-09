package edu.uniceub.tcc.calendareventmanager.helpers.monad;

public final class Monad {

  private Monad() {}

  public static <K> GenericMonad<K> init(final K obj) {
    return GenericMonad.init(obj);
  }
}
