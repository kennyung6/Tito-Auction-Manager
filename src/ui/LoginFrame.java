package ui;


import dao.UserDao;
import entity.User;
import validation.user.UserValidation;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/** Primary GUI window for the client application. */
public final class LoginFrame extends JFrame{
    

    JLabel titleLabel;
    JLabel emailLabel;
    JLabel pwdLabel;
    JPasswordField pwdField;
    JTextField emailTextField;
    JButton loginBtn;
    JPanel basePanel;
    JPanel loginPanel;
    JPanel loginDataPanel;
    JLabel registerText;

    private final UserValidation userValidation;



    public LoginFrame() {
        super("User Login");
        initLayout();
        initComponents();
        userValidation = new UserValidation(this);
    }

    void initLayout() {
        setSize (1080, 700);
        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


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
        registerText = new JLabel();


        // Add Bottom Border And Margin to TextFields
        Border bottomLine = BorderFactory.createMatteBorder(0, 0, 2, 0,
                new Color(10, 11, 38));
        Border empty = new EmptyBorder(0, 10, 0, 0);
        CompoundBorder border = new CompoundBorder(bottomLine, empty);



        // Login Title
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setText("Log In");


        // Email Label
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setText("Email");


        // Email TextField
        emailTextField.setPreferredSize(new Dimension(35,40));
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        emailTextField.setForeground(new Color(11,17,58));
        emailTextField.setBorder(border);


        // Password Label
        pwdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pwdLabel.setForeground(Color.WHITE);
        pwdLabel.setText("Password");


        // Password TextField
        pwdField.setPreferredSize(new Dimension(35,40));
        pwdField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pwdField.setForeground(new Color(11,17,58));
        pwdField.setText("");
        pwdField.setBorder(border);



        // Login Button
        loginBtn.setBackground(new Color(0, 204, 204));
        loginBtn.setText("LOGIN");
        loginBtn.addActionListener(this::loginButtonActionPerformed);

        // Register Text
        registerText.setText("<html><u>New User! Register Here</u><html>");
        registerText.setFont(new Font("Arial",Font.ITALIC,15));
        registerText.setForeground(Color.WHITE);
        registerText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerText.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me)
            {
                SignupFrame signupFrame = new SignupFrame();
                signupFrame.setVisible(true);
                dispose();
            }
        });



        // Layout settings
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
                                                .addComponent(registerText, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE )
                                                .addComponent(loginBtn, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                                .addContainerGap(100, Short.MAX_VALUE) )
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(Alignment.CENTER)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(pwdLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addGap(30,30,30)
                                        .addComponent(registerText, 30,30,30)
                                .addGap(170,170,170)

                                )
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
            String path = "/images/bidder.png";
            ImageIcon icon = new ImageIcon(SplashFrame.class.getResource(path));
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
            authorText.setBorder(new EmptyBorder(0,150,60,0));


            loginDataPanel.add(imgLabel, BorderLayout.PAGE_START);
            loginDataPanel.add(authorText, BorderLayout.PAGE_END);
        }

        return loginDataPanel;
    }

    private void loginButtonActionPerformed(ActionEvent evt) {
        String userName = emailTextField.getText().trim();
        String pass = String.valueOf(pwdField.getPassword()).trim();


                User user = new User();
                user.setUsername(userName);
                user.setPassword(pass);

                UserDao dao = new UserDao();
                List<User> users = dao.getUserList();

                boolean exists = false;
                for (User userNew : users) {
                    if (userNew.getUsername().equals(user.getUsername())) {
                        if (userNew.getPassword().equals(user.getPassword())) {
                            Dashboard dashboard = new Dashboard();
                            dashboard.setVisible(true);
                            dispose();
                            exists = true;
                            break;
                        }
                    }
                }

                if (!exists)
                    JOptionPane.showMessageDialog(null, "Username or Password is incorrect");


        }



}