/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transcripts;

import com.mysql.jdbc.Connection;
import java.sql.*;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor;
import sun.applet.resources.MsgAppletViewer;

/**
 *
 * @author Goitsemodimo Batenegi
 * @serial
 */
public class entryForm extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstm = null;
    Statement stm = null;
    ResultSet rs = null;

    /**
     * Creates new form entryForm
     */
    public entryForm() {
        initComponents();
        initialComponents();
        setGrade();

    }

    private void checkForStudent_ID() {
        int student_ID = Integer.parseInt((String) txtStudent_No.getText());

        //Scanner sc = new Scanner(System.in);
        try {
            

            //student_ID = sc.nextInt();
            String getAll = "select * from students, marks where students.student_id = marks.student_id & "
                    + "students.programme_id = marks.programme_id";
            pstm = conn.prepareStatement(getAll);

            pstm.setString(1, txtStudent_No.getText());

            rs = pstm.executeQuery();

            if (rs.next()) {
                String student_no = rs.getString(1);
                String name = rs.getString(3);
                String middle_name = rs.getString(4);
                String surname = rs.getString(5);
                String date_of_birth = rs.getString(6);
                String national_id_no = rs.getString(7);

                // Populate the relevant text fields with the appropriate values from the database
                txtName.setText(name);
                txtMiddleName.setText(middle_name);
                txtSurname.setText(surname);
                txtStdNo.setText(student_no);
                txtDOB.setText(date_of_birth);
                txtIDNo.setText(national_id_no);

            } else {
                JOptionPane.showMessageDialog(null, "The Student Number entered does not exist!");

            }

        } catch (IllegalArgumentException e) {

            e.getMessage();
        } catch (SQLException ex) {
            Logger.getLogger(entryForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initialComponents() {
        String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};
        DefaultComboBoxModel levelModel = new DefaultComboBoxModel(levels);
        cmbLevel.setModel(levelModel);

        cmbLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if (cmbLevel.getSelectedIndex() == 0 && cmbQualification.getSelectedIndex() == 0) {
                    String[] semesters = {"Semester 1", "Semester 2"};
                    DefaultComboBoxModel cmbSemesterDesc = new DefaultComboBoxModel(semesters);
                    cmbSemester.setModel(cmbSemesterDesc);

                    cmbSemester.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                            if (cmbCourseDescription.getSelectedIndex() == 0 && cmbSemester.getSelectedIndex() == 0) {
                                String[] moduleCodes = {"ALSS 101", "CETG 111", "CHEM 101", "COMP 101", "MATH 101", "PHYS 101"};
                                DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(moduleCodes);
                                cmbModuleCode.setModel(cmbModel);

                                cmbModuleCode.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                                        switch (cmbModuleCode.getSelectedItem().toString()) {
                                            case "ALSS 101":
                                                txtModuleDescription.setText("Technical Writing and Academic Literacy I");
                                                txtCredits.setText("2");
                                                break;
                                            case "CETG 111":
                                                txtModuleDescription.setText("Engineering Graphics");
                                                txtCredits.setText("3");
                                                break;
                                            case "CHEM 101":
                                                txtModuleDescription.setText("General Chemistry I");
                                                txtCredits.setText("4");
                                                break;
                                            case "COMP 101":
                                                txtModuleDescription.setText("Introduction to Computing");
                                                txtCredits.setText("3");
                                                break;
                                            case "MATH 101":
                                                txtModuleDescription.setText("Pre-Calculus");
                                                txtCredits.setText("4");
                                                break;
                                            case "PHYS 101":
                                                txtModuleDescription.setText("Introductory Physics");
                                                txtCredits.setText("4");
                                                break;
                                            default:
                                                break;
                                        }

                                    }
                                });

                            } else if (cmbCourseDescription.getSelectedIndex() == 0 && cmbSemester.getSelectedIndex() == 1) {
                                String[] moduleCodes = {"CETG 121", "CETG 122", "CHEM 102", "PHYS 101", "STAT 102", "MATH 102"};
                                DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(moduleCodes);
                                cmbModuleCode.setModel(cmbModel);

                                cmbModuleCode.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                                        switch (cmbModuleCode.getSelectedItem().toString()) {
                                            case "CETG 121":
                                                txtModuleDescription.setText("Workshop Practise");
                                                txtCredits.setText("3");
                                                break;
                                            case "CETG 122":
                                                txtModuleDescription.setText("Introduction Engineering");
                                                txtCredits.setText("2");
                                                break;
                                            case "CHEM 102":
                                                txtModuleDescription.setText("General Chemistry II");
                                                txtCredits.setText("4");
                                                break;
                                            case "PHYS 102":
                                                txtModuleDescription.setText("Introductory Physics II");
                                                txtCredits.setText("4");
                                                break;
                                            case "STAT 102":
                                                txtModuleDescription.setText("General Statistics I");
                                                txtCredits.setText("3");
                                                break;
                                            case "MATH 102":
                                                txtModuleDescription.setText("Introductory Calculus");
                                                txtCredits.setText("4");
                                                break;
                                            default:
                                                break;
                                        }

                                    }
                                });
                            }
                        }
                    });

                } else if (cmbLevel.getSelectedIndex() == 1 && cmbQualification.getSelectedIndex() == 0) {
                    String[] semesters = {"Semester 1", "Semester 2"};
                    DefaultComboBoxModel cmbSemesterDesc = new DefaultComboBoxModel(semesters);
                    cmbSemester.setModel(cmbSemesterDesc);

                    cmbSemester.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                            if (cmbCourseDescription.getSelectedIndex() == 0 && cmbSemester.getSelectedIndex() == 0) {
                                String[] moduleCodes = {"ALSS 201", "EEEN 211", "COMP 211", "EMTH 201", "MECE 211", "MMEE 210"};
                                DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(moduleCodes);
                                cmbModuleCode.setModel(cmbModel);

                                cmbModuleCode.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                                        switch (cmbModuleCode.getSelectedItem().toString()) {
                                            case "ALSS 201":
                                                txtModuleDescription.setText("Technical Report Writing");
                                                txtCredits.setText("2");
                                                break;
                                            case "EEEN 211":
                                                txtModuleDescription.setText("Fundamentals of Electrical Engineering I");
                                                txtCredits.setText("3");
                                                break;
                                            case "COMP 211":
                                                txtModuleDescription.setText("Procedural Programming");
                                                txtCredits.setText("4");
                                                break;
                                            case "EMTH 201":
                                                txtModuleDescription.setText("Engineering Mathematics I");
                                                txtCredits.setText("3");
                                                break;
                                            case "MECE 211":
                                                txtModuleDescription.setText("Engineering Mechanics (Statistics)");
                                                txtCredits.setText("3");
                                                break;
                                            case "MMEE 210":
                                                txtModuleDescription.setText("Materials Science");
                                                txtCredits.setText("3");
                                                break;
                                            default:
                                                break;
                                        }

                                    }
                                });

                            } else if (cmbCourseDescription.getSelectedIndex() == 0 && cmbSemester.getSelectedIndex() == 1) {
                                String[] moduleCodes = {"COMP 202", "COMP 331", "EEEN 221", "EMTH 202", "EEEN 223", "MECE 221", "CETG 220"};
                                DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(moduleCodes);
                                cmbModuleCode.setModel(cmbModel);

                                cmbModuleCode.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                                        switch (cmbModuleCode.getSelectedItem().toString()) {
                                            case "COMP 202":
                                                txtModuleDescription.setText("Object Oriented Programming");
                                                txtCredits.setText("4");
                                                break;
                                            case "COMP 331 ":
                                                txtModuleDescription.setText("Computer Networks");
                                                txtCredits.setText("4");
                                                break;
                                            case "EEEN 221":
                                                txtModuleDescription.setText("Fundamentals of Electrical Engineering II");
                                                txtCredits.setText("3");
                                                break;
                                            case "EMTH 202":
                                                txtModuleDescription.setText("Engineering Mathematics II");
                                                txtCredits.setText("3");
                                                break;
                                            case "EEEN 223":
                                                txtModuleDescription.setText("Software Development & Management");
                                                txtCredits.setText("4");
                                                break;
                                            case "MECE 221":
                                                txtModuleDescription.setText("Strength of Materials");
                                                txtCredits.setText("3");
                                                break;
                                            case "CETG 220":
                                                txtModuleDescription.setText("Workshop Technology & Skills Development");
                                                txtCredits.setText("4");
                                                break;
                                            default:
                                                break;
                                        }

                                    }
                                });
                            }
                        }
                    });

                }
            }
        });
    }

    //Clear all data fields, including tables and set all selected indexes of the combo boxes to 0
    //That is the first value of each list of items
    public void Clear() {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();

        try {

            txtName.setText("");
            txtStdNo.setText("");

            txtIDNo.setText("");
            txtAcademicYear.setText("");
            txtMark.setText("");
            txtModuleDescription.setText("");
            txtaSGPA.setText("");
            cmbContinuity_Results.setSelectedIndex(0);

            cmbLevel.setSelectedIndex(0);
            cmbSemester.setSelectedIndex(0);
            cmbQualification.setSelectedIndex(0);
            cmbCourseDescription.setSelectedIndex(0);
            cmbModuleCode.setModel(cmbModel);
            txtGrade.setText("");
            txtCredits.setText("");
            txtaTotal_Weighted_GP.setText("");
            txtaTotal_Credits.setText("");

            DefaultTableModel model = (DefaultTableModel) this.tblModuleData.getModel();
            model.setNumRows(0);

        } catch (IllegalStateException | IllegalArgumentException ie) {
            ie.getMessage();
        }

    }

    public void setGrade() {

        try {
            txtMark.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    final double mark = Double.parseDouble((String) txtMark.getText());

                    if (mark >= 90.0) {
                        txtGrade.setText("A+");
                    } else if (mark >= 85 && mark <= 89.9) {
                        txtGrade.setText("A");
                    } else if (mark >= 80 && mark <= 84.9) {
                        txtGrade.setText("A-");

                    } else if (mark >= 75 && mark <= 79.9) {
                        txtGrade.setText("B+");

                    } else if (mark >= 70 && mark <= 74.9) {
                        txtGrade.setText("B");

                    } else if (mark >= 65 && mark <= 69.9) {
                        txtGrade.setText("B-");

                    } else if (mark >= 60 && mark <= 64.9) {
                        txtGrade.setText("C+");

                    } else if (mark >= 55 && mark <= 59.9) {
                        txtGrade.setText("C");

                    } else if (mark >= 50 && mark <= 54.9) {
                        txtGrade.setText("C-");

                    } else if (mark >= 45 && mark <= 49.9) {
                        txtGrade.setText("D+");

                    } else if (mark >= 40 && mark <= 44.9) {
                        txtGrade.setText("D");

                    } else if (mark >= 35 && mark <= 39.9) {
                        txtGrade.setText("D-");

                    } else {
                        txtGrade.setText("E");
                    }
                }
            });

        } catch (IllegalArgumentException ex) {

            ex.getMessage();

        }
    }

    /**
     * This method adds module data to the table, including the calculated
     * weighted grade point average It the saves the information captured to the
     * database
     */
    public void add() {

        int credits = Integer.parseInt((String) txtCredits.getText());
        double mark = Double.parseDouble((String) txtMark.getText());
        double grade_point, weighted_gp = 0;

        try {
            if (mark > 90) {
                grade_point = 5.0;

            } else if (mark >= 85 && mark <= 89.9) {
                grade_point = 4.9;
            } else if (mark >= 80 && mark <= 84.9) {
                grade_point = 4.7;
            } else if (mark >= 75 && mark <= 79.9) {
                grade_point = 4.5;
            } else if (mark >= 70 && mark <= 74.9) {
                grade_point = 4.0;
            } else if (mark >= 65 && mark <= 69.9) {
                grade_point = 3.5;
            } else if (mark >= 60 && mark <= 64.9) {
                grade_point = 3.0;
            } else if (mark >= 55 && mark <= 59.9) {
                grade_point = 2.5;
            } else if (mark >= 50 && mark <= 54.9) {
                grade_point = 2.0;
            } else if (mark >= 45 && mark <= 49.9) {
                grade_point = 1.5;
            } else if (mark >= 40 && mark <= 44.9) {
                grade_point = 1.0;
            } else if (mark >= 35 && mark <= 39.9) {
                grade_point = 0.5;
            } else {
                grade_point = 0.0;
            }

            weighted_gp = credits * grade_point;

        } catch (IllegalArgumentException ex) {
            ex.getMessage();
        }

        DefaultTableModel model = (DefaultTableModel) tblModuleData.getModel();
        model.addRow(new Object[]{cmbModuleCode.getSelectedItem(), txtModuleDescription.getText(), txtCredits.getText(), txtMark.getText(), txtGrade.getText(), weighted_gp});

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpStudent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtStdNo = new javax.swing.JTextField();
        txtIDNo = new javax.swing.JTextField();
        txtMiddleName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        jpLevel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbLevel = new javax.swing.JComboBox<>();
        txtAcademicYear = new javax.swing.JTextField();
        cmbQualification = new javax.swing.JComboBox<>();
        cmbCourseDescription = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModuleData = new javax.swing.JTable();
        jpSemester = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbSemester = new javax.swing.JComboBox<>();
        txtModuleDescription = new javax.swing.JTextField();
        txtMark = new javax.swing.JTextField();
        cmbModuleCode = new javax.swing.JComboBox<>();
        txtCredits = new javax.swing.JTextField();
        txtGrade = new javax.swing.JTextField();
        jpResults = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaSGPA = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        cmbContinuity_Results = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtaTotal_Weighted_GP = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtaTotal_Credits = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCalculateSGPA = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtStudent_No = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STUDENT ACADEMIC RECORD");
        setBackground(new java.awt.Color(204, 255, 204));

        jpStudent.setBackground(new java.awt.Color(204, 102, 0));
        jpStudent.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Perpetua Titling MT", 0, 13))); // NOI18N

        jLabel1.setText("Name:");

        jLabel2.setText("Student Number:");

        jLabel3.setText("Date of Birth:");

        jLabel4.setText("National ID Number:");

        jLabel20.setText("Middle Name:");

        jLabel21.setText("Surname:");

        javax.swing.GroupLayout jpStudentLayout = new javax.swing.GroupLayout(jpStudent);
        jpStudent.setLayout(jpStudentLayout);
        jpStudentLayout.setHorizontalGroup(
            jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(txtMiddleName)
                    .addComponent(txtIDNo)
                    .addComponent(txtStdNo)
                    .addComponent(txtName)
                    .addComponent(txtDOB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpStudentLayout.setVerticalGroup(
            jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStudentLayout.createSequentialGroup()
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStdNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jpLevel.setBackground(new java.awt.Color(204, 153, 0));
        jpLevel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Level Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Felix Titling", 0, 13))); // NOI18N

        jLabel5.setText("Academic Year:");

        jLabel6.setText("Level:");

        jLabel7.setText("Qualification:");

        jLabel8.setText("Course Description:");

        cmbLevel.setEditable(true);

        cmbQualification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bachelor of Enginering", "Bachelor of Science", "Masters of Engineering", "Masters of Science", "Doctor of Philosophy" }));

        cmbCourseDescription.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electrical & Electronics Engineering", "Mechatronics & Industrial Instrumentation", "Mechanical & Energy Engineering", "Geological Engineering", "Civil & Environmental Engineering", "Mining Engineering", "Computer & Telecommunications Engineering", "Materials & Metallurgical Engineering", "Industrial & Manufacturing Engineering", "Chemical Engineering", "Biology & Biotechnology", "Environmental Sciences", "Computer Science & SOftware Engineering", "Forensic Sciences", "Pure & Applied Chemistry", "Earth & Environmental Sciences", "Geology", "Statistics", "Physics", "Pure & Applied Mathematics", "Information Systems & Data Management" }));

        javax.swing.GroupLayout jpLevelLayout = new javax.swing.GroupLayout(jpLevel);
        jpLevel.setLayout(jpLevelLayout);
        jpLevelLayout.setHorizontalGroup(
            jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLevelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addGroup(jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCourseDescription, 0, 342, Short.MAX_VALUE)
                    .addComponent(cmbLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAcademicYear)
                    .addComponent(cmbQualification, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpLevelLayout.setVerticalGroup(
            jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLevelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAcademicYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbQualification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jpLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(cmbCourseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tblModuleData.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tblModuleData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Code", "Module Description", "Credits", "Mark", "Grade", "Weighted_GP_"
            }
        ));
        tblModuleData.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblModuleData);
        tblModuleData.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jpSemester.setBackground(new java.awt.Color(255, 204, 51));
        jpSemester.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Semester Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Felix Titling", 0, 13))); // NOI18N

        jLabel9.setText("Semester:");

        jLabel10.setText("Module Code:");

        jLabel11.setText("Module Description:");

        jLabel12.setText("Credits:");

        jLabel13.setText("Mark:");

        jLabel14.setText("Grade:");

        txtModuleDescription.setEditable(false);

        cmbModuleCode.setEditable(true);
        cmbModuleCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbModuleCodeActionPerformed(evt);
            }
        });

        txtGrade.setEditable(false);

        javax.swing.GroupLayout jpSemesterLayout = new javax.swing.GroupLayout(jpSemester);
        jpSemester.setLayout(jpSemesterLayout);
        jpSemesterLayout.setHorizontalGroup(
            jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSemesterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(9, 9, 9)
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSemester, 0, 342, Short.MAX_VALUE)
                    .addComponent(txtModuleDescription)
                    .addComponent(txtMark)
                    .addComponent(cmbModuleCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCredits)
                    .addComponent(txtGrade))
                .addContainerGap())
        );
        jpSemesterLayout.setVerticalGroup(
            jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSemesterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbModuleCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtModuleDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jpResults.setBackground(new java.awt.Color(153, 102, 0));
        jpResults.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Results", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Felix Titling", 0, 13))); // NOI18N

        jLabel15.setText("Total Weighted GP:");

        txtaSGPA.setColumns(20);
        txtaSGPA.setRows(5);
        jScrollPane2.setViewportView(txtaSGPA);

        jLabel16.setText("Continuity Status:");

        cmbContinuity_Results.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proceed", "Excluded", "Complete" }));

        jLabel17.setText("SGPA:");

        txtaTotal_Weighted_GP.setColumns(20);
        txtaTotal_Weighted_GP.setRows(5);
        jScrollPane3.setViewportView(txtaTotal_Weighted_GP);

        jLabel18.setText("Total Credits:");

        txtaTotal_Credits.setColumns(20);
        txtaTotal_Credits.setRows(5);
        jScrollPane4.setViewportView(txtaTotal_Credits);

        javax.swing.GroupLayout jpResultsLayout = new javax.swing.GroupLayout(jpResults);
        jpResults.setLayout(jpResultsLayout);
        jpResultsLayout.setHorizontalGroup(
            jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResultsLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpResultsLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpResultsLayout.createSequentialGroup()
                        .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbContinuity_Results, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7))
        );
        jpResultsLayout.setVerticalGroup(
            jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultsLayout.createSequentialGroup()
                .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResultsLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel18))
                    .addGroup(jpResultsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpResultsLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpResultsLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)))
                .addGroup(jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbContinuity_Results, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCalculateSGPA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCalculateSGPA.setText("CALCULATE SGPA");
        btnCalculateSGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateSGPAActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrint.setText("PRINT TRANSCRIPT");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("UPDATE DATA");

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setText("ADD MODULE DATA");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Felix Titling", 0, 13))); // NOI18N

        jLabel19.setText("Enter student number");

        btnSearch.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
            .addComponent(txtStudent_No)
            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStudent_No, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpSemester, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpResults, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalculateSGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(227, 227, 227)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jpResults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jpLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalculateSGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jpSemester, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalculateSGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateSGPAActionPerformed
        // The code below calculates and saves the Semester Grade Point Average of the student to the text area

        DefaultTableModel model = (DefaultTableModel) tblModuleData.getModel();

        int total_credits = 0;

        double cgpa = 0.0, total_weighted_gp = 0, sgpa = 0;

        double grade_point = 0;
        String grade = "";
        double mark = Double.parseDouble((String) txtMark.getText());

        int credits = Integer.parseInt((String) txtCredits.getText());

        try {

            for (int i = 0; i < tblModuleData.getRowCount(); i++) {
                total_credits = total_credits + Integer.parseInt(tblModuleData.getValueAt(i, 2).toString());
                total_weighted_gp = total_weighted_gp + Double.parseDouble(tblModuleData.getValueAt(i, 5).toString());

            }

            if (grade.toUpperCase().equals("A+") && mark >= 90) {

                grade_point = 5.0;

            } else if (grade.toUpperCase().equals("A") && mark >= 85 && mark <= 89.9) {
                grade_point = 4.9;

            } else if (grade.toUpperCase().equals("A-") && mark >= 80 && mark <= 84.9) {
                grade_point = 4.7;

            } else if (grade.toUpperCase().equals("B+") && mark >= 75 && mark <= 79.9) {
                grade_point = 4.5;

            } else if (grade.toUpperCase().equals("B") && mark >= 70 && mark <= 74.9) {
                grade_point = 4.0;

            } else if (grade.toUpperCase().equals("B-") && mark >= 65 && mark <= 69.9) {
                grade_point = 3.5;

            } else if (grade.toUpperCase().equals("C+") && mark >= 60 && mark <= 64.9) {
                grade_point = 3.0;

            } else if (grade.toUpperCase().equals("C") && mark >= 55 && mark <= 59.9) {
                grade_point = 2.5;

            } else if (grade.toUpperCase().equals("C-") && mark >= 50 && mark <= 54.9) {
                grade_point = 2.0;

            } else if (grade.toUpperCase().equals("D+") && mark >= 45 && mark <= 49.9) {
                grade_point = 1.5;

            } else if (grade.toUpperCase().equals("D") && mark >= 40 && mark <= 44.9) {
                grade_point = 1.0;

            } else if (grade.toUpperCase().equals("D-") && mark >= 35 && mark <= 39.9) {
                grade_point = 0.5;

            } else if (grade.toUpperCase().equals("E") && mark < 34.9) {
                grade_point = 0.0;

            }

            String total_cred = Integer.toString(total_credits);
            txtaTotal_Credits.setText(total_cred);

            String total_w_gp = Double.toString(total_weighted_gp);
            txtaTotal_Weighted_GP.setText(total_w_gp);

            sgpa = total_weighted_gp / total_credits;

            String sgpa_ = Double.toString(sgpa);
            txtaSGPA.setText(sgpa_);

        } catch (IllegalArgumentException e) {
            e.getMessage();

        }

    }//GEN-LAST:event_btnCalculateSGPAActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // Reset all fields to default values
        Clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Add details of each Module to the table
        // Load details to the form then add the relevant data to the table
        add();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // To remove the selected item from the table
        DefaultTableModel model = (DefaultTableModel) this.tblModuleData.getModel();
        int[] rows = tblModuleData.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            model.removeRow(rows[i] - i);

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        /**
         * To create a database connection with the application to save data to
         * the database, retrieve data, modify and update student data via the
         * application
         *
         */
        String name, id_number, academic_year, level_description, qualification, course, semester, continuity_results, module_code, module_description, grade;
        int student_number, credits;
        float sgpa;
        double mark;
        Date date_of_birth = null;

        //date_of_birth = Date.valueOf((String) txtDOB.getText());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/academic_record", "root", "Biust");
            stm = conn.createStatement();
            //String insStudent = "insert into students values(?, programme_id, ?, ?, ?, ?, ?)";
            String insModule = "insert into modules values((module_id), ?, ?, ?, ?)";
            pstm = conn.prepareStatement(insModule);

            pstm.setString(1, cmbModuleCode.getSelectedItem().toString());
            pstm.setString(2, txtModuleDescription.getText());
            pstm.setString(3, txtAcademicYear.getText());
            pstm.setString(4, txtCredits.getText());

            //pstm.setString(1, txtStdNo.getText());
            //pstm.setString(2, txtName.getText());
            //pstm.setString(3, txtMiddleName.getText());
            //pstm.setString(4, txtSurname.getText());
            //pstm.setString(5, date;
            //pstm.setString(6, txtIDNo.getText());
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Entry has been successsfully saved.");

            //close the mysql connection
            conn.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(entryForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        checkForStudent_ID();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbModuleCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbModuleCodeActionPerformed
        // TODO add your handling code here:
        //initialComponents();
    }//GEN-LAST:event_cmbModuleCodeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(entryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(entryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(entryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(entryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new entryForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCalculateSGPA;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbContinuity_Results;
    private javax.swing.JComboBox<String> cmbCourseDescription;
    private javax.swing.JComboBox<String> cmbLevel;
    private javax.swing.JComboBox<String> cmbModuleCode;
    private javax.swing.JComboBox<String> cmbQualification;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpLevel;
    private javax.swing.JPanel jpResults;
    private javax.swing.JPanel jpSemester;
    private javax.swing.JPanel jpStudent;
    private javax.swing.JTable tblModuleData;
    private javax.swing.JTextField txtAcademicYear;
    private javax.swing.JTextField txtCredits;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtIDNo;
    private javax.swing.JTextField txtMark;
    private javax.swing.JTextField txtMiddleName;
    private javax.swing.JTextField txtModuleDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtStdNo;
    private javax.swing.JTextField txtStudent_No;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextArea txtaSGPA;
    private javax.swing.JTextArea txtaTotal_Credits;
    private javax.swing.JTextArea txtaTotal_Weighted_GP;
    // End of variables declaration//GEN-END:variables
}
