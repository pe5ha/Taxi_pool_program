import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class adminWindow extends JFrame{
    private JTabbedPane tabbedPane;
    private JPanel mainpanel;
    private JPanel employees;
    private JTable emptable;
    private JButton addtariff;
    private JPanel tariffs;
    private JPanel cars;
    private JPanel models;
    private JPanel transits;
    private JTable tariftable;
    private JTable cartable;
    private JTable modtable;
    private JTable transtable;
    private JScrollPane empscroll;
    private JButton upd;
    private JScrollPane transscroll;
    private JScrollPane modscroll;
    private JScrollPane carscroll;
    private JScrollPane tarifscroll;
    private JComboBox statbox1;
    private JComboBox statbox2;
    private JLabel stats1;
    private JLabel stats2;
    private JLabel stats3;
    private JLabel stats4;
    private JLabel statlable1;
    private JLabel statlable2;
    private JButton addemployee;
    private JButton addcar;
    private JButton addmodel;
    private JButton addtransit;
    private JPanel undrivers;
    private JTable unoccupiedlist;
    private JScrollPane unoccupiedscroll;
    private JButton notinspect;
    private JButton exit;


    adminWindow(String access,String IP){
        setContentPane(mainpanel);
        setPreferredSize(new Dimension(820,400));



        DefaultTableModel empmodel=new DefaultTableModel();
        emptable.setModel(empmodel);
        emptable.setDefaultEditor(Object.class,null);
        empscroll.setViewportView(emptable);


        DefaultTableModel tarifmodel=new DefaultTableModel();
        tariftable.setModel(tarifmodel);
        tariftable.setDefaultEditor(Object.class,null);
        tarifscroll.setViewportView(tariftable);

        DefaultTableModel carmodel=new DefaultTableModel();
        cartable.setModel(carmodel);
        cartable.setDefaultEditor(Object.class,null);
        carscroll.setViewportView(cartable);

        DefaultTableModel modmodel=new DefaultTableModel();
        modtable.setModel(modmodel);
        modtable.setDefaultEditor(Object.class,null);
        modscroll.setViewportView(modtable);

        DefaultTableModel transmodel=new DefaultTableModel();
        transtable.setModel(transmodel);
        transtable.setDefaultEditor(Object.class,null);
        transscroll.setViewportView(transtable);

        DefaultTableModel unoccList = new DefaultTableModel();
        unoccupiedlist.setModel(unoccList);
        unoccupiedlist.setDefaultEditor(Object.class,null);
//        unoccupiedscroll.setViewportView(unoccupiedlist);

        ArrayList<DefaultTableModel> tableModelList= new ArrayList<>();
        tableModelList.add(empmodel);
        tableModelList.add(tarifmodel);
        tableModelList.add(carmodel);
        tableModelList.add(modmodel);
        tableModelList.add(transmodel);

        update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);


        if(access.equals("ADMIN")) {
            setTitle("Интерфейс руководителя");
            tabbedPane.remove(tariffs);
            tabbedPane.remove(transits);
            tabbedPane.remove(undrivers);
        }
        if(access.equals("MECHANIC")) {
            setTitle("Интерфейс автомеханика");
            tabbedPane.remove(tariffs);
            tabbedPane.remove(transits);
            tabbedPane.remove(undrivers);
            tabbedPane.remove(models);
            tabbedPane.remove(employees);
            cars.remove(addcar);
        }

        if(access.equals("DISPATCHER")){
            setTitle("Интерфейс диспетчера");
            tabbedPane.remove(cars);
            tabbedPane.remove(models);
            tabbedPane.remove(employees);
            tabbedPane.remove(tariffs);
        } else if(access.equals("MARKETER")){
            setTitle("Интерфейс маркетолога");
            tabbedPane.remove(employees);
            tabbedPane.remove(cars);
            tabbedPane.remove(models);
            tabbedPane.remove(undrivers);
//            tabbedPane.remove(transits);
            transits.remove(addtransit);
            setPreferredSize(new Dimension(800,400));

            DefaultComboBoxModel<String> tarifbox=new DefaultComboBoxModel<>();
            statbox1.setModel(tarifbox);

            tarifbox.addElement("Все тарифы");
            for(int i=0;i<tableModelList.get(1).getRowCount();i++){
                tarifbox.addElement(tableModelList.get(1).getValueAt(i,0).toString());
            }
            DefaultComboBoxModel<String> timebox=new DefaultComboBoxModel<>();
            statbox2.setModel(timebox);
            timebox.addElement("За месяц");
            timebox.addElement("За год");
            timebox.addElement("За всё время");
            statbox2.setSelectedIndex(2);


            statupdate(IP,"");

            statbox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String where="";
                    SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
                    switch (statbox2.getSelectedIndex()){
                        case 2:
                            where="";
                            if(statbox1.getSelectedIndex()!=0) where=" where transit_tariffid = "+statbox1.getSelectedItem();
                            break;
                        case 1:
                            where=" where transit_date > date_sub('"+date.format(new Date())+"',interval 1 year)";
                            if(statbox1.getSelectedIndex()!=0) where+=" AND transit_tariffid = "+statbox1.getSelectedItem();
                            break;
                        case 0:
                            where=" where transit_date > date_sub('"+date.format(new Date())+"',interval 1 month)";
                            if(statbox1.getSelectedIndex()!=0) where+=" AND transit_tariffid = "+statbox1.getSelectedItem();
                            break;
                    }
                    statupdate(IP,where);
                }
            });
            statbox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String where="";
                    SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
                    switch (statbox2.getSelectedIndex()){
                        case 2:
                            where="";
                            if(statbox1.getSelectedIndex()!=0) where=" where transit_tariffid = "+statbox1.getSelectedItem();
                            break;
                        case 1:
                            where=" where transit_date > date_sub('"+date.format(new Date())+"',interval 1 year)";
                            if(statbox1.getSelectedIndex()!=0) where+=" AND transit_tariffid = "+statbox1.getSelectedItem();
                            break;
                        case 0:
                            where=" where transit_date > date_sub('"+date.format(new Date())+"',interval 1 month)";
                            if(statbox1.getSelectedIndex()!=0) where+=" AND transit_tariffid = "+statbox1.getSelectedItem();
                            break;
                    }
                    statupdate(IP,where);
                }
            });





        }






        emptable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2 && emptable.rowAtPoint(e.getPoint())>-1){
                    editWindow E=new editWindow(access,IP,0,empmodel,tableModelList,emptable.rowAtPoint(e.getPoint()));
                    E.pack();
                    E.setVisible(true);
                    E.setDefaultCloseOperation(2);
                    E.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        tariftable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2 && tariftable.rowAtPoint(e.getPoint())>-1){
                    editWindow E=new editWindow(access,IP,1,tarifmodel,tableModelList,tariftable.rowAtPoint(e.getPoint()));
                    E.pack();
                    E.setVisible(true);
                    E.setDefaultCloseOperation(2);
                    E.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        cartable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2 && cartable.rowAtPoint(e.getPoint())>-1){
                    editWindow E=new editWindow(access,IP,2,carmodel,tableModelList,cartable.rowAtPoint(e.getPoint()));
                    E.pack();
                    E.setVisible(true);
                    E.setDefaultCloseOperation(2);
                    E.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        modtable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2 && modtable.rowAtPoint(e.getPoint())>-1){
                    editWindow E=new editWindow(access,IP,3,modmodel,tableModelList,modtable.rowAtPoint(e.getPoint()));
                    E.pack();
                    E.setVisible(true);
                    E.setDefaultCloseOperation(2);
                    E.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        if(!access.equals("MARKETER")) {
            transtable.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && transtable.rowAtPoint(e.getPoint()) > -1) {
                        editWindow E = new editWindow(access, IP, 4, transmodel, tableModelList, transtable.rowAtPoint(e.getPoint()));
                        E.pack();
                        E.setVisible(true);
                        E.setDefaultCloseOperation(2);
                        E.addWindowListener(new WindowListener() {
                            @Override
                            public void windowOpened(WindowEvent e) {

                            }

                            @Override
                            public void windowClosing(WindowEvent e) {

                            }

                            @Override
                            public void windowClosed(WindowEvent e) {
                                update(IP, empmodel, tarifmodel, carmodel, modmodel, transmodel,unoccList);
                            }

                            @Override
                            public void windowIconified(WindowEvent e) {

                            }

                            @Override
                            public void windowDeiconified(WindowEvent e) {

                            }

                            @Override
                            public void windowActivated(WindowEvent e) {

                            }

                            @Override
                            public void windowDeactivated(WindowEvent e) {

                            }
                        });
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        addemployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected=tabbedPane.getSelectedIndex();
                if(access=="ADMIN") {
                    if (tabbedPane.getSelectedIndex() == 1) selected = 2;
                    if (tabbedPane.getSelectedIndex() == 2) selected = 3;
                } else if(access=="DISPATCHER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 4;
                } else if(access=="MARKETER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 1;
                }

                editWindow E=new editWindow(access,IP,selected,tableModelList.get(selected),tableModelList,-1);
                E.pack();
                E.setVisible(true);
                E.setDefaultCloseOperation(2);
                E.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
        addtariff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected=tabbedPane.getSelectedIndex();
                if(access=="ADMIN") {
                    if (tabbedPane.getSelectedIndex() == 1) selected = 2;
                    if (tabbedPane.getSelectedIndex() == 2) selected = 3;
                } else if(access=="DISPATCHER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 4;
                } else if(access=="MARKETER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 1;
                }

                editWindow E=new editWindow(access,IP,selected,tableModelList.get(selected),tableModelList,-1);
                E.pack();
                E.setVisible(true);
                E.setDefaultCloseOperation(2);
                E.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
        addcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected=tabbedPane.getSelectedIndex();
                if(access=="ADMIN") {
                    if (tabbedPane.getSelectedIndex() == 1) selected = 2;
                    if (tabbedPane.getSelectedIndex() == 2) selected = 3;
                } else if(access=="DISPATCHER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 4;
                } else if(access=="MARKETER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 1;
                }

                editWindow E=new editWindow(access,IP,selected,tableModelList.get(selected),tableModelList,-1);
                E.pack();
                E.setVisible(true);
                E.setDefaultCloseOperation(2);
                E.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
        addmodel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected=tabbedPane.getSelectedIndex();
                if(access=="ADMIN") {
                    if (tabbedPane.getSelectedIndex() == 1) selected = 2;
                    if (tabbedPane.getSelectedIndex() == 2) selected = 3;
                } else if(access=="DISPATCHER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 4;
                } else if(access=="MARKETER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 1;
                }

                editWindow E=new editWindow(access,IP,selected,tableModelList.get(selected),tableModelList,-1);
                E.pack();
                E.setVisible(true);
                E.setDefaultCloseOperation(2);
                E.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });
        addtransit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected=tabbedPane.getSelectedIndex();
                if(access=="ADMIN") {
                    if (tabbedPane.getSelectedIndex() == 1) selected = 2;
                    if (tabbedPane.getSelectedIndex() == 2) selected = 3;
                } else if(access=="DISPATCHER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 4;
                } else if(access=="MARKETER"){
                    if (tabbedPane.getSelectedIndex() == 0) selected = 1;
                }

                editWindow E=new editWindow(access,IP,selected,tableModelList.get(selected),tableModelList,-1);
                E.pack();
                E.setVisible(true);
                E.setDefaultCloseOperation(2);
                E.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }
        });

        notinspect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fromBD BD = new fromBD(IP);
                if(!notinspect.getText().equals("Показать все")) {

                    carmodel.setRowCount(0);
//                carmodel.setColumnCount(0);
                    BD.notinspected(carmodel);
                    notinspect.setText("Показать все");
                } else {
                    carmodel.setRowCount(0);
                    carmodel.setColumnCount(0);
                    BD.cars(carmodel);
                    notinspect.setText("Только не прошедшие ТО в этом году");
                }
            }
        });

        upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update(IP,empmodel,tarifmodel,carmodel,modmodel,transmodel,unoccList);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow M=new mainWindow();
                M.pack();
                M.setVisible(true);
                dispose();
            }
        });

    }



    public void statupdate(String IP,String where){
        String [] res= new String[4];
        fromBD BD = new fromBD(IP);

        res[0]=BD.selectstats("select count(*) from transits"+where+";");
        res[1]=BD.selectstats("select sum(transit_driverwage) from transits"+where+";");
        res[2]=BD.selectstats("select count(distinct passenger_phone) from transits"+where+";");

        float avg= Float.parseFloat(res[0])/Float.parseFloat(res[2]);
        res[3]=avg+"";


        stats1.setText(res[0]);
        stats2.setText(res[1]);
        stats3.setText(res[2]);
        stats4.setText(res[3]);

    }


    public void update(String  IP,DefaultTableModel empmodel,DefaultTableModel tarifmodel,DefaultTableModel carmodel,DefaultTableModel modmodel,DefaultTableModel transmodel, DefaultTableModel listModel){
        fromBD BD=new fromBD(IP);
        empmodel.setRowCount(0);
        empmodel.setColumnCount(0);
        BD.emp(empmodel);
        tarifmodel.setRowCount(0);
        tarifmodel.setColumnCount(0);
        BD.tarif(tarifmodel);
        carmodel.setRowCount(0);
        carmodel.setColumnCount(0);
        BD.cars(carmodel);
        modmodel.setRowCount(0);
        modmodel.setColumnCount(0);
        BD.models(modmodel);
        transmodel.setRowCount(0);
        transmodel.setColumnCount(0);
        BD.transits(transmodel);
        listModel.setRowCount(0);
        listModel.setColumnCount(0);
        BD.unoccupied(listModel);
    }
}
