package Projects.Project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public  class Project implements Developers{


    @Override
    public   ResultSet loadDevelopers(){
        ResultSet result = null;
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developer", "root","123456");
            Statement statement = connection.createStatement();

            //Create Table developers

            String createTable = "CREATE TABLE IF NOT EXISTS developers(id Integer, name Text, age Text, location Text, skill Text)";
            statement.execute(createTable);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }finally{
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException exception){
                    System.out.println(exception.getMessage());
                }
            }
        }

        //Fetch contents of project.txt

         File file = new File("C:\\Users\\E L I T E B O O K\\IdeaProjects\\Ingryd\\src\\Projects\\Project1\\project.txt");
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while( line != null){
                line = reader.readLine();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

        //populate the table with the fetched content
        String insertUserSQL1 = "INSERT INTO developers(name, age, location, skill) VALUES()"


        //Query statement to fetch the loaded contents

        // return the loaded contents
        return result;

    }


    public static void main(String[] args) {
        Project project1 = new Project();
        project1.loadDevelopers();
    }
}








