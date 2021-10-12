package net.javaguides.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.employeemanagerapp.EmployeeManagementApplication;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes=EmployeeManagementApplication.class)
@RunWith(SpringRunner.class)
class SpringbootBackendApplicationTests {
	   @Autowired
	    private EmployeeRepository employeeRepository;
	        

	    // JUnit test for saveEmployee
	    @Test
	    @Rollback(value = false)
	    @DirtiesContext 
	    public void saveEmployeeTest(){
           Employee employee=new Employee("ram","katrin","ramkatrin@gmail.com");
//	       

	        employeeRepository.save(employee);

	        Assertions.assertThat(employee.getId()).isGreaterThan(0);
	    }

	    @Test
	    @DirtiesContext 
	    public void getEmployeeTest(){

	        Employee employee = employeeRepository.findById(1L).get();

	        Assertions.assertThat(employee.getId()).isEqualTo(1L);

	    }

	    @Test
	    public void getListOfEmployeesTest(){

	        List<Employee> employees = employeeRepository.findAll();

	        Assertions.assertThat(employees.size()).isGreaterThan(0);

	    }

	    @Test
	    @Rollback(value = false)
	    @DirtiesContext 
	    public void updateEmployeeTest(){

	        Employee employee = employeeRepository.findById(3L).get();

	        employee.setEmailId("ram@gmail.com");

	        Employee employeeUpdated =  employeeRepository.save(employee);

	        Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("ram@gmail.com");

	    }

	    @Test
	    @Rollback(value = false)
	    @DirtiesContext 
	    public void deleteEmployeeTest(){

	        Employee employee = employeeRepository.findById(4L).get();

	        employeeRepository.delete(employee);
	        Employee employee1 = null;

	        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId("ram@gmail.com");

	        if(optionalEmployee.isPresent()){
	            employee1 = optionalEmployee.get();
	        }

	        Assertions.assertThat(employee1).isNull();
	    }



	    

	  


	

}
