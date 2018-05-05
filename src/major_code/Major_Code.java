/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package major_code;

/**
 *
 * @author Anji
 */
public class Major_Code 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        OutputModule out = new OutputModule();
           InputModule inputmodule= new InputModule(out);
                    inputmodule.setVisible(true);
    }
    
}
