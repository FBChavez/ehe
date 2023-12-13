import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//Meaw

public class GameFrame extends JFrame{

    public static void main(String[] args) {

        new GameFrame();
    }

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel1, titleNameLabel2, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    // Note: Add functionality to the backButton to return to the menuFrame
    JButton startButton, backButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerHP, scene1EnemiesHP, mentalHP, silverRing;
    String weapon, position;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();

    ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");

    public GameFrame(){
        // Creating the frame
        window = new JFrame();
        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        con = window.getContentPane();

        // Title
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);

        titleNameLabel1 = new JLabel("RESCUE MISSION:");
        titleNameLabel1.setForeground(Color.white);
        titleNameLabel1.setFont(titleFont);

        titleNameLabel2 = new JLabel("SAVE THE PRESENTS");
        titleNameLabel2.setForeground(Color.white);
        titleNameLabel2.setFont(titleFont);

        // Start Button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel1);
        titleNamePanel.add(titleNameLabel2);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        // displaying the "window" frame
        window.setVisible(true);
    }

    public void createGameScreen(){
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea("Main text area");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

//		choice4.setContentAreaFilled(false);  // Disable highlighting on press!!!


        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1,4));
        con.add(playerPanel);
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        weaponLabel.setBackground(Color.red);
        playerPanel.add(weaponLabel);
        weaponLabelName = new JLabel();
        weaponLabelName.setFont(normalFont);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.setFont(normalFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the logic to navigate back to the menuFrame
                dispose();  // Close the current window... Dili mugana
                MenuFrame menuFrame = new MenuFrame("Francis");  // Replace 'MenuFrame' with your actual class
                menuFrame.setVisible(true);
            }
        });
        backButton.setFocusPainted(false);

        // Add backButton to the playerPanel
        playerPanel.add(backButton);

        playerSetup();
    }

    public void playerSetup(){

        playerHP = 10;
        scene1EnemiesHP = 6;
        mentalHP = 3;
        weapon = "None";
        weaponLabelName.setText(weapon);
        hpLabelNumber.setText("" + playerHP);

        choice1.setVisible(true);
        choice2.setVisible(true);
        choice3.setVisible(true);
        choice4.setVisible(true);

        prelude1();
    }

    // Restart
    public void restart(){
        playerSetup();
    }

    // Mental Damage;
    public void dealMentalDamage(int dmg) {
        mentalHP = mentalHP - dmg;
        if (mentalHP < 1) {
            mentalHP = 0;
            playerHP = playerHP - 1;
            if (playerHP < 1) {
                emotionalDeath();
            }
        }

        hpLabelNumber.setText(""+playerHP);
    }

    // Death wish
    public void deathWish(){
        position = "deathWish";
        playerHP = 0;
        mainTextArea.setText("Your wish is granted! An icicle strikes and pierces your body. \n\nGAME OVER");

        choice1.setText("Restart."); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(true);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    // Emotional Damage
    public void emotionalDeath(){
        position = "emotionalDeath";
        playerHP = 0;
        mainTextArea.setText("Your willpower is not strong. Sadness took over you. \n\nGAME OVER");

        choice1.setText("Restart."); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(true);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    // killedByNarrator
    public void killedByNarrator() {
        position = "killedByNarrator";
        playerHP = 0;
        mainTextArea.setText("The Narrator has had enough of you. A giant snowball crushes you to death." +
                "\n\nGAME OVER.");

        choice1.setText("Restart"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(true);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    // Win
    public void win(){
        position = "win";

        mainTextArea.setText("You defeated the subordinates!\nThe subordinates dropped a ring!\n\n(You obtained a Silver Ring)");

        silverRing = 1;

        choice1.setText("Go east");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    // Lose
    public void lose(){
        position = "lose";
        playerHP = 0;
        mainTextArea.setText("You are dead!\n\nGAME OVER");

        choice1.setText("Restart"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(true);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    // Prelude
    public void prelude1(){
        position = "prelude1";
        mainTextArea.setText("Narrator: Twas the night before Christmas, and all through the North Pole, not a creature was stirring, " +
                "except for the mischievous Grinch and his subordinates who had stolen Santa's presents! ");
        choice1.setText("Oh no!"); //
        choice2.setText("How naughty!"); //
        choice3.setText("Not the presents!"); //
        choice4.setText("..."); //
    }

    public void prelude2(){
        position = "prelude2";
        mainTextArea.setText("Narrator: It's up to you to embark on a daring mission to retrieve the stolen gifts and save Christmas.");
        choice1.setText("Bring it on!"); //
        choice2.setText("Let's go!"); //
        choice3.setText("Why me?"); //
        choice4.setText("..."); //
    }

    public void prelude3(){
        position = "prelude3";
        mainTextArea.setText("Narrator: You are the CHOSEN to save Christmas. You got this!");
        choice1.setText("Uhm, if you say so."); //
        choice2.setText("I don't got this."); //
        choice3.setText("I would rather die."); //
        choice4.setText("..."); //
    }

    public void prelude4(){
        position = "prelude4";
        mainTextArea.setText("Narrator: You really got this!");
        choice1.setText("... I'll trust you."); //
        choice2.setText("Okay, fine."); //
        choice3.setText("I would rather die."); //
        choice4.setText("..."); //
    }

    // Receiving Weapon
    public void obtainPocketKnife(){
        position = "obtainPocketKnife";
        mainTextArea.setText("Narrator: As the CHOSEN, you will receive Santa's Pocket Knife. ");

        weapon = "Pocket Knife";

        choice1.setText("Thank you."); //
        choice2.setText("Where's my sword?"); //
        choice3.setText("Who are you anyway?"); //
        choice4.setText("..."); //
    }

    public void noSword() {
        position = "noSword";
        mainTextArea.setText("Narrator: This knife is sufficient for now. Don't ask for more.");

        choice1.setText("If you say so."); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    public void theNarrator() {
        position = "theNarrator";
        mainTextArea.setText("Narrator: You will never know me, but I'll always be watching you behind the shadows.");

        choice1.setText("Please keep me safe."); //
        choice2.setText("(Attack Him)"); //
        choice3.setText("I will trust you."); //
        choice4.setText("..."); //
    }

    public void attackNarrator() {
        position = "attackNarrator";
        mainTextArea.setText("Narrator: Don't try to do something weird on me. Just trust me." +
                "\n\nThe Narrator avoided your attack.");

        choice1.setText("Okay, I'll trust you"); //
        choice2.setText("(Attack Him Again)"); //
        choice3.setText("..."); //
        choice4.setText("");
    }

    public void mentalWarning() {
        position = "mentalWarning";
        mainTextArea.setText("Narrator: If you won't respond properly, your health will eventually lower.");

        choice1.setText("I'll talk, spare me."); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    public void guidance() {
        position = "guidance";
        mainTextArea.setText("Narrator: I'll guide you in saving Santa's Presents. Let's go, shall we?");

        choice1.setText("To Santa's Workshop!"); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene One
    public void scene1() {
        position = "scene1";
        mainTextArea.setText("As you enter Santa's Workshop, you spot three of Grinch's subordinates surrounded by presents." +
                "\n\nWhat will you do?");

        choice1.setText("(Stealth Approach)"); //
        choice2.setText("(Confront Head-On)"); //
        choice3.setText("(Use a Distraction)"); //
        choice4.setText("(Negotiate)");
    }

    // Scene 1 Choice 1
    public void stealthApproach() {
        position = "stealthApproach";
        mainTextArea.setText("The surprise attack works. You recover the presents after stabbing the bad guys." +
                "\n\nYou lose 2 health due to a scuffle.");

        choice1.setText("That was insane of me.");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 Choice 2
    public void playerAttackScene1(){
        position = "playerAttackScene1";

        int playerDamage = 0;

        if(weapon.equals("Pocket Knife")){
            playerDamage = new java.util.Random().nextInt(4);
        }

        mainTextArea.setText("You attacked the Grinch subordinates head-on and gave " + playerDamage + " damage!");

        scene1EnemiesHP = scene1EnemiesHP - playerDamage;

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void grinchAttackScene1(){
        position = "grinchAttackScene1";

        int subordinatesDamage = 0;

        subordinatesDamage = new java.util.Random().nextInt(2);

        mainTextArea.setText("The subordinate/s attacked you and gave " + subordinatesDamage + " damage!");

        playerHP = playerHP - subordinatesDamage;
        if (playerHP < 1) {
            playerHP = 0;
        }
        hpLabelNumber.setText(""+playerHP);

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void successfulFight1() {
        position = "successfulFight1";
        mainTextArea.setText("You defeated Grinch's subordinates.");

        choice1.setText("That was tiring.");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 Choice 3
    public void candyDistraction() {
        position = "candyDistraction";
        mainTextArea.setText("Distracting them by throwing candy canes on the window to make noise gives you the upper hand." +
                "Presents are retrieved. \n\n You escape with a scratch and lose 1 health.");

        choice1.setText("It somehow worked.");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 Choice 4
    public void negotiate() {
        position = "negotiate";
        mainTextArea.setText("Negotiation fail, and the Grinches attacked you. Prepare for a fight.");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 End
    public void scene1End() {
        position = "scene1End";
        mainTextArea.setText("Narrator: Thank you for retrieving these presents, but there is still more to be saved." +
                "\n\nYou see more of Grinch's henchmen outside running. What will you do?");

        choice1.setText("(Chase them)");
        choice2.setText("...");
        choice3.setText("");
        choice4.setText("");
    }

    public class TitleScreenHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            createGameScreen();
        }
    }


    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String yourChoice = event.getActionCommand();

            switch(position){
                case "prelude1":
                    switch(yourChoice){
                        case "c1":
                            prelude2();
                            break;
                        case "c2":
                            prelude2();
                            break;
                        case "c3":
                            prelude2();
                            break;
                        case "c4":
                            dealMentalDamage(1);
                            prelude2();
                            break;
                    }
                    break;
                case "prelude2":
                    switch(yourChoice){
                        case "c1":
                            obtainPocketKnife();
                            break;
                        case "c2":
                            obtainPocketKnife();
                            break;
                        case "c3":
                            prelude3();
                            break;
                        case "c4":
                            dealMentalDamage(1);
                            prelude3();
                            break;
                    }
                    break;
                case "prelude3":
                    switch(yourChoice){
                        case "c1":
                            obtainPocketKnife();
                            break;
                        case "c2":
                            dealMentalDamage(1);
                            prelude4();
                            break;
                        case "c3":
                            deathWish();
                            break;
                        case "c4":
                            dealMentalDamage(1);
                            prelude4();
                            break;
                    }
                    break;
                case "prelude4":
                    switch(yourChoice){
                        case "c1":
                            obtainPocketKnife();
                            break;
                        case "c2":
                            obtainPocketKnife();
                            break;
                        case "c3":
                            deathWish();
                            break;
                        case "c4":
                            dealMentalDamage(1);
                            obtainPocketKnife();;
                            break;
                    }
                    break;
                case "obtainPocketKnife":
                    switch(yourChoice){
                        case "c1":
                            guidance();
                            break;
                        case "c2":
                            noSword();
                            break;
                        case "c3":
                            theNarrator();
                            break;
                        case "c4":
                            dealMentalDamage(1);
                            guidance();;
                            break;
                    }
                    break;
                case "noSword":
                    switch(yourChoice){
                        case "c1":
                            guidance();
                            break;
                        case "c2":
                            dealMentalDamage(1);
                            mentalWarning();
                            break;
                    }
                    break;
                case "theNarrator":
                    switch(yourChoice){
                        case "c1":
                            guidance();
                            break;
                        case "c2":
                            attackNarrator();
                            break;
                        case "c3":
                            guidance();
                            break;
                        case "c4":
                            dealMentalDamage(1);
                            mentalWarning();
                            break;
                    }
                    break;
                case "attackNarrator":
                    switch(yourChoice){
                        case "c1":
                            guidance();
                            break;
                        case "c2":
                            killedByNarrator();
                            break;
                        case "c3":
                            dealMentalDamage(1);
                            mentalWarning();
                            break;

                    }
                    break;
                case "mentalWarning":
                    switch(yourChoice){
                        case "c1":
                            guidance();
                            break;
                        case "c2":
                            dealMentalDamage(3);
                            mentalWarning();
                            break;
                    }
                    break;
                case "guidance":
                    switch(yourChoice){
                        case "c1":
                            scene1();
                            break;
                        case "c2":
                            dealMentalDamage(3);
                            mentalWarning();
                            break;
                    }
                    break;
                // Scene 1
                case "scene1":
                    switch(yourChoice){
                        case "c1":
                            playerHP = playerHP - 2;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                lose();
                            } else {
                                stealthApproach();
                            }
                            break;
                        case "c2":
                            playerAttackScene1();
                            break;
                        case "c3":
                            playerHP = playerHP - 1;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                lose();
                            } else {
                                candyDistraction();
                            }
                            break;
                        case "c4":
                            negotiate();
                            break;
                    }
                    break;
                // Scene 1 outcomes
                case "stealthApproach", "successfulFight1", "candyDistraction":
                    switch(yourChoice){
                        case "c1":
                            scene1End();
                            break;
                    }
                    break;
                case "negotiate":
                    switch(yourChoice){
                        case "c1":
                            grinchAttackScene1();
                            break;
                    }
                    break;
                // Scene 1 Fights
                case "playerAttackScene1":
                    switch(yourChoice){
                        case "c1":
                            if(scene1EnemiesHP > 0) {
                                grinchAttackScene1();
                            } else {
                                successfulFight1();
                            }
                            break;
                    }
                    break;
                case "grinchAttackScene1":
                    switch(yourChoice){
                        case "c1":
                            if(playerHP > 0) {
                                playerAttackScene1();
                            }
                            else {
                                lose();
                            }
                            break;
                    }
                    break;
                // Scene 1 End
                case "scene1End":
                    switch(yourChoice){
                        case "c1":
                            break;
                    }
                    break;


                case "deathWish", "emotionalDeath", "lose", "killedByNarrator":
                    switch(yourChoice){
                        case "c1": restart();
                        break;
                    }
                    break;
            }
        }
    }

}