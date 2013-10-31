package russosoftware.scoring;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ScoringGUI extends JFrame
{
	/**
	 * Serial Version UID. Non-Essential unless working with ObjectOutputStreams for ObjectInputStreams. 
	 * This class does not require itself to be sent over a network, and as such no variables are required
	 * to be marked as transient, and this long could simply be the default 1L.
	 **/
	private static final long serialVersionUID = 56117292919710214L;
	
	//Format for Percentages
	private DecimalFormat decFormat = new DecimalFormat("0.00");
	
	//TabbedPane nested in main Content Pane
	private JTabbedPane tabPane1 = new JTabbedPane();
	
	//Settings Tab JComponents
	private JLabel bound1Label = new JLabel("Upper bound of Score 1:");
	private JTextField bound1 = new JTextField();
	private JLabel bound2Label = new JLabel("Upper bound of Score 2:");
	private JTextField bound2 = new JTextField();
	private JLabel bound3Label = new JLabel("Upper bound of Score 3:");
	private JTextField bound3 = new JTextField();
	private JLabel bound4Label = new JLabel("Upper bound of Score 4:");
	private JTextField bound4 = new JTextField();
	private JLabel bound5Label = new JLabel("Upper bound of Score 5:");
	private JTextField bound5 = new JTextField();
	private JLabel mcMultiplierLabel = new JLabel("Multiple Choice Multiplier:");
	private JTextField mcMultiplier = new JTextField();
	private JLabel frMultiplierLabel = new JLabel("Free Response Multiplier:");
	private JTextField frMultiplier = new JTextField();
	private JButton submitSettings = new JButton("Apply Settings");
	
	//Calculate Tab JComponents
	private JLabel mcScoreLabel = new JLabel("Multiple-Choice Score:");
	private JTextField mcScoreVal = new JTextField();
	private JLabel frScoreLabel = new JLabel("Free-Response Score:");
	private JTextField frScoreVal = new JTextField();
	private JButton calcButton = new JButton("Calculate Score");
	//private JTextField calculatedScore = new JTextField();
	
	//Establish variables containing multiple necessary values that are updated in the settings tab.
	private double score1UpperBound = 60.00;
	private double score2UpperBound = 70.00;
	private double score3UpperBound = 80.00;
	private double score4UpperBound = 90.00;
	private double score5UpperBound = 100.00;
	private double mcMultiplierInt = 1.00;
	private double frMultiplierInt = 1.00;
	
	//Construct the typical layout object for the Windows.
	private GridLayout layout = new GridLayout(10, 0);
	
	@SuppressWarnings("unused")
	private ScoringGUI() {}
	
	public ScoringGUI(int width, int height, String title)
	{
		super();
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		//Set FlowLayout for TabbedPane Panel. Later changed to GridLayout for each individual Tab.
		setLayout(new FlowLayout());
		try 
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Failed to retrieve Cross Platform Look and Feel Class. Reverting to Default Look and Feel.");
		}
		
		//Calculate Tab Panel
		JPanel calcPanel = new JPanel();
		calcPanel.setLayout(layout);
		
		//Multiple-Choice score field in Calculate Tab
		JPanel mcField = new JPanel();
		mcField.add(mcScoreLabel);
		mcScoreVal.setText(String.valueOf(0));
		mcScoreVal.setPreferredSize(new Dimension(100, 30));
		mcField.add(mcScoreVal);
		calcPanel.add(mcField);
		
		//Free-Response score field in Calculate Tab
		JPanel frField = new JPanel();
		frField.add(frScoreLabel);
		frScoreVal.setText(String.valueOf(0));
		frScoreVal.setPreferredSize(new Dimension(100, 30));
		frField.add(frScoreVal);
		calcPanel.add(frField);
		
		//Calculate score button in Calculate Tab
		JPanel calcuPanel = new JPanel();
		calcButton.addActionListener(new CalculateScore());
		calcButton.setPreferredSize(new Dimension(200, 30));
		calcuPanel.add(calcButton);
		calcPanel.add(calcuPanel);
		
		//Non-Editable JTextField which holds the last calculated value.
		/*
		JPanel calculatedPanel = new JPanel();
		calculatedScore.setPreferredSize(new Dimension(200, 30));
		calculatedScore.setEditable(false);
		calculatedPanel.add(calculatedScore);
		calcPanel.add(calculatedPanel);
		*/
		tabPane1.addTab("Calculate Scores", calcPanel);
		
		//Settings Tab Panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(layout);
	
		/*
		JPanel settingField1 = new JPanel();
		settingField1.add(mcMaxValueLabel);
		mcMaxValue.setText(String.valueOf(mcMaxInt));
		mcMaxValue.setPreferredSize(new Dimension(100, 30));
		settingField1.add(mcMaxValue);
		settingsPanel.add(settingField1);
		
		JPanel settingField2 = new JPanel();
		settingField2.add(frMaxValueLabel);
		frMaxValue.setText(String.valueOf(frMaxInt));
		frMaxValue.setPreferredSize(new Dimension(100, 30));
		settingField2.add(frMaxValue);
		settingsPanel.add(settingField2);
		*/
		
		//JTextField to input the score 1 upper bound
		JPanel settingField3 = new JPanel();
		settingField3.add(bound1Label);
		bound1.setText(String.valueOf(score1UpperBound));
		bound1.setPreferredSize(new Dimension(100, 30));
		settingField3.add(bound1);
		settingsPanel.add(settingField3);
		
		//JTextField to input the score 2 upper bound
		JPanel settingField4 = new JPanel();
		settingField4.add(bound2Label);
		bound2.setText(String.valueOf(score2UpperBound));
		bound2.setPreferredSize(new Dimension(100, 30));
		settingField4.add(bound2);
		settingsPanel.add(settingField4);
		
		//JTextField to input the score 3 upper bound
		JPanel settingField5 = new JPanel();
		settingField5.add(bound3Label);
		bound3.setText(String.valueOf(score3UpperBound));
		bound3.setPreferredSize(new Dimension(100, 30));
		settingField5.add(bound3);
		settingsPanel.add(settingField5);		
		
		//JTextField to input the score 4 upper bound
		JPanel settingField6 = new JPanel();
		settingField6.add(bound4Label);
		bound4.setText(String.valueOf(score4UpperBound));
		bound4.setPreferredSize(new Dimension(100, 30));
		settingField6.add(bound4);
		settingsPanel.add(settingField6);		
		
		//JTextField to input the score 5 upper bound
		JPanel settingField7 = new JPanel();
		settingField7.add(bound5Label);
		bound5.setText(String.valueOf(score5UpperBound));
		bound5.setPreferredSize(new Dimension(100, 30));
		settingField7.add(bound5);
		settingsPanel.add(settingField7);	
		
		//JTextField to input the Multiple-Choice Multiplier
		JPanel settingField8 = new JPanel();
		settingField8.add(mcMultiplierLabel);
		mcMultiplier.setText(String.valueOf(mcMultiplierInt));
		mcMultiplier.setPreferredSize(new Dimension(100, 30));
		settingField8.add(mcMultiplier);
		settingsPanel.add(settingField8);	
		
		//JTextField to input the Free-Response Multiplier
		JPanel settingField9 = new JPanel();
		settingField9.add(frMultiplierLabel);
		frMultiplier.setText(String.valueOf(frMultiplierInt));
		frMultiplier.setPreferredSize(new Dimension(100, 30));
		settingField9.add(frMultiplier);
		settingsPanel.add(settingField9);	
		
		//JButton to submit the Settings
		JPanel settingField10 = new JPanel();
		submitSettings.addActionListener(new SaveSettings());
		submitSettings.setPreferredSize(new Dimension(200, 30));
		settingField10.add(submitSettings);
		settingsPanel.add(settingField10);
		
		//Add Settings tab
		tabPane1.addTab("Settings", settingsPanel);
		
		//Add Tabbed Pane to the Content Pane (Internal JFrame Container)
		Container mainPane = getContentPane();
		mainPane.add(tabPane1);
	}
	
	/**
	 * Internal Class that implements the ActionListener
	 * interface to receive and perform actions based on whether 
	 * the submitSettings button in the GUI is pressed.
	 **/
	private class SaveSettings implements ActionListener
	{
		private double tempScore1UpperBound = 60.00;
		private double tempScore2UpperBound = 70.00;
		private double tempScore3UpperBound = 80.00;
		private double tempScore4UpperBound = 90.00;
		private double tempScore5UpperBound = 100.00;
		private double tempMcMultiplierInt = 1.00;
		private double tempFrMultiplierInt = 1.00;
		
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource() == submitSettings)
			{
				tempScore1UpperBound = score1UpperBound;
				tempScore2UpperBound = score2UpperBound;
				tempScore3UpperBound = score3UpperBound;
				tempScore4UpperBound = score4UpperBound;
				tempScore5UpperBound = score5UpperBound;
				tempMcMultiplierInt = mcMultiplierInt;
				tempFrMultiplierInt = frMultiplierInt;
				
				try
				{
					score1UpperBound = Double.parseDouble(bound1.getText());
					score2UpperBound = Double.parseDouble(bound2.getText());
					score3UpperBound = Double.parseDouble(bound3.getText());
					score4UpperBound = Double.parseDouble(bound4.getText());
					score5UpperBound = Double.parseDouble(bound5.getText());
					mcMultiplierInt = Double.parseDouble(mcMultiplier.getText());
					frMultiplierInt = Double.parseDouble(frMultiplier.getText());
				}
				catch(Exception e)
				{
					//Should it fail to parse a double from any of the Settings Fields, an exception is localized and posted in a JOptionPane Message Dialog
					//All Variables are reset to their previous values via temporary variables.
					JOptionPane.showMessageDialog(null, "Failed to Save Settings. Reverting to previous settings: " + e.getLocalizedMessage());
					score1UpperBound = tempScore1UpperBound;
					score2UpperBound = tempScore2UpperBound;
					score3UpperBound = tempScore3UpperBound;
					score4UpperBound = tempScore4UpperBound;
					score5UpperBound = tempScore5UpperBound;
					mcMultiplierInt = tempMcMultiplierInt;
					frMultiplierInt = tempFrMultiplierInt;
					return;
				}
				JOptionPane.showMessageDialog(null, "Successfully Applied Settings");
			}
		}
	}
	/**
	 * Internal Class that implements the ActionListener
	 * interface to receive and perform actions based on whether 
	 * the calcButton button in the GUI is pressed.
	 **/
	private class CalculateScore implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource() == calcButton)
			{
				double maxScore = score5UpperBound;
				double totalScore = 0;
				double mcScore;
				double frScore;
				try
				{
					mcScore = Double.parseDouble(mcScoreVal.getText());
					frScore = Double.parseDouble(frScoreVal.getText());
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "An error occurred while calculating the score. Scores must be invalid.");
					return;
				}
				System.out.println("Multi: " + mcScore);
				mcScore *= mcMultiplierInt;
				frScore *= frMultiplierInt;
				System.out.println(mcScore);
				totalScore = mcScore + frScore;
				int apScore = 0;
				if(totalScore < score1UpperBound)
					apScore = 1;
				else if(totalScore < score2UpperBound)
					apScore = 2;
				else if(totalScore < score3UpperBound)
					apScore = 3;
				else if(totalScore < score4UpperBound)
					apScore = 4;
				else
					apScore = 5;
				
				double value = calculateScore(totalScore, maxScore, apScore);
					
				
				JOptionPane.showMessageDialog(null, "Calculated Score: " + decFormat.format(value) + "%");
			}
		}

		private double calculateScore(double totalScore, double maxScore, int apScore) 
		{
			double calculated = 0;
			double wholeNum = 0;
			switch(apScore)
			{
			case 1:
				wholeNum = 60.00;
				break;
			case 2:
				wholeNum = 60.00;
				break;
			case 3: 
				wholeNum = 70.00;
				break;
			case 4: 
				wholeNum = 80.00;
				break;
			case 5:
				wholeNum = 90.00;
				break;
			default:
				JOptionPane.showMessageDialog(null, "There was an issue when calculating the percentage");
				break;
			}
			if(apScore > 1)
				calculated = (double)wholeNum + calcDecimal(totalScore, apScore);
			else
				calculated = wholeNum * calcDecimal(totalScore, apScore);
			System.out.println("Whole Number: " + wholeNum);
			System.out.println("Decimal: " + calcDecimal(totalScore, apScore));
			System.out.println("Calculated Value: " + calculated);
			return calculated;
		}	
		
		private double calcDecimal(double totalScore, int apScore)
		{
			System.out.println("AP Score:" + apScore);
			System.out.println(totalScore);
			double score = 0;
			switch(apScore)
			{
			case 1: score = (totalScore / score1UpperBound);
				break;
			case 2: score = ((totalScore - score1UpperBound) / (score2UpperBound - score1UpperBound)) * 10;
				break;
			case 3: score = ((totalScore - score2UpperBound) / (score3UpperBound - score2UpperBound)) * 10;
				break;
			case 4: score = ((totalScore - score3UpperBound) / (score4UpperBound - score3UpperBound)) * 10;
				break;
			case 5: score = ((totalScore - score4UpperBound) / (score5UpperBound - score4UpperBound)) * 10;
				break;
			}
			System.out.println(score);
			return score;
		}
	}
	
	/**
	 * Uses the invokeLater method in the SwingUtilities class to Queue an anonymous
	 * inner Runnable class which is called by the EDT Thread and constructs a new 
	 * ScoringGUI() object.
	 */
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				new ScoringGUI(400, 500, "Thomas Scoring");
			}
		});
	}
}
