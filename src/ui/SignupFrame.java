package ui;

import dao.UserDao;
import entity.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

/** Primary GUI window for the client application. */
public class SignupFrame extends JFrame implements ActionListener {


    JLabel titleLabel;
    JLabel emailLabel;
    JLabel telephoneNumber;
    JLabel address;
    JLabel pwdLabel;
    JPasswordField pwdField;
    JTextField emailTextField;
    JTextField telField;
    JTextField addField;
    JButton signUpBtn;
    JPanel basePanel;
    JPanel loginPanel;
    JPanel loginDataPanel;



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SignupFrame irc = new SignupFrame();
                irc.setVisible(true);
            }
        });

    }

    public SignupFrame() {
        super("Sign Up");
        initLayout();
        initComponents();
        initListeners();
    }

    void initLayout() {
        setSize (1080, 700);
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
        telephoneNumber=new JLabel();
        telField=new JTextField();
        address=new JLabel();
        addField=new JTextField();

        pwdLabel = new JLabel();
        pwdField = new JPasswordField();
        signUpBtn = new JButton();



        emailTextField.setMargin(new Insets(30,30,30,30));
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        emailTextField.setForeground(new Color(11,17,58));
        emailTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                new Color(10, 11, 38)));


        telField.setMargin(new Insets(30,30,30,30));
        telField.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        telField.setForeground(new Color(11,17,58));
        telField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                new Color(10, 11, 38)));

        telephoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        telephoneNumber.setForeground(Color.WHITE);
        telephoneNumber.setText("Tel. Number");


        addField.setMargin(new Insets(30,30,30,30));
        addField.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        addField.setForeground(new Color(11,17,58));
        addField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                new Color(10, 11, 38)));

        address.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        address.setForeground(Color.WHITE);
        address.setText("Address");


        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setText("Sign Up");

        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setText("Email");

        pwdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pwdLabel.setForeground(Color.WHITE);
        pwdLabel.setText("Password");

        pwdField.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        pwdField.setForeground(new Color(11,17,58));
        pwdField.setText("");
        pwdField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                new Color(10, 11, 38)));


        signUpBtn.setBackground(new Color(0, 204, 204));
        signUpBtn.setText("SIGN UP");


        GroupLayout jPanel1Layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(emailLabel)
                                                .addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                                .addComponent(pwdField)
                                                .addComponent(pwdLabel)
                                                .addComponent(telephoneNumber)
                                                .addComponent(telField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                                .addComponent(address)
                                                .addComponent(addField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)

                                                .addComponent(signUpBtn, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50,50)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(pwdLabel)
                                .addGap(18, 18, 18)
                                .addComponent(pwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(32, 32, 32)
                                .addComponent(telephoneNumber)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(32, 32, 32)
                                .addComponent(address)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(30,30,30)
                                .addComponent(signUpBtn, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addGap(140, 140, 140))
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
            Image newImg = image.getScaledInstance(400, 317,  Image.SCALE_SMOOTH); // scale it the smooth way
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
        signUpBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
      register();
    }

    private void register() {
        String user = emailTextField.getText();
        String pass = new String(pwdField.getPassword());
        String telephone = telField.getText();
        String address = addField.getText();


        if(user.length() != 0) {
                UserDao dao = new UserDao();
                User target = dao.getUser(user);
                if(target.getUsername() == null) {
                    User newUser = new User();
                    newUser.setUsername(user);
                    newUser.setPassword(pass);
                    newUser.setTel(telephone);
                    newUser.setAddress(address);
                    dao.insert(newUser);
                    JOptionPane.showMessageDialog(null, "Registration Successful");
                    new LoginFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username");
                }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials");
        }
    }


}