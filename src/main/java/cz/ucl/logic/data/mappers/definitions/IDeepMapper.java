package cz.ucl.logic.data.mappers.definitions;

public interface IDeepMapper<I, O> {

    O deepMapOrNull(I v);

}
