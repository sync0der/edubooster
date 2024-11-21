package uz.tsue.ricoin.mapper;

@FunctionalInterface
public interface CustomMapperInterface<T, R> {
    R toDto(T t);

    default T toEntity(R r) {
        throw new UnsupportedOperationException("Conversion to entity is not implemented");
    }

    ;
}
