import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

//printing an array 
class ArrayPrint{
    ArrayList<String> sr = new ArrayList<>();  
    ArrayPrint(ArrayList<String> sr){
        this.sr  = sr; 
        for(int i=0; i<sr.size(); i+=1){
            System.out.println(sr.get(i));
        }
    }
}

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    //ArrayList<String> str = new ArrayList<>(); 
    //ArrayList<String> con = new ArrayList<>(); 
    //ArrayList<String> sr = new ArrayList<>(); 
   
    //method to create String from ArrayList<String> 
    public String printString(ArrayList<String> sr){
        String r = String.join(", ", sr); 
        return r; 
    }
    ArrayList<String> str = new ArrayList<>();
    ArrayList<String> con = new ArrayList<>();
    
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "String: ";
        }
        else {
            System.out.println("Path: " + url.getPath());
            
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                str.add(parameters[1]);
                return "String " + parameters[1]+ " added! It's now " + printString(str);       
            } 
            else if (url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("="); 
                for(int i=0; i<str.size(); i+=1){
                        if(str.get(i).contains(parameters[1])){
                            con.add(str.get(i)); 
                        }
                    }
                return "String " + parameters[1] + " found! In the following words " + printString(con); 
            }
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
