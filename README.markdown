Performance Test Tool for Grails MessagePack Application
======================

This script is a simple performance test client for Grails MessagePack Plugin Demo Application.

Demo Application
---------------

If you have not installed the MessagePack Grails Demo Application, clone and install it first:

<https://github.com/ohneda/grails-msgpack-demo>
    
Performance Test
---------------

This performance test tool helps to measure and compare the processing time of JSON and MessagePack.

1. READ access
1. CREATE access
1. UPDATE access
1. DELETE access

Usage
---------------

Edit the first line of src/main/groovy/PerformanceTest.groovy to configure the settings to your environment.

    JsonClient client = new JsonClient('http://localhost:8080/grails-msgpack-demo/')
    MessagePackRpcClient rclient = new MessagePackRpcClient("localhost", 1985)

And type the following command:

    gradle runTest
