package home.undertow.capsule.blocking;

import home.undertow.capsule.entities.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by alex on 9/5/2015.
 */
public interface EmployeeDao extends CrudRepository<Employee, Long> {
    Employee findByFirstName(@Param("firstName") String firstName);
}
