package gestore_xml;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.converters.basic.*;
import java.io.*;
import java.nio.file.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class GestoreXML { // (1)
    
    public Boolean validaXML(int modalita, String fileXSD, String stringaXML) { // (2)
        
        try {   
            DocumentBuilder db  = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Document d          = null;
            InputStream stream;
            
            if(modalita == 0) {
                d       = db.parse(new File(stringaXML)); 
            } else if(modalita == 1) {
                stream  = new ByteArrayInputStream(stringaXML.getBytes("UTF-8"));
                d       = db.parse(stream);
            } 
            Schema s = sf.newSchema(new StreamSource(new File(fileXSD))); 
            s.newValidator().validate(new DOMSource(d));
          } catch (Exception e) {
            if (e instanceof SAXException) 
              System.out.println("Errore di validazione: " + e.getMessage());
            else
              System.out.println(e.getMessage());   
            return false;
          }
        return true;
    }
    
    public ParametriConfigurazione estraiXMLParametriConfigurazione() {
        
        ParametriConfigurazione parametriConfigurazione;
        try{
            XStream xs = new XStream();
            String configurazioniLette = new String(Files.readAllBytes(Paths.get("parametri_configurazione_quizpatente.xml")));
            xs.alias("ParametriConfigurazione", ParametriConfigurazione.class);
            parametriConfigurazione = (ParametriConfigurazione)xs.fromXML(configurazioniLette); 
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return parametriConfigurazione;
    }
    
    public String convertiInStringa(AzioneUtente messaggioDaConvertire) {
        
        XStream xs = new XStream();
        xs.alias("AzioneUtente", AzioneUtente.class);
        xs.registerConverter(new DateConverter("yyy:MM:dd_HH:mm:ss", null));
        return xs.toXML(messaggioDaConvertire);
    }
    
    public void aggiungiStringaAServerLog(String stringaXML) {
        
        stringaXML = stringaXML + '\n' + '\n';
        try {
            Files.write(Paths.get("server_log.txt"), stringaXML.getBytes(), StandardOpenOption.APPEND);
        } catch(IOException ex) {
            System.err.println("Il file non esiste");
            try {
                Files.write(Paths.get("server_log.txt"), stringaXML.getBytes());
            } catch(IOException e) {
                System.err.println("Impossibile salvare su file");
            }
        }
    }
}
/*
Note:
(1):  Questa classe si occupa della gestione dell'xml di tutto il progetto. 
      Essa viene importata nel progetto ServerLog poichè anch'esso necessita
      di gestire l'xml.
(2):  Questo metodo si comporta in modo diverso a seconda che la richiesta sia
      relativa alla validazione dei parametri di configurazione oppure del
      messaggio di log. 
*/