package aStar;

import java.awt.Color;
import java.awt.Dimension;
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
    private JTextField xGoal;
    private JTextField startXtxt;
    private JTextField yGoal;
    private JTextField startYtxt;
    private JLabel lblStart;
    private JLabel lblGoal;
    private JButton genMatrix;
    
	private int N_COLS = 6;
	private int N_ROWS = 6;
	
	private int startX;
	private int startY;
	private int goalX;
	private int goalY;
	
	private Cell matrix[][];
	
	
	public Board() {
		matrix = new Cell[N_ROWS+1][N_COLS+1];
		initComponents();	
	}
 
    private void initComponents() {

        pConfig = new javax.swing.JPanel();
        nRows = new javax.swing.JTextField();
        nCols = new javax.swing.JTextField();
        cols = new javax.swing.JLabel();
        rows = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        lblConfig = new javax.swing.JLabel();
        genMatrix = new javax.swing.JButton();
        startYtxt = new javax.swing.JTextField();
        startXtxt = new javax.swing.JTextField();
        yGoal = new javax.swing.JTextField();
        xGoal = new javax.swing.JTextField();
        lblStart = new javax.swing.JLabel();
        lblGoal = new javax.swing.JLabel();
        pMatrix = new javax.swing.JPanel();
        
        this.pFrame = new JFrame();
        
        createMatrix();
        
        this.pFrame.setTitle("Algorithm A* - Andrei Ionut Vaduva");
        this.pFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.pFrame.setBackground(new java.awt.Color(255, 255, 204));
        pMatrix.setPreferredSize(new Dimension(700, 600));
        this.pFrame.setPreferredSize(new Dimension(983,600));
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

        startYtxt.setPreferredSize(new java.awt.Dimension(50, 50));

        lblStart.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        lblStart.setForeground(new java.awt.Color(0, 0, 158));
        lblStart.setText("Start");

        lblGoal.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        lblGoal.setForeground(new Color(128,255,0));
        lblGoal.setText("Goal");

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
                            .addComponent(lblGoal)
                            .addComponent(lblStart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                                .addComponent(startXtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(startYtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                                .addComponent(xGoal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(yGoal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(startYtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startXtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStart))
                .addGap(18, 18, 18)
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yGoal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xGoal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGoal))
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
				if(b.getText().equals("")) {
					cell.setDefaultColor();
				}
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						cell.isBarrier();
					}
				});
				this.pMatrix.add(b);
				this.matrix[i][j] = cell;
    		}
    	}
    	this.pMatrix.setPreferredSize(new Dimension(700+(N_ROWS*20), 600+(N_COLS*15)));
    	this.pFrame.setPreferredSize(this.pMatrix.getPreferredSize());
    	this.pFrame.revalidate();
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
    	this.pMatrix.repaint();
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
				if(!c.getCell().getText().equals("")) {
					this.matrix[i][j].isBarrier();
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
		return startXtxt;
	}
    
    public JTextField getStartYtxt() {
		return startYtxt;
	}
    
    public int getN_COLS() {
		return N_COLS;
	}
    
    public int getN_ROWS() {
		return N_ROWS;
	}
    
    public JTextField getxGoal() {
		return xGoal;
	}
    
    public JTextField getyGoal() {
		return yGoal;
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
