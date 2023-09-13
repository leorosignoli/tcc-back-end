package edu.uniceub.tcc.calendareventmanager.constants;

public final class LogConstants {

  private LogConstants() {}

  public static final class Monad {
    public static final String DEBUG_GENERIC_MONAD_INITIATED_WITH_OBJECT =
        "Generic Monad initiated with object {}";
    public static final String DEBUG_RESULT_FROM_FUNCTION =
        "Objected resulted from function action {}";
    public static final String DEBUG_RESULT_FROM_CONSUMER =
        "Objected resulted from consumer action {}";

    private Monad() {}
  }
}
