package com.boris.simple.jabber;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JabberSender {
    @Value("${jid}")
    private String JID; // your.simple.jabber@jabb3r.org

    @Value("${password}")
    private String password;

    @Value("${host}") // jabb3r.org
    private String host;

    void send(String toJID, String text) {
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword(JID.split("@")[0], password)
                    .setXmppDomain(JID.split("@")[1])
                    .setHost(host)
                    .build();


            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect();
            connection.login();

            ChatManager chatManager = ChatManager.getInstanceFor(connection);
            EntityBareJid jid = JidCreate.entityBareFrom(toJID);
            Chat chat = chatManager.chatWith(jid);
            chat.send(text);

            connection.disconnect();
        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
