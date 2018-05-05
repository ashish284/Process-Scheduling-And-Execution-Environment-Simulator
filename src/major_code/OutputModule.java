/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package major_code;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Anji
 */
public class OutputModule extends javax.swing.JFrame 
{

    /**
     * Creates new form OutputModule
     */
        long arrival,start,end,burst,wait,ioWait,ioBurst;
	int ioPercent,contextSwitches,pid,ioTotalDuration;
        DefaultTableModel model;
        JTable table;
        static int count;
        
        public OutputModule() 
        {
                initComponents();
                this.model = (DefaultTableModel)jTable1.getModel();
                JTable table=new JTable(model);
                this.table=table;
        }
    
        
        
        void displayTable(LinkedList<Process> processes, int policy)
	{
           
                this.setVisible(true);
                //table.setSize(1366,768);
               this.setSize(1366,768);
               // this.pack();
                Iterator<Process> iterator=processes.iterator();
                if(count==0)
                {
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("DETAILS");
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("");
                    model.addColumn("");
                //    JTableHeader header=table.getTableHeader();
                //    header.setFont(new Font("Arial Black", Font.BOLD,14));
                /*    table.setFont(new Font("Serif", Font.BOLD,14));
                    table.setRowHeight(50);
                    DefaultTableCellRenderer header=new DefaultTableCellRenderer();
                    header.setFont(header.getFont().deriveFont(Font.BOLD));
                    table.getColumn(0).setHeaderRenderer(header);*/
                    count++;
                }
            if(policy==1)
            {        
                
                           // model.addElement("FCFS");
              /*  model.addColumn("PID");
                model.addColumn("ARRIVAL TIME");
                model.addColumn("START TIME");
                model.addColumn("CPU EXECUTION TIME");
                model.addColumn("IO EXECUTION TIME");
                model.addColumn("EXIT TIME");
                model.addColumn("RESPONSE TIME");
                model.addColumn("TURN AROUND TIME");
                model.addColumn("READY QUEUE WAIT TIME");
                model.addColumn("IO WAIT TIME");
                model.addColumn("CONTEXT SWITCHES");*/
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","FCFS","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"PID","ARRIVAL TIME","START TIME","CPU EXECUTION TIME","IO EXECUTION TIME","EXIT TIME","RESPONSE TIME","TURN AROUND TIME","READY QUEUE WAIT TIME","IO WAIT TIME","CONTEXT SWITCHES"});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                while(iterator.hasNext())
                {
                        Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
                        Vector v=new Vector();
                        v.add(pid);
                        v.add(arrival);
                        v.add(start);
                        v.add(burst);
                        v.add(ioTotalDuration);
                        v.add(end);
                        v.add(start-arrival);
                        v.add(end-arrival);
                        v.add(end-arrival-burst-ioTotalDuration-ioWait);
                        v.add(ioWait);
                        v.add(contextSwitches);
                        model.addRow(v);
                }
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
               // JTable table=new JTable(model);
            }
            else if(policy==2)
            {
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","SJF","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"PID","ARRIVAL TIME","START TIME","CPU EXECUTION TIME","IO EXECUTION TIME","EXIT TIME","RESPONSE TIME","TURN AROUND TIME","READY QUEUE WAIT TIME","IO WAIT TIME","CONTEXT SWITCHES"});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                while(iterator.hasNext())
                {
                        Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
                        Vector v=new Vector();
                        v.add(pid);
                        v.add(arrival);
                        v.add(start);
                        v.add(burst);
                        v.add(ioTotalDuration);
                        v.add(end);
                        v.add(start-arrival);
                        v.add(end-arrival);
                        v.add(end-arrival-burst-ioTotalDuration-ioWait);
                        v.add(ioWait);
                        v.add(contextSwitches);
                        model.addRow(v);
                } 
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
            }
            else if(policy==3)
            {
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","SRJF","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"PID","ARRIVAL TIME","START TIME","CPU EXECUTION TIME","IO EXECUTION TIME","EXIT TIME","RESPONSE TIME","TURN AROUND TIME","READY QUEUE WAIT TIME","IO WAIT TIME","CONTEXT SWITCHES"});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                while(iterator.hasNext())
                {
                        Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
                        Vector v=new Vector();
                        v.add(pid);
                        v.add(arrival);
                        v.add(start);
                        v.add(burst);
                        v.add(ioTotalDuration);
                        v.add(end);
                        v.add(start-arrival);
                        v.add(end-arrival);
                        v.add(end-arrival-burst-ioTotalDuration-ioWait);
                        v.add(ioWait);
                        v.add(contextSwitches);
                        model.addRow(v);
                }
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
            }
            else if(policy==4)
            {
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","PRIORITY","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"PID","ARRIVAL TIME","START TIME","CPU EXECUTION TIME","IO EXECUTION TIME","EXIT TIME","RESPONSE TIME","TURN AROUND TIME","READY QUEUE WAIT TIME","IO WAIT TIME","CONTEXT SWITCHES"});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                while(iterator.hasNext())
                {
                        Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
                        Vector v=new Vector();
                        v.add(pid);
                        v.add(arrival);
                        v.add(start);
                        v.add(burst);
                        v.add(ioTotalDuration);
                        v.add(end);
                        v.add(start-arrival);
                        v.add(end-arrival);
                        v.add(end-arrival-burst-ioTotalDuration-ioWait);
                        v.add(ioWait);
                        v.add(contextSwitches);
                        model.addRow(v);
                }
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
            }
            else if(policy==5)
            {
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","ROUND ROBIN","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"PID","ARRIVAL TIME","START TIME","CPU EXECUTION TIME","IO EXECUTION TIME","EXIT TIME","RESPONSE TIME","TURN AROUND TIME","READY QUEUE WAIT TIME","IO WAIT TIME","CONTEXT SWITCHES"});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                while(iterator.hasNext())
                {
                        Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
                        Vector v=new Vector();
                        v.add(pid);
                        v.add(arrival);
                        v.add(start);
                        v.add(burst);
                        v.add(ioTotalDuration);
                        v.add(end);
                        v.add(start-arrival);
                        v.add(end-arrival);
                        v.add(end-arrival-burst-ioTotalDuration-ioWait);
                        v.add(ioWait);
                        v.add(contextSwitches);
                        model.addRow(v);
                }
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
            }
            else if(policy==6)
            {
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","CUSTOM SCHEDULER","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"PID","ARRIVAL TIME","START TIME","CPU EXECUTION TIME","IO EXECUTION TIME","EXIT TIME","RESPONSE TIME","TURN AROUND TIME","READY QUEUE WAIT TIME","IO WAIT TIME","CONTEXT SWITCHES"});
                model.addRow(new Object[]{"","","","","","","","","","",""});
                while(iterator.hasNext())
                {
                        Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
                        Vector v=new Vector();
                        v.add(pid);
                        v.add(arrival);
                        v.add(start);
                        v.add(burst);
                        v.add(ioTotalDuration);
                        v.add(end);
                        v.add(start-arrival);
                        v.add(end-arrival);
                        v.add(end-arrival-burst-ioTotalDuration-ioWait);
                        v.add(ioWait);
                        v.add(contextSwitches);
                        model.addRow(v);
                }
                model.addRow(new Object[]{"","","","","","","","","","",""});
                model.addRow(new Object[]{"","","","","","","","","","",""});
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
        public static void main(String args[]) 
        {
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
            java.util.logging.Logger.getLogger(OutputModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OutputModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OutputModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OutputModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() 
                {
                        new OutputModule().setVisible(true);
                }
                });
        } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
