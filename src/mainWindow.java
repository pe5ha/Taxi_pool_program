import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mainWindow extends JFrame {
    private JPanel mainpanel;
    private JPanel authpanel;
    private JTextField login;
    private JButton enter;
    private JPasswordField password;
    private JPanel connpanel;
    private JTextField ip;
    private JButton connect;

    private String  ADMIN_LOGIN = "Admin";
    private String  ADMIN_PASSWORD = "108108";
    private String  MANAGER_LOGIN = "Dispatcher";
    private String  MANAGER_PASSWORD = "1111";
    private String  CARSMANAGER_LOGIN = "Marketer";
    private String  CARSMANAGER_PASSWORD = "4242";
    private String  MECHANIC_LOGIN = "Mechanic";
    private String  MECHANIC_PASSWORD = "5500";
    private String IP;
    
    mainWindow(){
        setContentPane(mainpanel);
        setPreferredSize(new Dimension(300,400));
        setLocation(500,200);
        setTitle("Подключение");

        setContentPane(connpanel);
        connpanel.setVisible(true);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    IP = ip.getText();
                    Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
                    System.out.println("Connection to database SUCCESFULL.");
                    conn.close();

                    JOptionPane.showMessageDialog(null,"База данных доступна к подключению","Внимание",1);

                    connpanel.setVisible(false);
                    setContentPane(authpanel);
                    setTitle("Авторизация");

                } catch (SQLException exc) {
                    System.out.println("Connection to database failed...");
                    JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
                    exc.printStackTrace();
                }

            }
        });

//        login.setText("Marketer");
//        password.setText("4242");

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ADMIN_LOGIN.equals(login.getText()) && ADMIN_PASSWORD.equals(new String(password.getPassword()))){
//                    JOptionPane.showMessageDialog(null,"Успешно","Внимание",1);
                    adminWindow A=new adminWindow("ADMIN",IP);
                    A.pack();
                    A.setVisible(true);
                    A.setDefaultCloseOperation(3);
                    dispose();
                } else if(MANAGER_LOGIN.equals(login.getText()) && MANAGER_PASSWORD.equals(new String(password.getPassword()))){
                    adminWindow A=new adminWindow("DISPATCHER",IP);
                    A.pack();
                    A.setVisible(true);
                    A.setDefaultCloseOperation(3);
                    dispose();
                }
                else if(CARSMANAGER_LOGIN.equals(login.getText()) && CARSMANAGER_PASSWORD.equals(new String(password.getPassword()))){
                    adminWindow A=new adminWindow("MARKETER",IP);
                    A.pack();
                    A.setVisible(true);
                    A.setDefaultCloseOperation(3);
                    dispose();
                }
                else if(MECHANIC_LOGIN.equals(login.getText()) && MECHANIC_PASSWORD.equals(new String(password.getPassword()))){
                    adminWindow A=new adminWindow("MECHANIC",IP);
                    A.pack();
                    A.setVisible(true);
                    A.setDefaultCloseOperation(3);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Неверный логин или пароль","Внимание",0);
                }
            }
        });
    }



    public static void main(String a[]){
        mainWindow W=new mainWindow();
        W.pack();
        W.setVisible(true);
        W.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
