import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{

	public JFrame interfaceFrame = new JFrame("GUI");
	
	public GUI() {
		Variable.GUIopen = true;
		Variable.status = "Waiting for GUI settings";
		initComponents();
	}

	private void btnStartActionPerformed(ActionEvent e) {
		String s = cmbFood.getSelectedItem().toString();

		if (s.equals("Swordfish")) {
			Variable.food = Variable.swordfish;
		}
		if (s.equals("Lobster")) {
			Variable.food = Variable.lobster;
		}
		if (s.equals("Tuna")) {
			Variable.food = Variable.tuna;
		}
		if (s.equals("Salmon")) {
			Variable.food = Variable.salmon;
		}
		if (s.equals("Trout")) {
			Variable.food = Variable.trout;
		}

		Variable.foodAmount = Integer.parseInt((String) cmbFoodAmount.getSelectedItem());
		
		switch(String.valueOf(cmbFightPlane.getSelectedItem())){
		case "Floor 1":
			Variable.currentArea = Variable.roaches1;
			break;
		case "Floor 2":
			Variable.currentArea = Variable.roaches2;
			break;
		}

		if(chkLowTierLoot.isSelected()){
			Variable.lootTier = Variable.lootTierLow;
		}else{
			Variable.lootTier = Variable.lootTierHigh;
		}
		
		Variable.GUIopen = false;
		Variable.status = "Starting script..";
		interfaceFrame.dispose();
	}
	
	private void initComponents() {

		lblFood = new JLabel();
		cmbFood = new JComboBox<>();
		btnStart = new JButton();
		lblFoodAmount = new JLabel();
		cmbFoodAmount = new JComboBox<>();
		lblFightPlane = new JLabel();
		cmbFightPlane = new JComboBox<>();
		lblLowTierLoot = new JLabel();
		chkLowTierLoot = new JCheckBox();

		interfaceFrame.setVisible(true);
		interfaceFrame.setTitle("RoachKiller F2P");
		interfaceFrame.setSize(250, 130);
		interfaceFrame.setLayout(new java.awt.FlowLayout());
		
		lblFood.setText("Food: ");
		interfaceFrame.add(lblFood);
		
		cmbFood.setModel(new DefaultComboBoxModel<>(new String[] { "Swordfish",
				"Lobster", "Tuna", "Salmon", "Trout" }));
		interfaceFrame.add(cmbFood);
		
		lblFoodAmount.setText("Food Amount: ");
		interfaceFrame.add(lblFoodAmount);
		
		cmbFoodAmount.setModel(new DefaultComboBoxModel<>(new String[] { "28",
				"27", "26", "25", "24", "23", "22", "21", "20", "19", "18",
				"17", "16", "15", "14", "13", "12", "11", "10", "9", "8", "7",
				"6", "5" }));
		interfaceFrame.add(cmbFoodAmount);
		
		lblFightPlane.setText("Location: ");
		interfaceFrame.add(lblFightPlane);
		
		cmbFightPlane.setModel(new DefaultComboBoxModel<>(new String[] { "Floor 1", "Floor 2" }));
		interfaceFrame.add(cmbFightPlane);
		
		lblLowTierLoot.setText("     Low Tier Loot: ");
		lblLowTierLoot.setToolTipText("Pick up low grade items (mith plate, uncut saphires, etc");
		interfaceFrame.add(lblLowTierLoot);
		
		chkLowTierLoot.setToolTipText("Pick up low grade items (mith plate, uncut saphires, etc");
		interfaceFrame.add(chkLowTierLoot);
		
		btnStart.setText("Start");
		btnStart.setPreferredSize(new Dimension(80,32));
		btnStart.setVerticalTextPosition(AbstractButton.CENTER);
		btnStart.setHorizontalTextPosition(AbstractButton.CENTER);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStartActionPerformed(e);
			}
		});
		interfaceFrame.add(btnStart);
	}

	private JLabel lblFood;
	private JComboBox<String> cmbFood;
	private JButton btnStart;
	private JLabel lblFoodAmount;
	private JComboBox<String> cmbFoodAmount;
	private JLabel lblFightPlane;
	private JComboBox<String> cmbFightPlane;
	private JLabel lblLowTierLoot;
	private JCheckBox chkLowTierLoot;
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}