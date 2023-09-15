package edu.uniceub.tcc.calendareventmanager.helpers.monad;

import static edu.uniceub.tcc.calendareventmanager.constants.LogConstants.Monad.*;

import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a structure that combines program functions and wraps their return values in a type
 * with additional computation known as Monad.
 *
 * @param <R> Initial object type in pipeline
 */
public class GenericMonad<R> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GenericMonad.class);

  private final R obj;

  private GenericMonad(final R obj) {
    LOGGER.debug(DEBUG_GENERIC_MONAD_INITIATED_WITH_OBJECT, obj);
    this.obj = obj;
  }

  protected static <K> GenericMonad<K> init(final K obj) {
    return new GenericMonad<>(obj);
  }

  public <T> GenericMonad<T> applyFunction(final Function<R, T> function) {
    final var result = function.apply(obj);
    LOGGER.debug(DEBUG_RESULT_FROM_FUNCTION, result);
    return new GenericMonad<>(result);
  }

  public GenericMonad<R> applyLogger(final FunctionalLogger<R> logger) {
    logger.log(obj);
    return this;
  }

  public GenericMonad<R> applyConsumer(final Consumer<R> consumer) {
    consumer.accept(obj);
    LOGGER.debug(DEBUG_RESULT_FROM_CONSUMER, obj);
    return this;
  }

  public R getValue() {
    return this.obj;
  }

  @FunctionalInterface
  public interface FunctionalLogger<T> {
    void log(T t);
  }
}
