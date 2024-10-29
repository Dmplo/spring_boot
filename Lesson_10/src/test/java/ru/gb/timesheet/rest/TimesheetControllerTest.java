package ru.gb.timesheet.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimesheetControllerTest {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @LocalServerPort
    private int port;
    private RestClient restClient;
    private List<Project> projects = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Timesheet> timesheets = new ArrayList<>();

    public static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
    }

    void createValues() {

        List<String> names = List.of("Johny", "Leo", "Brad", "Arnold", "Will", "Chuck", "Jackie", "Donald");
        List<String> lastnames = List.of("Depp", "Da Vinci", "Pitt", "Schwarzenegger", "Smith", "Norris", "Chan", "Duck");

        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setFirstname(getRandom(names));
            employee.setLastname(getRandom(lastnames));
            employee.setAge(ThreadLocalRandom.current().nextInt(20, 60));
            employee.setSalary(ThreadLocalRandom.current().nextInt(50000, 100000));
            employee = employeeRepository.save(employee);
            employees.add(employee);
        }


        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setId((long) i);
            project.setName("Project #" + i);
            project = projectRepository.save(project);
            projects.add(project);
        }


        LocalDate createdAt = LocalDate.now();
        for (int i = 1; i <= 10; i++) {
            createdAt = createdAt.plusDays(1);
            Timesheet timesheet = new Timesheet();
            timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
            timesheet.setCreatedAt(createdAt);
            timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
            timesheet.setEmployeeId(getRandom(employees).getId());
            timesheet = timesheetRepository.save(timesheet);
            timesheets.add(timesheet);
        }
    }

    @AfterEach
    void afterEach() {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        timesheetRepository.deleteAll();
        employees.clear();
        projects.clear();
        timesheets.clear();
    }

    @Test
    void testGetByIdOk() {
        createValues();
        Timesheet timesheet = getRandom(timesheets);
        System.out.println("timesheet");
        System.out.println(timesheet);
        ResponseEntity<Timesheet> actual = restClient.get().uri("/api/timesheets/" + timesheet.getId())
                .retrieve()
                .toEntity(Timesheet.class);
        System.out.println("actual");
        System.out.println(actual);
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Timesheet responseBody = actual.getBody();
        assertNotNull(responseBody);
        assertEquals(responseBody.getId(), timesheet.getId());
        assertEquals(responseBody.getMinutes(), timesheet.getMinutes());
    }

    @Test
    void testGetByIdNotFound() {
        createValues();
        int timesheetId = timesheets.size() + 1;

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restClient.get()
                    .uri("/api/timesheets/" + timesheetId)
                    .retrieve()
                    .toBodilessEntity();
        });
    }

    @Test
    void testGetAll() {
        createValues();
        ResponseEntity<List<Timesheet>> actual = restClient.get()
                .uri("/api/timesheets")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Timesheet>>() {
                });


        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        List<Timesheet> responseBody = actual.getBody();
        assertNotNull(responseBody);
        assertEquals(responseBody.size(), timesheets.size());
        assertEquals(responseBody.get(1).getId(), timesheets.get(1).getId());
    }

    @Test
    void testCreate() {
        Employee employee = new Employee();
        employee.setFirstname("Johny");
        employee.setLastname("Depp");
        employee.setAge(25);
        employee.setSalary(50000);
        employee = employeeRepository.save(employee);

        Project project = new Project();
        project.setName("projectName");
        project = projectRepository.save(project);

        Timesheet toCreate = new Timesheet();
        toCreate.setProjectId(project.getId());
        toCreate.setEmployeeId(employee.getId());
        toCreate.setCreatedAt(LocalDate.now());
        toCreate.setMinutes(100);

        ResponseEntity<Timesheet> response = restClient.post()
                .uri("/api/timesheets")
                .body(toCreate)
                .retrieve()
                .toEntity(Timesheet.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Timesheet responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getMinutes(), toCreate.getMinutes());

        assertTrue(timesheetRepository.existsById(responseBody.getId()));
    }

    @Test
    void testDelete() {
        createValues();
        Timesheet toDelete = getRandom(timesheets);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/api/timesheets/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertFalse(timesheetRepository.existsById(toDelete.getId()));
    }
}
