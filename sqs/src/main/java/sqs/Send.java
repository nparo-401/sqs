package sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

public class Send {
  private static final String QUEUE_NAME = "QueueA";
  
  public static void send() {
    final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    
    try {
      CreateQueueResult create_result = sqs.createQueue(QUEUE_NAME);
    } catch (AmazonSQSException e) {
      if (!e.getErrorCode().equals("QueueAlreadyExists")) {
        throw e;
      }
    }
    
    String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
    
    SendMessageRequest send_msg_request = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody("hello world").withDelaySeconds(5);
    sqs.sendMessage(send_msg_request);
    
    // Send multiple messages to the queue
    SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest().withQueueUrl(queueUrl).withEntries(new SendMessageBatchRequestEntry("msg_1", "Hello from message 1"), new SendMessageBatchRequestEntry("msg_2", "Hello from message 2").withDelaySeconds(10));
    sqs.sendMessageBatch(send_batch_request);
  }
}
