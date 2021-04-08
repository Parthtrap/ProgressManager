import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProgressManager implements ActionListener {

    JFrame frame;
    JTextField heading;
    JPanel itemList;                // Store the item names
    JPanel progressList;            // Store the progress bars
    JPanel currentProgress;         // Store the current progress measure
    JPanel progressAdder;           // Store the area to add progress
    JPanel progressAdderButtons;    // Store buttons to accept progress
    JPanel delButtonList;           // Store the button to delete a specific entry
    JPanel inputPanel;              // Store the text area and buttons to input from user
    JTextField[] item = new JTextField[10];                                     // TextField to store items
    JProgressBar[] progressBar = new JProgressBar[10];                          // Progress Bars
    JTextField[] progressCounter = new JTextField[10];                          // Display Current Progress
    JTextField[] progressInput = new JTextField[10];                            // Input area where you type additional progress
    JButton[] progressInputButton = new JButton[10];                            // Progress Input buttons
    JButton[] deleteItem = new JButton[10];                                     // Delete Item from the list
    JTextField itemInput;                           // Input item here
    JTextField maxProgressInput;                    // Input the item's max progress here
    JButton accept;                                 // Accept Item
    JButton exit;
    Font headingFont;
    Font delItemFont;
    GridLayout displayLayout = new GridLayout(10, 1, 10, 10);

    int[] maxProgressNumber = new int[10];
    int[] currentProgressNumber = new int[10];
    int index = 0;

    ProgressManager(){

        // Frame
        frame = new JFrame("Progress Manager");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);           //Not to permanent close the application
        frame.setSize(1170, 750);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#070f1d"));
        frame.setResizable(false);

        // Fonts
        headingFont = new Font("Castellar", Font.BOLD, 50);
        delItemFont = new Font("Castellar", Font.BOLD, 15);

        // Heading
        heading = new JTextField();
        heading.setBounds(240, 0, 700, 90);
        heading.setText("Progress Manager");
        heading.setEditable(false);
        heading.setBackground(Color.decode("#070f1d"));
        heading.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));
        heading.setForeground(Color.LIGHT_GRAY);
        heading.setFont(headingFont);
        heading.setHorizontalAlignment(JTextField.CENTER);

        // Input Panel
        inputPanel = new JPanel();
        inputPanel.setBounds(230,120,700, 40);
        inputPanel.setLayout(new GridLayout(1,2,10,20));
        inputPanel.setBackground(Color.decode("#070f1d"));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Input Items
        itemInput = new JTextField();
        itemInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                if(itemInput.getText().length()>=15) // Limit to 4 characters
                {
                    itemInput.setText(itemInput.getText().substring(0,14));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        maxProgressInput = new JTextField();
        maxProgressInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8) {
                    maxProgressInput.setEditable(true);
                } else {
                    maxProgressInput.setEditable(false);
                }
            }
        });
        accept = new JButton();
        accept.addActionListener(this);
        accept.setFocusable(false);
        accept.setText("Add New Entry");

        // Input Items -> Input Panel
        inputPanel.add(itemInput);
        inputPanel.add(maxProgressInput);
        inputPanel.add(accept);

        // Exit Button
        exit = new JButton();
        exit.addActionListener(this);
        exit.setFocusable(false);
        exit.setText("X");
        exit.setFont(new Font("Dialog", Font.BOLD, 40));
        exit.setBounds(1080, 15, 50, 50);
        exit.setBackground(Color.decode("#070f1d"));
        exit.setForeground(Color.LIGHT_GRAY);
        exit.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Item List Panel
        itemList = new JPanel();
        itemList.setBounds(32,220,166, 430);
        itemList.setLayout(displayLayout);
        itemList.setBackground(Color.decode("#070f1d"));
        itemList.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Items and Items -> Item List Panel
        for (int i=0; i<10; i++) {
            item[i] = new JTextField();
            item[i].setEditable(false);
            item[i].setText("<Empty>");
            item[i].setBackground(Color.decode("#070f1d"));
            item[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            item[i].setForeground(Color.LIGHT_GRAY);
            item[i].setHorizontalAlignment(JTextField.CENTER);
            itemList.add(item[i]);
        }

        // Progress Bar Panel
        progressList = new JPanel();
        progressList.setBounds(202,220,476, 430);
        progressList.setLayout(displayLayout);
        progressList.setBackground(Color.decode("#070f1d"));
        progressList.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Progress Bars and Progress Bars -> Progress Bar Panel
        for (int i=0; i<10; i++) {
            progressBar[i] = new JProgressBar();
            progressBar[i].setStringPainted(true);
            progressBar[i].setBackground(Color.decode("#070f1d"));
            progressBar[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            progressBar[i].setForeground(Color.LIGHT_GRAY);
            progressList.add(progressBar[i]);
        }

        // Current Progress Panel
        currentProgress = new JPanel();
        currentProgress.setBounds(682,220,166, 430);
        currentProgress.setLayout(displayLayout);
        currentProgress.setBackground(Color.decode("#070f1d"));
        currentProgress.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Current Progress Counter and Current Progress Counter -> Current Progress Panel
        for (int i=0; i<10; i++) {
            progressCounter[i] = new JTextField();
            progressCounter[i].setEditable(false);
            progressCounter[i].setText("0/0");
            progressCounter[i].setBackground(Color.decode("#070f1d"));
            progressCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            progressCounter[i].setForeground(Color.LIGHT_GRAY);
            progressCounter[i].setHorizontalAlignment(JTextField.CENTER);
            currentProgress.add(progressCounter[i]);
        }


        // Progress Adder Panel
        progressAdder = new JPanel();
        progressAdder.setBounds(852,220,166, 430);
        progressAdder.setLayout(displayLayout);
        progressAdder.setBackground(Color.decode("#070f1d"));
        progressAdder.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Progress Input and Progress Input -> Progress Adder Panel
        for (int i=0; i<10; i++) {
            progressInput[i] = new JTextField();
            progressInput[i].addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8 || ke.getKeyChar() == '-') {
                        progressInput[0].setEditable(true);
                        progressInput[1].setEditable(true);
                        progressInput[2].setEditable(true);
                        progressInput[3].setEditable(true);
                        progressInput[4].setEditable(true);
                        progressInput[5].setEditable(true);
                        progressInput[6].setEditable(true);
                        progressInput[7].setEditable(true);
                        progressInput[8].setEditable(true);
                        progressInput[9].setEditable(true);
                    } else {
                        progressInput[0].setEditable(false);
                        progressInput[1].setEditable(false);
                        progressInput[2].setEditable(false);
                        progressInput[3].setEditable(false);
                        progressInput[4].setEditable(false);
                        progressInput[5].setEditable(false);
                        progressInput[6].setEditable(false);
                        progressInput[7].setEditable(false);
                        progressInput[8].setEditable(false);
                        progressInput[9].setEditable(false);
                    }
                }
            });
            progressInput[i].setEditable(true);
            progressInput[i].setBackground(Color.decode("#032245"));
            progressInput[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            progressInput[i].setForeground(Color.LIGHT_GRAY);
            progressInput[i].setHorizontalAlignment(JTextField.CENTER);
            progressAdder.add(progressInput[i]);
        }

        // Progress Adder Button Panel
        progressAdderButtons = new JPanel();
        progressAdderButtons.setBounds(1022,220,56, 430);
        progressAdderButtons.setLayout(displayLayout);
        progressAdderButtons.setBackground(Color.decode("#070f1d"));
        progressAdderButtons.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Progress Adder Buttons and Progress Adder Buttons -> Progress Adder Button Panel
        for (int i=0; i<10; i++){
            progressInputButton[i] = new JButton();
            progressInputButton[i].addActionListener(this);
            progressInputButton[i].setFocusable(false);
            progressInputButton[i].setText("Add");
            progressInputButton[i].setBackground(Color.decode("#050f1d"));
            progressInputButton[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            progressInputButton[i].setForeground(Color.LIGHT_GRAY);
            progressInputButton[i].setHorizontalAlignment(JButton.CENTER);
            progressAdderButtons.add(progressInputButton[i]);
        }

        // Delete Item panel
        delButtonList = new JPanel();
        delButtonList.setBounds(1082,220,36, 430);
        delButtonList.setLayout(displayLayout);
        delButtonList.setBackground(Color.decode("#070f1d"));
        delButtonList.setBorder(BorderFactory.createLineBorder(Color.decode("#070f1d")));

        // Delete Item Buttons and Delete Item Buttons -> Delete Item panel
        for (int i=0; i<10; i++){
            deleteItem[i] = new JButton();
            deleteItem[i].addActionListener(this);
            deleteItem[i].setFocusable(false);
            deleteItem[i].setText("X");
            deleteItem[i].setBackground(Color.decode("#050f1d"));
            deleteItem[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            deleteItem[i].setForeground(Color.LIGHT_GRAY);
            deleteItem[i].setFont(delItemFont);
            deleteItem[i].setHorizontalAlignment(JButton.CENTER);
            delButtonList.add(deleteItem[i]);
        }

        // Adding Everything to Frame
        frame.add(delButtonList);
        frame.add(progressAdderButtons);
        frame.add(progressAdder);
        frame.add(currentProgress);
        frame.add(progressList);
        frame.add(itemList);
        frame.add(inputPanel);
        frame.add(heading);
        frame.add(exit);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ProgressManager manager = new ProgressManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Input take and add as Item
        if (e.getSource()==accept){
            item[index].setText(itemInput.getText());
            maxProgressNumber[index] = Integer.parseInt(maxProgressInput.getText());
            itemInput.setText("");
            maxProgressInput.setText(null);
            progressBar[index].setMaximum(maxProgressNumber[index]);
            progressCounter[index].setText(currentProgressNumber[index] +"/"+maxProgressNumber[index]);
            index++;
            if (index==10){
                accept.setEnabled(false);
            }
        }

        // Progress added update Progress Bars
        for (int i=0; i<10; i++){
            if (e.getSource() == progressInputButton[i]){
                if (i<index){
                    currentProgressNumber[i] += Integer.parseInt(progressInput[i].getText());
                    progressCounter[i].setText(currentProgressNumber[i] + "/" + maxProgressNumber[i]);
                    progressBar[i].setValue(currentProgressNumber[i]);
                }
                progressInput[i].setText(null);
            }
        }

        // Delete button deletes Item and rearrange rest
        for (int i=0; i<10; i++){
            if(e.getSource() == deleteItem[i]){
                for (int j=i; j<9; j++){
                    item[j].setText(item[j+1].getText());
                    currentProgressNumber[j] = currentProgressNumber[j+1];
                    maxProgressNumber[j] = maxProgressNumber[j+1];
                    progressCounter[j].setText(currentProgressNumber[j] + "/" + maxProgressNumber[j]);
                    progressBar[j].setMaximum(maxProgressNumber[j]);
                    progressBar[j].setValue(currentProgressNumber[j]);
                }
                index--;
                if (index==-1){
                    index++;
                }
                item[index].setText("<Empty>");
                currentProgressNumber[index] = 0;
                maxProgressNumber[index] = 0;
                progressBar[index].setValue(0);
                progressCounter[index].setText(currentProgressNumber[index] + "/" + maxProgressNumber[index]);
                progressBar[index].setMaximum(1);
                accept.setEnabled(true);
                if (index==-1){
                    index++;
                }
            }
        }

        // Exit Programme
        if (e.getSource()==exit){
            System.exit(0);
        }
    }
}
