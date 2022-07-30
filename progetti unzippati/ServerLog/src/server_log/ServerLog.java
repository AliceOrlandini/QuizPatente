
package server_log;
import java.io.*;
import java.net.*;
import gestore_xml.GestoreXML; // (1)

public class ServerLog { 

    public static final GestoreXML gestoreXML = new GestoreXML();
    
    public static void main(String[] args) {
        
        try( ServerSocket servs = new ServerSocket(4242,10)) {
            while(true) { // (2)
                Socket s = servs.accept();
                try(DataInputStream din = new DataInputStream(s.getInputStream())) {
                    String stringaXML = din.readUTF();
                    if(gestoreXML.validaXML(1, "messaggio_ server_ log.xsd", stringaXML))
                        gestoreXML.aggiungiStringaAServerLog(stringaXML);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                System.err.println("Azione Utente registrata con successo.");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
/*
Note:
(1):  Viene importata la classe GestoreXML presente nel progetto QuizPatente
      poichè questa classe necessita di gestire dei messaggi in formato xml.
(2):  Il server resta in attesa all'infinito di un nuovo messaggio. Poi, quando 
      lo riceve lo valida e lo salva su file di testo. 
*/