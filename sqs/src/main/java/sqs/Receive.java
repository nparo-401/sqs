package sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Receive {
//  private static final String QUEUE_NAME = "QueueA";
  
  public static void receive(String queueName) {
    final String QUEUE_NAME = queueName;
    final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
    
    List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
    
    for (Message m : messages) {
      System.out.println(m);
      sqs.deleteMessage(queueUrl, m.getReceiptHandle());
    }
  }
}
