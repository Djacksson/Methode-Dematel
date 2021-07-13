import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;

public class Saisie {

	JFrame frame = new JFrame();
	private JPanel contentPane;
	double D1[][] = new double [3][3];
	double D2[][] = new double [3][3];
	double D3[][] = new double [3][3];
	double I[][] = new double [3][3];
	double N[][] = new double [3][3];
	double T[][] = new double [3][3];
	double RC[][] = new double [2][3];
	double R_C[][] = new double [2][3];
	double c;
	int RA[][] = new int [2][3];
	String CE[] = new String [3];
	String effet = null;
	
	/*************************************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Saisie(); 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*************************************************************/
	
	/**************************************************************/
	
	public void detail(String value, Label label) {
		if(value.equals("0")) {
			label.setText("Influence 0");
		}
		else if(value.equals("1")) {
			label.setText("Influence 1");
		}
		else if(value.equals("2")) {
			label.setText("Influence 2");
		}
		else if(value.equals("3")) {
			label.setText("Influence 3");
		}
		else if(value.equals("4")) {
			label.setText("Influence 4");
		}
	}
	/*************************************************************/
	
	/**************************************************************/
	
	public void matrice(String value, double M[][], int i, int j) {
		M[i][j] = Double.parseDouble(value);
	}
	/*************************************************************/
	/**************************************************************/
	
	
	public double[][] matriceIndividuelle(double X[][], double Y[][], double Z[][]){
		double[][] A = new double [3][3];
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				A[i][j] =(X[i][j]+Y[i][j]+Z[i][j])/3;
			}
		}
		
		return A;
	}
	/*************************************************************/
	
	/**************************************************************/
	
	public double[][] sommeMatrice(double M[][]) {
		
        double s=0;
        double max=0;
        double[][] list = new double [2][3];
        double[][] S = new double[3][3];
        list = influenceR(M);

		for ( int i = 0; i < 2; ++i ) {
			for ( int j = 0; j < 3; ++j ) {
				if ( max < list[i][j] ) {
					max = list[i][j];
				}
			}
		}
	    
	    s = 1/max;
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				S[i][j] = s* M[i][j];
			}
		}
		
		return S;
	}
	/*************************************************************/
	
	/**************************************************************/
	
	public double[][] matricsoustraction(double N[][]){
		double M[][] = new double[3][3];
		
		 double I[][]= {{1,0,0,},{0,1,0},{0,0,1}};
	
	        for(int i=0;i<3;i++)
	        {
	            for(int j=0;j<3;j++)
	            {
	                M[i][j]=I[i][j]-N[i][j];
	            }
	        }
	        return M;
	}
	/*************************************************************/
	
	/**************************************************************/
		
	public double[][] invert(double a[][]){
         int n = a.length;
         double x[][] = new double[n][n];
         double b[][] = new double[n][n];
         int index[] = new int[n];
         for (int i=0; i<n; ++i) 
             b[i][i] = 1;
  
		  // Transform the matrix into an upper triangle
	        gaussian(a, index);
	  
	  // Update the matrix b[i][j] with the ratios stored
	         for (int i=0; i<n-1; ++i)
	             for (int j=i+1; j<n; ++j)
	                 for (int k=0; k<n; ++k)
	                     b[index[j]][k]
	                     	    -= a[index[j]][i]*b[index[i]][k];
	  
	  // Perform backward substitutions
	         for (int i=0; i<n; ++i) 
	         {
	             x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
	             for (int j=n-2; j>=0; --j) 
	             {
	                 x[j][i] = b[index[j]][i];
	                 for (int k=j+1; k<n; ++k) 
	                 {
	                     x[j][i] -= a[index[j]][k]*x[k][i];
	                 }
	                 x[j][i] /= a[index[j]][j];
	             }
	         }
	         return x;
	}
	/*************************************************************/

 	/*************************************************************/
 	
 	public void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];
 
        // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
  
    }
 	/*************************************************************/
 	
 	/*************************************************************/
 	
	//multiplier d*N
 	public double[][] matrictotal(double N[][]){

        double I[][]=new double[3][3] ;
        double X[][]=new double[3][3] ;
        double L[][]=new double[3][3] ;
        
        X = matricsoustraction(N);
        I = invert(X);
        
        for (int i= 0; i < 3;i++){
            for (int j = 0; j < 3;j++) {
               double s=0;
                /*
                  Multipliez la ligne de la première matrice par la colonne de la deuxième
                  matrice et stockez la somme du produit des éléments dans somme.
                 */
                for (int k = 0; k <  3;k++) {
                    s =s+ I[i][k] * N[k][j];
                }
 
                L[i][j] = s;
            }
        }
        return L;
	}
 	/*************************************************************/
 	
 	public double[][] influenceR(double R[][]) {
		
        double s=0;
        double[][] list = new double [2][3];
       
        //somme des lignes
        for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				s=s+R[i][j];						
			}
			list[0][i] = s;
			s = 0;
		}
        
        //somme des colonnes
        for(int j=0;j<3;j++){
			for(int i=0;i<3;i++){
				s= s+R[i][j];						
			}
			list[1][j] = s;
			s = 0;
        }
        return list;
 	 }
 	/**************************************************************/
 	
 	public double[][] Operation(double M[][]){
 		double[][] list = new double [3][3];
 		double[][] p = new double [2][3];
 		
 		list =  influenceR(M);
 		
		/*addition*/
			 p[0][0] = list[0][0] + list[1][0];
			 p[0][1] = list[0][1] + list[1][1];
			 p[0][2] = list[0][2] + list[1][2];
		/*soustraction*/	 
			 p[1][0] = list[0][0] - list[1][0];
			 p[1][1] = list[0][1] - list[1][1];
			 p[1][2] = list[0][2] - list[1][2];

		return p;
 	}
 	/**************************************************************/
 	
 	public int[][] rangRC(double M[][]) {
 		double m1[][] = new double[2][3];
 		double m2[][] = new double[2][3];
 		int m[][] = new int[2][3];

 		for (int i= 0; i < 2;i++){
            for (int j = 0; j < 3;j++) {
            	m1[i][j] = M[i][j];
         		m2[i][j] = M[i][j];
            }
        }
 		
 		Arrays.sort(m2[0]);
 		Arrays.sort(m2[1]);

        for (int j = 0; j < 3;j++) {
        	
        	if(m2[0][0] == m1[0][j]) {
        		m[0][j] = 1;
        	}if(m2[0][1] == m1[0][j]) {
        		m[0][j] = 2;
        	}if(m2[0][2] == m1[0][j]) {
        		m[0][j] = 3;
        	}
        	/***************************/
        	if(m2[1][0] == m1[1][j]) {
        		m[1][j] = 1;
        	}if(m2[1][1] == m1[1][j]) {
        		m[1][j] = 2;
        	}if(m2[1][2] == m1[1][j]) {
        		m[1][j] = 3;
        	}
        	
        }
 		
        /*
 		System.out.print("Rangs: \n");
 		for (int i= 0; i < 2;i++){
            for (int j = 0; j < 3;j++) {
            	System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n");
        }
        */
 		
 		return m;
 	}
 	
 	/************************************************************/
 	
 	//CAUSEEFFET
 	public String[] causeEffet(double M[][]) {
 		String[] a = new String[3];
 		
 		for(int i=0;i<3;i++){
			if(M[1][i]>=0) {
				a[i]="cause";
			}
			else  {
				a[i]="effet";
			}
 		}
 		return a;
	}
 	/**************************************************************/
 	
 	/************************************************************/
 	
 	public double threshold(double M[][]){
 		double s=0;
 		double c=0;
 		for(int i=0;i<3;i++){
 			for(int j=0;j<3;j++){
					s = s + M[i][j] ; 
			}
			c = s/(3*3);
 		}
 		 return c;
 	}
	
	/************************************************************/
 	
 	public double[][] roundMatrice(double M[][]){
 		
 		double[][] A = null;
 		double A1[][] = new double[2][3];
 		double A2[][] = new double[3][3];
 		
 		if(M.length == 2) {
 			for(int i=0; i<2; i++) {
 	        	for(int j=0; j<3; j++) {
 	        		A1[i][j] = (double)Math.round(M[i][j]*100)/100;
 	        	}
 	        }
 			A = A1;
 			
 		}if(M.length == 3) {
 			for(int i=0; i<3; i++) {
 	        	for(int j=0; j<3; j++) {
 	        		A2[i][j] = (double)Math.round(M[i][j]*100)/100;
 	        	}
 	        }
 			A = A2;
 		}
 		return A;
 	}
 	/**************************************************************/
 	
 	/********************************************************************/
 	public void testAffichageMatrice(double m1[][], double m2[][], double m3[][], double m4[][], double m5[][], String m6[], double k) {
 		
 		System.out.println("Affichage de la matrice individuelle");
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(m1[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("Affichage de la matrice Normalisee");
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(m2[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("Affichage de la matrice Totale");
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(m3[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("Affichage de l'influence \t");
		for (int i=0; i<2; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(m4[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("Affichage de l'influence relationship \t");
		for (int i=0; i<2; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(m4[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("Affichage de la causeEffet \t");
		for (int i=0; i<3; i++) {
			System.out.println(" causeeffet: "+ m6[i]);
		}
		
		System.out.println("Affichage de la THRESHOULD \t");
		System.out.println(" threshould:      "+ (double)Math.round(k*100)/100);
 	}
 	/**************************************************************/
 	
	Saisie() {
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] list = new String[] {"0","1","2","3","4"};
		
		/*************************************Decideur 1*********************************/
		Panel panel = new Panel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 10, 764, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Label label = new Label("Decideur 1 : ");
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		label.setBackground(new Color(245, 245, 220));
		label.setBounds(10, 10, 73, 22);
		panel.add(label);
		
		Label label_0 = new Label("Pas Influence");
		label_0.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_0.setBounds(221, 10, 80, 22);
		panel.add(label_0);
		
		Label label_1 = new Label("Pas Influence");
		label_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_1.setBounds(437, 10, 80, 22);
		panel.add(label_1);
		
		Label label_2 = new Label("Pas Influence");
		label_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_2.setBounds(655, 10, 100, 22);
		panel.add(label_2);
		
		Label label_3 = new Label("Pas Influence");
		label_3.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_3.setBounds(221, 54, 80, 22);
		panel.add(label_3);
		
		Label label_4 = new Label("Pas Influence");
		label_4.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_4.setBounds(437, 56, 80, 22);
		panel.add(label_4);
		
		Label label_5 = new Label("Pas Influence");
		label_5.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_5.setBounds(655, 54, 100, 22);
		panel.add(label_5);
		
		JComboBox<Object> comboBox_0 = new JComboBox<Object>(list);
		comboBox_0.setPreferredSize(new Dimension(25, 20));
		comboBox_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = (String)comboBox_0.getSelectedItem();
				int lig = 0;
				int col = 1;
				
				matrice(item,D1,lig,col);
				detail(item, label_0);
			}
		});
		comboBox_0.setBounds(162, 10, 50, 20);
		panel.add(comboBox_0);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>(list);
		comboBox_1.setPreferredSize(new Dimension(25, 20));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = (String)comboBox_1.getSelectedItem();
				int lig = 0;
				int col = 2;
				
				matrice(item,D1,lig,col);
				detail(item, label_1);
			}
		});
		comboBox_1.setBounds(380, 10, 50, 20);
		panel.add(comboBox_1);
		
		JComboBox<Object> comboBox_2 = new JComboBox<Object>(list);
		comboBox_2.setPreferredSize(new Dimension(25, 20));
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = (String)comboBox_2.getSelectedItem();
				int lig = 1;
				int col = 0;
				
				matrice(item,D1,lig,col);
				detail(item, label_2);
			}
		});
		comboBox_2.setBounds(600, 10, 50, 20);
		panel.add(comboBox_2);
		
		JComboBox<Object> comboBox_3 = new JComboBox<Object>(list);
		comboBox_3.setPreferredSize(new Dimension(25, 20));
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = (String)comboBox_3.getSelectedItem();
				int lig = 1;
				int col = 2;
				
				matrice(item,D1,lig,col);
				detail(item, label_3);
			}
		});
		comboBox_3.setBounds(162, 56, 50, 20);
		panel.add(comboBox_3);
		
		JComboBox<Object> comboBox_4 = new JComboBox<Object>(list);
		comboBox_4.setPreferredSize(new Dimension(25, 20));
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = (String)comboBox_4.getSelectedItem();
				int lig = 2;
				int col = 0;
				
				matrice(item,D1,lig,col);
				detail(item, label_4);
			}
		});
		comboBox_4.setBounds(380, 56, 50, 20);
		panel.add(comboBox_4);
		
		JComboBox<Object> comboBox_5 = new JComboBox<Object>(list);
		comboBox_5.setPreferredSize(new Dimension(25, 20));
		comboBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = (String)comboBox_5.getSelectedItem();
				int lig = 2;
				int col = 1;
				
				matrice(item,D1,lig,col);
				detail(item, label_5);
			}
		});
		comboBox_5.setBounds(600, 56, 50, 20);
		panel.add(comboBox_5);
		
		
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Initialisation des diagonales
				for (int i=0; i<3; i++) {
					for (int j=0; j<3; j++) {
						if (i==j) {
							D1[i][j] = 0;
						}
					}
				}
				
				//Test d'affchage de la matrice
				
				System.out.println("Affichage de la matrice du decideur 1");
				for (int i=0; i<3; i++) {
					for (int j=0; j<3; j++) {
						System.out.print((double)Math.round(D1[i][j]*100)/100 + "\t");
					}
					System.out.print("\n");
				}
				
			}
		});
		btnNewButton.setBounds(10, 100, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_0.setSelectedItem("0");
				comboBox_1.setSelectedItem("0");
				comboBox_2.setSelectedItem("0");
				comboBox_3.setSelectedItem("0");
				comboBox_4.setSelectedItem("0");
				comboBox_5.setSelectedItem("0");
			}
		});
		btnNewButton_1.setBounds(665, 100, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("B1 vs B2");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(101, 10, 60, 20);
		panel.add(lblNewLabel);
		
		JLabel lblBVsB = new JLabel("B1 vs B3");
		lblBVsB.setForeground(Color.WHITE);
		lblBVsB.setBounds(319, 10, 60, 20);
		panel.add(lblBVsB);
		
		JLabel lblBVsB_1 = new JLabel("B2 vs B3");
		lblBVsB_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBVsB_1.setForeground(Color.WHITE);
		lblBVsB_1.setBounds(535, 10, 60, 20);
		panel.add(lblBVsB_1);
		
		JLabel lblBVsB_2 = new JLabel("B2 vs B1");
		lblBVsB_2.setForeground(Color.WHITE);
		lblBVsB_2.setBounds(101, 56, 60, 20);
		panel.add(lblBVsB_2);
		
		JLabel lblBVsB_3 = new JLabel("B3 vs B1");
		lblBVsB_3.setForeground(Color.WHITE);
		lblBVsB_3.setBounds(319, 56, 60, 20);
		panel.add(lblBVsB_3);
		
		JLabel lblBVsB_4 = new JLabel("B3 vs B2");
		lblBVsB_4.setForeground(Color.WHITE);
		lblBVsB_4.setBounds(535, 56, 60, 20);
		panel.add(lblBVsB_4);
		
		/***********************************Decideur 2*************************************/
		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 150, 764, 130);
		contentPane.add(panel_1);
		
		Label label_6 = new Label("Decideur 2 :");
		label_6.setFont(new Font("Dialog", Font.BOLD, 13));
		label_6.setBackground(new Color(245, 245, 220));
		label_6.setBounds(10, 10, 73, 22);
		panel_1.add(label_6);
		
		Label label_0_1 = new Label("Pas Influence");
		label_0_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_0_1.setBounds(221, 12, 80, 22);
		panel_1.add(label_0_1);
		
		Label label_1_1 = new Label("Pas Influence");
		label_1_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_1_1.setBounds(437, 10, 80, 22);
		panel_1.add(label_1_1);
		
		Label label_2_1 = new Label("Pas Influence");
		label_2_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_2_1.setBounds(655, 10, 100, 22);
		panel_1.add(label_2_1);
		
		Label label_3_1 = new Label("Pas Influence");
		label_3_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_3_1.setBounds(221, 56, 80, 22);
		panel_1.add(label_3_1);
		
		Label label_4_1 = new Label("Pas Influence");
		label_4_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_4_1.setBounds(437, 56, 80, 22);
		panel_1.add(label_4_1);
		
		Label label_5_1 = new Label("Pas Influence");
		label_5_1.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_5_1.setBounds(655, 54, 100, 22);
		panel_1.add(label_5_1);
		
		JComboBox<Object> comboBox_0_1 = new JComboBox<Object>(list);
		comboBox_0_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_0_1.getSelectedItem();
				int lig = 0;
				int col = 1;
				
				matrice(item,D2,lig,col);
				detail(item, label_0_1);
			}
		});
		comboBox_0_1.setBounds(162, 12, 50, 20);
		panel_1.add(comboBox_0_1);
		
		JComboBox<Object> comboBox_1_1 = new JComboBox<Object>(list);
		comboBox_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_1_1.getSelectedItem();
				int lig = 0;
				int col = 2;
				
				matrice(item,D2,lig,col);
				detail(item, label_1_1);
			}
		});
		comboBox_1_1.setBounds(379, 10, 50, 20);
		panel_1.add(comboBox_1_1);
		
		JComboBox<Object> comboBox_2_1 = new JComboBox<Object>(list);
		comboBox_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_2_1.getSelectedItem();
				int lig = 1;
				int col = 0;
				
				matrice(item,D2,lig,col);
				detail(item, label_2_1);
			}
		});
		comboBox_2_1.setBounds(600, 10, 50, 20);
		panel_1.add(comboBox_2_1);
		
		JComboBox<Object> comboBox_3_1 = new JComboBox<Object>(list);
		comboBox_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_3_1.getSelectedItem();
				int lig = 1;
				int col = 2;
				
				matrice(item,D2,lig,col);
				detail(item, label_3_1);
			}
		});
		comboBox_3_1.setBounds(162, 58, 50, 20);
		panel_1.add(comboBox_3_1);
		
		JComboBox<Object> comboBox_4_1 = new JComboBox<Object>(list);
		comboBox_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_4_1.getSelectedItem();
				int lig = 2;
				int col = 0;
				
				matrice(item,D2,lig,col);
				detail(item, label_4_1);
			}
		});
		comboBox_4_1.setBounds(379, 56, 50, 20);
		panel_1.add(comboBox_4_1);
		
		JComboBox<Object> comboBox_5_1 = new JComboBox<Object>(list);
		comboBox_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_5_1.getSelectedItem();
				int lig = 2;
				int col = 1;
				
				matrice(item,D2,lig,col);
				detail(item, label_5_1);
			}
		});
		comboBox_5_1.setBounds(600, 56, 50, 20);
		panel_1.add(comboBox_5_1);
		
		JButton btnNewButton_2 = new JButton("Valider");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Initialisation des diagonales
				for (int i=0; i<3; i++) {
					for (int j=0; j<3; j++) {
						if (i==j) {
							D2[i][j] = 0;
						}
					}
				}
				
				//Test d'affchage de la matrice
				System.out.println("Affichage de la matrice du decideur 2");
				for (int i=0; i<3; i++) {
					for (int j=0; j<3; j++) {
						System.out.print((double)Math.round(D2[i][j]*100)/100 + "\t");
					}
					System.out.print("\n");
				}
				
			}
		});
		btnNewButton_2.setBounds(10, 100, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Reset");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_0_1.setSelectedItem("0");
				comboBox_1_1.setSelectedItem("0");
				comboBox_2_1.setSelectedItem("0");
				comboBox_3_1.setSelectedItem("0");
				comboBox_4_1.setSelectedItem("0");
				comboBox_5_1.setSelectedItem("0");
			}
		});
		btnNewButton_1_1.setBounds(665, 100, 89, 23);
		panel_1.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("B1 vs B2");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(99, 12, 60, 20);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblBVsB_2_1 = new JLabel("B2 vs B1");
		lblBVsB_2_1.setForeground(Color.WHITE);
		lblBVsB_2_1.setBounds(99, 58, 60, 20);
		panel_1.add(lblBVsB_2_1);
		
		JLabel lblBVsB_5 = new JLabel("B1 vs B3");
		lblBVsB_5.setForeground(Color.WHITE);
		lblBVsB_5.setBounds(324, 10, 60, 20);
		panel_1.add(lblBVsB_5);
		
		JLabel lblBVsB_3_1 = new JLabel("B3 vs B1");
		lblBVsB_3_1.setForeground(Color.WHITE);
		lblBVsB_3_1.setBounds(324, 56, 60, 20);
		panel_1.add(lblBVsB_3_1);
		
		JLabel lblBVsB_4_1 = new JLabel("B3 vs B2");
		lblBVsB_4_1.setForeground(Color.WHITE);
		lblBVsB_4_1.setBounds(535, 56, 60, 20);
		panel_1.add(lblBVsB_4_1);
		
		JLabel lblBVsB_1_1 = new JLabel("B2 vs B3");
		lblBVsB_1_1.setForeground(Color.WHITE);
		lblBVsB_1_1.setBounds(535, 10, 60, 20);
		panel_1.add(lblBVsB_1_1);
		
		/*************************************Decideur 3*********************************/
		Panel panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(10, 290, 764, 130);
		contentPane.add(panel_2);
		
		Label label_7 = new Label("Decideur 3 :");
		label_7.setFont(new Font("Dialog", Font.BOLD, 13));
		label_7.setBackground(new Color(245, 245, 220));
		label_7.setBounds(10, 10, 73, 22);
		panel_2.add(label_7);
		
		Label label_0_2 = new Label("Pas Influence");
		label_0_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_0_2.setBounds(221, 10, 80, 22);
		panel_2.add(label_0_2);
		
		Label label_1_2 = new Label("Pas Influence");
		label_1_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_1_2.setBounds(437, 10, 80, 22);
		panel_2.add(label_1_2);
		
		Label label_2_2 = new Label("Pas Influence");
		label_2_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_2_2.setBounds(655, 10, 100, 22);
		panel_2.add(label_2_2);
		
		Label label_3_2 = new Label("Pas Influence");
		label_3_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_3_2.setBounds(221, 54, 80, 22);
		panel_2.add(label_3_2);
		
		Label label_4_2 = new Label("Pas Influence");
		label_4_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_4_2.setBounds(437, 56, 80, 22);
		panel_2.add(label_4_2);
		
		Label label_5_2 = new Label("Pas Influence");
		label_5_2.setFont(new Font("Dialog", Font.ITALIC, 11));
		label_5_2.setBounds(655, 54, 100, 22);
		panel_2.add(label_5_2);
		
		JComboBox<Object> comboBox_0_2 = new JComboBox<Object>(list);
		comboBox_0_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_0_2.getSelectedItem();
				int lig = 0;
				int col = 1;
				
				matrice(item,D3,lig,col);
				detail(item, label_0_2);
			}
		});
		comboBox_0_2.setBounds(162, 10, 50, 20);
		panel_2.add(comboBox_0_2);
		
		JComboBox<Object> comboBox_1_2 = new JComboBox<Object>(list);
		comboBox_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_1_2.getSelectedItem();
				int lig = 0;
				int col = 2;
				
				matrice(item,D3,lig,col);
				detail(item, label_1_2);
			}
		});
		comboBox_1_2.setBounds(381, 10, 50, 20);
		panel_2.add(comboBox_1_2);
		
		JComboBox<Object> comboBox_2_2 = new JComboBox<Object>(list);
		comboBox_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_2_2.getSelectedItem();
				int lig = 1;
				int col = 0;
				
				matrice(item,D3,lig,col);
				detail(item, label_2_2);
			}
		});
		comboBox_2_2.setBounds(600, 10, 50, 20);
		panel_2.add(comboBox_2_2);
		
		JComboBox<Object> comboBox_3_2 = new JComboBox<Object>(list);
		comboBox_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_3_2.getSelectedItem();
				int lig = 1;
				int col = 2;
				
				matrice(item,D3,lig,col);
				detail(item, label_3_2);
			}
		});
		comboBox_3_2.setBounds(162, 56, 50, 20);
		panel_2.add(comboBox_3_2);
		
		JComboBox<Object> comboBox_4_2 = new JComboBox<Object>(list);
		comboBox_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_4_2.getSelectedItem();
				int lig = 2;
				int col = 0;
				
				matrice(item,D3,lig,col);
				detail(item, label_4_2);
			}
		});
		comboBox_4_2.setBounds(381, 56, 50, 20);
		panel_2.add(comboBox_4_2);
		
		JComboBox<Object> comboBox_5_2 = new JComboBox<Object>(list);
		comboBox_5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)comboBox_5_2.getSelectedItem();
				int lig = 2;
				int col = 1;
				
				matrice(item,D3,lig,col);
				detail(item, label_5_2);
			}
		});
		comboBox_5_2.setBounds(600, 56, 50, 20);
		panel_2.add(comboBox_5_2);
		
		JButton btnNewButton_3 = new JButton("Valider");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Initialisation des diagonales
				for (int i=0; i<3; i++) {
					for (int j=0; j<3; j++) {
						if (i==j) {
							D3[i][j] = 0;
						}
					}
				}
				
				//Test d'affchage de la matrice
				System.out.println("Affichage de la matrice du decideur 3");
				for (int i=0; i<3; i++) {
					for (int j=0; j<3; j++) {
						System.out.print((double)Math.round(D3[i][j]*100)/100 + "\t");
					}
					System.out.print("\n");
				}
			}
		});
		btnNewButton_3.setBounds(10, 100, 89, 23);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_1_2 = new JButton("Reset");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_0_2.setSelectedItem("0");
				comboBox_1_2.setSelectedItem("0");
				comboBox_2_2.setSelectedItem("0");
				comboBox_3_2.setSelectedItem("0");
				comboBox_4_2.setSelectedItem("0");
				comboBox_5_2.setSelectedItem("0");
			}
		});
		btnNewButton_1_2.setBounds(665, 100, 89, 23);
		panel_2.add(btnNewButton_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("B1 vs B2");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(99, 10, 60, 20);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblBVsB_2_2 = new JLabel("B2 vs B1");
		lblBVsB_2_2.setForeground(Color.WHITE);
		lblBVsB_2_2.setBounds(99, 56, 60, 20);
		panel_2.add(lblBVsB_2_2);
		
		JLabel lblBVsB_6 = new JLabel("B1 vs B3");
		lblBVsB_6.setForeground(Color.WHITE);
		lblBVsB_6.setBounds(326, 10, 60, 20);
		panel_2.add(lblBVsB_6);
		
		JLabel lblBVsB_3_2 = new JLabel("B3 vs B1");
		lblBVsB_3_2.setForeground(Color.WHITE);
		lblBVsB_3_2.setBounds(326, 56, 60, 20);
		panel_2.add(lblBVsB_3_2);
		
		JLabel lblBVsB_4_2 = new JLabel("B3 vs B2");
		lblBVsB_4_2.setForeground(Color.WHITE);
		lblBVsB_4_2.setBounds(535, 56, 60, 20);
		panel_2.add(lblBVsB_4_2);
		
		JLabel lblBVsB_1_2 = new JLabel("B2 vs B3");
		lblBVsB_1_2.setForeground(Color.WHITE);
		lblBVsB_1_2.setBounds(535, 10, 60, 20);
		panel_2.add(lblBVsB_1_2);
		
		JButton btnNewButton_4 = new JButton("Calculer");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				I = roundMatrice(matriceIndividuelle(D1,D2,D3));
				N = roundMatrice(sommeMatrice(I));
				T = roundMatrice(matrictotal(N));
				RC = roundMatrice(influenceR(T));
				R_C = roundMatrice(Operation(T));
				RA = rangRC(R_C);
				CE = causeEffet(R_C);
				c = threshold(T);
				
				//testAffichageMatrice(I,N,T,RC,R_C,CE,c);
				
				/********************************************************************/
				new Affichage(true, I, N, T, RC, R_C,RA, CE, c);
				frame.setVisible(false);
				/********************************************************************/
			}
		});
		btnNewButton_4.setBounds(350, 428, 89, 23);
		contentPane.add(btnNewButton_4);
		frame.setVisible(true);
	}
}
	