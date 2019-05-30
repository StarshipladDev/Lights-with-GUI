
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import java.awt.Polygon;

/**
 *
 * @author StarshipladDev
 */
public class Etedue9V2 extends JFrame {
	/* Sets up architecture variables */
	public static int WIDTHy = 500;
	public static int HEIGHTy = 500;
	public static int sizeOfLight=1;
	public static int buttonLength = (HEIGHTy / 5) * 2;
	public static int buttonHeight = HEIGHTy / 10;
	public static boolean built = false;
	public static Random rand = new Random();
	JTextField lightEntry;
	JTextField connectionEntry;
	JButton fillButton;
	JTextArea solveResult;
	JButton solveButton;
	JButton giveAnswerButton;
	JTextArea errorText;
	DrawPanel dp;
	JMenuItem menuItem;
	JLabel lightsLabel, connectionsLabel, solutionLabel, warningsLabel;
	public static void main(String[] args) {
		// TODO code application logic here
		//GUI stuff here
		Etedue9V2 frame = new Etedue9V2();
		ButtonListner bl = new ButtonListner(frame);
		frame.lightsLabel = new JLabel("Enter Lights:");
		frame.lightsLabel.setBackground(Color.BLACK);
		frame.lightsLabel.setLocation(0, (WIDTHy/10));
		frame.lightsLabel.setBounds(10, WIDTHy/10 -10, 100, 100);
		frame.lightEntry = new JTextField(30);
		frame.lightEntry.setSize(buttonLength * 2, buttonHeight);
		frame.lightEntry.setLocation(0, WIDTHy / 10 * 2);
		//frame.lightEntry.setText("Enter Lights here"); LEGACY CODE
		// Connection Label here
		frame.connectionsLabel = new JLabel("Enter Connections:");
		frame.connectionsLabel.setBackground(Color.BLACK);
		frame.connectionsLabel.setLocation(0, (WIDTHy/10)*3);
		frame.connectionsLabel.setBounds(10, WIDTHy/10 *3-10, 200, 100);

		frame.connectionEntry = new JTextField(30);
		//frame.connectionEntry.setText("Enter Connections here");LEGACY CODE
		frame.connectionEntry.setSize(buttonLength * 2, buttonHeight);
		frame.connectionEntry.setLocation(0, (WIDTHy / 10) * 4);
		frame.fillButton = new JButton("Fill");
		frame.fillButton.setSize(buttonLength * 2, buttonHeight);
		frame.fillButton.setLocation(0, (WIDTHy / 10) * 5);
		frame.fillButton.addActionListener(bl);
		// Results label here
		frame.solutionLabel = new JLabel("Solution:");
		frame.solutionLabel.setBackground(Color.BLACK);
		frame.solutionLabel.setLocation(0, (WIDTHy/10)*6);
		frame.solutionLabel.setBounds(10, WIDTHy/10 *6-10, 100, 100);

		frame.solveResult = new JTextArea();
		frame.solveResult.setLineWrap(true);
		frame.solveResult.setText("Correct lights will be dispalyed here");
		frame.solveResult.setLineWrap(true);
		JScrollPane scrolllResult = new JScrollPane(frame.solveResult);
		scrolllResult.setSize(buttonLength * 2, buttonHeight);
		scrolllResult.setLocation(0, (WIDTHy / 10) * 7);
		frame.solveButton = new JButton("Solve");
		frame.solveButton.setSize(buttonLength * 2, buttonHeight);
		frame.solveButton.setLocation(0, (WIDTHy / 10) * 8);
		frame.solveButton.addActionListener(bl);
		frame.giveAnswerButton = new JButton("Solution");
		frame.giveAnswerButton.setSize(buttonLength * 2, buttonHeight);
		frame.giveAnswerButton.setLocation(0, (WIDTHy / 10) * 9);
		frame.giveAnswerButton.addActionListener(bl);
		// Error label here
		frame.warningsLabel = new JLabel("Warnings:");
		frame.warningsLabel.setBackground(Color.BLACK);
		frame.warningsLabel.setLocation(0, (WIDTHy/10)*10);
		frame.warningsLabel.setBounds(10, WIDTHy/10 *10-10, 100, 100);

		frame.errorText = new JTextArea("");
		frame.errorText.setForeground(Color.red);
		frame.errorText.setLineWrap(true);
		JScrollPane scrolllError = new JScrollPane(frame.errorText);
		scrolllError.setSize(buttonLength * 2, buttonHeight);
		scrolllError.setLocation(0, (WIDTHy / 10) * 11);
		frame.setName("Etedude 9 Wth GUI");
		frame.setLayout(null);
		frame.setMinimumSize(new Dimension(WIDTHy + 200 + 200, HEIGHTy + 200));
		frame.setBackground(Color.cyan);
		char[] nameInput = new char[]{};
		char[][] connectionInput = new char[][]{{}, {}};
		frame.dp = new DrawPanel(nameInput, connectionInput);
		frame.dp.setLocation(buttonLength * 2, buttonHeight);
		MouseListen ml = new MouseListen(frame.dp);
		frame.getContentPane().addMouseListener(ml);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(frame.dp);
		frame.add(frame.lightsLabel);
		frame.add(frame.lightEntry);
		frame.add(frame.connectionsLabel);
		frame.add(frame.connectionEntry);
		frame.add(frame.fillButton);
		frame.add(frame.solutionLabel);
		frame.add(scrolllResult);
		frame.add(frame.solveButton);
		frame.add(frame.giveAnswerButton);
		frame.add(frame.warningsLabel);
		frame.add(scrolllError);
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Help");
		frame.menuItem = new JMenuItem("Help", new ImageIcon(createImage(0)));
		frame.menuItem.addActionListener(bl);
		menu.add(frame.menuItem);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.setIconImage(createImage(1));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		built = true;
		//GUI stuff end
		
	}
	/**
	 * 
	 * @param type int - 1 is Window logo, 0 is menu logo
	 * @return an Image in Icon size
	 */
	public static Image createImage(int type) {
		//Create a 16x16 pixel image.
		BufferedImage i = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		//Draw into it.
		Graphics gi = i.getGraphics();
		if (type == 1) {
			gi.setColor(Color.YELLOW);
			gi.fillRect(0, 0, 15, 15);
			gi.setColor(Color.BLACK);
			gi.drawString("A", 8, 8);
		} else {
			gi.setColor(Color.WHITE);
			gi.fillRect(0, 0, 15, 15);
			gi.setColor(Color.BLUE);
			gi.fillOval(0, 0, 15, 15);
			gi.setColor(Color.BLACK);
			gi.drawString("?", 4, 10);
		}

		//Clean up.
		gi.dispose();
		//Return it.
		return i;
	}
	/**
	 * 
	 * MouseListen is a class that runs everytime the mouse is clicked on the main window
	 * Uses MouseAdapter instead of Mouse Listener as only 'mouseReleased' needed
	 *
	 */
	private static class MouseListen extends MouseAdapter {

		DrawPanel p;
		/**
		 * 
		 * @param p the DrawPanel to effect when mouse clicked
		 */
		public MouseListen(DrawPanel p) {
			this.p = p;
		}
		public void mouseReleased(MouseEvent e) {
			if (built) {
				int i = 0;
				int dpoffset = buttonLength * 2;
				while (p.points != null && i < p.points.length) {
					if (e.getX() > p.points[i][0] + dpoffset && e.getX() < p.points[i][2] + dpoffset) {
						if (e.getY() > p.points[i][1]+buttonHeight&& e.getY() < p.points[i][3]+buttonHeight) {
							p.lightsOn = turnOff(p.lightsOn.clone(), p.connections.clone(), i);
							p.bestScore=0;
							p.bestSwitch=new boolean[p.lightsOn.length];
							p.printCount=0;
						}
					}
					i++;
				}
			}
			p.repaint();

		}
	}
	/**
	 * 
	 * Button LIstner is the Class that performs actions whenever a button it is assigneed too is clicked on
	 * Currently deals with:
	 * Fill button 
	 * Solve button
	 * Solution Button
	 */
	private static class ButtonListner implements ActionListener {

		Etedue9V2 p;
		/**
		 * 
		 * @param p Etedue9V2 that has variables checked to change actions
		 */
		public ButtonListner(Etedue9V2 p) {
			this.p = p;
		}

		public void actionPerformed(ActionEvent e) {
			int i = 0;
			//Add input
			if (p.lightEntry.getText().length() > 0 && e.getSource() == p.fillButton) {
				p.dp.lightList = new char[p.lightEntry.getText().length()];
				int cleanser = 0;
				while (cleanser < p.dp.lightList.length) {
					p.dp.lightList[cleanser] = '.';
					cleanser++;
				}
				if (e.getSource() == p.fillButton) {
					p.errorText.setText("");
					int f = 0;
					i = 0;
					String input = p.lightEntry.getText();
					while (f < input.length()) {
						if (input.charAt(f) <= 'Z' && input.charAt(f) >= 'A') {
							boolean print = true;
							int x = 0;
							while (x < p.dp.lightList.length) {
								if (p.dp.lightList[x] == input.charAt(f)) {
									print = false;
									p.errorText.setText(p.errorText.getText() + "\n" + p.lightEntry.getText().charAt(f) + " already present");
								}
								x++;
							}

							//check if individual input
							if (f < p.lightEntry.getText().length() - 1) {
								if (p.lightEntry.getText().charAt(f + 1) != ' ') {
									print = false;
									p.errorText.setText(p.errorText.getText() + "\n" + p.lightEntry.getText().charAt(f) + " followed by non-space");
								}
							}
							if (f > 0) {
								if (p.lightEntry.getText().charAt(f - 1) != ' ') {
									p.errorText.setText(p.errorText.getText() + "\n" + p.lightEntry.getText().charAt(f) + " following non-space");
									print = false;
								}
							}
							if (print) {
								p.dp.lightList[i] = input.charAt(f);
								i++;
							}

						}
						else {
							if(p.lightEntry.getText().charAt(f)!=' ') {
								p.errorText.setText(p.errorText.getText() + "\n" + p.lightEntry.getText().charAt(f) + " invalid,must be capital letter");
							}
						}

						f++;
					}

				}
				p.dp.connections = new int[i][];
				int ff = 0;
				while (ff < p.dp.connections.length) {
					p.dp.connections[ff] = new int[i];
					ff++;
				}
				//Add connections
				if (p.connectionEntry.getText().length() > 0) {
					int x = 0;
					//For each Light name
					while (x < i) {
						int f = 0;
						//For each letter in the entry
						while (f < p.connectionEntry.getText().length() - 1) {
							int adval = 0;
							boolean add = true;
							//If the letter is current light name
							if (p.connectionEntry.getText().charAt(f) == p.dp.lightList[x]) {
								if (f > 0 && p.connectionEntry.getText().charAt(f - 1) != ' ') {
									add = false;
								} //Check if the character following it (The connection) is a valid capital letter
								else if (p.connectionEntry.getText().charAt(f + 1) > 'Z' || p.connectionEntry.getText().charAt(f + 1) < 'A') {
									add = false;
								}//Check it is not followed by a '.'
								else if (p.connectionEntry.getText().charAt(f + 1) == '.') {
									add = false;
								} //Check the value following it is followed by a space
								else if (f < p.connectionEntry.getText().length() - 2) {
									if (p.connectionEntry.getText().charAt(f + 2) != ' ') {
										add = false;
									}
								}
								if (add) {
									int y = 0;
									int addIndex = 0;
									add = false;
									//Check if connection letter is a valid connection
									while (y < p.dp.lightList.length) {
										if (p.connectionEntry.getText().charAt(f + 1) == p.dp.lightList[y]) {
											add = true;
											addIndex = y;
										}
										y++;
									}
									if (add) {
										//Add at [letterindex][connectionletterindex] a 1 in connections
										p.dp.connections[x][addIndex] = 1;
									}
								}

							}
							f++;
						}

						x++;
					}
				}
				//Add final results to frame
				p.dp.lightsOn = new boolean[i];
				p.dp.points = new int[i][4];
				p.dp.lightColor = new Color[i];
				p.dp.bestScore=0;
				p.dp.bestSwitch=new boolean[p.dp.lightsOn.length];
				p.dp.printCount=0;
				i = 0;
				while (i < p.dp.lightColor.length) {
					p.dp.lightColor[i] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
					p.dp.lightsOn[i] = true;
					i++;
				}
				p.dp.repaint();
				p.repaint();

			} 
			//MENU BUTTON
			else if (e.getSource() == p.menuItem) {
				JOptionPane.showMessageDialog(null, "Welcome to light with GUI, Etedude 9.\n Enter space seperated capital letter values in the top box.\n"
						+ "Enter space seprated 2 letter values made up of values in the top box to draw connections"
						+ "\nPress the button 'Fill' to Create the entered lights graph.\n You can click on individual lights to turn them and their connected"
						+ "lights on and off.\n Press 'Solve' to have the program automaticly solve the problem to turn all ights off, one step each press, or 'Solution' to receive the "
						+ "lights needed to be pressed.Errors will be dispalyed in the scrollable message box at the bottom left.\n"
						+ "Solution by Starshiplad and classmate(anon)");
			} 
			//SOLVE BUTTON
			else if (e.getSource() == p.solveButton) {
				if(p.dp.lightsOn.length>0) {
					if(offCount(p.dp.lightsOn)==p.dp.lightsOn.length) {
						p.solveResult.setText("All lights off");
					}
					else{
						if(p.dp.bestScore==0) {
							boolean[] switchList = new boolean[p.dp.lightsOn.length];
							Solve(p.dp,switchList.clone(),p,p.dp.lightsOn, p.dp.connections, 0);
							p.solveResult.setText("");
						}
						boolean wrote=false;
						while(p.dp.printCount<p.dp.lightsOn.length && !wrote) {
							if(p.dp.bestSwitch[p.dp.printCount]) {
								turnOff(p.dp.lightsOn,p.dp.connections, p.dp.printCount);
								p.solveResult.setText(p.solveResult.getText()+" "+p.dp.lightList[p.dp.printCount]);
								p.repaint();
								p.dp.repaint();
								wrote=true;

							}
							p.dp.printCount++;

						}
					}
				}
				else {
					p.errorText.setText("No lights in program");
				}
			}
			//SOLUTION BUTTON
			else if(e.getSource()== p.giveAnswerButton) {
				if(offCount(p.dp.lightsOn)==p.dp.lightsOn.length) {
					p.solveResult.setText("All lights off");
				}
				else if(p.dp.lightsOn.length>0) {
					boolean[] switchList = new boolean[p.dp.lightsOn.length];
					p.solveResult.setText("");
					switchList = Solve(p.dp,switchList.clone(),p,p.dp.lightsOn, p.dp.connections, 0);
					int a1=0;
					if(p.dp.bestScore==p.dp.lightsOn.length) {
						p.solveResult.setText("All lights can be turned off by pressing the following:\n");
					}else {
						p.solveResult.setText("Max lights of "+p.dp.bestScore+" can be turned off by pressing the following:\n");
					}
					while(a1<switchList.length) {
						if(p.dp.bestSwitch[a1]) {
							p.solveResult.setText(p.solveResult.getText()+" "+p.dp.lightList[a1]);
						}
						a1++;
					}
					p.dp.bestScore=0;

				}
				else {
					p.errorText.setText("No lights in program");
				}


			}

		}
	}
	/**
	 * Simple utility method to count 'false' fvaleus in array of booleans
	 * @param onList- passes a list of booleans representing the 'on' state of a light
	 * @return int the count of 'off' lights
	 */
	public static int offCount(boolean[] onList) {
		int ii = 0;
		int value = 0;
		while (ii < onList.length) {
			if (onList[ii] == false) {
				value++;
			}
			ii++;
		}
		return value;

	}
/**
 * 
 * @param lightList - The list of booleans represntign whihc lights are on
 * @param connectionList - The list of connections. 2D array. connectionList[i][x]will be 1 if lightlist[x] toggles on 'i' being toggled
 * @param i the light to be turned off
 * @return the new state of the lights
 */
	public static boolean[] turnOff(boolean[] lightList, int[][] connectionList, int i) {

		lightList[i] = !lightList[i];
		int x = 0;
		while (x < connectionList[i].length) {
			if (connectionList[i][x] == 1) {
				lightList[x] = !lightList[x];
			}
			x++;
		}
		return lightList;
	}
	/**
	 * Solve recursively tries each combination of light, replacing the best switch configuration if a break case has a higher 'off' value
	 * @param p The DrawPanel to receive variables from
	 * @param switches the concatting list of whether to toggle the light at 'depth'
	 * @param e the Etedue9V2 to receive variables from
	 * @param onList the current configuration of lights that are on or off
	 * @param connectionList The list of connections. 2D array. connectionList[i][x]will be 1 if lightlist[x] toggles on 'i' being toggled.
	 * It is to be passed to 'turnoff'
	 * @param depth - the depth of the recurrsive call. Break case is if depth= onList length
	 * @return
	 */
	public static boolean[] Solve(DrawPanel p, boolean[] switches,Etedue9V2 e,boolean[] onList, int[][] connectionList,int depth) {
		boolean[] withSwitch= onList.clone();
		boolean[] withoutSwitch=onList.clone();
		int i=0;
		if(depth==onList.length-1) {
			withSwitch=turnOff(withSwitch, connectionList,depth);
			if(offCount(withSwitch)>offCount(withoutSwitch)){
				if(offCount(withSwitch)>p.bestScore) {
					switches[depth]=true;
					p.bestSwitch=switches;
					p.bestScore=offCount(withSwitch);
					int a1=0;
					while(a1<p.bestSwitch.length) {
						a1++;
					}
				}

				return withSwitch;
			}else {
				if(offCount(withoutSwitch)>p.bestScore) {
					switches[depth]=false;
					p.bestSwitch=switches;
					p.bestScore=offCount(withoutSwitch);
				}
				return withoutSwitch;

			}
		}
		else {
			withoutSwitch=Solve(p,switches.clone(),e,withoutSwitch, connectionList, depth+1);
			withSwitch=turnOff(withSwitch, connectionList,depth);
			switches[depth]=true;
			withSwitch=Solve(p,switches.clone(),e,withSwitch, connectionList, depth+1);
			if(offCount(withSwitch)==onList.length||offCount(withSwitch)>offCount(withoutSwitch)) {
				return withSwitch;
			}else if(offCount(withoutSwitch)==onList.length||offCount(withoutSwitch)>=offCount(withSwitch)){

				return withoutSwitch;
			}else {
				return withSwitch;
			}

		}




	}
	/**
	 * 
	 *
	 */
	private static class DrawPanel extends JPanel {

		char[] lightList;
		char[][] connectionList;
		int[][] points;
		boolean[] lightsOn;
		boolean[] bestSwitch;
		int bestScore;
		int[][] connections;
		int printCount;
		Color[] lightColor;
		public DrawPanel() {

		}

		public DrawPanel(char[] listOfLight, char[][] listOfConnections) {
			printCount=0;
			int i = 0;
			lightColor = new Color[listOfLight.length];
			while (i < lightColor.length) {
				lightColor[i] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

				i++;
			}
			lightList = listOfLight;
			connectionList = listOfConnections;
			lightsOn = new boolean[listOfLight.length];
			char[] conns;
			i = 0;
			for (i = 0; i < lightList.length; i++) {
				int f = 0;
				int x = 0;
				conns = null;
				while (f < listOfConnections.length) {
					if (listOfConnections[f][0] == listOfLight[i]) {
						x++;
					}
					f++;
				}
				if (x > 0) {
					f = 0;
					conns = new char[x];
					while (f < listOfConnections.length) {
						if (listOfConnections[f][0] == listOfLight[i]) {
							x--;
							conns[x] = listOfConnections[f][1];
						}
						f++;
					}

				}

			}
			setSize(WIDTHy, HEIGHTy);
			setBackground(new Color(244, 149, 66));
			repaint();
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			//Menu Bar
			g.setColor(new Color(66, 134, 244));
			g.fillRect(0, 0, WIDTHy, buttonHeight);
			g.fillRect(0, HEIGHTy - buttonHeight, WIDTHy, buttonHeight);
			if (lightsOn.length > 0) {
				sizeOfLight = WIDTHy / (lightsOn.length + 2);
			}
			int b = sizeOfLight / 2;
			//Lights
			Color coloron;
			Color coloroff;
			coloron = Color.yellow;
			coloroff = Color.lightGray;
			int i = 0;
			//upper column
			int currentYPos = (WIDTHy/5) + 70;
			int xOffset = 10;
			while (i < lightsOn.length / 2) {
				if (lightsOn[i]) {
					g.setColor(coloron);
				} else {
					g.setColor(coloroff);
				}
				g.fillOval(0 + (i * sizeOfLight) + xOffset, currentYPos, sizeOfLight, sizeOfLight);
				points[i][0] = (i * sizeOfLight) + xOffset;
				points[i][1] = currentYPos;
				points[i][2] = (i * sizeOfLight) + sizeOfLight  + xOffset;
				points[i][3] = currentYPos + sizeOfLight;
				g.setColor(Color.black);
				g.drawString("" + String.valueOf(lightList[i]), 0 + (i * sizeOfLight) + b  + xOffset, currentYPos + b);
				i++;
				xOffset += 20;
				if(i <= lightsOn.length / 4){
					currentYPos -= 20;
				} else {
					currentYPos += 20;
				}
			}
			//Lower column
			currentYPos = (WIDTHy / 2) + 50;
			xOffset = 10;
			while (i < lightsOn.length) {
				if (lightsOn[i]) {
					g.setColor(coloron);
				} else {
					g.setColor(coloroff);
				}
				g.fillOval(((i - (lightsOn.length / 2) + 1) * sizeOfLight + xOffset), currentYPos, sizeOfLight, sizeOfLight);
				points[i][0] = ((i - (lightsOn.length / 2) + 1) * sizeOfLight + xOffset);
				points[i][1] = currentYPos;
				points[i][2] = ((i - (lightsOn.length / 2) + 1) * sizeOfLight) + sizeOfLight + xOffset;
				points[i][3] = currentYPos + sizeOfLight;
				g.setColor(Color.black);
				g.drawString("" + String.valueOf(lightList[i]), 0 + (((i - (lightsOn.length / 2) + 1)) * sizeOfLight) + b + xOffset, currentYPos + b);
				i++;
				xOffset += 20;
				if(i <= ((lightsOn.length / 2) + (lightsOn.length / 4))){
					currentYPos += 20;
				} else {
					currentYPos -= 20;
				}
			}
			i = 0;
			//Draw connections

			while (i < lightsOn.length) {
				int offset = rand.nextInt(sizeOfLight / 4) + b;
				g.setColor(lightColor[i]);
				int x = i;
				int r = 0;
				while (r < connections[x].length) {
					if (connections[x][r] == 1) {
						g.setColor(Color.BLACK);
						g.drawLine(points[r][0] + offset, points[r][1] + offset, points[i][0] + offset, points[i][1] + offset);
						g.fillRect(points[r][0] + offset, points[r][1] + offset, 5, 5);
					}
					r++;
				}
				i++;
			}
		}
	}
}
