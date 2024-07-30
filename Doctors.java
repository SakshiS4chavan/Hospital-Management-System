package Hospital.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {

    private Connection con;   //for connecting to the DB
    
    public Doctors(Connection con) {
        this.con = con;
    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("Doctors:");
            System.out.println("*-----------*----------------*-----------------------------*");
            System.out.println("| Doctor Id | Name           |  Specialization             |");
            System.out.println("*-----------*----------------*-----------------------------*");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                System.out.printf("|%11d|%-17s|%-29s|\n", id, name, specialization);
                System.out.println("*-----------*----------------*-----------------------------*");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
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
