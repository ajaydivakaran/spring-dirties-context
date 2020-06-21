### Open questions
1. To understand why mocking of a bean dependency for the JMS Consumer test requires the earlier
test classes to have a DirtiesContext annotation when the entire test suite is run. 
2. Mocking of bean dependency for RestControllers seem to work differently than a JMS Listener i.e don't 
need DirtiesContext on the ealier test classes.

