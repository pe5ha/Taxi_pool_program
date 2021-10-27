import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class fromBD {
    private String IP;

    fromBD(String ip){
        IP=ip;



    }


    public void update(String s){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");

            Statement statement = conn.createStatement();
            statement.executeUpdate(s);

            conn.close();
            statement.close();

        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }
    }

    public String selectstats(String s){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");

            Statement statement = conn.createStatement();
            ResultSet resultSet=statement.executeQuery(s);
            resultSet.next();

            String res=resultSet.getString(1);

            conn.close();
            statement.close();
            return res;
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }
        return null;
    }


    public void transits(DefaultTableModel tableModel){
        tableModel.addColumn("Номер пассажира");
        tableModel.addColumn("Дата поездки");
        tableModel.addColumn("Время посадки");
        tableModel.addColumn("Время выхода");
        tableModel.addColumn("Откуда");
        tableModel.addColumn("Куда");
        tableModel.addColumn("Расстояние");
        tableModel.addColumn("Код водителя");
        tableModel.addColumn("Код тарифа");
        tableModel.addColumn("Прибыль");
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");

            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT * FROM taxi_pool.transits ORDER BY transit_date DESC;");

            while(res.next()){
                tableModel.addRow(new String[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),
                        res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10),});
            }
            conn.close();
            statement.close();

        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }

    }

    public void models(DefaultTableModel tableModel){
        tableModel.addColumn("Код модели");
        tableModel.addColumn("Название");
        tableModel.addColumn("Характеристики");
        tableModel.addColumn("Стоимость");

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");

            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT * FROM taxi_pool.models;");

            while(res.next()){
                tableModel.addRow(new String[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            conn.close();
            statement.close();
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }

    }

    public void cars(DefaultTableModel tableModel){
        tableModel.addColumn("Код машины");
        tableModel.addColumn("Год выпуска");
        tableModel.addColumn("Пробег");
        tableModel.addColumn("Последний техосмотр");
        tableModel.addColumn("Название модели");
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");

            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT * FROM taxi_pool.cars;");

            while(res.next()){
                tableModel.addRow(new String[]{res.getString(1),res.getString(2).substring(0,4),res.getString(3),res.getString(4),res.getString(5)});
            }
            conn.close();
            statement.close();
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }

    }

    public void tarif(DefaultTableModel tableModel){
        tableModel.addColumn("Код тарифа");
        tableModel.addColumn("Название");
        tableModel.addColumn("Цена за километр");
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");

            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT * FROM taxi_pool.tariffs;");

            while(res.next()){
                tableModel.addRow(new String[]{res.getString(1),res.getString(2),res.getString(3)});
            }
            conn.close();
            statement.close();
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }

    }

    public void emp(DefaultTableModel tableModel){
        tableModel.addColumn("Код сотрудника");
        tableModel.addColumn("Имя");
        tableModel.addColumn("Пасспорт");
        tableModel.addColumn("Телефон");
        tableModel.addColumn("Должность");
        tableModel.addColumn("Категория");
        tableModel.addColumn("Номер машины");

        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");


            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT * FROM taxi_pool.employees;");

            while(res.next()){
                tableModel.addRow(new String[]{res.getString(1),res.getString(2),res.getString(3),
                        res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
            }
            conn.close();
            statement.close();
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }
    }
    public void unoccupied(DefaultTableModel listModel){


        listModel.addColumn("Код водителя");
        listModel.addColumn("Имя водителя");
        listModel.addColumn("Номер телефона");
        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");


            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT employee_code, employee_name, employee_phone FROM employees WHERE employee_code not in ( SELECT transit_drivercode FROM transits WHERE transit_timeofexit IS NULL) AND employee_position = 'Driver' AND driver_carid IS NOT NULL;");


            while(res.next()){
                listModel.addRow(new String[]{res.getString(1),res.getString(2),res.getString(3)});
            }
            conn.close();
            statement.close();
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }
    }

    public void notinspected(DefaultTableModel listModel){

        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://"+IP+"/taxi_pool?serverTimezone=Europe/Moscow", "root", "1234");
            System.out.println("Connection to database SUCCESFULL.");


            Statement statement = conn.createStatement();
            ResultSet res=statement.executeQuery("SELECT * FROM taxi_pool.cars WHERE car_lastinspection < DATE_SUB(CURDATE(),INTERVAL 1 YEAR);");

            while(res.next()){
                listModel.addRow(new String[]{res.getString(1),res.getString(2).substring(0,4),res.getString(3),res.getString(4),res.getString(5)});
            }
            conn.close();
            statement.close();
        } catch (SQLException exc) {
            System.out.println("Connection to database failed...");
            JOptionPane.showMessageDialog(null,"Невозможно подключиться к базе данных","Ошибка",0);
            exc.printStackTrace();
        }
    }



}
