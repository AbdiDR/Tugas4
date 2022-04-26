import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login {
    public String username, password;

    Connector connector = new Connector();

    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("Login dan Register");

    JLabel lUsername = new JLabel("Username");
    JTextField tfUsername = new JTextField();
    JLabel lPassword = new JLabel("Password");
    JTextField tfPassword = new JTextField();

    JButton btnLogin = new JButton("Login");
    JButton btnRegister = new JButton("Register");

    public Login() {
        window.setLayout(null);
        window.setSize(400,200);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(3);

//ADD COMPONENT
        window.add(lUsername);
        window.add(tfUsername);
        window.add(lPassword);
        window.add(tfPassword);
        window.add(btnLogin);
        window.add(btnRegister);
//LABEL
        lUsername.setBounds(5, 35, 120, 20);
        lPassword.setBounds(5, 60, 120, 20);


//TEXTFIELD
        tfUsername.setBounds(110, 35, 150, 20);
        tfPassword.setBounds(110, 60, 150, 20);



//BUTTON PANEL
        btnLogin.setBounds(140, 85, 90, 20);
        btnRegister.setBounds(140,105,90,20);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "SELECT * FROM users WHERE username = '" + getUsername() + "' AND password = '" + getPassword() + "'";
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet resultSet = connector.statement.executeQuery(query);

                    if (resultSet.next()){
                        JOptionPane.showMessageDialog(null, "Login Berhasil");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Login gagal, silahkan coba lagi");
                        tfPassword.requestFocus();
                    }
                } catch (SQLException e) {
                    System.out.println("Error");
                }
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String user = tfUsername.getText();
                String pass = tfPassword.getText();

                if(!user.isEmpty() && !pass.isEmpty()){
                    try {
                        String query = "INSERT INTO users(username,password) VALUES ('"+getUsername()+"','"+getPassword()+"')";

                        connector.statement = connector.koneksi.createStatement();
                        connector.statement.executeUpdate(query);

                        System.out.println("Register Berhasil");
                        JOptionPane.showMessageDialog(null,"Register Berhasil !!");
                    } catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else if(user.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Tidak Boleh Kosong");
                }

            }
        });
    }
    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword(){
        return tfPassword.getText();
    }
}
