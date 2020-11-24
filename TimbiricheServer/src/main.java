
import blackboard.Blackboard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labcisco
 */
public class main {
    
    public static void main(String[] args){
        Blackboard bb = Blackboard.getInstance();
        bb.empezar();
        System.out.println("Server de timbiriche online.");
    }
}
