package bg.project.SidikaFarm.mapper;


public interface CustomMapper<S,T> {
    T toEntity(S dto);
    S toDTO(T entity);
}
