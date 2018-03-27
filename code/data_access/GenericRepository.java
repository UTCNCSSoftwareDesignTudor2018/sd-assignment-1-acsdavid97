package data_access;

import data_access.dto.GenericDTO;

import java.util.Collection;

/**
 * Defines CRUD operations on GenericDTOs.
 * @param <T> type of the object stored in repository.
 */
public interface GenericRepository<T extends GenericDTO> {

    /**
     * Return an object of type T from the repository, having the specified id.
     * @param id id of the object to be fetched.
     * @return object with id specified, or null otherwise.
     * @throws RepositoryException
     */
    T findById(int id) throws RepositoryException;

    /**
     * adds an object to the repository
     * @param toAdd object to be inserted into the repository.
     * @throws RepositoryException
     */
    void add(T toAdd) throws RepositoryException;

    /**
     * @return all instances of object type T.
     * @throws RepositoryException
     */
    Collection<T> getAll() throws RepositoryException;

    /**
     * Updates an object in the repository, having the same id as toUpdate.
     * @param toUpdate object containing the id of the object to be updated, and the new data.
     * @return true if successful.
     * @throws RepositoryException
     */
    boolean update(T toUpdate) throws RepositoryException;

    /**
     * Deleted an instance of an object from the repository.
     * @param toDelete object having id to be deleted.
     * @return true if successful.
     * @throws RepositoryException
     */
    boolean delete(T toDelete) throws RepositoryException;

    /**
     * Deleted an instance of an object from the repository.
     * @param id id to be deleted.
     * @return true if successful.
     * @throws RepositoryException
     */
    boolean delete(int id) throws RepositoryException;
}
