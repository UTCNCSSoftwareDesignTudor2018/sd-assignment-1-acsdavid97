package data_access;

import data_access.dto.GenericDTO;

import java.util.Collection;

public interface GenericRepository<T extends GenericDTO> {

    T findById(int id) throws RepositoryException;

    void add(T toAdd) throws RepositoryException;

    Collection<T> getAll() throws RepositoryException;

    boolean update(T toUpdate) throws RepositoryException;

    boolean delete(T toDelete) throws RepositoryException;

    boolean delete(int id) throws RepositoryException;
}
