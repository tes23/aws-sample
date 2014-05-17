package info.cd.amazonaws.sample;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import org.junit.Assert;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: tes
 * Date: 5/17/14
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestEC2Instances {

    @org.junit.Test
    public void testStartEC2Instance() throws Exception {
        AWSCredentials credentials = new PropertiesCredentials(TestEC2Instances.class.getResourceAsStream("AwsCredentials.properties"));

        AmazonEC2Client client = new AmazonEC2Client(credentials);
        client.setEndpoint("ec2.eu-west-1.amazonaws.com");

//        RunInstancesRequest request = new RunInstancesRequest("ami-2918e35", 1, 1);
//        RunInstancesResult result = AmazonEC2Client.

    }
}
