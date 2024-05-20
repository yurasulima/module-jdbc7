package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static void main(String[] args) throws SQLException, IOException {
        List<MaxProjectCountClient> findMaxProjectsClientList = new DatabaseQueryService().findMaxProjectsClient();
        System.out.println("findMaxProjectsClientList = " + findMaxProjectsClientList);

        List<FindLongestProject> findLongestProjectsList = new DatabaseQueryService().findLongestProjects();
        System.out.println("findLongestProjectsList = " + findLongestProjectsList);

        List<FindMaxSalaryWorker> findMaxSalaryWorkerList = new DatabaseQueryService().findMaxSalaryWorkers();
        System.out.println("findMaxSalaryWorkerList = " + findMaxSalaryWorkerList);

        List<FindYoungestEldestWorkers> findYoungestEldestWorkersList = new DatabaseQueryService().findYoungestEldestWorkers();
        System.out.println("findYoungestEldestWorkersList = " + findYoungestEldestWorkersList);

    }

    public List<FindYoungestEldestWorkers> findYoungestEldestWorkers() throws IOException, SQLException {
        Path sqlPath = Path.of("sql/find_youngest_eldest_workers.sql");
        String sql = new String(Files.readAllBytes(sqlPath));

        List<FindYoungestEldestWorkers> result = new ArrayList<>();

        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            String type = resultSet.getString("TYPE");
            String birthday = resultSet.getString("birthday");

            FindYoungestEldestWorkers item = new FindYoungestEldestWorkers();
            item.setName(name);
            item.setBirthday(birthday);
            item.setType(type);

            result.add(item);
        }
        return result;
    }
    public List<FindMaxSalaryWorker> findMaxSalaryWorkers() throws IOException, SQLException {
        Path sqlPath = Path.of("sql/find_max_salary_worker.sql");
        String sql = new String(Files.readAllBytes(sqlPath));

        List<FindMaxSalaryWorker> result = new ArrayList<>();

        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            String salary = resultSet.getString("salary");

            FindMaxSalaryWorker item = new FindMaxSalaryWorker();
            item.setName(name);
            item.setSalary(salary);

            result.add(item);
        }
        return result;

    }
    public List<FindLongestProject> findLongestProjects() throws IOException, SQLException {
        Path sqlPath = Path.of("sql/find_longest_project.sql");
        String sql = new String(Files.readAllBytes(sqlPath));

        List<FindLongestProject> result = new ArrayList<>();

        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int projectId = resultSet.getInt("project_id");

            FindLongestProject item = new FindLongestProject();
            item.setProjectId(projectId);

            result.add(item);
        }
        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException, IOException {
        Path sqlPath = Path.of("sql/find_max_projects_client.sql");
        String sql = new String(Files.readAllBytes(sqlPath));

        List<MaxProjectCountClient> result = new ArrayList<>();

        Connection connection = Database.getInstance().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            int projectCount = resultSet.getInt("project_count");

            MaxProjectCountClient item = new MaxProjectCountClient();
            item.setName(name);
            item.setProjectCount(projectCount);
            result.add(item);
        }
        return result;
    }

}
