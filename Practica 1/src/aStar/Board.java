package aStar;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Cell;

public class Board{
	
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
    private JButton clear;
    private JButton clearAll;
    
	private int N_COLS = 20;
	private int N_ROWS = 20;
	
	private final Color startCellColor = new Color(0,0,153);
	private final Color goalCellColor = new Color(128,255,0);
	private final Color barrierColor = new Color(0,0,0);
	//private final Color defaultCellColor = new Color(255,255,204);
	private final Color pathCellColor = new Color(102,0,0);
	
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
        clear = new javax.swing.JButton();
        clearAll = new javax.swing.JButton();
 	
        this.pFrame = new JFrame(); 
        this.pFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.pFrame.setBackground(new java.awt.Color(0, 153, 153));
        this.pFrame.setMaximumSize(new java.awt.Dimension(1450, 1000));
        this.pFrame.setPreferredSize(new java.awt.Dimension(1475, 1000));
        
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

        txtStartY.setPreferredSize(new java.awt.Dimension(50, 50));
        

        startLbl.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        startLbl.setForeground(new java.awt.Color(51, 0, 204));
        startLbl.setText("Start");

        goalLbl.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        goalLbl.setForeground(new java.awt.Color(0, 255, 0));
        goalLbl.setText("Goal");

        clear.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        clear.setForeground(new java.awt.Color(51, 102, 255));
        clear.setText("Clear");

        clearAll.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        clearAll.setForeground(new java.awt.Color(255, 0, 51));
        clearAll.setText("Clear All");
        clearAll.setBackground(new java.awt.Color(255, 255, 255));
        clear.setBackground(new java.awt.Color(255, 255, 255));


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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pConfigLayout.createSequentialGroup()
                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                            .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(start))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(exit)))
                            .addGroup(pConfigLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(clear)
                                .addGap(24, 24, 24)
                                .addComponent(clearAll)))
                        .addGap(30, 30, 30))))
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblConfig)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pConfigLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(genMatrix)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear)
                    .addComponent(clearAll))
                .addGap(55, 55, 55)
                .addComponent(exit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.pFrame.getContentPane());
        this.pFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        
        clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaintMatrix();
			} 
        });

        clearAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				createMatrix();
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
    	nClicks = 0;
    	this.pMatrix.removeAll();
    	this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
    	matrix = new Cell[N_ROWS+1][N_COLS+1];
    	for(int i = 1; i<=N_ROWS; i++) {
    		for(int j = 1; j<=N_COLS;j++) {
				Cell cell = new Cell(i,j);
				JButton b = cell.getCell();
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						nClicks++;
						if(nClicks == 1) 
							setStart(cell);
						else if (nClicks == 2)
							setGoal(cell);
						else
							cell.setBarrier(barrierColor);
					}

					private void setGoal(Cell cell) {
						// TODO Auto-generated method stub
						goalX = cell.getX();
						goalY = cell.getY();
						cell.setGoal();
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
    	//this.matrix[x][y].mark(new Color(036,231,017));
    	this.pMatrix.getComponent(((x-1)*N_ROWS)+y-1).setBackground(new Color(036,231,017));
    //	repaintMatrix();
    }
    
    public void markSpecialCell(int x, int y, Color c) {
    	this.pMatrix.getComponent(((x-1)*N_ROWS)+y-1).setBackground(c);
   // 	repaintMatrix();
    }
    
  //  public void repaintMatrix(){
    /*	this.pMatrix.removeAll();
    	this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
    	for(int i = 1; i<=N_ROWS; i++) {
    		for(int j = 1; j<=N_COLS;j++) {
				this.pMatrix.add(this.matrix[i][j].getCell()); 
    		}
    	}*/
    	//this.pMatrix.getComponent(20).setBackground(new Color(100,100,100));


  //  }
    
	public void pathPaint(ArrayList<Cell> path) {
		for(int i = 0; i<path.size(); i++) {
			Cell c = path.get(i);
			int x = c.getX();
			int y = c.getY();
			if(x == startX && y == startY) {
				markSpecialCell(startX, startY, startCellColor);
			}
			else if (x == goalX && y == goalY) {
				markSpecialCell(goalX, goalY, goalCellColor);
			}
			else {
				markSpecialCell(x,y, pathCellColor);
			}
		}
	}
	
	public void repaintMatrix() {
		nClicks = 0;
		Cell[][] m = this.matrix;
		createMatrix();
		for(int i = 1; i <= N_ROWS; i++) {
			for(int j = 1; j <= N_COLS; j++) {
				if(m[i][j].isBarrier()) {
					this.matrix[i][j].setBarrier(barrierColor);
				}
				/*Color c = this.pMatrix.getComponent(i).getBackground();
				if(c != barrierColor) {
					this.pMatrix.getComponent(i).setBackground(defaultCellColor);
				}
				else {
				//	matrix[i][j].setBarrier(this.barrierColor);
					this.pMatrix.getComponent(i).setBackground(barrierColor);
				}*/
			}
		}
	}
    
	public void ready() {
		
		this.start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String err = "Los datos introducidos son incorrectos!";
				try{	
					/*startX = 3;
					startY = 9;
					goalX = 8;
					goalY = 8;
					for(int j = 1; j<=11; j++)
						matrix[6][j].setBarrier(barrierColor);*/
					Star s = new Star(matrix);
					if(startX == 0 || startY == 0 || goalX == 0 || goalY == 0) {
						startX = Integer.parseInt(txtStartX.getText());
						startY = Integer.parseInt(txtStartY.getText());
						goalX = Integer.parseInt(txtGoalX.getText());
						goalY = Integer.parseInt(txtGoalY.getText());
					}
					if(startX > N_ROWS || startY > N_COLS || goalX > N_ROWS || goalY > N_COLS)
						throw new Exception("Error, fuera de rango!");
					if(matrix[startX][startY].isBarrier())
						throw new Exception("Error, inicio en obstaculo!");
					if(matrix[goalX][goalY].isBarrier())
						throw new Exception("Error, fin en obstaculo!");
					nClicks = 0;
					s.play(startX,startY,goalX,goalY);
					pathPaint(s.getPath());
					MouseListener mouseListener= new MouseListener() {					
				        @Override
				        public void mouseReleased(MouseEvent e) {
				        }
				
				        @Override
				        public void mousePressed(MouseEvent e) {
				        }
				
				        @Override
				        public void mouseExited(MouseEvent e) {
				        }
				
				        @Override
				        public void mouseEntered(MouseEvent e) {
				        }
				
				        @Override
				        public void mouseClicked(MouseEvent e) {
				            if(e.getButton() == MouseEvent.BUTTON3) {
				            	repaintMatrix();
				            }     // MouseEvent.BUTTON3 es el boton derecho
					    }
					};
					
					pFrame.addMouseListener(mouseListener);
					
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JFrame(),err,"Error, formato incorrecto",JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(new JFrame(),err,excep.getMessage(),JOptionPane.ERROR_MESSAGE);
				}
			}
		}); 
	}
	
	
	
    public Cell[][] getMatrix() {
		return matrix;
	}
    
}
