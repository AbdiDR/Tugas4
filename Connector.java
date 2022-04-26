import java.sql.*;

public class Connector {
    String DBurl      = "jdbc:mysql://localhost/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";

    Statement statement;
    Connection koneksi;

    public Connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            System.out.println("Koneksi gagal " +ex.getMessage());
        }
    }
}
