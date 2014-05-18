package info.cd.amazonaws.sample;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by User: tes
 * Date: 5/17/14
 * Time: 5:41 PM
 */
public class TestEC2Instances {

    private Properties properties;
    private String instanceId;
    private String imageId;
    private String endpoint;
    private AmazonEC2Client ec2Client;

    @Before
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(TestEC2Instances.class.getResourceAsStream("AwsInstances.properties"));

        instanceId = getProperty("instanceID");
        endpoint = getProperty("endpoint");
        imageId = getProperty("imageID");

        AWSCredentials credentials = new PropertiesCredentials(TestEC2Instances.class.getResourceAsStream("AwsCredentials.properties"));

        ec2Client = new AmazonEC2Client(credentials);
        ec2Client.setEndpoint(endpoint);

    }

    @Ignore
    @Test
    public void testStartEC2Instance() throws Exception {
        StartInstancesRequest request = new StartInstancesRequest().withInstanceIds(instanceId);
        StartInstancesResult result = ec2Client.startInstances(request);

//        RunInstancesRequest request = new RunInstancesRequest("ami-2918e35e", 1, 1);
//        RunInstancesResult result = client.runInstances(request);
        assertNotNull(result);
        List<InstanceStateChange> instances = result.getStartingInstances();
        assertEquals(1, instances.size());



    }

    @Test
    public void testRunnableInstances() throws Exception {
        DescribeInstancesRequest descRequest = new DescribeInstancesRequest();
        descRequest.setInstanceIds(Arrays.asList(instanceId));

        DescribeInstancesResult descResult = ec2Client.describeInstances(descRequest);
        assertNotNull(descResult);
        List<Reservation> reservations = descResult.getReservations();
        assertEquals(1, reservations.size());  //the list can not be null
        List<Instance> instances = reservations.get(0).getInstances();
        assertEquals(1, instances.size());    //the list can not be null
        Instance instance = instances.get(0);
        assertNotNull(instance);
        assertEquals(instanceId, instance.getInstanceId());
        assertEquals(imageId, instance.getImageId());
        assertEquals("running", instance.getState().getName());

    }

    private String getProperty(String key) {
        return properties != null ? properties.getProperty(key) : null;
    }
}
