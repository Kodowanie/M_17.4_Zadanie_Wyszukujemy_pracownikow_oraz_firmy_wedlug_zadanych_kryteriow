package hibernate.mantomany.dao;

import hibernate.mantomany.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee,Integer> {
    @Query
    List<Employee> findAccordingToName(@Param("LAST_NAME")String lastname);
}
