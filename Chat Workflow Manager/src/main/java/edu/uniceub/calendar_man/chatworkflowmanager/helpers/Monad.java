package edu.uniceub.calendar_man.chatworkflowmanager.helpers;

public final class Monad {

  private Monad() {}

  public static <K> GenericMonad<K> init(final K obj) {
    return GenericMonad.init(obj);
  }
}
