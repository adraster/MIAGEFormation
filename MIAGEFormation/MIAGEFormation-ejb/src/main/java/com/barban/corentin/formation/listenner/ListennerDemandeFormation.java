/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.listenner;

import DTO.DemandeFormationDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import com.barban.corentin.formation.sender.SenderDemandeRessourceDisponiblesJMS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.barban.corentin.formation.business.GestionFormationLocal;
import com.barban.corentin.formation.entities.Formationcompose;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Corentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_FORMATION_DEMANDEE")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ListennerDemandeFormation implements MessageListener {

    @EJB
    private GestionFormationLocal gestionFormation;

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_FORMATION_DEMANDEE";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public ListennerDemandeFormation() {
        this.setupMessageQueueConsumer();
    }

    private void setupMessageQueueConsumer() {
        try {
            this.context = new InitialContext();
            this.factory = (ConnectionFactory) context.lookup(factoryName);
            this.dest = (Destination) context.lookup(destName);
            this.connection = factory.createConnection();
            this.connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } catch (NamingException ex) {
            Logger.getLogger(ListennerDemandeFormation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListennerDemandeFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage response = this.session.createTextMessage();
            ObjectMessage text = (ObjectMessage) message;
            if (text.getObject() instanceof DemandeFormationDTO) {
                DemandeFormationDTO df = (DemandeFormationDTO) text.getObject();
                // Creation de la demande 
                Stockagedemandeformation sf = this.gestionFormation.stockerDemandeFormation(df.getCodeFormation(), df.getIntitule(), df.getCodeClient(), df.getNbPersonnes(), df.getNomClient());
                //Lister les formations en statut pressentie avec le meme code de formation
                int nbParticipantsDemandee = df.getNbPersonnes();
                int capaciteMaxFormation = df.getCapaciteMax();

                System.out.println("capacite" + df.getCapaciteMax());
                HashMap<Formation, Integer> formationNonRemplies = this.gestionFormation.listerFormationNonRemplie(df.getCodeFormation(), df.getCapaciteMax());
                System.out.println("formation non remplies : " + formationNonRemplies.toString());
                
                for (Map.Entry<Formation, Integer> formationNr : formationNonRemplies.entrySet()) {
                    Formation formation = formationNr.getKey();
                    Integer nbParticipantEnCours = formationNr.getValue();
                    if (nbParticipantEnCours < capaciteMaxFormation) {
                        int personnePouvantEtreAjoutees = capaciteMaxFormation - nbParticipantEnCours;
                        this.gestionFormation.ajouterFormationCompose(formation, sf, personnePouvantEtreAjoutees);
                        nbParticipantsDemandee = nbParticipantsDemandee - personnePouvantEtreAjoutees;
                    }
                }
 
                if(nbParticipantsDemandee > 0){
                    Formation f = this.gestionFormation.ajouterFormation(sf, nbParticipantsDemandee);
                    SenderDemandeRessourceDisponiblesJMS sender = new SenderDemandeRessourceDisponiblesJMS(f, sf, df);
                    sender.sendMessageDemandeRessource(df.getListFormateursPressentis(), df.getListSallesPressenties());
                }
                response.setText("Received DEMANDE FORMATION: " + df.toString());
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                this.replyProducer.send(message.getJMSReplyTo(), response);

            }
        } catch (JMSException e) {
            Logger.getLogger(ListennerDemandeFormation.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}