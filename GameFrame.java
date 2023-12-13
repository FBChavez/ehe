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

//Scene 3 done

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
    int playerHP, mentalHP, silverRing, scene1EnemiesHP, frostyHP;
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
        playerPanel.setLayout(new GridLayout(1,5));
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
                window.setVisible(false);
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
        mentalHP = 3;
        scene1EnemiesHP = 6;
        frostyHP = 20;
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
        hpLabelNumber.setText(""+playerHP);
        mainTextArea.setText("Your wish is granted! An ice shard strikes and pierces your body." +
                "\n\nGAME OVER");

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
        hpLabelNumber.setText(""+playerHP);
        mainTextArea.setText("Your willpower is not strong. Sadness took over you. " +
                "\n\nGAME OVER");

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
        hpLabelNumber.setText(""+playerHP);
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

    // killedByNarrator
    public void giveUp() {
        position = "giveUp";
        playerHP = 0;
        hpLabelNumber.setText(""+playerHP);
        mainTextArea.setText("The Narrator left you and you freeze to death." +
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

        mainTextArea.setText("");

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
        hpLabelNumber.setText(""+playerHP);
        mainTextArea.setText("You are dead!" +
                "\n\nGAME OVER");

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
        mainTextArea.setText("Narrator: As the CHOSEN, you will receive Santa's Knife. ");

        weapon = "Knife";
        weaponLabelName.setText(weapon);

        choice1.setText("Thank you."); //
        choice2.setText("Where's my sword?"); //
        choice3.setText("Who are you anyway?"); //
        choice4.setText("..."); //
    }

    public void noSword() {
        position = "noSword";
        mainTextArea.setText("Narrator: This Knife is sufficient for now. Don't ask for more.");

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

    // Scene 1 Start
    public void scene1() {
        position = "scene1";
        mainTextArea.setText("As you enter Santa's Workshop, you spot three of Grinch's subordinates surrounded by presents." +
                "\n\nWhat will you do?");

        choice1.setText("(Stealth Approach)"); //
        choice2.setText("(Confront Head-On)"); //
        choice3.setText("(Use a Distraction)"); //
        choice4.setText("(Negotiate)"); //
    }

    // Scene 1 Choice 1
    public void stealthApproach() {
        position = "stealthApproach";
        mainTextArea.setText("The surprise attack works. You recover the presents after stabbing the bad guys." +
                "\n\nYou lose 2 health due to a scuffle.");

        choice1.setText("That was insane of me."); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 Choice 2
    public void playerAttackScene1(){
        position = "playerAttackScene1";

        int playerDamage = 0;

        if(weapon.equals("Knife")){
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

        mainTextArea.setText("The subordinate/s attacked you and dealt " + subordinatesDamage + " damage!");

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

        choice1.setText("That was tiring."); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 Choice 3
    public void candyDistraction() {
        position = "candyDistraction";
        mainTextArea.setText("Distracting them by throwing candy canes on the window to make noise gives you the upper hand." +
                "Presents are retrieved." +
                "\n\nYou escape with a scratch and lose 1 health points.");

        choice1.setText("It somehow worked."); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 Choice 4
    public void negotiate() {
        position = "negotiate";
        mainTextArea.setText("Negotiation fails, and the Grinches attacks you. Prepare for a fight.");

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 1 End
    public void scene1End() {
        position = "scene1End";
        mainTextArea.setText("Narrator: Thank you for retrieving these presents, but there is still more to be saved. " +
                "Grinch's henchmen are up to something." +
                "\n\nWhat will you do?");

        choice1.setText("(Chase them)"); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    public void stayStill1() {
        position = "stayStill1";
        mainTextArea.setText("Narrator: Don't just stand there and do nothing.");

        choice1.setText("(Chase them)"); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 2 Start
    public void scene2() {
        position = "scene2";
        mainTextArea.setText("As you chase the Grinch's henchmen into the Snowy Forest, " +
                "magical snow creatures block your path." +
                "\n\nWhat will you do?");

        choice1.setText("(Friendly Interaction)"); //
        choice2.setText("(Intimidation)"); //
        choice3.setText("(Navigate Around)"); //
        choice4.setText("(Gift Offering)"); //
    }

    // Scene 2 Choice 1
    public void friendlyInteraction() {
        position = "friendlyInteraction";
        mainTextArea.setText("Your attempt to befriend the snow creatures is successful. " +
                "The snow spirits appreciate your kindness and let you pass unharmed." +
                "\n\nYou gain 3 Health Points.");

        choice1.setText("Thank you spirits!"); //
        choice2.setText("Yay, I'm healed!"); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 2 Choice 2
    public void intimidation() {
        position = "intimidation";
        mainTextArea.setText("Your intimidation tactics work, " +
                "but you sustain some injuries in the process." +
                "\n\nYou lose 3 Health Points due to frost burns.");

        choice1.setText("So cold and painful."); //
        choice2.setText("Screw these spirits."); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 2 Choice 3
    public void navigateAround() {
        position = "navigateAround";
        mainTextArea.setText("You find a hidden path, avoiding the snow creatures altogether.");

        choice1.setText("Finally, I'm out."); //
        choice2.setText("I'm sneaky."); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 2 Choice 4
    public void giftOffering() {
        position = "giftOffering";
        mainTextArea.setText("The creatures love the sweet gifts and allow you to pass." +
                "\n\nYou gain 3 Health Points and an upgrade to your Knife.");

        weapon = "Knife(+3)";
        weaponLabelName.setText(weapon);

        choice1.setText("Thank you very much."); //
        choice2.setText("Lovely upgrade."); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 2 End
    public void scene2End() {
        position = "scene2End";
        mainTextArea.setText("Narrator: As you leave the Snowy Forest, the Grinch's henchmen has retreated" +
                "into the treacherous Ice Caverns, where slippery slopes and icy traps await." +
                "\n\nWhat will you do?");

        choice1.setText("(Follow them)"); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    public void stayStill2() {
        position = "stayStill2";
        mainTextArea.setText("Narrator: You'll freeze to death if you don't move.");

        choice1.setText("(Follow them)"); //
        choice2.setText("..."); //
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 3 Start
    public void scene3() {
        position = "scene3";
        mainTextArea.setText("You arrive at the Ice Caverns." +
                "\n\nWhat will you do?");

        choice1.setText("(Cautious Descent)"); //
        choice2.setText("(Speedy Descent)"); //
        choice3.setText("(Call Narrator)"); //
        choice4.setText("(Find Another Route)"); //
    }

    // Scene 3 Choice 1
    public void cautiousDescent() {
        position = "cautiousDescent";
        mainTextArea.setText("You descend cautiously, avoiding traps and keeping your health intact.");

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 3 Choice 2
    public void speedyDescent() {
        position = "speedyDescent";
        mainTextArea.setText("An icicle hits you." +
                "\n\nYou lose 5 Health Points.");

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 3 Choice 3
    public void callNarrator() {
        position = "callNarrator";
        mainTextArea.setText("Narrator: My advice is to look for another path.");

        choice1.setText("(Find Another Route)"); //
        choice2.setText("(Cautious Descent)"); //
        choice3.setText("(Speedy Descent)"); //
        choice4.setText("I give up!"); //

    }

    // Scene 3 Choice 4
    public void findAnotherRoute() {
        position = "findAnotherRoute";
        mainTextArea.setText("You find an alternate path and encounter a Frost Giant." +
                "\n\nWhat will you do?");

        choice1.setText("(Attack him)"); //
        choice2.setText("(Ignore him)"); //
        choice3.setText("(Talk to him)"); //
        choice4.setText("(Run)"); //
    }

    public void attackFrosty() {
        position = "attackFrosty";
        mainTextArea.setText("The Frost Giant is offended by your aggression. " +
                "Now you have an angry Frost Giant to deal with.");

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void ignoreFrosty() {
        position = "ignoreFrosty";
        mainTextArea.setText("The Frost Giant feels ignored and gets upset. " +
                "He unleashes a freezing gust of wind. " +
                "Looks like ignoring him wasn't the best choice." +
                "\n\nYou lose 3 Health Points.");

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void talkToFrosty() {
        position = "talkToFrosty";
        mainTextArea.setText("Frosty the Frost Giant is lonely and appreciates your company. " +
                "He allows you to pass peacefully and even shares a warming spell." +
                "\n\nYou gain 3 Health Points.");

        choice1.setText("Goodbye Frosty!"); //
        choice2.setText("I'll be back."); //
        choice3.setText("");
        choice4.setText("");
    }

    public void runFromFrosty() {
        position = "runFromFrosty";
        mainTextArea.setText("The Frost Giant is slow but persistent. " +
                "You manage to outrun him, but not without sustaining " +
                "some minor injuries from his icy attack." +
                "\n\nYou lose 2 Health Points.");

        choice1.setText(">"); //
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void playerAttackScene3(){
        position = "playerAttackScene3";

        int playerDamage = 0;

        if(weapon.equals("Knife")){
            playerDamage = new java.util.Random().nextInt(4);
        }

        if(weapon.equals("Knife(+3)")){
            playerDamage = (new java.util.Random().nextInt(4)) + 3;
        }

        mainTextArea.setText("You attacked the Frost Giant and dealt " + playerDamage + " damage!");

        frostyHP = frostyHP - playerDamage;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void frostyAttackScene3(){
        position = "frostyAttackScene3";

        int frostyDamage = 0;

        frostyDamage = (new java.util.Random().nextInt(4)) + 2;

        mainTextArea.setText("The Frost Giant unleashed a hail storm and dealt " + frostyDamage + " damage!");

        playerHP = playerHP - frostyDamage;
        if (playerHP < 1) {
            playerHP = 0;
        }
        hpLabelNumber.setText(""+playerHP);

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void successfulFight3() {
        position = "successfulFight3";
        mainTextArea.setText("Narrator: I am impressed with your skills." +
                " Here is a reward for you." +
                "\n\nYou gain 7 Health Points and a new weapon.");

        weapon = "Sword";
        weaponLabelName.setText(weapon);

        choice1.setText("How am I alive?!"); //
        choice2.setText("Now you're talking."); //
        choice3.setText("It's a sword!"); //
        choice4.setText("...");
    }

    // Scene 3 End
    public void scene3End() {
        position = "scene3End";
        mainTextArea.setText("Narrator: After reaching the end of the Ice Caverns, " +
                "you follow the path leading to the Grinch's lair where he's guarding the stolen presents.");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    // Scene 4 Start
    public void scene4() {
        position = "scene4";
        mainTextArea.setText("\n\nWhat will you do?");

        choice1.setText("()"); //
        choice2.setText("()"); //
        choice3.setText("()"); //
        choice4.setText("()"); //
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
            // Prelude
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
            // Scene 1 Start
                case "scene1":
                    switch(yourChoice){
                        case "c1":
                            playerHP = playerHP - 2;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                playerHP = 0;
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
                                playerHP = 0;
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
                            mentalHP = 3;
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
                                playerHP = 0;
                                lose();
                            }
                            break;
                    }
                    break;
                // Scene 1 End
                case "scene1End":
                    switch(yourChoice){
                        case "c1":
                            scene2();
                            break;
                        case "c2":
                            dealMentalDamage(1);
                            stayStill1();
                            break;
                    }
                    break;
                case "stayStill1":
                    switch(yourChoice){
                        case "c1":
                            scene2();
                            break;
                        case "c2":
                            dealMentalDamage(1);
                            if(playerHP < 0) {
                                playerHP = 0;
                                lose();
                            }
                            break;
                    }
                    break;
            // Scene 2 Start
                case "scene2":
                    switch(yourChoice){
                        case "c1":
                            playerHP = playerHP + 3;
                            hpLabelNumber.setText(""+playerHP);
                            friendlyInteraction();
                            break;
                        case "c2":
                            playerHP = playerHP - 3;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                playerHP = 0;
                                lose();
                            } else {
                                intimidation();
                            }
                            break;
                        case "c3":
                            navigateAround();
                            break;
                        case "c4":
                            playerHP = playerHP + 3;
                            hpLabelNumber.setText(""+playerHP);
                            giftOffering();
                            break;
                    }
                    break;
                // Scene 2 Outcomes
                case "friendlyInteraction", "intimidation", "navigateAround", "giftOffering":
                    switch(yourChoice){
                        case "c1":
                            scene2End();
                            break;
                        case "c2":
                            scene2End();
                            break;
                    }
                    break;
                // Scene 2 End
                case "scene2End":
                    switch(yourChoice){
                        case "c1":
                            scene3();
                            break;
                        case "c2":
                            dealMentalDamage(1);
                            stayStill2();
                            break;
                    }
                    break;
                case "stayStill2":
                    switch(yourChoice){
                        case "c1":
                            scene3();
                            break;
                        case "c2":
                            dealMentalDamage(1);
                            if(playerHP < 0) {
                                playerHP = 0;
                                lose();
                            }
                            break;
                    }
                    break;
            // Scene 3 Start
                case "scene3":
                    switch(yourChoice){
                        case "c1":
                            cautiousDescent();
                            break;
                        case "c2":
                            playerHP = playerHP - 5;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                playerHP = 0;
                                lose();
                            } else {
                                speedyDescent();
                            }
                            break;
                        case "c3":
                            callNarrator();
                            break;
                        case "c4":
                            findAnotherRoute();
                            break;
                    }
                    break;
                // Scene 3 Outcomes
                case "cautiousDescent", "speedyDescent", "ignoreFrosty", "runFromFrosty":
                    switch(yourChoice){
                        case "c1":
                            scene3End();
                            break;
                    }
                    break;
                case "callNarrator":
                    switch(yourChoice){
                        case "c1":
                            findAnotherRoute();
                            break;
                        case "c2":
                            cautiousDescent();
                            break;
                        case "c3":
                            playerHP = playerHP - 5;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                playerHP = 0;
                                lose();
                            } else {
                                speedyDescent();
                            }
                            break;
                        case "c4":
                            giveUp();
                            break;
                    }
                    break;
                case "findAnotherRoute":
                    switch(yourChoice){
                        case "c1":
                            attackFrosty();
                            break;
                        case "c2":
                            playerHP = playerHP - 3;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                playerHP = 0;
                                lose();
                            } else {
                                ignoreFrosty();
                            }
                            break;
                        case "c3":
                            playerHP = playerHP + 3;
                            hpLabelNumber.setText(""+playerHP);
                            talkToFrosty();
                            break;
                        case "c4":
                            playerHP = playerHP - 2;
                            hpLabelNumber.setText(""+playerHP);
                            if(playerHP < 1) {
                                playerHP = 0;
                                lose();
                            } else {
                                runFromFrosty();
                            }
                            break;
                    }
                    break;
                case "talkToFrosty":
                    switch(yourChoice){
                        case "c1":
                            scene3End();
                            break;
                        case "c2":
                            scene3End();
                            break;
                    }
                    break;
                case "attackFrosty":
                    switch(yourChoice){
                        case "c1":
                            playerAttackScene3();
                            break;
                    }
                    break;
                // Scene 3 Fights
                case "playerAttackScene3":
                    switch(yourChoice){
                        case "c1":
                            if(frostyHP > 0) {
                                frostyAttackScene3();
                            } else {
                                mentalHP = 3;
                                playerHP = playerHP + 7;
                                hpLabelNumber.setText(""+playerHP);
                                successfulFight3();
                            }
                            break;
                    }
                    break;
                case "frostyAttackScene3":
                    switch(yourChoice){
                        case "c1":
                            if(playerHP > 0) {
                                playerAttackScene3();
                            }
                            else {
                                playerHP = 0;
                                lose();
                            }
                            break;
                    }
                    break;
                case "successfulFight3":
                    switch(yourChoice){
                        case "c1":
                            scene3End();
                            break;
                        case "c2":
                            scene3End();
                            break;
                        case "c3":
                            scene3End();
                            break;
                        case "c4":
                            mentalHP = mentalHP -1;
                            scene3End();
                            break;
                    }
                    break;
                // Scene 3 End
                case "scene3End":
                    switch(yourChoice){
                        case "c1":
                            scene4();
                            break;
                    }
                    break;
            // Scene 4 Start


            // Death events
                case "deathWish", "emotionalDeath", "lose", "killedByNarrator", "giveUp":
                    switch(yourChoice){
                        case "c1": restart();
                        break;
                    }
                    break;
            }
        }
    }

}