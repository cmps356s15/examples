package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.TopUp;

/* 
 * ToDo before running this example:
 * Open the commond line then go to c:\glassfish4\bin (or the folder where glassfish is installed) then run the following:
 * asadmin create-jms-resource --restype javax.jms.Queue jms/OoredooQueue
 * asadmin create-jms-resource --restype javax.jms.QueueConnectionFactory jms/OoredooQueueConnectionFactory
 * Note asadmin is located @ ...glassfish\bin  (...your glassfish installation folder)
 */
@WebServlet(urlPatterns = "/mdb")
public class MessageServlet extends HttpServlet {

    @Resource(mappedName = "jms/OoredooQueue")
    private Queue queue;
    @Resource(mappedName = "jms/OoredooQueueConnectionFactory")
    private QueueConnectionFactory queueConnectionFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QueueConnection queueConnection = null;
        try (JMSContext context = queueConnectionFactory.createContext()) {

            //1. Send a text message
            String textMessage = "Ya Ooredoo Mabrouk the new name! Although it is quite boring lol!";
            System.out.println("1. Sent TextMessage to the Queue");
            context.createProducer().send(queue, textMessage);

            //2. Sending ObjectMessage to the Queue
            ObjectMessage objectMessage = context.createObjectMessage();
            TopUp topUp = new TopUp("Hala", "55667788", 50);
            objectMessage.setObject(topUp);
            context.createProducer().send(queue, objectMessage);
            System.out.println("2. Sent TopUp ObjectMessage to the Queue");

        } catch (JMSException ex) {
            Logger.getLogger(MessageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("confirmationMessage", "Mabrouk TextMessage & TopUp ObjectMessage sent to OoredooQueue");
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }
}
