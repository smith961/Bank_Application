package Projects.Abdulhakeem_Smith.Project1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public  class Project implements Projects.Project1.Project1.Developers {

    Connection connection = null;
    private void createTable() throws SQLException {
        Connection connection1 = connectToDatabase();
        Statement statement = connection1.createStatement();
        String createTable = "CREATE TABLE IF NOT EXISTS developers(name Text, age Integer, location Text, skill Text)";
        statement.execute(createTable);
        statement.close();
        closeDatabase();
    }

    private void loadFromTextToDB(String fileName){

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;

            while( (line = reader.readLine()) != null ){
                String mode = line.substring(0, line.lastIndexOf('#'));
                String[] parts = mode.split(",");
                String name = parts[0].trim();
                int age = Integer.parseInt(parts[1].trim());
                String location = parts[2].trim();
                String skill = parts[3].trim();
                uploadToDB(name, age, location, skill);
            }

        }catch (IOException exception){
            System.out.println(exception.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void uploadToDB(String name, int age, String location, String skill) throws SQLException{
        Connection conn = connectToDatabase();
        Statement statement = conn.createStatement();
        String uploadSQL = String.format("INSERT INTO developers(name, age, location, skill) VALUES('%S', %d, '%s', '%s')", name, age, location, skill);
        statement.execute(uploadSQL);
        statement.close();
        closeDatabase();
    }

    private Connection connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developer", "root", "123456");
        return connection;
    }

    private void closeDatabase() throws SQLException{
        try{
            if( connection != null ){
                connection.close();
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public ResultSet loadDevelopers() {
        ResultSet resultSet = null;
        try{
            createTable();
            loadFromTextToDB("C:\\Users\\E L I T E B O O K\\IdeaProjects\\Ingryd\\src\\Projects\\Project1\\project.txt");
            Connection conn = connectToDatabase();
            Statement statement = conn.createStatement();
            String selectStatement = "SELECT * FROM developers";
            resultSet = statement.executeQuery(selectStatement);


        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return resultSet;
    }

    public static void main(String[] args) {
        Project project = new Project();
        project.loadDevelopers();
    }


}








