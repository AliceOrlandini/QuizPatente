package gestore_xml;

import java.io.*;

public class ParametriConfigurazione implements Serializable { // (1)
    
    public String indirizzoIPClient;
    public String indirizzoIPServerLog;
    public int portaServerLog;
    public String indirizzoIPDatabase;
    public int portaDatabase;
    public String usernameDatabase;
    public String passwordDatabase;
    public String fontCaratteri;
    public String coloreSfondoBoxDomande;
    public int numeroRigheTabella;
    public GestoreXML gestoreXML;
    
    public ParametriConfigurazione() {
        
        gestoreXML = new GestoreXML();
        
        if(gestoreXML.validaXML(0, "parametri_configurazione_quizpatente.xsd", "parametri_configurazione_quizpatente.xml")) {
            ParametriConfigurazione tmp = gestoreXML.estraiXMLParametriConfigurazione();
            
            this.indirizzoIPClient      = tmp.indirizzoIPClient;
            this.indirizzoIPServerLog   = tmp.indirizzoIPServerLog;
            this.portaServerLog         = tmp.portaServerLog;
            this.indirizzoIPDatabase    = tmp.indirizzoIPDatabase;
            this.portaDatabase          = tmp.portaDatabase;
            this.usernameDatabase       = tmp.usernameDatabase;
            this.passwordDatabase       = tmp.passwordDatabase;
            this.fontCaratteri          = tmp.fontCaratteri;
            this.coloreSfondoBoxDomande = tmp.coloreSfondoBoxDomande;
            this.numeroRigheTabella     = tmp.numeroRigheTabella;
        }
    }
}
/*
Note:
(1):  Questa classe si occupa di ricavare i parametri di configurazione dal file
      xml, di validarli tramite il gestore xml e di restituirli all'interfaccia 
      grafica in modo che possa utilizzarli. 
*/