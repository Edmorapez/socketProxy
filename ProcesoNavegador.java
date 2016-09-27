import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProcesoNavegador {

    private Socket socketNavegador;
    
    ProcesoNavegador(Socket sn)
    {
        socketNavegador = sn;
    }
    public void run(){
        System.out.println("Inicia SocketNavegadortt");
        try {
            BufferedReader entradaNavegador = new BufferedReader (new InputStreamReader(socketNavegador.getInputStream()));
            PrintWriter salCliente = new PrintWriter(new OutputStreamWriter(socketNavegador.getOutputStream(),"8859_1"),true);
            String cadena;
            String get="GET";
            String host="Host";
            int puerto=80;
            boolean bandera=false;
            do          
            {           
                cadena = entradaNavegador.readLine();
                if(cadena != null && cadena.length() != 0){
                    
                    if(cadena.contains(get)){
                        get = cadena;
                    }
                    else if(cadena.contains(host)){
                    cadena=cadena.replace(" ", "");
                    String[] array=cadena.split(":");
                    if(array.length==3){
                        host=array[1];
                        puerto=Integer.parseInt(array[2]);
                    }
                    else{
                        host=array[1];
                    }
                    bandera=true;
                    break;
                    }
                }                   
            }
            while (cadena != null && cadena.length() != 0);
            if(bandera){
                System.out.println("{{"+ get+" }}"+"{{"+ host+" }}"+ "{{"+ puerto+"}}");
               
                String respuesta ="<H1>Hola Mundo</H1>";
                salCliente.println(respuesta);
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            socketNavegador.close();
            System.out.println("Finaliza SocketNavegador");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
      
   
}