# Demo for bulk data load for unit test

## Install
1. Clone the repo
2. Run `gradle build`
3. Run test `gradle test`

## The magic receipe
Spring Boot can work together with DBUnit and JUnit, so what is done within the repo is to prepare a XML file and store in `src/tests/resources/**.xml`, and this contains the individual entities that needed to be sync up to the db in runtime (for this I use H2). Once it is ready, it is just a matter of hooking up the data to the test case using `@DatabaseSetup("/<resource path>")` to trigger the loading before the test start kicking off.

### IMPORTANT
Since DBUnit is not that smart, make sure your entity data is clean and does not violate any integrity related problems, otherwise the test will naturally fail.