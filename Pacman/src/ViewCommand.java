import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewCommand {
	
	private JFrame fenetre;
	private int _turn;
	
	public ViewCommand() {
		_turn = 0;
		fenetre = new JFrame();
		fenetre.setTitle("Commande");
		fenetre.setSize(700, 300);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
    	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	createView();
    	fenetre.setVisible(true);
    }
	
	public int get_turn() {
		return _turn;
	}

	public void set_turn(int _turn) {
		this._turn = _turn;
	}

	public void createView(){
		    // Panel général constitué de 2 sous-panel top et bottom
			JPanel general = new JPanel();
			general.setLayout(new GridLayout(2,0));
			
			// Top Panel constitué des 4 buttons
			JPanel top_panel = new JPanel();
			top_panel.setLayout(new GridLayout(0,4));
			
			Icon restart_icon =  new ImageIcon("Resources/icon_restart.png");
			JButton restart = new JButton(restart_icon);
			top_panel.add(restart);
			
			Icon run_icon =  new ImageIcon("Resources/icon_run.png");
			JButton run = new JButton(run_icon);
			top_panel.add(run);
			
			Icon step_icon =  new ImageIcon("Resources/icon_step.png");
			JButton step = new JButton(step_icon);
			top_panel.add(step);
			
			Icon pause_icon =  new ImageIcon("Resources/icon_pause.png");
			JButton pause = new JButton(pause_icon);
			top_panel.add(pause);
			
			//Bottom Panel constitué du slider et affichage du nombre de tours 
			JPanel bottom_panel = new JPanel();
			bottom_panel.setLayout(new GridLayout(0,2));
			
			// Slider
			JPanel sliderpanel = new JPanel();
			sliderpanel.setLayout(new GridLayout(2,0));
			
			JLabel sliderLabel = new JLabel("Number of turns per second", JLabel.CENTER);
	        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        
			JSlider speed = new JSlider(JSlider.HORIZONTAL,0,10,1);
			speed.setMajorTickSpacing(1);
			speed.setMinorTickSpacing(1);
			speed.setPaintTicks(true);
			speed.setPaintLabels(true);
			speed.setLabelTable(speed.createStandardLabels(1));
			
			sliderpanel.add(sliderLabel);
			sliderpanel.add(speed);
			bottom_panel.add(sliderpanel);
			
			// Label des tours 
			JLabel nombreTours = new JLabel("Turn: "+ Integer.toString(_turn),JLabel.CENTER);
			bottom_panel.add(nombreTours);
			
			// Ajout des 2 sous-panel au panel général
			general.add(top_panel);
			general.add(bottom_panel);
			
			fenetre.setContentPane(general);
		}
		
		
	
}
