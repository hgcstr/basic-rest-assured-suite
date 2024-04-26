# Basic Rest Assured Test Suite

This repository contains a basic test suite implemented using Rest Assured for testing RESTful APIs. The tests interact with various endpoints of the [FakeStoreAPI](https://fakestoreapi.com/) and [ReqRes](https://reqres.in/) APIs.

## Test Interactions

The test suite covers interactions for the following HTTP methods:

- **GET**: Retrieve data from the API endpoints.
- **POST**: Create new resources.
- **PUT**: Update existing resources.
- **DELETE**: Delete resources from the API.

## Test Frameworks Used

The tests are implemented using the following frameworks:

- **TestNG**: TestNG is used for structuring and executing tests, providing assertion capabilities and test configuration.
- **BDD/Cucumber**: Additionally, BDD (Behavior-Driven Development) tests are implemented using Cucumber, enhancing test readability and collaboration.

## API Endpoints

The tests interact with the following free API endpoints:

- [FakeStoreAPI](https://fakestoreapi.com/)
- [ReqRes](https://reqres.in/)
  
## Getting Started

To run the tests locally, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have Java and Maven installed.
3. Navigate to the project directory.
4. Run the tests using Maven:

```bash
mvn test
