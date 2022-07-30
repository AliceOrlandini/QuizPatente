package gestore_xml;

import java.util.*;

public class AzioneUtente { // (1)
    
    public String nomeApplicazione;
    public String indirizzoIPClient;
    public Date timestamp;
    public String azione;
    
    public AzioneUtente(String nomeApplicazione, String indirizzoIPClient, Date timestamp, String azione) {
        
        this.nomeApplicazione   = nomeApplicazione;
        this.indirizzoIPClient  = indirizzoIPClient;
        this.timestamp          = timestamp;
        this.azione             = azione;
    }
}

/*
Note:
(1):  Questa classe viene utilizzata per creare un nuovo messaggio di log da 
      inviare al server. Il messaggio quindi conterrà il nome dell'applicazione,
      l'indirizzo IP del client, data e ora del momento in cui il messaggio
      viene creato ed una stringa che riassume quale azione è stata compiuta
      dall'utente. 
*/