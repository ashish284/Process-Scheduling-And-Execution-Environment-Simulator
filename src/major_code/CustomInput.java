/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package major_code;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler;

/**
 *
 * @author ashley
 */
public class CustomInput extends javax.swing.JPanel {

    /**
     * Creates new form CustomInput
     */
    public CustomInput() {

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        executionButton = new javax.swing.JButton();
        cpuExecutionButton = new javax.swing.JButton();
        ioExecutionButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        code = new javax.swing.JTextArea();
        currentFile = new javax.swing.JLabel();
        docButton = new javax.swing.JButton();
        apiButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 600));

        executionButton.setText("CustomExecution.java");
        executionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executionButtonActionPerformed(evt);
            }
        });

        cpuExecutionButton.setText("CustomCPUExecution.java");
        cpuExecutionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpuExecutionButtonActionPerformed(evt);
            }
        });

        ioExecutionButton.setText("CustomIOExecution.java");
        ioExecutionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ioExecutionButtonActionPerformed(evt);
            }
        });

        code.setColumns(100);
        code.setRows(50);
        jScrollPane2.setViewportView(code);

        docButton.setText("Documentation");
        docButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docButtonActionPerformed(evt);
            }
        });

        apiButton.setText("API");
        apiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apiButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(currentFile, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(213, 213, 213)
                                                .addComponent(docButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(apiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(ioExecutionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(executionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(cpuExecutionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(currentFile, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(docButton)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(apiButton)
                                                .addGap(72, 72, 72)
                                                .addComponent(executionButton)
                                                .addGap(81, 81, 81)
                                                .addComponent(cpuExecutionButton)
                                                .addGap(72, 72, 72)
                                                .addComponent(ioExecutionButton)
                                                .addContainerGap(189, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(SaveButton)
                                                .addGap(24, 24, 24))))
        );
    }// </editor-fold>                     

    private void apiButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        currentFile.setText("");
        JEditorPane jep = new JEditorPane();
        jep.setEditable(false);
        String workingDirectory = System.getProperty("user.dir");
        String fileName;

        if (System.getProperty("os.name").equals("Linux")) {
            fileName = "file://" + workingDirectory + "/FullDocumentation.html";
        } else {
            fileName = "file:\\" + workingDirectory + "\\FullDocumentation.html";
        }
        try {
            jep.setPage(fileName);
        } catch (Exception e) {
            jep.setContentType("text/html");
            jep.setText(fileName);
//jep.setText("<html>Could not load full documentation page</html>" + fileName);
        }

        JScrollPane scrollPane = new JScrollPane(jep);
        JFrame f = new JFrame("Documentation");
        f.getContentPane().add(scrollPane);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    private void docButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JEditorPane jep = new JEditorPane();
        jep.setEditable(false);
        String workingDirectory = System.getProperty("user.dir");
        String fileName = currentFile.getText();
        if (fileName == "") {
            if (System.getProperty("os.name").equals("Linux")) {
                fileName = "file://" + workingDirectory + "/ErrorPage.html";
            } else {
                fileName = "file:\\" + workingDirectory + "\\ErrorPage.html";
            }
            try {
                jep.setPage(fileName);
            } catch (Exception e) {
                jep.setContentType("text/html");
                jep.setText("<html>Could not load</html>" + fileName);
            }
        } else {
            int length = fileName.length();

            fileName = fileName.substring(0, (length - 5));
            if (System.getProperty("os.name").equals("Linux")) {
                fileName = "file://" + workingDirectory + "/" + fileName + "Document.html";
            } else {
                fileName = "file:\\" + workingDirectory + "\\" + fileName + "Document.html";
            }

            try {

                jep.setPage(fileName);
            } catch (Exception e) {
                jep.setContentType("text/html");
                jep.setText("<html>Could not load</html>" + fileName);
            }
        }

        JScrollPane scrollPane = new JScrollPane(jep);
        JFrame f = new JFrame(currentFile.getText() + " Document");
        f.getContentPane().add(scrollPane);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    private void executionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executionButtonActionPerformed
        // TODO add your handling code here:
        code.setText("");
        currentFile.setVisible(true);
        currentFile.setText("Execution.java");
        ioExecutionButton.setFocusPainted(false);
        cpuExecutionButton.setFocusPainted(false);
        executionButton.setFocusPainted(true);
        String workingDirectory = System.getProperty("user.dir");
        String fileName = currentFile.getText();
        String codeFileName = "Custom" + currentFile.getText();

        int length = fileName.length();
        fileName = fileName.substring(0, (length - 5));

        if (System.getProperty("os.name").equals("Linux")) {
            fileName = workingDirectory + "/" + fileName;
            codeFileName = workingDirectory + "/src/major_code/" + codeFileName;

        } else {
            fileName = workingDirectory + "\\" + fileName;
            codeFileName = workingDirectory + "\\src\\major_code\\" + codeFileName;

        }

        try {
            FileInputStream fin = new FileInputStream(fileName);
            BufferedInputStream bin = new BufferedInputStream(fin);
            FileOutputStream fout = new FileOutputStream(codeFileName);
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            int i;
            File file = new File(fileName);
            char b[] = new char[(int) file.length()];
            int k = 0;
            while ((i = bin.read()) != -1) {
                b[k++] = (char) i;
            }
            String algo = new String(b);
            bout.write(algo.getBytes());
            bout.flush();
            bout.close();
            fout.close();
            bin.close();
            fin.close();
            String s = new String(b);
            code.setText(s);
        } catch (Exception e) {
            System.out.println("Error in exection.java");
        }
    }//GEN-LAST:event_executionButtonActionPerformed

    private void cpuExecutionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuExecutionButtonActionPerformed
        // TODO add your handling code here:
        code.setText("");
        currentFile.setVisible(true);
        currentFile.setText("CPUExecution.java");
        ioExecutionButton.setFocusPainted(false);
        executionButton.setFocusPainted(false);
        cpuExecutionButton.setFocusPainted(true);
        String workingDirectory = System.getProperty("user.dir");
        String fileName = currentFile.getText();
        String codeFileName = "Custom" + currentFile.getText();
        int length = fileName.length();
        fileName = fileName.substring(0, (length - 5));

        if (System.getProperty("os.name").equals("Linux")) {
            fileName = workingDirectory + "/" + fileName;
            codeFileName = workingDirectory + "/src/major_code/" + codeFileName;
        } else {
            fileName = workingDirectory + "\\" + fileName;
            codeFileName = workingDirectory + "\\src\\major_code\\" + codeFileName;
        }

        try {
            FileInputStream fin = new FileInputStream(fileName);
            BufferedInputStream bin = new BufferedInputStream(fin);
            FileOutputStream fout = new FileOutputStream(codeFileName);
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            int i;
            File file = new File(fileName);
            char b[] = new char[(int) file.length()];
            int k = 0;
            while ((i = bin.read()) != -1) {
                b[k++] = (char) i;
            }
            String algo = new String(b);
//            System.out.println(algo);
            bout.write(algo.getBytes());
            bout.flush();
            bout.close();
            fout.close();
            bin.close();
            fin.close();
            String s = new String(b);
            code.setText(s);
        } catch (Exception e) {
            System.out.println("Error in exection.java");
        }
    }//GEN-LAST:event_cpuExecutionButtonActionPerformed

    private void ioExecutionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ioExecutionButtonActionPerformed
        // TODO add your handling code here:
        code.setText("");
        currentFile.setVisible(true);
        currentFile.setText("IOExecution.java");

        executionButton.setFocusPainted(false);
        cpuExecutionButton.setFocusPainted(false);
        ioExecutionButton.setFocusPainted(true);
        String workingDirectory = System.getProperty("user.dir");
        String fileName = currentFile.getText();
        String codeFileName = "Custom" + currentFile.getText();

        int length = fileName.length();
        fileName = fileName.substring(0, (length - 5));

        if (System.getProperty("os.name").equals("Linux")) {
            fileName = workingDirectory + "/" + fileName;
            codeFileName = workingDirectory + "/src/major_code/" + codeFileName;

        } else {
            fileName = workingDirectory + "\\" + fileName;
            codeFileName = workingDirectory + "\\src\\major_code\\" + codeFileName;

        }

        try {
            FileInputStream fin = new FileInputStream(fileName);
            BufferedInputStream bin = new BufferedInputStream(fin);
            FileOutputStream fout = new FileOutputStream(codeFileName);
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            int i;
            File file = new File(fileName);
            char b[] = new char[(int) file.length()];
            int k = 0;
            while ((i = bin.read()) != -1) {
                b[k++] = (char) i;
            }
            String algo = new String(b);
            bout.write(algo.getBytes());
            bout.flush();
            bout.close();
            fout.close();
            bin.close();
            fin.close();
            String s = new String(b);
            code.setText(s);
        } catch (Exception e) {
            System.out.println("Error in exection.java");
        }
    }//GEN-LAST:event_ioExecutionButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JFrame frame = new JFrame();

        if (currentFile.getText() == "") {
            JOptionPane.showMessageDialog(frame, "Select one java file to be entered!");
            code.setText("");

        } else {
            String workingDirectory = System.getProperty("user.dir");
            String fileName;
            String errFileName;
            if (System.getProperty("os.name").equals("Linux")) {
                fileName = workingDirectory + "/src/major_code/Custom" + currentFile.getText();
                errFileName = workingDirectory + "/compilationError";
            } else {
                fileName = workingDirectory + "\\src\\major_code\\Custom" + currentFile.getText();
                errFileName = workingDirectory + "\\compilationError";
            }

            try {
                FileOutputStream fout = new FileOutputStream(fileName);
                BufferedOutputStream bout = new BufferedOutputStream(fout);

                if (code.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "No code entered!");
                } else {
                    byte sourceCode[] = code.getText().getBytes();
                    bout.write(sourceCode);
                    bout.flush();
                    bout.close();
                    fout.close();

                    code.setText("");
                    //int errorCode = com.sun.tools.javac.Main.compile(new String[] {"-classpath", "bin","-d", "/temp/dynacode_classes","dynacode/sample/PostmanImpl.java" });
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    FileOutputStream err = new FileOutputStream(errFileName);
                    FileInputStream fin1 = new FileInputStream(errFileName);
                    BufferedInputStream bin1 = new BufferedInputStream(fin1);
                    int result = compiler.run(null, System.out, err, fileName);
                    err.flush();
                    err.close();
                    if (result == 0) {
                        JOptionPane.showMessageDialog(frame, "Custom" + currentFile.getText() + " saved without any errors.");

                    } else {

                        int i;
                        File file = new File(errFileName);
                        char b[] = new char[(int) file.length()];
                        int k = 0;
                        while ((i = bin1.read()) != -1) {
                            b[k++] = (char) i;
                        }
                        String compilationError = new String(b);
                        System.out.println(compilationError);
                        bin1.close();
                        fin1.close();
                        JOptionPane.showMessageDialog(frame, "Custom" + currentFile.getText() + " has errors!!\n\nERROR::\n" +compilationError);

                    }
//                    CustomExecution.execExecution();

                }
            } catch (Exception e) {
                System.out.println("Error in custom input: save " + e);
            }

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apiButton;
    private javax.swing.JTextArea code;
    private javax.swing.JButton cpuExecutionButton;
    private javax.swing.JLabel currentFile;
    private javax.swing.JButton docButton;
    private javax.swing.JButton executionButton;
    private javax.swing.JButton ioExecutionButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton SaveButton;

    // End of variables declaration//GEN-END:variables
}
