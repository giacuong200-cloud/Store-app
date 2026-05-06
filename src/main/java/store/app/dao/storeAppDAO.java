package store.app.dao;

import java.util.List;

public abstract class storeAppDAO<E, K> {

    public abstract void insert(E entity);

    public abstract void update(E entity);

    public abstract void delete(K key);

    public abstract E selectById(K key);

    public abstract List<E> selectAll();

    protected abstract List<E> selectBySql(String sql, Object... args);
}