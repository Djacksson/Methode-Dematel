import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class Affichage {    
	JFrame frame = new JFrame();
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTextField textField;

    
	@SuppressWarnings("unchecked")
	Affichage(boolean b, double individual[][], double normale[][], double totale[][],double influence[][], double operation[][],int rang[][], String causeEffet[], double threshold){
    	frame = new JFrame();
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
         
        
        /**************************************************/
        table=new JTable();
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.setBorder(new CompoundBorder(new CompoundBorder(), null));
        table.setBounds(30,40,200,300);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{individual[0][0], individual[0][1], individual[0][2]},
        		{individual[1][0], individual[1][1], individual[1][2]},
        		{individual[2][0], individual[2][1], individual[2][2]},
        	},
        	new String[] {
        		"B1", "B2", "B3"
        	}
        ));
        
        
        @SuppressWarnings({ "rawtypes" })
		JList rowHeader = new JList(new AbstractListModel() {
    		private static final long serialVersionUID = 1L;
    		String[] values = new String[] {"B1", "B2", "B3"};
    		
        	public int getSize() {	return values.length;}
        	public Object getElementAt(int index) {	return values[index];}
        });
        
        rowHeader.setBackground(SystemColor.menu);
        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(16);
        rowHeader.setCellRenderer(new RowHeaderRenderer(table));
        
        JScrollPane scrollpane=new JScrollPane(table);
        scrollpane.setBounds(10, 64, 220, 71);
        scrollpane.setRowHeaderView(rowHeader);
        frame.getContentPane().add(scrollpane);
        
        Label label = new Label("Matrice Individuelle ");
        label.setFont(new Font("Dialog", Font.BOLD, 14));
        label.setAlignment(Label.CENTER);
        label.setBounds(10, 35, 220, 25);
        frame.getContentPane().add(label);
        
        JSeparator separator = new JSeparator();
        separator.setToolTipText("");
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLUE);
        separator.setBounds(255, 25, 5, 120);
        frame.getContentPane().add(separator);
        /*******************************************************/
        
        table_1 = new JTable();
        table_1.setFillsViewportHeight(true);
        table_1.setEnabled(false);
        table_1.setBorder(new CompoundBorder(new CompoundBorder(), null));
        table_1.setBounds(30,40,200,300);
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        		{normale[0][0], normale[0][1], normale[0][2]},
        		{normale[1][0], normale[1][1], normale[1][2]},
        		{normale[2][0], normale[2][1], normale[2][2]},
        	},
        	new String[] {
        		"B1", "B2", "B3"
        	}
        ));
        
        
        @SuppressWarnings({ "rawtypes" })
		JList rowHeader_1 = new JList(new AbstractListModel() {
    		private static final long serialVersionUID = 1L;
    		String[] values = new String[] {"B1", "B2", "B3"};
    		
        	public int getSize() {	return values.length;}
        	public Object getElementAt(int index) {	return values[index];}
        });
        
        rowHeader_1.setBackground(SystemColor.menu);
        rowHeader_1.setFixedCellWidth(50);
        rowHeader_1.setFixedCellHeight(16);
        rowHeader_1.setCellRenderer(new RowHeaderRenderer(table_1));
        
        JScrollPane scrollpane_1=new JScrollPane(table_1);
        scrollpane_1.setBounds(280, 64, 220, 71);
        scrollpane_1.setRowHeaderView(rowHeader_1);
        frame.getContentPane().add(scrollpane_1);
        
        Label label_1 = new Label("Matrice Normalisee ");
        label_1.setFont(new Font("Dialog", Font.BOLD, 14));
        label_1.setAlignment(Label.CENTER);
        label_1.setBounds(280, 35, 220, 25);
        frame.getContentPane().add(label_1);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setToolTipText("");
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setForeground(Color.BLUE);
        separator_1.setBounds(525, 25, 5, 120);
        frame.getContentPane().add(separator_1);
        /**********************************************************/
        
        table_2 = new JTable();
        table_2.setFillsViewportHeight(true);
        table_2.setEnabled(false);
        table_2.setBorder(new CompoundBorder(new CompoundBorder(), null));
        table_2.setBounds(130,40,200,300);
        table_2.setModel(new DefaultTableModel(
        	new Object[][] {
        		{totale[0][0], totale[0][1], totale[0][2]},
        		{totale[1][0], totale[1][1], totale[1][2]},
        		{totale[2][0], totale[2][1], totale[2][2]},
        	},
        	new String[] {
        		"B1", "B2", "B3"
        	}
        ));
        
        
        @SuppressWarnings({ "rawtypes" })
		JList rowHeader_2 = new JList(new AbstractListModel() {
    		private static final long serialVersionUID = 1L;
    		String[] values = new String[] {"B1", "B2", "B3"};
    		
        	public int getSize() {	return values.length;}
        	public Object getElementAt(int index) {	return values[index];}
        });
        
        rowHeader_2.setBackground(SystemColor.menu);
        rowHeader_2.setFixedCellWidth(50);
        rowHeader_2.setFixedCellHeight(16);
        rowHeader_2.setCellRenderer(new RowHeaderRenderer(table_2));
        
        JScrollPane scrollpane_2=new JScrollPane(table_2);
        scrollpane_2.setBounds(554, 64, 220, 71);
        scrollpane_2.setRowHeaderView(rowHeader_2);
        frame.getContentPane().add(scrollpane_2);
        
        Label label_2 = new Label("Matrice Totale ");
        label_2.setFont(new Font("Dialog", Font.BOLD, 14));
        label_2.setAlignment(Label.CENTER);
        label_2.setBounds(554, 35, 220, 25);
        frame.getContentPane().add(label_2);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setToolTipText("");
        separator_2.setForeground(Color.BLUE);
        separator_2.setBounds(10, 156, 764, 5);
        frame.getContentPane().add(separator_2);
        /*********************************************************/
        
        table_3 = new JTable();
        table_3.setFillsViewportHeight(true);
        table_3.setEnabled(false);
        table_3.setBorder(new CompoundBorder(new CompoundBorder(), null));
        table_3.setBounds(130,40,200,300);
        table_3.setModel(new DefaultTableModel(
        	new Object[][] {
        		{influence[0][0], influence[1][0], operation[0][0], rang[0][0], operation[1][0], rang[1][0], causeEffet[0]},
        		{influence[0][1], influence[1][1], operation[0][1], rang[0][1], operation[1][1], rang[1][1], causeEffet[1]},
        		{influence[0][2], influence[1][2], operation[0][2], rang[0][2], operation[1][2], rang[1][2], causeEffet[2]},
        	},
        	new String[] {
        		"R", "C", "R+C", "Rang (R+C)", "R-C", "Rang (R-C)", "Cause/Effet"
        	}
        ));
        
        
        @SuppressWarnings({ "rawtypes" })
		JList rowHeader_3 = new JList(new AbstractListModel() {
    		private static final long serialVersionUID = 1L;
    		String[] values = new String[] {"B1", "B2", "B3"};
    		
        	public int getSize() {	return values.length;}
        	public Object getElementAt(int index) {	return values[index];}
        });
        
        rowHeader_3.setBackground(SystemColor.menu);
        rowHeader_3.setFixedCellWidth(90);
        rowHeader_3.setFixedCellHeight(16);
        rowHeader_3.setCellRenderer(new RowHeaderRenderer(table_3));
        
        JScrollPane scrollpane_3=new JScrollPane(table_3);
        scrollpane_3.setBounds(10, 216, 764, 71);
        scrollpane_3.setRowHeaderView(rowHeader_3);
        frame.getContentPane().add(scrollpane_3);
        
        Label label_3 = new Label("D\u00E9gre d'Influence et de R\u00E9lation");
        label_3.setFont(new Font("Dialog", Font.BOLD, 14));
        label_3.setAlignment(Label.CENTER);
        label_3.setBounds(10, 185, 764, 25);
        frame.getContentPane().add(label_3);
        
        JLabel lblNewLabel = new JLabel("Threshold :");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(10, 370, 100, 25);
        frame.getContentPane().add(lblNewLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBounds(120, 370, 158, 25);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        textField.setText(""+(double)Math.round(threshold*100)/100);
        
        JButton btnNewButton = new JButton("Fermer");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		frame.setVisible(false);
        	}
        });
        btnNewButton.setBounds(685, 427, 89, 23);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Relancer");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new Launch();
        		frame.setVisible(false);
        	}
        });
        btnNewButton_1.setBounds(586, 427, 89, 23);
        frame.getContentPane().add(btnNewButton_1);
        
        JSeparator separator_2_1 = new JSeparator();
        separator_2_1.setToolTipText("");
        separator_2_1.setForeground(Color.BLUE);
        separator_2_1.setBounds(10, 310, 764, 5);
        frame.getContentPane().add(separator_2_1);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setToolTipText("");
        separator_3.setOrientation(SwingConstants.VERTICAL);
        separator_3.setForeground(Color.BLUE);
        separator_3.setBounds(392, 330, 2, 120);
        frame.getContentPane().add(separator_3);
        
        
        /**********************************************************/
        frame.setSize(800,500);
        frame.setVisible(b);
    }
    
    @SuppressWarnings("rawtypes")
	class RowHeaderRenderer extends JLabel implements ListCellRenderer {
		private static final long serialVersionUID = 1L;

		RowHeaderRenderer(JTable table) {
		    JTableHeader header = table.getTableHeader();
		    setOpaque(true);
		    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		    setHorizontalAlignment(CENTER);
		    setForeground(header.getForeground());
		    setBackground(header.getBackground());
		    setFont(header.getFont());
		  }

		  public Component getListCellRendererComponent(JList list, Object value,
		      int index, boolean isSelected, boolean cellHasFocus) {
		    setText((value == null) ? "" : value.toString());
		    return this;
		  }
	}
}  