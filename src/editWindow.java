import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class editWindow extends JFrame{
    private JPanel mainpanel;
    private JPanel empedit;
    private JPanel tarifedit;
//    private JTextField tarif1;
    private JTextField tarif2;
    private JTextField tarif3;
    private JButton tarifsave;
//    private JTextField emp1;
    private JTextField emp2;
    private JTextField emp3;
    private JTextField emp4;
    private JTextField emp5;
    private JTextField emp6;
    private JComboBox emp7;
    private JButton empsave;
    private JButton empdelete;
    private JPanel caredit;
    private JPanel modedit;
    private JPanel transedit;
    private JTextField car1;
    private JTextField car2;
    private JTextField car3;
    private JTextField car4;
    private JComboBox car5;
    private JButton carsave;
    private JTextField mod1;
    private JTextField mod2;
    private JTextField mod3;
    private JTextField mod4;
    private JButton modsave;
    private JTextField trans1;
    private JButton transsave;
    private JTextField trans2;
    private JTextField trans3;
    private JTextField trans4;
    private JTextField trans5;
    private JTextField trans6;
    private JTextField trans7;
    private JComboBox trans8;
    private JComboBox trans9;
//    private JTextField trans10;
    private JButton tarifdelete;
    private JButton cardelete;
    private JButton moddelete;
    private JButton transdelete;


    editWindow(String access, String IP, int t, DefaultTableModel tableModel, ArrayList<DefaultTableModel> tableModelList, int row){
        setContentPane(mainpanel);
        setPreferredSize(new Dimension(300,400));
        setTitle("Редактирование");
        if(row==-1) setTitle("Добавление записи");

        if(access.equals("MECHANIC")){
            caredit.remove(cardelete);
        }

        switch (t){
            case 0:
                setContentPane(empedit);
                empedit.setVisible(true);

//                ArrayList<String> carbox=new ArrayList<>();

                DefaultComboBoxModel<String> carBoxModel=new DefaultComboBoxModel<>();
                emp7.setModel(carBoxModel);
                carBoxModel.addElement("");

                for(int i=0;i<tableModelList.get(2).getRowCount();i++){
//                   carbox.add(tableModelList.get(1).getValueAt(i,0).toString());
                   carBoxModel.addElement(tableModelList.get(2).getValueAt(i,0).toString());
                }


                if(row>-1) {
//                    emp1.setText("" + tableModel.getValueAt(row, 0));
                    emp2.setText("" + tableModel.getValueAt(row, 1));
                    emp3.setText("" + tableModel.getValueAt(row, 2));
                    emp4.setText((""+tableModel.getValueAt(row, 3)).equals("null")?(""):tableModel.getValueAt(row, 3).toString());
                    emp5.setText((""+tableModel.getValueAt(row, 4)).equals("null")?(""):tableModel.getValueAt(row, 4).toString());
                    emp6.setText((""+tableModel.getValueAt(row, 5)).equals("null")?(""):tableModel.getValueAt(row, 5).toString());
//                    emp7.setText((""+tableModel.getValueAt(row, 6)).equals("null")?(""):tableModel.getValueAt(row, 6).toString());
                    emp7.setSelectedItem((""+tableModel.getValueAt(row, 6)).equals("null")?(""):tableModel.getValueAt(row, 6).toString());
                    empdelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD = new fromBD(IP);
                            BD.update("delete from employees where employee_code=" + tableModel.getValueAt(row, 0) + ";");
                            tableModel.removeRow(row);
                            JOptionPane.showMessageDialog(null,"Удалено","Внимание",1);
                            dispose();
                        }
                    });
                    empsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{"",emp2.getText(),emp3.getText(),emp4.getText(),emp5.getText(),emp6.getText(),emp7.getSelectedItem().toString()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                String upd[]=res.split(",");
                                BD.update("update employees set employee_name="+upd[1]+",employee_passport=" +
                                        upd[2]+",employee_phone="+upd[3]+",employee_position="+upd[4]+",employee_category="+upd[5]+",driver_carid="+upd[6]+" where employee_code="+tableModel.getValueAt(row,0)+";");
                                JOptionPane.showMessageDialog(null,"Запись сохранена","Внимание",1);
                                dispose();
                            }
                        }
                    });
                } else {
                    empdelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    empsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{"",emp2.getText(),emp3.getText(),emp4.getText(),emp5.getText(),emp6.getText(),emp7.getSelectedItem().toString()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                BD.update("insert into employees values (null"+res+");");
                                JOptionPane.showMessageDialog(null,"Запись добавлена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                }

                break;
            case 1:
                setContentPane(tarifedit);
                tarifedit.setVisible(true);
                if(row>-1) {
//                    tarif1.setText("" + tableModel.getValueAt(row, 0));
                    tarif2.setText("" + tableModel.getValueAt(row, 1));
                    tarif3.setText("" + tableModel.getValueAt(row, 2));
                    tarifdelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD = new fromBD(IP);
                            BD.update("delete from tariffs where tariff_id=" + tableModel.getValueAt(row, 0) + ";");
                            tableModel.removeRow(row);
                            JOptionPane.showMessageDialog(null,"Удалено","Внимание",1);
                            dispose();
                        }
                    });
                    tarifsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{"",tarif2.getText(),tarif3.getText()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                String upd[]=res.split(",");
                                BD.update("update tariffs set tariff_title="+upd[1]+",tariff_priceperkm=" +
                                        upd[2]+" where tariff_id="+tableModel.getValueAt(row,0)+";");
                                JOptionPane.showMessageDialog(null,"Запись сохранена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                } else {
                    tarifdelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    tarifsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{"",tarif2.getText(),tarif3.getText()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                BD.update("insert into tariffs values (null" + res + ");");
                                JOptionPane.showMessageDialog(null,"Запись добавлена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                }
                break;
            case 2:
                setContentPane(caredit);
                caredit.setVisible(true);

                if(access.equals("MECHANIC")){
                    car1.setEnabled(false);
                    car2.setEnabled(false);
                    car5.setEnabled(false);
                }

                DefaultComboBoxModel<String> carmodelBox=new DefaultComboBoxModel<>();
                car5.setModel(carmodelBox);

                carmodelBox.addElement("");
                for(int i=0;i<tableModelList.get(3).getRowCount();i++){
                    carmodelBox.addElement(tableModelList.get(3).getValueAt(i,0).toString());
                }

                if(row>-1) {
                    car1.setText("" + tableModel.getValueAt(row, 0));
                    car2.setText("" + tableModel.getValueAt(row, 1));
                    car3.setText("" + tableModel.getValueAt(row, 2));
                    car4.setText((""+tableModel.getValueAt(row, 3)).equals("null")?(""):tableModel.getValueAt(row, 3).toString());
//                    car5.setText((""+tableModel.getValueAt(row, 4)).equals("null")?(""):tableModel.getValueAt(row, 4).toString());
                    car5.setSelectedItem((""+tableModel.getValueAt(row, 4)).equals("null")?(""):tableModel.getValueAt(row, 4).toString());



                    cardelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD = new fromBD(IP);
                            BD.update("delete from cars where car_id=" + tableModel.getValueAt(row, 0) + ";");
                            tableModel.removeRow(row);
                            JOptionPane.showMessageDialog(null,"Удалено","Внимание",1);
                            dispose();
                        }
                    });
                    carsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{car1.getText(),car2.getText(),car3.getText(),car4.getText(),car5.getSelectedItem().toString()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                String upd[]=res.split(",");
                                BD.update("update cars set car_id="+upd[0]+",car_yofmanufacture="+upd[1]+",car_mileage=" +
                                        upd[2]+",car_lastinspection="+upd[3]+",car_modelcode="+upd[4]+" where car_id="+tableModel.getValueAt(row,0)+";");
                                JOptionPane.showMessageDialog(null,"Запись сохранена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                } else {
                    cardelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    carsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{car1.getText(),car2.getText(),car3.getText(),car4.getText(),car5.getSelectedItem().toString()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                BD.update("insert into cars values ("+res+");");
                                JOptionPane.showMessageDialog(null,"Запись добавлена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                }

                break;
            case 3:
                setContentPane(modedit);
                modedit.setVisible(true);

                if(row>-1) {
                    mod1.setText("" + tableModel.getValueAt(row, 0));
                    mod2.setText("" + tableModel.getValueAt(row, 1));
                    mod3.setText("" + tableModel.getValueAt(row, 2));
                    mod4.setText((""+tableModel.getValueAt(row, 3)).equals("null")?(""):tableModel.getValueAt(row, 3).toString());

                    moddelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD = new fromBD(IP);
                            BD.update("delete from models where model_code='" + tableModel.getValueAt(row, 0) + "';");
                            tableModel.removeRow(row);
                            JOptionPane.showMessageDialog(null,"Удалено","Внимание",1);
                            dispose();
                        }
                    });
                    modsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{mod1.getText(),mod2.getText(),mod3.getText(),mod4.getText()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                String upd[]=res.split(",");
                                BD.update("update models set model_code="+upd[0]+",model_title="+upd[1]+",model_specifications=" +
                                        upd[2]+",model_cost="+upd[3]+" where model_code="+tableModel.getValueAt(row,0)+";");
                                JOptionPane.showMessageDialog(null,"Запись сохранена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                } else {
                    moddelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    modsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{mod1.getText(),mod2.getText(),mod3.getText(),mod4.getText()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                BD.update("insert into models values ("+res+");");
                                JOptionPane.showMessageDialog(null,"Запись добавлена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                }

                break;
            case 4:
                setContentPane(transedit);
                transedit.setVisible(true);


                DefaultComboBoxModel<String> tarifbox=new DefaultComboBoxModel<>();
                trans9.setModel(tarifbox);

                tarifbox.addElement("");
                for(int i=0;i<tableModelList.get(1).getRowCount();i++){
                    tarifbox.addElement(tableModelList.get(1).getValueAt(i,0).toString());
                }

                DefaultComboBoxModel<String> driverbox=new DefaultComboBoxModel<>();
                trans8.setModel(driverbox);

                driverbox.addElement("");
//                for(int i=0;i<tableModelList.get(2).getRowCount();i++){
//                    if(tableModelList.get(0).getValueAt(i,4).toString().equals("Driver"))
//                        driverbox.addElement(tableModelList.get(0).getValueAt(i,0).toString());
//                }

                if(row>-1) {

                    DefaultTableModel unoccList = new DefaultTableModel();
                    fromBD BD=new fromBD(IP);
                    BD.unoccupied(unoccList);
                    for(int i=0;i<unoccList.getRowCount();i++){
                        driverbox.addElement(unoccList.getValueAt(i,0).toString());
                    }
                    SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
                    trans1.setText("" + tableModel.getValueAt(row, 0));
                    trans2.setText("" + tableModel.getValueAt(row, 1));
                    trans3.setText("" + tableModel.getValueAt(row, 2));
                    trans4.setText((""+tableModel.getValueAt(row, 3)).equals("null")?("" + time.format(new Date())):tableModel.getValueAt(row, 3).toString());
                    trans5.setText((""+tableModel.getValueAt(row, 4)).equals("null")?(""):tableModel.getValueAt(row, 4).toString());
                    trans6.setText((""+tableModel.getValueAt(row, 5)).equals("null")?(""):tableModel.getValueAt(row, 5).toString());
                    trans7.setText((""+tableModel.getValueAt(row, 6)).equals("null")?(""):tableModel.getValueAt(row, 6).toString());
//                    trans8.setText("" + tableModel.getValueAt(row, 7));
//                    trans9.setText("" + tableModel.getValueAt(row, 8));
//                    trans10.setText("" + tableModel.getValueAt(row, 9));
                    driverbox.addElement("" + tableModel.getValueAt(row, 7));
                    trans8.setSelectedItem("" + tableModel.getValueAt(row, 7));
                    trans9.setSelectedItem("" + tableModel.getValueAt(row, 8));

                    transdelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD = new fromBD(IP);
                            BD.update("delete from transits where " +
                                    "passenger_phone=" + tableModel.getValueAt(row, 0) + " "+
                                    "and transit_date='"+ tableModel.getValueAt(row,1) + "' "+
                                    "and transit_boardingtime='"+tableModel.getValueAt(row,2)+"';");
                            tableModel.removeRow(row);
                            JOptionPane.showMessageDialog(null,"Удалено","Внимание",1);
                            dispose();
                        }
                    });
                    transsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{trans1.getText(),trans2.getText(),trans3.getText(),trans4.getText(),trans5.getText(),trans6.getText(),trans7.getText(),driverbox.getSelectedItem().toString(),tarifbox.getSelectedItem().toString()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                String upd[]=res.split(",");
                                BD.update("update transits set passenger_phone="+upd[0]+",transit_date="+upd[1]+",transit_boardingtime=" +
                                        upd[2]+",transit_timeofexit="+upd[3]+",transit_from="+upd[4]+",transit_to="+upd[5]+",transit_distance="+upd[6]+",transit_drivercode="+upd[7]+",transit_tariffid="+upd[8]+" where " +
                                        "passenger_phone=" + tableModel.getValueAt(row, 0) + " "+
                                        "and transit_date='"+ tableModel.getValueAt(row,1) + "' "+
                                        "and transit_boardingtime='"+tableModel.getValueAt(row,2)+"';");
                                JOptionPane.showMessageDialog(null,"Запись сохранена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                } else {

                    DefaultTableModel unoccList = new DefaultTableModel();
                    fromBD BD=new fromBD(IP);
                    BD.unoccupied(unoccList);

                    for(int i=0;i<unoccList.getRowCount();i++){
                        driverbox.addElement(unoccList.getValueAt(i,0).toString());
                    }

                    SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
                    trans2.setText("" + date.format(new Date()));
                    SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
                    trans3.setText("" + time.format(new Date()));
                    transdelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    transsave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fromBD BD=new fromBD(IP);
                            String res=checker(t,new String[]{trans1.getText(),trans2.getText(),trans3.getText(),trans4.getText(),trans5.getText(),trans6.getText(),trans7.getText(),driverbox.getSelectedItem().toString(),tarifbox.getSelectedItem().toString()},tableModelList,row);
                            if(res.contains("!Ошибка")) JOptionPane.showMessageDialog(null,res,"Внимание",1);
                            else {
                                BD.update("insert into transits values ("+res+",null);");
                                JOptionPane.showMessageDialog(null,"Запись добавлена","Внимание",1);
                                dispose();
                            }

                        }
                    });
                }

                break;

        }

    }




    public String checker(int type,String values[],ArrayList<DefaultTableModel> tableModelList,int row){
        String res="";
        switch (type){
            case 0:
                //if(values[0].equals("")||values[1].equals("")||values[2].equals("")) return "Не все обязательные поля заполнены";
//                    for(int i=0;i<tableModelList.get(0).getRowCount();i++){
//                        if(row!=i && values[0].equals(tableModelList.get(0).getValueAt(i,0))) return "!Ошибка: Сотрудник с таким кодом уже есть в базе";
//                    }
//
//                if(values[0].matches("^[0-9]+$")) res+=values[0];
//                else return "!Ошибка: Неккоректный код сотрудника";

                if(values[1].equals("")) return "!Ошибка: Отстуствует имя сотрудника";
                else res+=",'"+values[1]+"'";

                if(values[2].matches("^[0-9]{10}$")) res+=","+values[2];
                else return "!Ошибка: Неккоректный номер паспорта";

                if(values[3].equals("")) res+=","+"null";
                else if(values[3].matches("^[0-9]{11}$")) res+=","+values[3];
                else return "!Ошибка: Неккоректный номер телефона";

                if(values[4].equals("")) res+=","+"null";
                else res+=",'"+values[4]+"'";

                if(values[5].equals("")) res+=","+"null";
                else res+=",'"+values[5]+"'";

                if(values[6].equals("")) res+=","+"null";
                else {
                    int carfk=0;
                    for(int i=0;i<tableModelList.get(2).getRowCount();i++){
                        if(values[6].equals(tableModelList.get(2).getValueAt(i,0))) carfk++;
                    }
                    if(carfk==0) return "!Ошибка: Нет машины с таким номером";
                    else res+=","+values[6];
                }

                return res;

            case 1:

//                    for(int i=0;i<tableModelList.get(1).getRowCount();i++){
//                        if(row!=i && values[0].equals(tableModelList.get(1).getValueAt(i,0))) return "!Ошибка: Тариф с таким кодом уже есть в базе";
//                    }
//
//                if(values[0].matches("^[0-9]+$")) res+=values[0];
//                else return "!Ошибка: Неккоректный код тарифа";

                if(values[1].equals("")) return "!Ошибка: Отстуствует название тарифа";
                else res+=",'"+values[1]+"'";

                if(values[2].matches("^[0-9]+$")) res+=","+values[2];
                else return "!Ошибка: Неккоректная цена тарифа";

                return res;
            case 2:

                    for(int i=0;i<tableModelList.get(2).getRowCount();i++){
                        if(row!=i && values[0].equals(tableModelList.get(2).getValueAt(i,0))) return "!Ошибка: Машина с таким кодом уже есть в базе";
                    }

                if(values[0].matches("^[0-9]+$")) res+=values[0];
                else return "!Ошибка: Неккоректный код машины";


                if(values[1].matches("^[0-9]{4}$")&&Integer.parseInt(values[1])<2021&&Integer.parseInt(values[1])>2000) res+=",'"+values[1]+"'";
                else return "!Ошибка: Неккоректный год выпуска";


                if(values[2].matches("^[0-9]+$")) res+=","+values[2];
                else return "!Ошибка: Неккоректый пробег";


                if(values[3].equals("")) res+=","+"null";
                else if(correctDate(values[3],2000,2020)==1) res+=",'"+values[3]+"'";
                else return "!Ошибка: Неверная дата";

                int modfk=0;
                for(int i=0;i<tableModelList.get(3).getRowCount();i++){
                    if(values[4].equals(tableModelList.get(3).getValueAt(i,0))) modfk++;
                }
                if(modfk==0) return "!Ошибка: Такой модели в базе не существует";
                else res+=",'"+values[4]+"'";

                return res;
            case 3:

                    for(int i=0;i<tableModelList.get(3).getRowCount();i++){
                        if(row!=i && values[0].equals(tableModelList.get(3).getValueAt(i,0))) return "!Ошибка: Такая модель уже есть в базе";
                    }

                if(values[0].equals("")) return "!Ошибка: Неккоректный код модели";
                else res+="'"+values[0]+"'";

                if(values[1].equals("")) return "!Ошибка: Отсутсвует название модели";
                else res+=",'"+values[1]+"'";

                if(values[2].equals("")) return "!Ошибка: Отсутсвует спецификации модели";
                else res+=",'"+values[2]+"'";

                if(values[3].matches("^[0-9]+$")) res+=","+values[3];
                else return "!Ошибка: Отсутствует стоимость модели";

                return res;
            case 4:
                    for(int i=0;i<tableModelList.get(4).getRowCount();i++){
                        if(row!=i && values[0].equals(tableModelList.get(4).getValueAt(i,0))&&
                        values[1].equals(tableModelList.get(4).getValueAt(i,1))&&
                        values[2].equals(tableModelList.get(4).getValueAt(i,2))) return "!Ошибка: Такая запись уже есть в базе";
                    }

                if(values[0].matches("^[0-9]{11}$")) res+=values[0];
                else return "!Ошибка: Неккоректный номер телефона";

                if(correctDate(values[1],2000,2020)==1) res+=",'"+values[1]+"'";
                else return "!Ошибка: Неверная дата";

                if(correctTime(values[2])) res+=",'"+values[2]+"'";
                else return "!Ошибка: Неверное время";

                if(values[3].equals("")) res+=","+"null";
                else if(correctTime(values[3])) res+=",'"+values[3]+"'";
                else return "!Ошибка: Неверное время";

                if(values[4].equals("")) return "!Ошибка: Не указано место отправления";
                else res+=",'"+values[4]+"'";

                if(values[5].equals("")) return "!Ошибка: Не указано место прибытия";
                else res+=",'"+values[5]+"'";

                if(values[6].matches("^[0-9]+$")) res+=","+values[6];
                else return "!Ошибка: Не указана дистанция";

                int driverfk=0;
                for(int i=0;i<tableModelList.get(0).getRowCount();i++){
                    if(values[7].equals(tableModelList.get(0).getValueAt(i,0))&&
                            tableModelList.get(0).getValueAt(i,4).equals("Driver")) driverfk++;
                }
                if(driverfk==0) return "!Ошибка: Отсутствует водитель с таким кодом";
                else res+=","+values[7];

                int tariffk=0;
                for(int i=0;i<tableModelList.get(1).getRowCount();i++){
                    if(values[8].equals(tableModelList.get(1).getValueAt(i,0))) tariffk++;
                }
                if(tariffk==0) return "!Ошибка: Несуществующий тариф";
                else res+=","+values[8];

                return res;
        }
        return "!Ошибка";
    }


    public int correctDate(String date,int since, int until){
        if(!date.matches("\\d{4}[-]\\d{2}[-]\\d{2}")) return 0;
        String d[] = date.split("[-]");
        int ddmmyy[]= new int[3];
        for(int i=0;i<3;i++){
            ddmmyy[i]= Integer.parseInt(d[i]);
        }
        if(ddmmyy[2]>31||ddmmyy[2]<1) return 0;
        if(ddmmyy[1]>12||ddmmyy[1]<1) return 0;
        if(ddmmyy[2]==31){
            for(int i=2;i<=12;i+=2){
                if(i==8)i++;
                if(ddmmyy[1]==i) return 0;
            }
        }
        if(ddmmyy[2]==30&&ddmmyy[1]==2) return 0;
        if(ddmmyy[2]==29&&ddmmyy[1]==2){
            if(ddmmyy[0]%400!=0){
                if(ddmmyy[0]%100==0) return 0;
                if(ddmmyy[0]%4!=0) return 0;
            }
        }
        if(ddmmyy[0]>until||ddmmyy[0]<since) return -1;
        return 1;
    }
    public boolean correctTime(String time){
        if(!time.matches("\\d{2}[:]\\d{2}[:]\\d{2}")) return false;
        String t[]=time.split(":");
        int hhmmss[]=new int[3];
        for(int i=0;i<3;i++){
            hhmmss[i]=Integer.parseInt(t[i]);
        }
        if(hhmmss[0]<0 || hhmmss[0]>23) return false;
        if(hhmmss[1]<0 || hhmmss[1]>59) return false;
        if(hhmmss[2]<0 || hhmmss[2]>59) return false;
        return true;
    }

}
