package onetomanym17.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import onetomanym17.dto.Company;
import onetomanym17.dto.Employee;

public class EmployeeDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vinod").createEntityManager();
	}
	
	public void saveEmployee(int companyId,Employee employee) {
		EntityManager entityManager=getEntityManager();
		Company company=entityManager.find(Company.class, companyId);
		if(company!=null) {
//			company is present
			EntityTransaction  entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(employee);
//			update Company details
			List<Employee> employees=company.getEmployees();
			employees.add(employee);
//			im resetting the employees list
			company.setEmployees(employees);
			entityTransaction.commit();
			
		}else {
//			company is not present
			System.out.println("Sorry Company is not present with this Id");
		}
	}
	
	
	public void deleteEmployee(int id) {
		EntityManager entityManager=getEntityManager();
		Employee employee=entityManager.find(Employee.class, id);
		if(employee!=null) {
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(employee);
			entityTransaction.commit();
		}else {
			System.out.println("Sorry Id is not present");
		}
	}
	public void findEmployee(int id) {
		EntityManager entityManager=getEntityManager();
		Employee employee=entityManager.find(Employee.class, id);
		if(employee!=null) {
			System.out.println(employee);
		}else {
			System.out.println("Sorry Id is not present");
		}
	}

	public void updateEmployee(int id,Employee employee) {
		EntityManager entityManager=getEntityManager();
		Employee dbEmployee=entityManager.find(Employee.class, id);
		if(dbEmployee!=null) {
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			employee.setId(id);
			entityManager.merge(employee);
			entityTransaction.commit();
		}else {
			System.out.println("Sorry Id is not present");
		}
	}
}

