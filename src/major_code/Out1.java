/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package major_code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
//import javax.swing.JFrame;

/**
 *
 * @author Anji
 */
public class Out1 extends javax.swing.JFrame {

    /**
     * Creates new form Out
     */
    
    LinkedList<ProcessOrder> processOrder[] = new LinkedList[Processor.getNumberOfCores()];
    long endTime;
    boolean called;
    int policy;
    int numberOfProcess;
    
    
    public Out1() {
        initComponents();
    }
     public Out1(LinkedList[] processOrder, long endTime, int policy,int numberOfProcess) {

  //      super("Gantt Chart");
          initComponents();
        called = false;
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  "+endTime);
     //   this.setOpacity(0.5f);
        this.processOrder = processOrder;
            
        this.endTime = endTime;
       // this.setSize(2000, 2000);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setBackground(Color.WHITE);
        this.policy=policy;
        this.numberOfProcess=numberOfProcess;
        if(policy==1)
           // g.drawString("Process Scheduling and Execution Environment Simulator - FCFS", 20, 70);
               jLabel1.setText("Process Scheduling and Execution Environment Simulator - FCFS");
            if(policy==2)
           // g.drawString("Process Scheduling and Execution Environment Simulator - SJF", 20, 70);
                  jLabel1.setText("Process Scheduling and Execution Environment Simulator - SJF");
            if(policy==3)
          //  g.drawString("Process Scheduling and Execution Environment Simulator - SRJF", 20, 70);
                  jLabel1.setText("Process Scheduling and Execution Environment Simulator - SRJF");
            if(policy==4)
            //g.drawString("Process Scheduling and Execution Environment Simulator - PRIORITY", 20, 70);
                  jLabel1.setText("Process Scheduling and Execution Environment Simulator - PRIORITY");
            if(policy==5)
           // g.drawString("Process Scheduling and Execution Environment Simulator - ROUND ROBIN", 20, 70);
                  jLabel1.setText("Process Scheduling and Execution Environment Simulator - ROUND ROBIN");
            if(policy==6)
            //g.drawString("Process Scheduling and Execution Environment Simulator - CUSTOM SCHEDULER", 20, 70);
                  jLabel1.setText("Process Scheduling and Execution Environment Simulator - CUSTOM SCHEDULER");
           
        
        //this.setVisible(true);
        
    }
      public void paint(Graphics g) {
        if (called == false) {
            ProcessOrder po;
            int size = 0;
            int cores = Processor.getNumberOfCores();
            ProcessOrder currentProcess[] = new ProcessOrder[cores];
            int x[] = new int[cores];
            int y[] = new int[cores];
            int newx,newy;
            Color colors[] = new Color[8];
            colors[0] = new Color(255, 0, 0);
            colors[1] = new Color(0, 0, 255);
            colors[2] = new Color(128, 0, 128);
            colors[3] = new Color(0, 0, 128);
            colors[4] = new Color(0, 255, 255);
            colors[5] = new Color(128, 0, 0);
            colors[6] = new Color(0, 128, 128);
            colors[7] = new Color(128, 128, 0);
            
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        /*   if(policy==1)
           // g.drawString("Process Scheduling and Execution Environment Simulator - FCFS", 20, 70);
               jLabel1.setText("Process Scheduling and Execution Environment Simulator - FCFS");
            if(policy==2)
            g.drawString("Process Scheduling and Execution Environment Simulator - SJF", 20, 70);
            if(policy==3)
            g.drawString("Process Scheduling and Execution Environment Simulator - SRJF", 20, 70);
            if(policy==4)
            g.drawString("Process Scheduling and Execution Environment Simulator - PRIORITY", 20, 70);
            if(policy==5)
            g.drawString("Process Scheduling and Execution Environment Simulator - ROUND ROBIN", 20, 70);
            if(policy==6)
            g.drawString("Process Scheduling and Execution Environment Simulator - CUSTOM SCHEDULER", 20, 70);*/
            for (int i = 0; i < cores; i++) {
                x[i] = 100;
                y[i] = 100 + i * 100;
            }
            newx=100;
            newy=100+cores*100;
            /*for (int i = 0; i < cores; i++) {
            if (processOrder[i] != null) {
                size = size + processOrder[i].size();
            }
            }*/
            size=numberOfProcess;
            
            for(int i=1;i<=size;i++)
            {
              g.setColor(colors[i % 8]);
              g.fillRect(newx,newy,50,20);
              g.setColor(Color.black);
              g.setFont(new Font("TimesRoman", Font.PLAIN, 17));
              g.drawString("p"+i,newx+50,newy+20);
              newx+=100;
            
            }
            for (int i = 0; i < cores; i++) {
                g.setColor(Color.black);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.drawString("cpu " + i + "", 20, y[i] + 25);

            }

            /*for (int i = 0; i < processOrder.length; i++) {
            System.out.println("core " + i);
            for+ (ProcessOrder p : processOrder[i]) {
                System.out.println("----------Process " + p.pid + " and duration= " + p.duration);
            }
        }*/
            called = true;
            for (int j = 0; j < endTime; j++) {
                System.out.println("j " + j + " endTime " + endTime);
                for (int i = 0; i < cores; i++) {
                    if (!processOrder[i].isEmpty()) {
                        // System.out.println("hello");
                        if ((currentProcess[i] == null)) {
                            po = (ProcessOrder) processOrder[i].getFirst();
                            
                            if (po.startTime == j) {
                                currentProcess[i] = po;
                               // if((currentProcess[i].endTime - currentProcess[i].startTime) == 1)
                                    
                            }
                        }

                        if (currentProcess[i] == null) {
                            g.setColor(Color.WHITE);
                            g.fillRect(x[i], y[i], 2, 50);
                            g.setColor(Color.black);
                            g.drawLine(x[i], y[i], x[i] + 2, y[i]);
                            g.drawLine(x[i], y[i] + 50, x[i] + 2, y[i] + 50);
                            x[i] = x[i] + 2;
                        }

                        if (currentProcess[i] != null && currentProcess[i].startTime < currentProcess[i].endTime) {
                            currentProcess[i].startTime++;

                            g.setColor(colors[(currentProcess[i].pid) % 8]);
                            g.fillRect(x[i], y[i], 2, 50);
                            g.setColor(Color.black);
                            g.drawLine(x[i], y[i], x[i] + 2, y[i]);
                            g.drawLine(x[i], y[i] + 50, x[i] + 2, y[i] + 50);
                            x[i] = x[i] + 2;
                            g.setColor(Color.black);
                            g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                            System.out.println("----------Process " + currentProcess[i].pid + " start " + currentProcess[i].startTime + " end time " + currentProcess[i].endTime + " at cpu " + i);
                            if (currentProcess[i] != null && currentProcess[i].startTime == currentProcess[i].endTime) {
                                currentProcess[i] = null;
                                processOrder[i].remove();
                            }
                        }

                    } else {
                        
                        System.out.println("cpu " + i + " is empty");
                    }

                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("Error in gantt chart");
                }
            }
            
        }

    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(759, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(691, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Out1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Out1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Out1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Out1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Out1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
