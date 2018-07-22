/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaphase3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Ayesh
 */
public class DBAPhase3 {

    /**
     * @param args the command line arguments
     */
    public static final String DBURL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    public static final String DBUSER = "TEAM4";
    public static final String DBPASS = "TEAM4";
    public static int cust_id = 0;
   // public static ResultSet customer;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                /*
                    jdbc stuff
                */        
        try{
                // Load Oracle JDBC Driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        
        // Connect to Oracle Database
        Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // make the result set scrolable forward/backward updatable
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        //result set
        ResultSet rs ;
          rs = statement.executeQuery("SELECT max(cust_id) from customer");
        if(rs.next()){
            System.out.println(rs.getInt(1));
            cust_id = rs.getInt(1);
        }
        System.out.println("new cust id will be " + (cust_id + 1));
        String nm;
//        System.out.println("enter name : ");
//        Scanner in = new Scanner(System.in);
//        nm = in.nextLine();
//        rs = statement.executeQuery("Select al_type from albums where name = '" + nm + "'");
//        if(rs.next()){
//            String av = rs.getString("al_type");
//            System.out.println("your chosen name is " + nm + " is " + av );
//        }
//        else
//             System.out.println("your chosen name is " + nm + " is not Available" );
        
        ///////////////////////////////////////////////////////////////////////////////////////
        
        JFrame frame1 = new JFrame("Rhythms of Life - User Application");
        JFrame frame2 = new JFrame("Your Mood Today!");
        frame1.setSize(800,700);
        frame2.setSize(1500,1000);
        frame1.setVisible(true);
        frame2.setVisible(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
       /*
            frame 1 - welcome page
        */     
       BufferedImage myPicture;
       
        try {
            myPicture = ImageIO.read(new File("pic6.jpg"));
            frame1.setIconImage(myPicture);
            
            myPicture = ImageIO.read(new File("pic7.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            
            picLabel.setPreferredSize(new Dimension(200,300));
            
            picLabel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));
            picLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            
            frame1.add(picLabel,BorderLayout.CENTER);
            frame1.getContentPane().setBackground(new Color(150,0,100));
            myPicture = ImageIO.read(new File("welcome.png.png"));
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel pic2 = new JLabel(new ImageIcon(myPicture));
        //    pic2.setPreferredSize(new Dimension(700,100));
            panel.add(pic2);
            myPicture = ImageIO.read(new File("piccc.png"));
            JLabel pic = new JLabel(new ImageIcon(myPicture));
           // pic.setPreferredSize(new Dimension(50,50));
            panel.add(pic);
            panel.setBackground(Color.black);
            frame1.add(panel,BorderLayout.NORTH);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            
        }               
        Icon i=new ImageIcon("pic8.jpg");
        
        JPanel p1= new JPanel();
        JButton b1 = new JButton("Take Me Home!");
        p1.add(b1);
        p1.setBackground(new Color(0,50,100));
        b1.setPreferredSize(new Dimension(200,50));
      //  b1.setIcon(i);
        frame1.add(p1,BorderLayout.SOUTH);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame1.setVisible(false);
                frame2.setVisible(true);
            }
        });
        
        /*
            Frame2 - Main Page
        */
        
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton goback = new JButton("Go Back to Welcome Page!");
        p2.add(goback);
        JButton register = new JButton("Register Now!");
        p2.add(register);
        frame2.add(p2,BorderLayout.SOUTH);
        p2.setBackground(Color.black);
        goback.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame2.setVisible(false);
                frame1.setVisible(true);
            }
        });
        //registration dialog was here
       
        
        
        register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 //registration dialog
        JDialog registration = new JDialog(frame2,"New Registration",true);
        registration.setSize(700, 500);
        registration.setLocation(350, 200);
        JPanel reg_top_panel = new JPanel();
        JLabel reg = new JLabel("Register For Free And in 3 Easy STEPS NOW!");
        reg.setFont(new Font("Dialog",Font.BOLD,28));
        reg.setForeground(new Color(0,0,100));
        reg_top_panel.add(reg);
        registration.add(reg_top_panel,BorderLayout.NORTH);
        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        JLabel ssn = new JLabel("Enter your SSN:");
        ssn.setHorizontalAlignment(SwingConstants.CENTER);
        g.ipadx = 50;
        g.ipady = 0;
        g.gridx = 0;
        g.gridy = 0;
        center.add(ssn,g);
        ssn.setFont(new Font("sansseriff",Font.BOLD,24));
        JTextField SSN = new JTextField();
        SSN.setPreferredSize(new Dimension(200,40));
        g.gridx = 1;
        center.add(SSN,g);
        JLabel name = new JLabel("Enter your name:");
        name.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0;
        g.gridy = 1;
        center.add(name,g);
        name.setFont(new Font("sansseriff",Font.BOLD,24));
        JTextField NAME = new JTextField();
        NAME.setPreferredSize(new Dimension(200,40));
        g.gridx = 1;
        g.gridy = 1;
        center.add(NAME,g);
        JLabel pass = new JLabel("Enter your password:");
        pass.setFont(new Font("sansseriff",Font.BOLD,24));
        pass.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0;
        g.gridy = 2;
        center.add(pass,g);
        JPasswordField passw = new JPasswordField();
        passw.setPreferredSize(new Dimension(200,40));
        g.gridx = 1;
        g.gridy = 2;
        center.add(passw,g);


        JLabel fav = new JLabel("Your favorite Genre:");
        fav.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0;
        g.gridy = 3;
        center.add(fav,g);
        fav.setFont(new Font("sansseriff",Font.BOLD,24));
        JTextField FAV = new JTextField();
        FAV.setPreferredSize(new Dimension(200,40));
        g.gridx = 1;
        g.gridy = 3;
        center.add(FAV,g);
        JPanel east = new JPanel();
        JLabel empty = new JLabel("");
        east.add(empty);
        empty.setPreferredSize(new Dimension(60,300));
        registration.add(east,BorderLayout.EAST);
        JPanel south = new JPanel();
        JButton regi = new JButton("Register");
        south.add(regi);
        regi.setPreferredSize(new Dimension(300,80));
        regi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                String SSn = SSN.getText();
                String Name = NAME.getText();
                String fv = FAV.getText();
                Statement myStmt = con.createStatement();
                int result = myStmt.executeUpdate("insert into customer (SSN, name, favorite_genre,cust_id,password) values('"+SSn+"','"+Name+"','"+fv+"',"+(cust_id+1) +",'"+String.copyValueOf(passw.getPassword())+"')"); 
                cust_id++;
                JOptionPane.showMessageDialog(null, "You have Successfully Registered and your new Customer ID is "+ cust_id);
                registration.setVisible(false);
                }catch(Exception ex){System.out.println(ex);}
            }
        });
        registration.add(south,BorderLayout.SOUTH);
        registration.add(center,BorderLayout.CENTER);
        //registration.setVisible(false);
        registration.dispose();
                registration.setVisible(true);
            }
        });
        
        //end registration dialog

        
        try{
            myPicture = ImageIO.read(new File("pic.jpg"));
            JPanel east_2 = new JPanel();
            JLabel piclabel = new JLabel(new ImageIcon(myPicture));
            piclabel.setPreferredSize(new Dimension(400,1000));
            east_2.add(piclabel);
            frame2.add(east_2,BorderLayout.EAST);
            east_2.setBackground(Color.black);
            JPanel center_2 = new JPanel();
            center_2.setLayout(new BoxLayout(center_2,BoxLayout.Y_AXIS));
            JPanel center_2_0 = new JPanel();
            JLabel comment = new JLabel("<html> WHAT ARE YOU IN FOR <br>TODAY ?</html>");
            comment.setFont(new Font("monospaced",Font.BOLD,60));
            comment.setForeground(new Color(150,0,0));
            center_2_0.add(comment);
            center_2_0.setBackground(Color.black);
            center_2.add(center_2_0);
            JPanel center_2_1 = new JPanel(new GridLayout(1,0));
            myPicture = ImageIO.read(new File("pic8.jpg"));
            JButton album = new JButton(new ImageIcon(myPicture));


            center_2_1.add(album);
            //login dialog was here

         //   login.setVisible(false);
            album.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    /////////

                                //login dialog
            JDialog login = new JDialog(frame2,"Login!",true);
            login.setVisible(false);
            login.setSize(new Dimension(500,300));
            JPanel center_login = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            JLabel user = new JLabel("Customer_ID/Name/SSN:");
            gbc.ipadx = 10;
            gbc.ipady = 0;
            center_login.add(user,gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            JTextField usern = new JTextField();
            usern.setPreferredSize(new Dimension(200,30));
            gbc.ipadx = 20;
            center_login.add(usern,gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            JLabel PASS = new JLabel("Password:");
            center_login.add(PASS,gbc);
            JPasswordField PASSW= new JPasswordField();
            PASSW.setPreferredSize(new Dimension(200,30));
            gbc.gridx = 1;
            gbc.gridy = 1;
            center_login.add(PASSW,gbc);            
            login.add(center_login,BorderLayout.CENTER);
            
            JPanel log_south = new JPanel();
            JButton LOGIN = new JButton("Login!");
            log_south.add(LOGIN);
            login.add(log_south,BorderLayout.SOUTH);
            login.setLocation(500, 300);
            
            LOGIN.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                                                    JFrame albumframe = new JFrame("Browse Music");
        albumframe.setSize(new Dimension(1500,1000));
        albumframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        albumframe.setVisible(false);
                      ResultSet rs = statement.executeQuery("SELECT * FROM customer where ssn = '" + usern.getText()+"' OR name = '" + usern.getText() + "'");
                         if(rs.next()){
                            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5));
                           String passW = String.copyValueOf(PASSW.getPassword());
                            if(rs.getString(5).equals(passW)){
                                albumframe.setVisible(true);
                                frame2.setVisible(false);
                                frame2.dispose();
                                login.setVisible(false);
                                login.dispose();
             /*
                albumframe - customer page
            */

            BufferedImage img;
            JLabel[] pics = new JLabel[10];
            JPanel favorites = new JPanel();
            favorites.setLayout(new GridBagLayout());
               GridBagConstraints gb2 = new GridBagConstraints();
               gb2.gridx=0;
               gb2.gridy = 0;
               gb2.ipadx = 0;
            switch(rs.getString("favorite_genre")){
                case "HEAVYMETAL":
                case "metal":
                case "Metal":
                case "METAL":
                case "HeavyMetal":
                case "heavymetal":img = ImageIO.read(new File("metal1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("metal2.jpg"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("metal3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("metal10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
                case "ROCK":
                case "Rock":
                case "rock":
                case "rocknroll":
                case "BLUES":
                case "blues":
                case "Blues":
                  img =  ImageIO.read(new File("rock1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("rock2.jpg"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("rock3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rock10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
                case "POP":
                case "pop":
                case "Pop":
                case "Modern":
                img =  ImageIO.read(new File("pop1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("pop2.png"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("pop3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("pop10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
                case "HIP HOP":
                case "Hip Hop":
                case "Rap":
                case "rap":
                case "RAP":
                case "RnB":
                case "RNB":
                      img =  ImageIO.read(new File("rap1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("rap2.jpg"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("rap3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("rap10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
                case "Country":
                case "COUNTRY":
                case "country":
                case "Serene":
                  img =  ImageIO.read(new File("con1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("con2.jpg"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("con3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("con10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
                case "Arabic":
                case "arabic":
                case "arab":
                case "ARABIC":
                case "Middle Eastern":
                case "middle eastern":
                      img =  ImageIO.read(new File("ar1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("ar2.jpg"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("ar3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("ar10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
                case "INDIAN":
                case "Indian":
                case "indian":
                case "Desi":
                case "desi":
                case "DESI":
                case "Punjabi":
                case "Bhangra":
                      img =  ImageIO.read(new File("in1.jpg"));
                pics[0] = new JLabel(new ImageIcon(img));
               img =ImageIO.read(new File("in2.jpg"));
                pics[1] = new JLabel(new ImageIcon(img));
                img =ImageIO.read(new File("in3.jpg"));
                pics[2] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in4.jpg"));
                pics[3] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in5.jpg"));
                pics[4] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in6.jpg"));
                pics[5] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in7.jpg"));
                pics[6] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in8.jpg"));
                pics[7] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in9.jpg"));
                pics[8] = new JLabel(new ImageIcon(img));
                img = ImageIO.read(new File("in10.jpg"));
                pics[9] = new JLabel(new ImageIcon(img));
                break;
            }
            int i = 0;
                for(JLabel pic:pics){
                    gb2.gridx = i;
                 favorites.add(pic,gb2);
                 i++;
                            }
            JScrollPane scroll = new JScrollPane(favorites);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            gb2.gridx = 0;
            gb2.gridheight = 4;
            gb2.gridy = GridBagConstraints.RELATIVE;
            gb2.fill = GridBagConstraints.BOTH;
            gb2.gridwidth = 3;
            gb2.anchor = GridBagConstraints.CENTER;
            //JPanel inst = new JPanel();
            JLabel insT = new JLabel("<html> You're a precious customer to us,<br> therefore we have <br> collected for you a <br> list of names of <br> albums which match your <br> favorite genre. <br> Please do take the time <br> to browse through them.<br>Or else get started<br> on the right<br> and choose what<br> you want to search! </html>");
            insT.setHorizontalAlignment(SwingConstants.CENTER);
            insT.setFont(new Font("seriff",Font.BOLD,30));
            insT.setForeground(new Color(100,0,0));
            favorites.add(insT,gb2);
            
            gb2.gridx = 3;

            gb2.ipady = 0;
            gb2.gridy = 1;
            gb2.gridheight = 1;
            gb2.gridwidth = 1;
            gb2.weightx = 1.0;
            gb2.weighty = 0.0;
            gb2.insets = new Insets(3,3,3,3);
            gb2.fill = GridBagConstraints.HORIZONTAL;
            ButtonGroup bg = new ButtonGroup();
            final JRadioButton bart = new JRadioButton("Artist",true);
            bart.setBackground(new Color(0,0,150));
            bart.setForeground(new Color(200,0,0));
            bart.setFont(new Font("seriff",Font.BOLD,20));
            //artist frame profile was here
          
          
            
            //dialog to search artist
            JDialog artist = new JDialog(albumframe,"Search Your Artist",true);
            artist.setSize(700,150);
            artist.setLocation(500, 400);
            JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel Artist = new JLabel("Enter the artist name : ");
            center.add(Artist);
            JTextField enterartist= new JTextField();
            enterartist.setPreferredSize(new Dimension(300,30));
            center.add(enterartist);
            artist.add(center,BorderLayout.CENTER);
                   
            JPanel south = new JPanel();
            JButton search = new JButton("Search");
            south.add(search);
            search.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                              //artist frame profile
            JFrame artistpage = new JFrame("ARTIST PROFILE");
            artistpage.setSize(new Dimension(1500,1000));
            artistpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            artistpage.setVisible(false);
            JPanel artsouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton ok = new JButton("Done/Go Back <-");
            ok.setFont(new Font("seriff",Font.BOLD,20));
            ok.setPreferredSize(new Dimension(200,50));
            artsouth.add(ok);
                   ok.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    artistpage.setVisible(false);
                    artistpage.dispose();
                    albumframe.setVisible(true);                    
                }
            });
            
            artistpage.add(artsouth,BorderLayout.SOUTH);
            artsouth.setBackground(new Color(150,0,0));
            JPanel artcenter = new JPanel(new GridBagLayout());
                JScrollPane scroll2 = new JScrollPane(artcenter);
            scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            GridBagConstraints gbart = new GridBagConstraints();
            
         
            gbart.gridwidth = 3;
            gbart.gridx = 0;
            gbart.gridy = 0;
            gbart.gridheight = 4;
                            try{
                                
                                Statement st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs2 = st2.executeQuery("select * from artist where name = '"+enterartist.getText().toUpperCase() +"'");   
                            
                            if(rs2.next()){
                            //    System.out.println(rs2.getString(1)+" "+rs2.getString(3) + " " + rs2.getInt(2));
                               // ResultSet rs3;
                                
//                                rs3 = st3.executeQuery("select * from artist natural join band where name = '" + enterartist.getText().toUpperCase() + "'");
//                                if(rs2.getString(3).equals("band")){
//                                    System.out.println(rs2.getString(1));
//                                    
//                                    rs3 = st3.executeQuery("select * from artist natural join band where name = '" + enterartist.getText().toUpperCase() + "'");
//                                }
//                                else
//                                    rs3 = st3.executeQuery("select * from artist natural join solo_singer where name ='" + enterartist.getText().toUpperCase() + "'");
                                
                                  //  System.out.println(rs3.getString(1) + " " + rs3.getInt(2) + rs3.getInt(5));
                                  
                                        JOptionPane.showMessageDialog(null, "<html> Your Artist is Found! <br>Name:" + rs2.getString(1) + " <br>No. of awards won: " + rs2.getInt(2) + "<br>Artist Type: " + rs2.getString(3) + "</html");
                                        artist.setVisible(false);
                                       
                                        //JLabel artpic = new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Ayesh\\Pictures\\256 pics\\artist page\\"+rs2.getString(1)+".jpg"))));
                                         JLabel artpic = new JLabel(new ImageIcon(ImageIO.read(new File(rs2.getString(1)+".jpg"))));
                                        artistpage.setVisible(true);
                                        artcenter.add(artpic,gbart);
                                        System.out.println(rs2.getString(1));
                                        gbart.gridy = 5;
                                        gbart.gridx = 0;
                                        JLabel artname = new JLabel("<html> ARTIST NAME : " + rs2.getString(1) + "<br> NO. OF AWARDS : " + rs2.getInt(2) + "<br> ARTIST TYPE : " + rs2.getString(3) + "</html>");
                                        artcenter.add(artname,gbart);
                                        artname.setFont(new Font("seriff",Font.BOLD,20));
                                        artname.setForeground(new Color(150,0,150));
                                        Statement st3 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                                        ResultSet rs4 = st3.executeQuery("select * from albums where artistname = '"+rs2.getString(1)+"'");
                                        int countalbum = 1;
                                        gbart.gridy = 0;
                                        gbart.gridheight = 1;
                                        gbart.gridwidth = 2;
                                        gbart.gridx = 7;
                                        while(rs4.next()){
                                            Statement st4 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
                                            //gbart.gridy = 0;                                            
                                            
                                            ResultSet rs5 = st4.executeQuery("Select branch_id,location from branch natural join stores where product_id = " + rs4.getInt(1));
                                            int branch_id;
                                            String location;
                                            rs5.absolute(1);
                                            branch_id = rs5.getInt(1);
                                            location = rs5.getString(2);
                                           JLabel albumname = new JLabel("<html>" + countalbum + ")<br> ALBUM NAME:"+rs4.getString(3)+"<br>GENRE:"+rs4.getString(2)+"<br> SONGS:"+rs4.getString(5)+"<br> STATUS:"+rs4.getString(4) + "<br> AVAILABLE IN BRANCH NO.:" + branch_id + "<br>BRANCH LOCATION: " + location + "</html>");
                                           albumname.setFont(new Font("seriff",Font.BOLD,20));
                                           albumname.setForeground(new Color(150,0,50));
                                          artcenter.add(albumname,gbart);
                                            System.out.println(countalbum);
                                            countalbum++;
                                            gbart.gridy+=1;
                                        }
                                        
                                        artcenter.setBackground(Color.black);
                                        artistpage.add(scroll2,BorderLayout.CENTER);
                                System.out.println("FAIL");
                                }
//                            else
//                                System.out.println("NOT FOUND");
                           // rs2.close();
                        }catch(Exception ex){System.out.println(ex);}
                        }
                    });
         
                    artist.add(south,BorderLayout.SOUTH);
                    artist.setVisible(false);
                    artist.dispose();
            bart.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    artist.setVisible(true);
                  
                }
            });
           
            final JRadioButton balb = new JRadioButton("Album");
            balb.setBackground(new Color(150,0,0));
            balb.setForeground(new Color(0,0,150));
            balb.setFont(new Font("seriff",Font.BOLD,20));
            
               //dialog to search album
            JDialog searchalbum = new JDialog(albumframe,"Search Your Album",true);
            searchalbum.setSize(700,150);
            searchalbum.setLocation(500, 400);
            searchalbum.setVisible(false);
            JPanel center2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel Album = new JLabel("Enter the album name : ");
            center2.add(Album);
            JTextField enteralbum= new JTextField();
            enteralbum.setPreferredSize(new Dimension(300,30));
            center2.add(enteralbum);
            searchalbum.add(center2,BorderLayout.CENTER);
                   
            JPanel south2 = new JPanel();
            JButton search2 = new JButton("Search");
            south2.add(search2);
            searchalbum.add(south2,BorderLayout.SOUTH);
            //album page was  here
     
            
            
            ///////////////////////////////////////
            search2.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                          //album page
            JDialog albumpage = new JDialog(albumframe,"ALBUM PAGE",true);
            albumpage.setSize(new Dimension(1500,1000));
            albumpage.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            albumpage.setVisible(false);
            JPanel albsouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton ok2 = new JButton("Done/Go Back <-");
            ok2.setFont(new Font("seriff",Font.BOLD,20));
            ok2.setPreferredSize(new Dimension(200,50));
            albsouth.add(ok2);
            ok2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    albumpage.setVisible(false);
                    albumpage.dispose();
                    albumframe.setVisible(true);
                }
            });
            albumpage.add(albsouth,BorderLayout.SOUTH);
            albsouth.setBackground(new Color(150,0,0));
            JPanel albcenter = new JPanel(new GridBagLayout());
                JScrollPane scroll3 = new JScrollPane(albcenter);
            scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scroll3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            albumpage.add(scroll3,BorderLayout.CENTER);
            GridBagConstraints gbart2 = new GridBagConstraints();
            
         
            gbart2.gridwidth = 3;
            gbart2.gridx = 0;
            gbart2.gridy = 0;
            gbart2.gridheight = 4;
                   try{
                    Statement st3 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = st3.executeQuery("select * from albums where name = '"+enteralbum.getText().toUpperCase() +"'"); 
                    while(rs.next()){
                        JLabel albpic = new JLabel(new ImageIcon(rs.getString(3) + ".jpg"));
                        albcenter.add(albpic,gbart2);
                        JLabel albinfo = new JLabel("<html> ALBUM NAME : " + rs.getString(3) + "<br> SONGS : " + rs.getString(5) + "<br> ARTIST: " + rs.getString(7) + "<br> AVAILABILITY : " + rs.getString(4) + "</html>");
                        albinfo.setFont(new Font("seriff",Font.BOLD,20));
                        albinfo.setForeground(new Color(150,0,0));
                        gbart2.gridx = 4;
                        gbart2.gridy = 0;
                        albcenter.add(albinfo);
                    }
                   albumpage.setVisible(true);
                   }
                   catch(Exception ex){
                       System.out.println(ex);
                   }
               }
            });
            balb.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   searchalbum.setVisible(true);
                }
            });
            bg.add(bart);
            bg.add(balb);
            
            favorites.add(bart,gb2);
         gb2.gridx = 3;
            gb2.gridy = GridBagConstraints.RELATIVE;
           // gb2.gridheight = 2;
            bart.setPreferredSize(new Dimension(100,30));
            favorites.add(balb,gb2);
            balb.setPreferredSize(new Dimension(100,30));
            gb2.gridx = 3;
            gb2.gridy = GridBagConstraints.RELATIVE;
            
            favorites.setBackground(Color.black);
            JPanel al_south = new JPanel();
            JLabel al_s_l = new JLabel("");
            al_south.add(al_s_l);
            albumframe.add(al_south,BorderLayout.SOUTH);
            JPanel al_north = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton edit = new JButton("Edit Profile");
            //edit screen was here
    
            
            JButton delete = new JButton("Delete Profile");
            al_north.add(edit);
            edit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                            //edit screen
            JDialog editscreen = new JDialog(albumframe,"Edit Profile",true);
            editscreen.setSize(new Dimension(500,500));
            editscreen.setVisible(false);
            JPanel editpanel = new JPanel();
            editpanel.setLayout(new BoxLayout(editpanel,BoxLayout.PAGE_AXIS));
            JPanel ed_1 = new JPanel(new FlowLayout());
            JLabel n = new JLabel("Name:");
            n.setForeground(new Color(150,0,0));
            n.setFont(new Font("Dialog",Font.BOLD,20));
            ed_1.add(n);
            JTextField N = new JTextField("Enter name here");
            N.selectAll();
            N.setPreferredSize(new Dimension(200,30));
            ed_1.add(N);
            ed_1.setBackground(Color.black);
            editpanel.add(ed_1);
            JPanel ed_2 = new JPanel(new FlowLayout());
            JLabel p = new JLabel("Password:");
            p.setForeground(new Color(150,0,0));
            p.setFont(new Font("Dialog",Font.BOLD,20));
            ed_2.add(p);
            ed_2.setBackground(Color.black);
            JPasswordField P = new JPasswordField("Enter password here");
            P.selectAll();
            P.setPreferredSize(new Dimension(200,30));
            ed_2.add(P);
            editpanel.add(ed_2);
            JPanel ed_3 = new JPanel(new FlowLayout());
            JLabel f = new JLabel("Favorite Genre:");
            f.setForeground(new Color(150,0,0));
            f.setFont(new Font("Dialog",Font.BOLD,20));
            ed_3.add(f);
            JTextField F = new JTextField("Enter genre here");
            F.selectAll();
            ed_3.add(F);
            ed_3.setBackground(Color.black);
            F.setPreferredSize(new Dimension(200,30));
            editpanel.add(ed_3);
            editscreen.add(editpanel,BorderLayout.CENTER);
            JPanel edit_bottom = new JPanel();
            JButton done = new JButton("Done!");
            edit_bottom.add(done);
            edit_bottom.setBackground(new Color(150,0,0));
            editscreen.add(edit_bottom,BorderLayout.SOUTH);
            done.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                    JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO UPDATE?");
                    Statement myStmt = con.createStatement();
                    System.out.println(rs.getString("ssn"));
                    int result = myStmt.executeUpdate("update customer set name='"+N.getText()+ "',password = '" + String.copyValueOf(P.getPassword())+"',favorite_genre ='"+F.getText()+"' where ssn="+rs.getString("ssn"));
                    JOptionPane.showMessageDialog(null, result + "Records Successfully Updated!");
                    editscreen.setVisible(false);
                    editscreen.dispose();
                    }
                    catch(Exception ex){System.out.println(ex);}
                }
            });
                    editscreen.setVisible(true);
                }
            });
            al_north.add(delete);
            delete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                    JOptionPane.showConfirmDialog(null, "ARE YOU REALLY SURE YOU WANT TO LEAVE US? :(");
                    Statement myStmt = con.createStatement();  
                    rs.absolute(1);
                    int result = myStmt.executeUpdate("delete from customer where ssn ="+rs.getString("ssn"));
                    JOptionPane.showMessageDialog(null, result + " Record successfully Deleted!");
                    albumframe.setVisible(false);
                    albumframe.dispose();
                    frame2.setVisible(true);
               //     login.setVisible(false);
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                }
            });
            JButton goback = new JButton("Go Back/Exit Profile");
            al_north.add(goback);
            goback.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    albumframe.dispose();                   
                    frame2.setVisible(true);
                    login.setVisible(false);
                    login.dispose();
                }
            });
            albumframe.add(al_north,BorderLayout.NORTH);
            albumframe.add(scroll,BorderLayout.CENTER);
                                
                             }
                         else
                             JOptionPane.showMessageDialog(null, "Invalid UserName/Password");
                         }
                         else{
                             JOptionPane.showMessageDialog(null, "Invalid UserName/Password");
                         }
                }catch(Exception ex)
                {System.out.println(ex);}
                 login.setVisible(false);
            }
               
                });
                    /////////
                    login.setVisible(true);
                }
            });
            
            myPicture = ImageIO.read(new File("drum.png"));
            
            //instrument page`
            JFrame instrument = new JFrame("Search Instuments!");
            instrument.setSize(new Dimension(1500,1000));
            instrument.setVisible(false);
            instrument.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel inst_panel = new JPanel(new GridLayout(4,4));
            JLabel[] insts = new JLabel[16];
            for(int j =0;j<16;j++){
                insts[j] = new JLabel(new ImageIcon("ins"+(j+1) +".jpg"));
                
                inst_panel.add(insts[j]);
            }
            inst_panel.setBackground(new Color(120,0,0));
            instrument.add(inst_panel,BorderLayout.CENTER);
            JPanel inst_bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton searchins = new JButton("Search Instruments!");
            searchins.setFont(new Font("seriff",Font.BOLD,20));
            JButton gback = new JButton("<- Go Back");
            gback.setFont(new Font("seriff",Font.BOLD,20));
            inst_bottom.add(searchins);
            searchins.setPreferredSize(new Dimension(400,50));
            inst_bottom.add(gback);
            gback.setPreferredSize(new Dimension(200,50));
            inst_bottom.setBackground(Color.black);
            inst_bottom.setPreferredSize(new Dimension(1500,50));
            instrument.add(inst_bottom,BorderLayout.SOUTH);
         
            JButton inst = new JButton(new ImageIcon(myPicture));
            center_2_1.add(inst);
            center_2.add(center_2_1);
            inst.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    instrument.setVisible(true);
                    frame2.setVisible(false);
                    frame2.dispose();
                }
            });
            
            //dialogs for seaching instruments
            JDialog instdialog = new JDialog(instrument,"SEARCH INSTRUMENT",true);
            instdialog.setVisible(false);
            instdialog.setSize(700,200);
            JPanel centerinst = new JPanel(new FlowLayout(FlowLayout.CENTER));
            centerinst.setBackground(new Color(120,100,0));
            JLabel enterinst = new JLabel("Enter Instrument Name:");
            enterinst.setFont(new Font("seriff",Font.BOLD,20));
            centerinst.add(enterinst);
            enterinst.setForeground(new Color(0,30,150));
            JTextField tosearch = new JTextField("Enter here");
            tosearch.selectAll();
            tosearch.setPreferredSize(new Dimension(200,30));
            centerinst.add(tosearch);
            instdialog.add(centerinst,BorderLayout.CENTER);
            JPanel bottomofdialog = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomofdialog.setBackground(new Color(120,0,50));
            JButton searchnow = new JButton("Search->");
            bottomofdialog.add(searchnow);
            instdialog.add(bottomofdialog,BorderLayout.SOUTH);
            searchnow.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e){
                    try{
                        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = statement.executeQuery("SELECT * FROM instruments where inst_type like '%"+tosearch.getText().toUpperCase()+"%'");
                        
                        if(rs.next()){
                            Statement statement2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                            int id = rs.getInt(1);
                            ResultSet rs2 = statement2.executeQuery("select b.location from branch b natural join stores_inst s where s.INSTRUMENT_ID ="+id);
                            while(rs2.next())
                                 JOptionPane.showMessageDialog(null, "<html> AN INSTRUMENT HAS BEEN FOUND <br> INSTRUMENT ID: " + id + "<br> PRICE : $" + rs.getInt(2) + "<br> INSTRUMENT NAME: " + rs.getString(3) + "<br> AVAILABLE IN BRANCH : " + rs2.getString(1) + "</html");
                            instdialog.setVisible(false);
                            instdialog.dispose();
                        }
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                }
            });
            
            searchins.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    instdialog.setVisible(true);
                }
            });
            gback.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    instrument.setVisible(false);
                    instrument.dispose();
                    frame2.setVisible(true);
                }
            });
            ///////////////////////////////////////////////////
            frame2.add(center_2,BorderLayout.CENTER);
            
     
        }catch(Exception e){System.out.println(e);}
        
        
        
        
        
        
        
        }
        catch (Exception e){
            System.out.println(e);
        }
        }
            
        });

       
}
}
