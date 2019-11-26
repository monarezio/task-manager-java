package cz.ucl.logic.data.mappers.definitions;

/**
 * Interface is for "Shallow mapping", so I don't
 * @param <I> the input class that will be mapped from
 * @param <O> the output class that will be mapped to
 */
public interface IMapper<I, O> {

    O mapOrNull(I v);

}
