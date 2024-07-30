package Hospital.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection con;   //for connecting to the DB
    private Scanner sc;       //for the user input
    
    public Patient(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }
    
    public void addPatient() {
        System.out.println("Enter patient name:");
        String name = sc.next();
        System.out.println("Enter patient age:");
        int age = sc.nextInt();
        System.out.println("Enter patient gender:");
        String gender = sc.next();
        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            int affectRows = ps.executeUpdate();
            if (affectRows > 0) {
                System.out.println("Patient added successfully");
            } else {
                System.out.println("Patient is not added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void viewPatient() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("Patients:");
            System.out.println("*-----------*----------------*--------------*----------------*");
            System.out.println("| Patient Id| Name           |   Age        |  Gender        |");
            System.out.println("*-----------*----------------*--------------*----------------*");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                
            System.out.printf("|%11d|%-17s|%-15d|%-17s|\n", id, name, age, gender);
            System.out.println("*-----------*----------------*--------------*----------------*");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


