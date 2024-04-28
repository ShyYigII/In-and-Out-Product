package dao;

public interface DAOInterface<T> {
    public void insert(T t);
    public void delete(T t);

    public void view(T t);

    public void update(T t);
}
