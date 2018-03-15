package aStar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Cell;
import dataStructures.Data;
import dataStructures.WayPointStructure;

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
    private JButton clear;
    private JButton clearAll;
    private JPanel pPanel;
    private JButton cost1;
    private JButton cost2;
    private JButton cost3;
    private JButton wpoints;
    private JButton barrierBtn;
    private JButton normalBtn;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private boolean activeWpoints;
    private boolean activeBarrier;
    private boolean activeCost1;
    private boolean activeCost2;
    private boolean activeCost3;
    private ArrayList<Cell> wPointsList;

    private int N_COLS = 20;
    private int N_ROWS = 20;

    private final Color startCellColor = new Color(0, 0, 153);
    private final Color goalCellColor = new Color(128, 255, 0);
    private final Color barrierColor = new Color(0, 0, 0);
    //private final Color defaultCellColor = new Color(255,255,204);
    private final Color pathCellColor = new Color(102, 0, 0);
    private final Color cost1Color = new Color(255, 102, 102);
    private final Color cost2Color = new Color(255, 204, 51);
    private final Color cost3Color = new Color(255, 102, 0);

    private int startX;
    private int startY;
    private int goalX;
    private int goalY;

    private int nClicks;

    private Cell matrix[][];

    public Board() {
        matrix = new Cell[N_ROWS + 1][N_COLS + 1];
        nClicks = 0;
        activeBarrier = false;
        activeWpoints = false;
        activeCost1 = false;
        activeCost2 = false;
        activeCost3 = false;
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
        wpoints = new javax.swing.JButton();
        cost1 = new javax.swing.JButton();
        cost2 = new javax.swing.JButton();
        cost3 = new javax.swing.JButton();
        pPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        barrierBtn = new JButton();
        normalBtn = new JButton();

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
                        .addGap(0, 1156, Short.MAX_VALUE)
        );
        pMatrixLayout.setVerticalGroup(
                pMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        barrierBtn.setBackground(new java.awt.Color(0, 0, 0));
        barrierBtn.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        barrierBtn.setForeground(new java.awt.Color(204, 0, 204));
        barrierBtn.setText("Barrier");

        normalBtn.setBackground(new java.awt.Color(0, 255, 204));
        normalBtn.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        normalBtn.setText("Normal");

        pConfig.setBackground(new java.awt.Color(255, 255, 204));
        pConfig.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        pConfig.setMaximumSize(new java.awt.Dimension(260, 576));

        cols.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cols.setForeground(new java.awt.Color(255, 102, 0));
        cols.setText("Columns");

        rows.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rows.setForeground(new java.awt.Color(255, 102, 0));
        rows.setText("Rows");

        start.setBackground(new Color(55, 55, 55));
        start.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        start.setForeground(new java.awt.Color(0, 209, 0));
        start.setText("Start");

        exit.setBackground(new java.awt.Color(255, 255, 255));
        exit.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 0, 0));
        exit.setText("Exit");

        lblConfig.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        lblConfig.setForeground(new java.awt.Color(0, 0, 0));
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
        clear.setForeground(new java.awt.Color(255, 0, 51));
        clear.setText("Clear");
        clear.setBackground(new Color(255, 255, 51));

        clearAll.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        clearAll.setForeground(new java.awt.Color(255, 0, 51));
        clearAll.setText("Clear All");
        clearAll.setBackground(new Color(255, 255, 51));

        wpoints.setBackground(new java.awt.Color(102, 0, 102));
        wpoints.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        wpoints.setText("Way Points");
        wpoints.setForeground(new Color(51, 255, 51));

        cost1.setBackground(cost1Color);
        cost1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cost1.setText("Cost 1");
        cost1.setForeground(new Color(0, 0, 153));

        cost2.setBackground(cost2Color);
        cost2.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cost2.setText("Cost 2");
        cost2.setForeground(new Color(0, 0, 153));

        cost3.setBackground(cost3Color);
        cost3.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cost3.setText("Cost 3");
        cost3.setForeground(new Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel1.setText("Basic Search");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Advanced Search");
        pPanel.setLayout(new BorderLayout());
        this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
        this.pMatrix.setVisible(true);

        javax.swing.GroupLayout pConfigLayout = new javax.swing.GroupLayout(pConfig);
        pConfig.setLayout(pConfigLayout);
        pConfigLayout.setHorizontalGroup(
                pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConfigLayout.createSequentialGroup()
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(exit))
                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1)
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addGap(11, 11, 11)
                                                                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(106, 106, 106))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pConfigLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addComponent(wpoints)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(barrierBtn)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(normalBtn))
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(startLbl)
                                                                        .addComponent(goalLbl))
                                                                .addGap(79, 79, 79)
                                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtGoalX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(8, 8, 8))
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addComponent(clear)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(clearAll))
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addComponent(cost1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cost2)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(cost3)))))
                                .addGap(30, 30, 30))
                        .addGroup(pConfigLayout.createSequentialGroup()
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addComponent(rows)
                                                                .addGap(232, 232, 232)
                                                                .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addComponent(cols)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(nCols, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                                .addGap(85, 85, 85)
                                                                .addComponent(lblConfig))))
                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                .addGap(119, 119, 119)
                                                .addComponent(genMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        pConfigLayout.setVerticalGroup(
                pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pConfigLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblConfig)
                                .addGap(35, 35, 35)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rows)
                                        .addComponent(nRows, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nCols, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cols))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genMatrix, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtStartX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtStartY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startLbl))
                                .addGap(29, 29, 29)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtGoalX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGoalY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(goalLbl))
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pConfigLayout.createSequentialGroup()
                                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cost2, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cost1)
                                                                .addComponent(cost3)))
                                                .addGap(18, 18, 18)
                                                .addComponent(wpoints))
                                        .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(normalBtn)
                                                .addComponent(barrierBtn)))
                                .addGap(49, 49, 49)
                                .addGroup(pConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(clear)
                                        .addComponent(clearAll))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(exit)
                                .addGap(37, 37, 37))
        );

        pPanel.setPreferredSize(new java.awt.Dimension(1459, 1000));

        pPanel.add(pMatrix, BorderLayout.CENTER);
        pPanel.add(pConfig, BorderLayout.EAST);
        pFrame.add(pPanel);

        this.genMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    N_COLS = Integer.parseInt(nCols.getText());
                    N_ROWS = Integer.parseInt(nRows.getText());
                    createMatrix();
                } catch (NumberFormatException e) {
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
                nClicks = 0;
                txtGoalX.setText("");
                txtStartY.setText("");
                txtStartX.setText("");
                txtGoalY.setText("");
                repaintMatrix();
            }
        });

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                createMatrix();
                activeWpoints = false;
                activeBarrier = false;
                activeCost1 = false;
                activeCost2 = false;
                activeCost3 = false;
                txtGoalX.setText("");
                txtStartY.setText("");
                txtStartX.setText("");
                txtGoalY.setText("");
            }
        });

        wpoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                nClicks = 0;
                activeWpoints = true;
                wPointsList = new ArrayList<Cell>();
                activeBarrier = false;
                activeCost1 = false;
                activeCost2 = false;
                activeCost3 = false;
            }
        });

        this.barrierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeBarrier = true;
                activeWpoints = false;
                activeCost1 = false;
                activeCost2 = false;
                activeCost3 = false;
            }
        });

        this.normalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeBarrier = false;
                activeWpoints = false;
                activeCost1 = false;
                activeCost2 = false;
                activeCost3 = false;
                nClicks = 0;
            }
        });

        this.cost1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeCost1 = true;
                activeBarrier = false;
                activeWpoints = false;
                activeCost2 = false;
                activeCost3 = false;
            }
        });

        this.cost2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeCost2 = true;
                activeBarrier = false;
                activeWpoints = false;
                activeCost1 = false;
                activeCost3 = false;
            }
        });

        this.cost3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeCost3 = true;
                activeBarrier = false;
                activeWpoints = false;
                activeCost1 = false;
                activeCost2 = false;
            }
        });

        this.pFrame.pack();
        this.pFrame.setLocationRelativeTo(null);
        this.pFrame.setVisible(true);
    }

    private void wPointsMode(Star s) {
        ArrayList<WayPointStructure> allPath = new ArrayList<WayPointStructure>();
        wPointsList.remove(0);
        while (!wPointsList.isEmpty()) {
            Cell goal = wPointsList.get(0);

            goalX = goal.getX();
            goalY = goal.getY();
            s.play(startX, startY, goalX, goalY);
            allPath.add(new WayPointStructure(s.getPath(), startX, startY, goalX, goalY));
            startX = goalX;
            startY = goalY;
            orderWayPoints();
            wPointsList.remove(goal);
            repaintMatrix();
            s = new Star(matrix);
        }
        wayPointsPathPaint(allPath);
    }
    //Tengo la lista ordenada respecto al origen

    private void orderWayPoints() {
        // TODO Auto-generated method stub
        Cell next = wPointsList.get(0);
        for (int j = 1; j < wPointsList.size(); j++) {
            Cell cell = wPointsList.get(j);
            double a = (next.getX() - cell.getX());
            double b = (next.getY() - cell.getY());
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
            cell.setH(stim);
        }
        this.wPointsList.sort(new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                return c1.compareTo(c2);
            }
        });
    }

    private void wayPointsPathPaint(ArrayList<WayPointStructure> allPath) {
        // TODO Auto-generated method stub
        for (WayPointStructure p : allPath) {
            startX = p.getStartX();
            startY = p.getStartY();
            goalX = p.getGoalX();
            goalY = p.getGoalY();
            pathPaint(p.getFragment());
        }
    }

    /*
     * Borra el panel de la matriz y la pinta de nuevo
     * */
    private void createMatrix() {
        nClicks = 0;
        this.pMatrix.removeAll();
        this.pMatrix.setLayout(new GridLayout(this.N_ROWS, this.N_COLS));
        matrix = new Cell[N_ROWS + 1][N_COLS + 1];
        for (int i = 1; i <= N_ROWS; i++) {
            for (int j = 1; j <= N_COLS; j++) {
                Cell cell = new Cell(i, j);
                JButton b = cell.getCell();
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        nClicks++;
                        if (activeWpoints) {
                            if (nClicks == 1) {
                                cell.setStart();
                                startX = cell.getX();
                                startY = cell.getY();
                            } else {
                                cell.setGoal();
                                double a = (startX - cell.getX());
                                double b = (startY - cell.getY());
                                double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
                                cell.setH(stim);
                            }
                            add(cell);
                        } else if (activeBarrier) {
                            cell.setBarrier(barrierColor);
                            markSpecialCell(cell.getX(), cell.getY(), barrierColor);
                        } else if (activeCost1) {
                            cell.setCost1();
                            markSpecialCell(cell.getX(), cell.getY(), cost1Color);
                        } else if (activeCost2) {
                            cell.setCost2();
                            markSpecialCell(cell.getX(), cell.getY(), cost2Color);
                        } else if (activeCost3) {
                            cell.setCost3();
                            markSpecialCell(cell.getX(), cell.getY(), cost3Color);
                        } else {
                            if (nClicks == 1) {
                                setStart(cell);
                            } else if (nClicks == 2) {
                                setGoal(cell);
                            } else {
                                cell.setBarrier(barrierColor);
                            }
                        }
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
        this.pMatrix.getComponent(((x - 1) * N_COLS) + (y - 1)).setBackground(new Color(036, 231, 017));
    }

    public void markSpecialCell(int x, int y, Color c) {
        int n = ((x - 1) * N_COLS) + (y - 1);
        this.pMatrix.getComponent(n).setBackground(c);
    }

    public void pathPaint(ArrayList<Cell> path) {
        for (int i = 0; i < path.size(); i++) {
            Cell c = path.get(i);
            int x = c.getX();
            int y = c.getY();
            if (x == startX && y == startY) {
                markSpecialCell(startX, startY, startCellColor);
            } else if (x == goalX && y == goalY) {
                markSpecialCell(goalX, goalY, goalCellColor);
            } else {
                markSpecialCell(x, y, pathCellColor);
            }
        }
    }

    public void repaintMatrix() {
        nClicks = 0;
        Cell[][] m = this.matrix;
        createMatrix();
        for (int i = 1; i <= N_ROWS; i++) {
            for (int j = 1; j <= N_COLS; j++) {
                if (m[i][j].isBarrier()) {
                    this.matrix[i][j].setBarrier(barrierColor);
                }
            }
        }
    }

    public void ready() {

        this.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String err = "Los datos introducidos son incorrectos!";
                try {
                    /*startX = 3;
					startY = 9;
					goalX = 8;
					goalY = 8;
					for(int j = 1; j<=11; j++)
						matrix[6][j].setBarrier(barrierColor);*/
                    Star s = new Star(matrix);
                    if (!txtStartX.getText().equals("") || !txtStartY.getText().equals("") || !txtGoalX.getText().equals("") || !txtGoalY.getText().equals("")) {
                        startX = Integer.parseInt(txtStartX.getText());
                        startY = Integer.parseInt(txtStartY.getText());
                        goalX = Integer.parseInt(txtGoalX.getText());
                        goalY = Integer.parseInt(txtGoalY.getText());
                        txtGoalX.setText("");
                        txtStartY.setText("");
                        txtStartX.setText("");
                        txtGoalY.setText("");
                        repaintMatrix();
                    }
                    if (startX > N_ROWS || startY > N_COLS || goalX > N_ROWS || goalY > N_COLS) {
                        throw new Exception("Error, fuera de rango!");
                    }
                    if (!activeWpoints && matrix[startX][startY].isBarrier()) {
                        throw new Exception("Error, inicio en obstaculo!");
                    }
                    if (!activeWpoints && matrix[goalX][goalY].isBarrier()) {
                        throw new Exception("Error, fin en obstaculo!");
                    }
                    nClicks = 0;
                    if (!activeWpoints) { // If is a normal mode
                        s.play(startX, startY, goalX, goalY);
                        pathPaint(s.getPath());
                    } else {
                        wPointsMode(s);
                    }

                    MouseListener mouseListener = new MouseListener() {
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
                            if (e.getButton() == MouseEvent.BUTTON3) {
                                repaintMatrix();
                            }     // MouseEvent.BUTTON3 es el boton derecho
                        }
                    };

                    pFrame.addMouseListener(mouseListener);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), err, "Error, formato incorrecto", JOptionPane.ERROR_MESSAGE);
                } catch (Exception excep) {
                    JOptionPane.showMessageDialog(new JFrame(), err, excep.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void add(Cell c) {
        Data data = existsCell(this.wPointsList, c.getX(), c.getY());
        if (!data.isFound()) {
            this.wPointsList.add(c);
        }
        this.wPointsList.sort(new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                // TODO Auto-generated method stub
                return c1.compareTo(c2);
            }
        });
    }

    private Data existsCell(ArrayList<Cell> list, int x, int y) {
        boolean ok = false;
        Cell found = null;
        int i = 0;
        while (i < list.size() && !ok) {
            Cell aux = list.get(i);
            if (aux.getX() == x && aux.getY() == y) {
                ok = true;
                found = aux;
            }
            i++;
        }
        return new Data(ok, found);
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

}
