import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/** Primary GUI window for the client application. */
public class LoginPage extends JFrame implements ActionListener {
    

    private JLabel titleLabel;
    private JLabel emailLabel;
    private JLabel pwdLabel;
    private JPasswordField pwdField;
    private JTextField emailTextField;
    private JButton loginBtn;
    private JPanel basePanel;
    private JPanel loginPanel;
    private JPanel loginDataPanel;
    private JButton btnNewButton;



    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    LoginPage irc = new LoginPage();
                    irc.setVisible(true);
                }
            });

        }

    public LoginPage() {
        super("User Login");
        initLayout();
        initComponents();
        initListeners();
    }

    void initLayout() {
        setSize (900, 700);
        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
        setResizable(false);


        // The overall Container
        basePanel = new JPanel();
        basePanel.setBackground(Color.white);
        GroupLayout layout = new GroupLayout(basePanel);
        basePanel.setLayout(layout);
       /* layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);*/

        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER).
                addGroup(layout.createSequentialGroup().
                        addComponent(loginDataPanel()).
                        addComponent(loginPanel(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

        layout.setVerticalGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(Alignment.CENTER).
                        addComponent(loginDataPanel()).
                        addComponent(loginPanel(), 700, 700, 700)));

        add(basePanel);
    }

    private void initComponents() {

        emailTextField = new JTextField();
        titleLabel = new JLabel();
        emailLabel = new JLabel();
        pwdLabel = new JLabel();
        pwdField = new JPasswordField();
        loginBtn = new JButton();



        emailTextField.setMargin(new Insets(30,30,30,30));
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        emailTextField.setForeground(new Color(11,17,58));
        emailTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                new java.awt.Color(10, 11, 38)));



        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setText("Log In");

        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setText("Email");

        pwdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pwdLabel.setForeground(Color.WHITE);
        pwdLabel.setText("Password");

        pwdField.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        pwdField.setForeground(new Color(11,17,58));
        pwdField.setText("pwdField");
        pwdField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                new java.awt.Color(10, 11, 38)));


        loginBtn.setBackground(new Color(0, 204, 204));
        loginBtn.setText("LOGIN");


        GroupLayout jPanel1Layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(emailLabel)
                                                .addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                                .addComponent(pwdField)
                                                .addComponent(pwdLabel)
                                                .addComponent(loginBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(pwdLabel)
                                .addGap(18, 18, 18)
                                .addComponent(pwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170))
        );

    }

    JPanel loginPanel() {
        if (loginPanel == null) {
            loginPanel = new JPanel();
            loginPanel.setPreferredSize(new Dimension(416, 520));
            loginPanel.setBackground(new Color(51, 65,130));

        }

        return loginPanel;
    }

    JPanel loginDataPanel() {
        if (loginDataPanel == null) {
            loginDataPanel = new JPanel(new BorderLayout());
            loginDataPanel.setPreferredSize(new Dimension(160, 520));
            loginDataPanel.setBackground(Color.white);

            // Image View
            String path = "/resources/bidder.png";
            ImageIcon icon = new ImageIcon(WelcomePage.class.getResource(path));
            Image image = icon.getImage(); // transform it
            Image newImg = image.getScaledInstance(400, 317,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newImg);  // transform it back
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBorder(new EmptyBorder(150,0,0,0));//top,left,bottom,right

            // Text View
            JLabel authorText = new JLabel();
            authorText.setText("(c) 2020 Titus Auction Manager - TAMAN");
            authorText.setForeground(new Color(11,17,58));
            authorText.setFont(new Font("Arial",Font.ITALIC,13));
            authorText.setBorder(new EmptyBorder(0,120,60,0));



            loginDataPanel.add(imgLabel, BorderLayout.PAGE_START);
            loginDataPanel.add(authorText, BorderLayout.PAGE_END);
        }

        return loginDataPanel;
    }

    public void initListeners() {
            loginBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String userName = emailTextField.getText();
            String password = pwdField.getText();
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8889/swing_demo",
                        "root", "root");

                PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select name, password from student where name=? and password=?");

                st.setString(1, userName);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    dispose();

                    AuctionBoard auctionBoard = new AuctionBoard();
                    auctionBoard.setVisible(true);

                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                }


            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }/*else if (e.getSource() == resetButton) {
            *//*
             * Here we will dispose the previous frame,
             * show the previous JFrame.
             *//*
            onBackPressed();
        }else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }*/

        }

    public void onBackPressed(){
        /*frame1.setVisible(true);
        frame2.setVisible(false);
        frame2.dispose();*/
    }

}