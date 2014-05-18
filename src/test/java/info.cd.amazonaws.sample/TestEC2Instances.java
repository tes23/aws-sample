package info.cd.amazonaws.sample;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.junit.Before;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by User: tes
 * Date: 5/17/14
 * Time: 5:41 PM
 */
public class TestEC2Instances {

    private String instanceId;
    private String endpoint;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(TestEC2Instances.class.getResourceAsStream("AwsInstances.properties"));

        instanceId = properties.getProperty("instanceID");
        endpoint = properties.getProperty("endpoint");

    }

    @org.junit.Test
    public void testStartEC2Instance() throws Exception {
        AWSCredentials credentials = new PropertiesCredentials(TestEC2Instances.class.getResourceAsStream("AwsCredentials.properties"));

        AmazonEC2Client client = new AmazonEC2Client(credentials);
        client.setEndpoint(endpoint);

        StartInstancesRequest request = new StartInstancesRequest().withInstanceIds(instanceId);
        StartInstancesResult result = client.startInstances(request);

//        RunInstancesRequest request = new RunInstancesRequest("ami-2918e35e", 1, 1);
//        RunInstancesResult result = client.runInstances(request);
        assertNotNull(result);
        List<InstanceStateChange> instances = result.getStartingInstances();
        assertEquals(1, instances.size());

//        DescribeInstancesRequest descRequest = new DescribeInstancesRequest();
//        descRequest.setInstanceIds(Arrays.asList(instanceId));
//
//        DescribeInstancesResult descResult = client.describeInstances(descRequest);
//        assertNotNull(descResult);

    }
}
