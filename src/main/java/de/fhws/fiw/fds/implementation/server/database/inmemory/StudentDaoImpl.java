package de.fhws.fiw.fds.implementation.server.database.inmemory;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.StudentDao;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class StudentDaoImpl extends AbstractInMemoryStorage<Student> implements StudentDao {

    @Override
    public CollectionModelResult<Student> readByQuery(String firstName, String lastName, SearchParameter searchParameter) {
        return readByPredicate(
                student -> (
                        student.getFirstName().toLowerCase().contains(firstName.toLowerCase()) &&
                        student.getLastName().toLowerCase().contains(lastName.toLowerCase())
                ),
                searchParameter);
    }

}
