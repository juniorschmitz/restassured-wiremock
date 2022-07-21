# RestAssured + WireMock

Project created for testing CI with Github Actions with RestAssured tests.

The project is building a mock server using Wiremock for all scenarios, so the only command that needs to be executed in order to run the tests is:
```aidl
mvn clean compile test
```

Or simple:
```aidl
mvn test
```

The project is currently using Github actions for running the tests, if you want to know more about it check out the .github/workflows/actions.yml file.

Enjoy!