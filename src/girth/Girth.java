/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package girth;
 
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Girth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //initialize girth
        int girth = 0;
        
        //input start
        BufferedReader br = null;
        try {
            while (true) {
                br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter a digraph : (To calculate the girth, enter 'c') ");
                String inputN = br.readLine();
                int orderN = Integer.parseInt(inputN);

                //Adjacency list 
                ArrayList<String> aListInput = new ArrayList<String>();

                if(orderN > 0){


                         while (true) {

                            String input = br.readLine();

                            if ("c".equals(input)) {
                                //System.out.println("Output: ");
                                break;
                            }else{
                               aListInput.add(input);
                            }

                        }
                        //get adjacency matric for the diagraph here
                        ArrayList<ArrayList<Integer>> aMatrix = getAdjacencyMatrix(orderN, aListInput);
                        System.out.println( aMatrix.toString() );

                        //Output girth here
                        getGirth(orderN, aMatrix);

                        



                }else{
                        System.out.println("Wrong number for the first line! Please run this application again and enter a diagraph with the first line: eg. 4 ");
                        System.exit(0);
                }
                System.out.println("");
                System.out.println("Do you want to try another digraph? : (if yes, enter 'y'. if no, enter 'n') ");
                String yesOrNo = br.readLine();
                if ("n".equals(yesOrNo)) {
                        //System.out.println("Output: ");
                        break;
                }
                
            }
           

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
   
    }// main function ends here
    
    private static ArrayList<ArrayList<Integer>> getAdjacencyMatrix(int n, ArrayList<String> aList){
        ArrayList<ArrayList<Integer>> adjacency = new ArrayList<ArrayList<Integer>>();
        
        //for the first place, initialize the n by n matrix here
        for(int i = 0; i < n; i++){
            adjacency.add(new ArrayList<Integer>());
            for(int j = 0; j < n; j++){
                 adjacency.get(i).add(0); 
            }
        }// Initialized n * n matrix with all elements to zero
        System.out.println("Adjacency list from the input:");
        System.out.println(aList.toString());
        //Set the matrix here
        for(int i = 0; i < aList.size(); i++ ){
            String eachRow = aList.get(i);
            if(!eachRow.equals("")){
                 //parse string
                String[] parts = eachRow.split(" ");
                for(int j = 0; j < parts.length; j++ ){
                    int eleInt = Integer.parseInt(parts[j]);

                    adjacency.get(i).set(eleInt, 1);
                }
            }
           
        }
        System.out.println( "Adjacency matrix from the input: ");
        return adjacency;
    }
    
    private static void getGirth(int n, ArrayList<ArrayList<Integer>> adjacency ){
        
        ArrayList<ArrayList<Integer>> adjacencyCopy = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> adjacencyRealCopy = new ArrayList<ArrayList<Integer>>();
        
        //prepare copies for the matrix operations
        for(int i = 0; i < n; i++){
            adjacencyCopy.add(new ArrayList<Integer>());
            adjacencyRealCopy.add(new ArrayList<Integer>());
            for(int j = 0; j < n; j++){
                
                 int element = adjacency.get(i).get(j);  
                 adjacencyCopy.get(i).add(element); 
                 adjacencyRealCopy.get(i).add(element); 
            }
        }
        
        //System.out.println("*******Copies***************");
        //System.out.println(adjacencyCopy.toString());
        //System.out.println(adjacencyRealCopy.toString());
        //System.out.println("***************************");
            
       
        for(int i = 2; i < (n+1); i++){
        //for(int i = 0; i < 1; i++){
        
            for(int j = 0; j < (n); j++){ //raw
                 
                 for(int k = 0; k < (n); k++){ //colum number
                    int element = 0;
                    int valueMultiple = 0;
                    for(int m = 0; m < (n); m++){ //k colum m row 
                        // J*M
                       int valueRight = adjacency.get(m).get(k);
                       int valueLeft = adjacencyRealCopy.get(j).get(m);
                      
                       valueMultiple = valueLeft*valueRight;
                       
                       element = element + valueMultiple;
                    }
                  
                    adjacencyCopy.get(j).set(k, element);
                  
                 }
          
            }
           
            //here all elelment sum check
           int sumAll = 0;
           for(int y = 0; y < adjacencyRealCopy.size(); y++ ){
                int sumCol = 0; 
                for(int z = 0; z < adjacencyRealCopy.size(); z++ ){
                    adjacencyRealCopy.get(y).set(z, adjacencyCopy.get(y).get(z));
                    if(y == z) sumCol = sumCol + adjacencyRealCopy.get(y).get(z); 
                }
               sumAll = sumAll + sumCol;
           }
           
           
            if( sumAll > 0 ) {
               System.out.println("Output: ");
               System.out.println("The girth of the graph is: "+ (i) );
               
               break;
           }else{
               if(i == n){
                   System.out.println("Output: ");
                   System.out.println("The girth of the graph is: 0" );
               }
               
           }
        }
    }

}
