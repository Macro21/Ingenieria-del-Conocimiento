package aStar;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Cell;

public class Board {
	
    // Variables declaration - do not modify           
	private JFrame pFrame;
    private JLabel cols;
    private JLabel lblConfig;
    private JTextField nCols;
    private JTextField nRows;
    private JPanel pConfig;
    private JPanel pMatrix;
    private JLabel rows;
    private JButton start;
    private JButton exit;
    private JTextField txtGoalX;
    private JTextField txtStartX;
    private JTextField txtGoalY;
    private JTextField txtStartY;
    private JLabel startLbl;
    private JLabel goalLbl;
    private JButton genMatrix;
    
	private int N_COLS = 20;
	private int N_ROWS = 20;
	
	private int startX;
	private int startY;
	private int goalX;
	private int goalY;
	
	private int nClicks;
	
	private Cell matrix[][];
	
	public Board() {
		matrix = new Cell[N_ROWS+1][N_COLS+1];
		nClicks = 0;
		initComponents();	
	}
 
   /* private void initComponents() {

        pConfig = new javax.swing.JPanel();
        nRows = new javax.swing.JTextField();
        nCols = new javax.swing.JTextField();
        cols = new javax.swing.JLabel();
        rows = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        lblConfig = new javax.swing.JLabel();
        genMatrix = new javax.swing.JButton();
        txtStartY = new javax.swing.JTextField();
        txtStartX = new javax.swing.JTextField();
        txtGoalY = new javax.swing.JTextField();
        txtGoalX = new javax.swing.JTextField();
        startLbl = new javax.swing.JLabel();
        goalLbl = new javax.swing.JLabel();
        pMatrix = new javax.swing.JPanel();
        
        this.pFrame = new JFrame();
        
        createMatrix();
        
        this.pFrame.setTitle("Algorithm A* - Andrei Ionut Vaduva");
        this.pFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.pFrame.setBackground(new java.awt.Color(255, 255, 204));
        pMatrix.setPreferredSize(new Dimension(1170, 900));
        this.pFrame.setPreferredSize(new Dimension(1450,1000));
        pConfig.setBackground(new java.awt.Color(255, 255, 204));
        pConfig.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
   

        cols.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cols.setForeground(new java.awt.Color(255, 102, 0));
        cols.setText("Columns");

        rows.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rows.setForeground(new java.awt.Color(255, 102, 0));
        rows.setText("Rows");

        start.setBackground(new java.awt.Color(0, 0, 150));
        start.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        start.setForeground(new java.awt.Color(0, 209, 0));
        start.setText("Start");

        exit.setBackground(new java.awt.Color(255, 255, 255));
        exit.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 0, 0));
        exit.setText("Exit");

        lblConfig.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        lblConfig.setForeground(new java.awt.Color(255, 102, 0));
        lblConfig.setText("Configuration");

        genMatrix.setBackground(new java.awt.Color(51, 255, 204));
        genMatrix.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        genMatrix.setForeground(new java.awt.Color(0, 0, 204));
        genMatrix.setText("New Matrix");

        txtStartY.setPreferredSize(new java.awt.Dimension(50, 50));

        startLbl.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        startLbl.setForeground(new java.awt.Color(0, 0, 158));
        startLbl.setText("Start");

        goalLbl.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        goalLbl.setForeground(new Color(128,255,0));
        goalLbl.setText("Goal");

        this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
    	this.pMatrix.setVisible(true);
        
    	
        javax.swing.GroupLayout pConfigLayout = new javax.swing.GroupLayout(pConfig);
        pConfig.setLayout(pConfigLayout);
        pConfigLayout.setHorizontalGroup(
            pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblConfig)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pConfigLayout.createSequentialGroup()
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(goalLbl)
                            .addComponent(startLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                                .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                                .addComponent(txtGoalX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(pConfigLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pConfigLayout.createSequentialGroup()
                                .addComponent(rows)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pConfigLayout.createSequentialGroup()
                                .addComponent(cols)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nCols, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                        .addComponent(genMatrix)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                        .addComponent(exit)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                        .addComponent(start)
                        .addGap(83, 83, 83))))
        );
        pConfigLayout.setVerticalGroup(
            pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblConfig)
                .addGap(42, 42, 42)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rows)
                    .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cols)
                    .addComponent(nCols, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(genMatrix)
                .addGap(18, 18, 18)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startLbl))
                .addGap(18, 18, 18)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGoalX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goalLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(start)
                .addGap(40, 40, 40)
                .addComponent(exit)
                .addContainerGap())
        );

        pMatrix.setBackground(new java.awt.Color(255, 255, 204));
        pMatrix.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.pFrame.getContentPane());
        this.pFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        
        this.genMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		N_COLS = Integer.parseInt(nCols.getText());
            		N_ROWS = Integer.parseInt(nRows.getText());		
            		createMatrix();
            	}
            	catch(NumberFormatException e) {
            		System.out.println("Error de parseo");
            	}
            }
        });
       
        
        this.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.exit(0);		
			}
		});

        this.pFrame.pack();
        this.pFrame.setLocationRelativeTo(null);
        this.pFrame.setVisible(true);
    }*/
    
    
    
    private void initComponents() {

        pMatrix = new javax.swing.JPanel();
        pConfig = new javax.swing.JPanel();
        nRows = new javax.swing.JTextField();
        nCols = new javax.swing.JTextField();
        cols = new javax.swing.JLabel();
        rows = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        lblConfig = new javax.swing.JLabel();
        genMatrix = new javax.swing.JButton();
        txtStartY = new javax.swing.JTextField();
        txtStartX = new javax.swing.JTextField();
        txtGoalY = new javax.swing.JTextField();
        txtGoalX = new javax.swing.JTextField();
        startLbl = new javax.swing.JLabel();
        goalLbl = new javax.swing.JLabel();

        this.pFrame = new JFrame();
        
        this.pFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.pFrame.setBackground(new java.awt.Color(0, 153, 153));
        this.pFrame.setMaximumSize(new java.awt.Dimension(1450, 1000));
        this.pFrame.setPreferredSize(new java.awt.Dimension(1450, 1000));
        
        createMatrix();
        
        this.pFrame.setTitle("Algorithm A* - Andrei Ionut Vaduva");
        this.pFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.pFrame.setBackground(new java.awt.Color(255, 255, 204));
      //  pMatrix.setPreferredSize(new Dimension(1170, 900));
      //  this.pFrame.setPreferredSize(new Dimension(1450,1000));
        pConfig.setBackground(new java.awt.Color(255, 255, 204));
        pConfig.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));

        pMatrix.setBackground(new java.awt.Color(255, 255, 204));
        pMatrix.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        pMatrix.setMaximumSize(new java.awt.Dimension(1170, 900));
        pMatrix.setPreferredSize(new java.awt.Dimension(1170, 900));

        javax.swing.GroupLayout pMatrixLayout = new javax.swing.GroupLayout(pMatrix);
        pMatrix.setLayout(pMatrixLayout);
        pMatrixLayout.setHorizontalGroup(
            pMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1166, Short.MAX_VALUE)
        );
        pMatrixLayout.setVerticalGroup(
            pMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );

        pConfig.setBackground(new java.awt.Color(255, 255, 204));
        pConfig.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        pConfig.setMaximumSize(new java.awt.Dimension(260, 576));

        cols.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cols.setForeground(new java.awt.Color(255, 102, 0));
        cols.setText("Columns");

        rows.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rows.setForeground(new java.awt.Color(255, 102, 0));
        rows.setText("Rows");

        start.setBackground(new java.awt.Color(255, 255, 255));
        start.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        start.setForeground(new java.awt.Color(0, 209, 0));
        start.setText("Start");

        exit.setBackground(new java.awt.Color(255, 255, 255));
        exit.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 0, 0));
        exit.setText("Exit");

        lblConfig.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        lblConfig.setForeground(new java.awt.Color(255, 102, 0));
        lblConfig.setText("Configuration");

        genMatrix.setBackground(new java.awt.Color(51, 255, 204));
        genMatrix.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        genMatrix.setForeground(new java.awt.Color(0, 0, 204));
        genMatrix.setText("New Matrix");

        startLbl.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        startLbl.setForeground(new java.awt.Color(255, 102, 0));
        startLbl.setText("Start");

        goalLbl.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        goalLbl.setForeground(new java.awt.Color(255, 102, 0));
        goalLbl.setText("Goal");
        
        this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
    	this.pMatrix.setVisible(true);

        javax.swing.GroupLayout pConfigLayout = new javax.swing.GroupLayout(pConfig);
        pConfig.setLayout(pConfigLayout);
        pConfigLayout.setHorizontalGroup(
            pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pConfigLayout.createSequentialGroup()
                        .addComponent(rows)
                        .addGap(48, 187, Short.MAX_VALUE))
                    .addGroup(pConfigLayout.createSequentialGroup()
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(goalLbl)
                                .addComponent(startLbl))
                            .addComponent(cols))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nCols, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pConfigLayout.createSequentialGroup()
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtGoalX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exit))
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                        .addComponent(genMatrix)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                        .addComponent(start)
                        .addGap(81, 81, 81))))
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblConfig)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pConfigLayout.setVerticalGroup(
            pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblConfig)
                .addGap(40, 40, 40)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rows)
                    .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cols)
                    .addComponent(nCols, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(genMatrix)
                .addGap(33, 33, 33)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startLbl))
                .addGap(24, 24, 24)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGoalX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goalLbl))
                .addGap(35, 35, 35)
                .addComponent(start)
                .addGap(31, 31, 31)
                .addComponent(exit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.pFrame.getContentPane());
        this.pFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(pConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addComponent(pConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        this.genMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		N_COLS = Integer.parseInt(nCols.getText());
            		N_ROWS = Integer.parseInt(nRows.getText());		
            		createMatrix();
            	}
            	catch(NumberFormatException e) {
            		System.out.println("Error de parseo");
            	}
            }
        });
       
        
        this.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.exit(0);		
			}
		});

        this.pFrame.pack();
        this.pFrame.setLocationRelativeTo(null);
        this.pFrame.setVisible(true);
    
    }
    
    /*
     * Borra el panel de la matriz y la pinta de nuevo
     * */
    private void createMatrix(){
    	this.pMatrix.removeAll();
    	this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
    	matrix = new Cell[N_ROWS+1][N_COLS+1];
    	for(int i = 1; i<=N_ROWS; i++) {
    		for(int j = 1; j<=N_COLS;j++) {
				Cell cell = new Cell(i,j);
				JButton b = cell.getCell();
				if(!cell.isBarrier()) {
					cell.setDefaultColor();
				}
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						nClicks++;
						if(nClicks == 1) 
							setStart(cell);
						else if (nClicks == 2)
							setGoal(cell);
						else
							cell.isBarrier();
					}

					private void setGoal(Cell cell) {
						// TODO Auto-generated method stub
						goalX = cell.getX();
						goalY = cell.getY();
						cell.setGoal();
						start.getActionListeners().notify();
					}

					private void setStart(Cell cell) {
						// TODO Auto-generated method stub
						startX = cell.getX();
						startY = cell.getY();
						cell.setStart();
					}
				});
				this.pMatrix.add(b);
				this.matrix[i][j] = cell;
    		}
    	}
    	this.pMatrix.validate();
    	this.pFrame.revalidate();
    	this.pFrame.repaint();
    	this.pFrame.pack();
    	
    }
    
    public void markCell(int x, int y) {
    	this.matrix[x][y].mark(new Color(036,231,017));
    	repaintMatrix();
    }
    
    public void markSpecialCell(int x, int y, Color c) {
    	this.matrix[x][y].mark(c);
    	repaintMatrix();
    }
    
    public void repaintMatrix(){
    	this.pMatrix.removeAll();
    	this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
    	for(int i = 1; i<=N_ROWS; i++) {
    		for(int j = 1; j<=N_COLS;j++) {
				this.pMatrix.add(this.matrix[i][j].getCell()); 
    		}
    	}
    	this.pMatrix.revalidate();
    	this.pFrame.revalidate();

    }
    
	public void pathPaint(ArrayList<Cell> path) {
		for(int i = 0; i<path.size(); i++) {
			Cell c = path.get(i);
			int x = c.getX();
			int y = c.getY();
			if(x == startX && y == startY) {
				markSpecialCell(startX, startY, new Color(0,0,153));
			}
			else if (x == goalX && y == goalY) {
				markSpecialCell(goalX, goalY, new Color(128,255,0));
			}
			else {
				markSpecialCell(c.getX(),c.getY(), new Color(102,0,0));
			}
		}
	}
	
	public void repaintDefaultMatrix(Cell matrix[][]) {
		createMatrix();	
		for(int i = 1; i<= N_ROWS; i++) {
			for(int j = 1; j <= N_COLS; j++) {
				Cell c = matrix[i][j];
				if(c.isBarrier()) {
					this.matrix[i][j].setBarrier();
				}
			}
		}
	}
    
    public Cell[][] getMatrix() {
		return matrix;
	}
    
    public int getRows() {
		return N_ROWS;
	}
    
    public int getCols() {
		return N_COLS;
	}
    
    public JButton getStart() {
		return start;
	}
    
    public JTextField getStartXtxt() {
		return txtStartX;
	}
    
    public JTextField getStartYtxt() {
		return txtStartY;
	}
    
    public int getN_COLS() {
		return N_COLS;
	}
    
    public int getN_ROWS() {
		return N_ROWS;
	}
    
    public JTextField getxGoal() {
		return txtGoalX;
	}
    
    public JTextField getyGoal() {
		return txtGoalY;
	}

	public void setXs(int startX) {
		this.startX = startX;
	}

	public void setYs(int startY) {
		this.startY = startY;
	}

	public void setXg(int goalX) {
		this.goalX = goalX;
	}

	public void setYg(int goalY) {
		this.goalY = goalY;
	}
    
    public JFrame getpFrame() {
		return pFrame;
	}
    
}
