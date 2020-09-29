import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewCommand {

	
	
private JFrame fenetre;
	
	public ViewCommand() {
	fenetre = new JFrame();
	fenetre.setTitle("Commande");
	fenetre.setSize(700, 300);
	fenetre.setLocationRelativeTo(null);
	fenetre.setResizable(false);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createView();
    fenetre.setVisible(true);
    
	}
	
	public void createView(){
		   
			JPanel general = new JPanel();
			general.setLayout(new GridBagLayout());
			GridBagConstraints constrainte = new GridBagConstraints();
			constrainte.gridx = 0;
			constrainte.gridy = 0;
			Icon restart_icon =  new ImageIcon("Resources/icon_restart.png");
			JButton restart = new JButton(restart_icon);
			general.add(restart,constrainte);
			
			constrainte.gridx = 1;
			Icon run_icon =  new ImageIcon("Resources/icon_run.png");
			JButton run = new JButton(run_icon);
			general.add(run);
			constrainte.gridx = 2;
			Icon step_icon =  new ImageIcon("Resources/icon_step.png");
			JButton step = new JButton(step_icon);
			general.add(step);
			constrainte.gridx = 3;
			Icon pause_icon =  new ImageIcon("Resources/icon_pause.png");
			JButton pause = new JButton(pause_icon);
			general.add(pause);
			
			
			JPanel sliderpanel = new JPanel();
			sliderpanel.setLayout(new BoxLayout(sliderpanel, BoxLayout.Y_AXIS));
			
			JLabel sliderLabel = new JLabel("Number of turns per second", JLabel.CENTER);
	        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			JSlider speed = new JSlider(JSlider.HORIZONTAL,0,10,1);
			
			
			speed.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					
					
				}
			});
			speed.setMajorTickSpacing(1);
			speed.setMinorTickSpacing(1);
			speed.setPaintTrack(true);
			speed.setPaintTicks(true);
			speed.setPaintLabels(true);
			speed.setLabelTable(speed.createStandardLabels(1));
			general.add(sliderLabel);
			sliderpanel.add(sliderLabel);
			sliderpanel.add(speed);
			general.add(sliderpanel);
			
			
			fenetre.setContentPane(general);
	 
			
	 
		}
		
		
	
}
